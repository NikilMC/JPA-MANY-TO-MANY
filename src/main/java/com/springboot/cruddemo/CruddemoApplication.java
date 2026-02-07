package com.springboot.cruddemo;

import com.springboot.cruddemo.dao.AppDAO;
import com.springboot.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
            deleteStudent(appDAO);
        };
    }

    private void findInstructorWithCourses(AppDAO appDAO) {
        int id = 1;
        Instructor tempInstructor = appDAO.findInstructorById(id);
        System.out.println(tempInstructor.getCourses());
        System.out.println("Done" );
    }

    private void createInstructor(AppDAO appDAO) {
        Instructor tempInstructor = new Instructor("Likin", "Vagaar", "sslikinvagaar1021@gmail.com" );
        InstructorDetail tempInstructorDetail = new InstructorDetail("http://youtube.com/likin", "gnidoc" );
        tempInstructor.setInstructorDetail(tempInstructorDetail);
        System.out.println("Saving Instructor : " + tempInstructor);
        appDAO.save(tempInstructor);
        System.out.println("Done" );
    }

    private void findInstructor(AppDAO appDAO) {
        int id = 1;
        System.out.println("Finding the Instructor id : " + id);
        Instructor tempInstructor = appDAO.findInstructorById(id);
        System.out.println("tempInstructor : " + tempInstructor);
        System.out.println("The Associated Instructor Details : " + tempInstructor.getInstructorDetail());
    }

    private void deleteInstructor(AppDAO appDAO) {
        int id = 1;
        Instructor tempInstructor = appDAO.findInstructorById(id);
        System.out.println("Deleting Instructor" + tempInstructor);
        System.out.println("Deleting Instructor Details" + tempInstructor.getInstructorDetail());
        appDAO.deleteInstructorById(1);
        System.out.println("Done" );
    }

    private void findInstructorDetail(AppDAO appDAO) {
        int id = 2;
        InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);
        System.out.println("Instructor Detail : " + instructorDetail);
        System.out.println("Instructor : " + instructorDetail.getInstructor());
        System.out.println("Done" );
    }

    private void deleteInstructorDetail(AppDAO appDAO) {
        int id = 2;
        InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);
        System.out.println("Deleting Instructor Detail : " + instructorDetail);
        System.out.println("Deleting Instructor : " + instructorDetail.getInstructor());
        appDAO.deleteInstructorDetailById(id);
        System.out.println("Done" );
    }

    public void createInstructorWithCourses(AppDAO appDAO) {
        Instructor tempInstructor = new Instructor("Likin", "Vagaar", "sslikinvagaar1021@gmail.com" );
        InstructorDetail tempInstructorDetail = new InstructorDetail("http://youtube.com/likin", "gnidoc" );
        tempInstructor.setInstructorDetail(tempInstructorDetail);
        Course course1 = new Course("Guitar" );
        Course course2 = new Course("Keyboard" );
        Course course3 = new Course("Flute" );
        tempInstructor.add(course1);
        tempInstructor.add(course2);
        tempInstructor.add(course3);
        appDAO.save(tempInstructor);
    }

    public void findCourseForInstructor(AppDAO appDAO) {
        int id = 1;
        System.out.println("Finding Instructor ID : " + id);
        Instructor tempInstructor = appDAO.findInstructorById(id);
        System.out.println("tempInstructor : " + tempInstructor);
        System.out.println("Finding Courses for Instructor ID : " + id);
        List<Course> courses = appDAO.findCoursesByInstructorId(id);
        tempInstructor.setCourses(courses);
        System.out.println("Associated Courses : " + tempInstructor.getCourses());
    }

    public void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
        int id=1;
        System.out.println("Finding Instructor ID : "+id);
        Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(id);
        System.out.println("tempInstructor : "+tempInstructor);
        System.out.println("Associated Courses : "+tempInstructor.getCourses());
        System.out.println("Done");
    }

    public void updateInstructor(AppDAO appDAO){
        int id=1;
        Instructor tempInstructor = appDAO.findInstructorById(id);
        tempInstructor.setLastName("TESTER");
        appDAO.update(tempInstructor);
        System.out.println("Done");
    }

    public void deleteCourse(AppDAO appDAO){
        int id=1;
        appDAO.deleteCourseById(id);
        System.out.println("Done");
    }

    public void createCourseAndReviews(AppDAO appDAO){
        Course course = new Course("Black Myth Wukong");
        course.addReview(new Review("Great Game!!"));
        course.addReview(new Review("Goated Game!!"));
        course.addReview(new Review("Good Game!!"));
        appDAO.save(course);
        System.out.println("Done");
    }

    public void retrieveCourseAndReviews(AppDAO appDAO){
        int id=10;
        Course tempCourse = appDAO.findCourseAndReviewsByCourseId(id);
        System.out.println(tempCourse);
        System.out.println(tempCourse.getReviews());
        System.out.println("Done");
    }

    public void createCourseAndStudents(AppDAO appDAO){
        Course course = new Course("Pacman");
        Student student1 = new Student("John","johndoe@gmail.com","Doe");
        Student student2 = new Student("Ben","bendover@gmail.com","Dover");
        Student student3 = new Student("Master","masterbaitor@gmail.com","Baitor");
        course.addStudent(student1);
        course.addStudent(student2);
        course.addStudent(student3);
        appDAO.save(course);
        System.out.println("Done");
    }

    public void findCourseAndStudents(AppDAO appDAO) {
        int id=10;
        Course tempCourse = appDAO.findCourseAndStudentsbyCourseId(id);
        System.out.println("Course : " + tempCourse);
        System.out.println("Students : "+tempCourse.getStudents() );
        System.out.println("Done");
    }

    public void findStudentAndCourses(AppDAO appDAO){
        int id=2;
        Student tempStudent = appDAO.findStudentAndCourseByStudentId(id);
        System.out.println("Student : "+tempStudent);
        System.out.println("Courses : "+tempStudent.getCourses());
        System.out.println("Done");
    }

    public void addMoreCoursesForStudent(AppDAO appDAO){
        int id=1;
        Student tempStudent = appDAO.findStudentAndCourseByStudentId(id);
        Course tempCourse1 = new Course("Rubik's Cube - How To Speed Cube");
        Course tempCourse2 = new Course("Game Development");
        tempStudent.addCourse(tempCourse1);
        tempStudent.addCourse(tempCourse2);
        appDAO.update(tempStudent);
        System.out.println("Done");
    }

    public void deleteStudent(AppDAO appDAO){
        int id=1;
        appDAO.deleteStudentById(id);
        System.out.println("Done");
    }
}