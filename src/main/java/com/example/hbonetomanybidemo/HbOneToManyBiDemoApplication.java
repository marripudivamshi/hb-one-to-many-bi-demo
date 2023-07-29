package com.example.hbonetomanybidemo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.hbonetomanybidemo.dao.InstructorDAO;
import com.example.hbonetomanybidemo.entity.Course;
import com.example.hbonetomanybidemo.entity.Instructor;
import com.example.hbonetomanybidemo.entity.InstructorDetail;

@SpringBootApplication
public class HbOneToManyBiDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HbOneToManyBiDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(InstructorDAO instructorDAO) {
		return runner -> {
			System.out.println("Welcome to hibernate");
			
			//saveInstructor(instructorDAO);
			
			//findInstructorById(instructorDAO);
			
			//deleteInstructor(instructorDAO);
			
			//findInstructorDetailById(instructorDAO);
			
			//deleteInstructorDetailById(instructorDAO);
			
			createInstructorWithCourses(instructorDAO);
			
			//findCoursesOfLazyInstructor(instructorDAO);
			
			//findCoursesDirectlyFromLazyInstructor(instructorDAO);
			
			//updateInstructor(instructorDAO);
			
			//updateCourse(instructorDAO);
			
			//deleteInstructor(instructorDAO);
			
			//deleteCourse(instructorDAO);
		};
	}
	
	private void deleteCourse(InstructorDAO instructorDAO) {
		int id = 10;
		instructorDAO.deleteCourseById(id);
		System.out.println("Course deleted successfully");
		
	}

	private void deleteInstructor(InstructorDAO instructorDAO) {
		int id = 1;
		instructorDAO.deleteInstructorById(id);
		System.out.println("instructor deleted successfully");
	}

	private void updateCourse(InstructorDAO instructorDAO) {
		int id = 10;
		Course course1 = instructorDAO.findCourseById(id);
		
		System.out.println("found Course : " +course1);
		
		course1.setTitle("developer beeps");
		
		instructorDAO.updateCourse(course1);
		System.out.println("updated course successfully");
		
	}
	
	private void updateInstructor(InstructorDAO instructorDAO) {
		int id = 1;
		Instructor instructor1 = instructorDAO.findInstructorById(id);
		
		System.out.println("found instructor : " +instructor1);
		
		instructor1.setLastName("developer");
		
		instructorDAO.updateInstructor(instructor1);
		System.out.println("updated instructor successfully");
		
	}

	

	private void createInstructorWithCourses(InstructorDAO instructorDAO) {
		Instructor instructor1 = new Instructor ("vamshi", "marripudi", "vamshi.marripudi@fmr.com");
		InstructorDetail instructorDetail1 = new InstructorDetail("I love coding", "guitar");
		
		instructor1.setInstructorDetail(instructorDetail1);
		
		Course course1 = new Course("balu abcdefg");
		Course course2 = new Course("billa");
		Course course3 = new Course("darling");
		
		instructor1.add(course1);
		instructor1.add(course2);
		instructor1.add(course3);
		
		instructorDAO.save(instructor1);
		System.out.println("saved instructor along with courses");
		
		
	}
	
	private void findCoursesOfLazyInstructor(InstructorDAO instructorDAO) {
		int id = 1;
		Instructor instructor1 = instructorDAO.findInstructorById(id);
		
		System.out.println("found instructor : " +instructor1);
		
		List<Course> courses = instructorDAO.findCoursesInLazyInstructor(id);
		instructor1.setCourses(courses);
		
		System.out.println("Instructor's courses found : " +instructor1.getCourses());
		
	}
	
	private void findCoursesDirectlyFromLazyInstructor(InstructorDAO instructorDAO) {
		int id = 1;
		Instructor instructor1 = instructorDAO.findInstructorWithCoursesByIdJoinFetch(id);
		
		System.out.println("found instructor : " +instructor1);
		
		System.out.println("Instructor's courses found : " +instructor1.getCourses());
		
	}
	
	
	
	

	private void saveInstructor(InstructorDAO instructorDAO) {
		Instructor instructor1 = new Instructor ("vamshi", "marripudi", "vamshi.marripudi@fmr.com");
		InstructorDetail instructorDetail1 = new InstructorDetail("I love coding", "guitar");
		
		Instructor instructor2 = new Instructor ("abbu", "paturi", "abbu.paturi@fmr.com");
		InstructorDetail instructorDetail2 = new InstructorDetail("I love coding", "bike");
		
		Instructor instructor3 = new Instructor ("padmavathi", "velaga", "padmavathi.velaga@fmr.com");
		InstructorDetail instructorDetail3 = new InstructorDetail("I love coding", "chit chat");
		
		instructor1.setInstructorDetail(instructorDetail1);
		instructor2.setInstructorDetail(instructorDetail2);
		instructor3.setInstructorDetail(instructorDetail3);
		
		instructorDAO.save(instructor1);
		instructorDAO.save(instructor2);
		instructorDAO.save(instructor3);
		System.out.println("instructor & instructorDetail 1, 2, 3 saved successfully");
	}
	
	private void findInstructorById(InstructorDAO instructorDAO) {
		int id = 1;
		Instructor instructor1 = instructorDAO.findInstructorById(id);
		
		System.out.println("found instructor : " +instructor1);
		System.out.println("instructor detail found : " +instructor1.getInstructorDetail());
	}
	
	private void findInstructorDetailById(InstructorDAO instructorDAO) {
		int id =2;
		InstructorDetail instructorDetail = instructorDAO.findInstructorDetailById(id);
		
		System.out.println("Instructor detail found : " +instructorDetail);
	}
	
	private void deleteInstructorDetailById(InstructorDAO instructorDAO) {
		int id = 3;
		instructorDAO.deleteInstructorDetailById(id);
		System.out.println("deleted InstructorDetail By Id done");
		
	}

}
