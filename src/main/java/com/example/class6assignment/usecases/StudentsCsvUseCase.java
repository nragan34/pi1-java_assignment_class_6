package com.example.class6assignment.usecases;

import com.example.class6assignment.models.Assignment;
import com.example.class6assignment.models.Course;
import com.example.class6assignment.models.CourseCsv;
import com.example.class6assignment.models.Student;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.example.class6assignment.ThymeleafConfig.CSV_TEMPLATE_ENGINE;


@Component
public class StudentsCsvUseCase {

  private final TemplateEngine templateEngine;

  @Autowired
  public StudentsCsvUseCase(@Qualifier(CSV_TEMPLATE_ENGINE) TemplateEngine templateEngine) {
    this.templateEngine = templateEngine;

  }

  public List<Student> getStudentData() {

    List<Student> students = new ArrayList<>();

    ////////////////////////////////////////
    //// Student 1 Assignments
    //////////////////////////////////////

    Assignment kentSmothermanCalculusAssignment1 = new Assignment("Area under the curve",
        new Date(2021, 8, 2), 4);

    Assignment kentSmothermanCalculusAssignment2 = new Assignment("Acceleration",
        new Date(121, 8, 4), 3.9);

    Assignment kentSmothermanCalculusAssignment3 = new Assignment("Dual-axis Equations",
        new Date(121, 8, 6), 3.6);

    Assignment kentSmothermanAvancedJavaAssignment1 = new Assignment("Algorithms",
        new Date(121, 8, 3), 3.2);

    Assignment kentSmothermanAvancedJavaAssignment2 = new Assignment("Algorithms",
        new Date(121, 8, 5), 3.7);

    // Calculus Course
    Course kentSmothermanCalculus = new Course("Calculus", 0,
        List.of(kentSmothermanCalculusAssignment1, kentSmothermanCalculusAssignment2,
            kentSmothermanCalculusAssignment3));

    // Advanced Java Course
    Course kentSmothermanAdvancedJava = new Course("Advanced Java", 0,
        List.of(kentSmothermanAvancedJavaAssignment1, kentSmothermanAvancedJavaAssignment2));

    // Add Courses to Student 1
    Student kentSmotherman = new Student("Kent", "Smotherman",
        List.of(kentSmothermanCalculus, kentSmothermanAdvancedJava));

    ////////////////////////////////////////
    //// Student 2 Assignments
    //////////////////////////////////////

    Assignment norvilleRogersCalculusAssignment1 = new Assignment("Area under the curve",
        new Date(121, 8, 2), 2.8);

    Assignment norvilleRogersCalculusAssignment2 = new Assignment("Acceleration",
        new Date(121, 8, 4), 3.6);

    Assignment norvilleRogersCalculusAssignment3 = new Assignment("Dual-axis Equations",
        new Date(121, 8, 6), 3.2);

    Assignment norvilleRogersJava101Assignment1 = new Assignment("Syntax", new Date(121, 8, 3),
        3.5);

    Assignment norvilleRogersJava101Assignment2 = new Assignment("Control Structures",
        new Date(121, 8, 5), 3.1);

    // Calculus Course
    Course norvilleRogersCalculusCourse = new Course("Calculus", 0,
        List.of(norvilleRogersCalculusAssignment1,
            norvilleRogersCalculusAssignment2, norvilleRogersCalculusAssignment3));

    // Java101 Course
    Course norvilleRogersJava101Course = new Course("Java101", 0,
        List.of(norvilleRogersJava101Assignment1, norvilleRogersJava101Assignment2));

    // Add Courses to Student 2
    Student norvilleRogers = new Student("Norville", "Rogers",
        List.of(norvilleRogersCalculusCourse, norvilleRogersJava101Course));

    ////////////////////////////////////////
    //// Add Students to students list
    //////////////////////////////////////

    students.add(kentSmotherman);
    students.add(norvilleRogers);

    return students;
  }


  // grades are 1-4 average the grades between 1-4. 4 being the best grade.
  public List<Student> setGradeAverage(List<Student> studentList) {

//    // create student/courses/assignments
//    getStudentData();
//
//    // Student / Course
//    HashMap<Student, List<Course>> studentCourseMap = new HashMap<>();
//    List<Course> courses = new ArrayList<>();
//
//    // Course / Assignment
//    HashMap<Course,List<Assignment>> courseAssignmentMap = new HashMap<>();
//    List<Assignment> assignments = new ArrayList<>();

    for (Student student : studentList) {

      // get student
      student.getCourses();

      for (Course course : student.getCourses()) {

        // get average
        course.getAverageGrade();

        double totalOfStudentGrade = 0;

        for (Assignment assignment : course.getAssignments()) {

          System.out.println("grade.... ");
          System.out.println(student.getFirstName() + " " + assignment.getGrade());
          totalOfStudentGrade += assignment.getGrade();

        }
        double averageGrade = ((totalOfStudentGrade / course.getAssignments().size()));
        course.setAverageGrade(averageGrade);
      }
    }

//    // for each assignment add 4 to the total
//    double totalPointValue = 0;
//    double totalOfStudentGrade = 0;
//
//    double averageGrade = (100 * (totalOfStudentGrade / totalPointValue));

    return studentList;

  }


  public String setAverage(Double grades) {

    return null;
  }

  // Course name, student last name, student first name, average grade
  public List<CourseCsv> createCourseList(List<Student> students) {

    List<CourseCsv> courseCentricList = new ArrayList<>();

    for (Student student:students) {

      for (Course course: student.getCourses() ) {

        CourseCsv courseCsv = new CourseCsv();

        String courseName = course.getName();
        String lastName = student.getLastName();
        String firstName = student.getFirstName();
        double courseAverage = course.getAverageGrade();

        courseCsv.setCourseName(courseName);
        courseCsv.setLastName(lastName);
        courseCsv.setFirstName(firstName);
        courseCsv.setAverageGrade(courseAverage);

        courseCentricList.add(courseCsv);
      }
    }
    return courseCentricList ;
  }

  //   student last name, student first name, course name, overall course average grade,
// course average grade as of this assignment (in date order), assignment name,
//// assignment date, assignment grade
  public String getStudentsAsCsv() {
    List<Student> student = getStudentData();
    List<Student> studentCompleteList = setGradeAverage(student);
    StringWriter writer = new StringWriter();
    Context context = new Context();
    context.setVariable("students", studentCompleteList);
    templateEngine.process("students", context, writer);
    return writer.toString();
  }


  //  // Course name, student last name, student first name, average grade
  public String getCoursesAsCsv() {
    List<Student> student = getStudentData();
    List<Student> studentCompleteList = setGradeAverage(student);
    List<CourseCsv> courseCentricList = createCourseList(studentCompleteList);
    List<CourseCsv> finalCourseList = courseCentricList
        .stream()
        .sorted(Comparator.comparing(CourseCsv::getCourseName))
        .collect(Collectors.toList());

    StringWriter writer = new StringWriter();
    Context context = new Context();
    context.setVariable("courses", finalCourseList);
    templateEngine.process("courses", context, writer);
    return writer.toString();
  }
}
