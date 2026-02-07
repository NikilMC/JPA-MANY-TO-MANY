package com.springboot.cruddemo.dao;

import com.springboot.cruddemo.entity.Course;
import com.springboot.cruddemo.entity.Instructor;
import com.springboot.cruddemo.entity.InstructorDetail;
import com.springboot.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor){
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int id){
        return entityManager.find(Instructor.class,id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id){
        Instructor theInstructor = entityManager.find(Instructor.class,id);
        List<Course> courses = theInstructor.getCourses();
        for(Course c:courses){
            c.setInstructor(null);
        }
        entityManager.remove(theInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id){
        return entityManager.find(InstructorDetail.class,id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id){
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class,id);
        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id){
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data",Course.class);
        query.setParameter("data",id);
        return query.getResultList();
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id){
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i  "+"JOIN FETCH i.courses  "
                                               + "where i.id = :data", Instructor.class);
        query.setParameter("data",id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor){
        entityManager.merge(tempInstructor);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id){
        Course tempCourse = entityManager.find(Course.class,id);
        entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void save(Course course){
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id){
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                +"JOIN FETCH c.reviews "
                +"where c.id = :data",Course.class);
        query.setParameter("data",id);
        return query.getSingleResult();
    }

    @Override
    public Student findStudentAndCourseByStudentId(int id){
        TypedQuery<Student> query = entityManager.createQuery(
                "select s from Student s "
                        +"JOIN FETCH s.courses "
                        +"where s.id = :data",Student.class);
        query.setParameter("data",id);
        return query.getSingleResult();
    }

    @Override
    public Course findCourseAndStudentsbyCourseId(int id){
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        +"JOIN FETCH c.students "
                        +"where c.id = :data",Course.class);
        query.setParameter("data",id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(int id){
        Student tempStudent = entityManager.find(Student.class,id);
        if(tempStudent!=null){
            List<Course> courses = tempStudent.getCourses();
            for(Course tempCourse : courses){
                tempCourse.getStudents().remove(tempStudent);
            }
            entityManager.remove(tempStudent);
        }
    }
}
