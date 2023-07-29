package com.example.hbonetomanybidemo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.hbonetomanybidemo.entity.Course;
import com.example.hbonetomanybidemo.entity.Instructor;
import com.example.hbonetomanybidemo.entity.InstructorDetail;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class InstructorDAOImpl implements InstructorDAO {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	@Transactional
	public void save(Instructor instructor) {
		entityManager.persist(instructor);
	}

	@Override
	public Instructor findInstructorById(int id) {
		Instructor instructor = entityManager.find(Instructor.class, id);
		return instructor;
	}

	@Override
	public InstructorDetail findInstructorDetailById(int id) {
		InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);
		return instructorDetail;
	}

	@Override
	@Transactional
	public void deleteInstructorDetailById(int id) {
		InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);
		instructorDetail.setInstructor(null);
		entityManager.remove(instructorDetail);
	}

	@Override
	public List<Course> findCoursesInLazyInstructor(int id) {
		TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
		query.setParameter("data", id);
		List<Course> courses = query.getResultList();
		return courses;
	}

	@Override
	public Instructor findInstructorWithCoursesByIdJoinFetch(int id) {
		TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i JOIN FETCH i.courses where i.id = :data", Instructor.class);
		query.setParameter("data", id);
		Instructor instructor = query.getSingleResult();
		return instructor;
	}

	@Override
	@Transactional
	public void updateInstructor(Instructor instructor1) {
		entityManager.merge(instructor1);
		
	}

	@Override
	public Course findCourseById(int id) {
		Course course = entityManager.find(Course.class, id);
		return course;
	}

	@Override
	@Transactional
	public void updateCourse(Course course1) {
		entityManager.merge(course1);
	}
	
	@Override
	@Transactional
	public void deleteInstructorById(int id) {
		Instructor instructor = entityManager.find(Instructor.class, id);
		List<Course> courses = instructor.getCourses();
		for (Course course : courses) {
			course.setInstructor(null);
		}
		entityManager.remove(instructor);
	}

	@Override
	@Transactional
	public void deleteCourseById(int id) {
		Course course = entityManager.find(Course.class, id);
		entityManager.remove(course);
		
	}
}
