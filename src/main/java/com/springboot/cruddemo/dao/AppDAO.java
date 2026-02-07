package com.springboot.cruddemo.dao;

import com.springboot.cruddemo.entity.Course;
import com.springboot.cruddemo.entity.Instructor;
import com.springboot.cruddemo.entity.InstructorDetail;
import com.springboot.cruddemo.entity.Student;

import java.util.List;

public interface AppDAO {
    void save(Instructor theInstructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);
    List<Course> findCoursesByInstructorId(int id);
    Instructor findInstructorByIdJoinFetch(int id);
    void update(Instructor instructor);
    void deleteCourseById(int id);
    void save(Course course);
    Course findCourseAndReviewsByCourseId(int id);
    Course findCourseAndStudentsbyCourseId(int id);
    Student findStudentAndCourseByStudentId(int id);
    void update(Student student);
    void deleteStudentById(int id);
}
