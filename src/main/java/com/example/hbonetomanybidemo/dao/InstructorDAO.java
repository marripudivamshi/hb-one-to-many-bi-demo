package com.example.hbonetomanybidemo.dao;

import java.util.List;

import com.example.hbonetomanybidemo.entity.Course;
import com.example.hbonetomanybidemo.entity.Instructor;
import com.example.hbonetomanybidemo.entity.InstructorDetail;

public interface InstructorDAO {
	void save(Instructor instructor);
	
	Instructor findInstructorById(int id);
	
	void deleteInstructorById(int id);

	InstructorDetail findInstructorDetailById(int id);

	void deleteInstructorDetailById(int id);

	List<Course> findCoursesInLazyInstructor(int id);

	Instructor findInstructorWithCoursesByIdJoinFetch(int id);

	void updateInstructor(Instructor instructor1);

	Course findCourseById(int id);

	void updateCourse(Course course1);

	void deleteCourseById(int id);
	
	
}
