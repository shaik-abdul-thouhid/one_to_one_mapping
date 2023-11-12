package com.learn_spring_mappings.one_to_one_mapping;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.learn_spring_mappings.one_to_one_mapping.dao.AppDAO;
import com.learn_spring_mappings.one_to_one_mapping.entity.Course;
import com.learn_spring_mappings.one_to_one_mapping.entity.Instructor;
import com.learn_spring_mappings.one_to_one_mapping.entity.InstructorDetail;

@SpringBootApplication
public class OneToOneMappingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneToOneMappingApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> this.createInstructor(appDAO);
	}

	public void updateCourse(AppDAO appDAO) {
		int id = 10;

		System.out.println("finding course id: " + id);

		var course = appDAO.findCourseById(id);

		System.out.println("updating course id: " + id);

		course.setTitle("Enjoy simple things");

		appDAO.update(course);

		System.out.println("Done!");
	}

	public void updateInstructor(AppDAO appDAO) {

		int id = 1;

		// find the instructor
		System.out.println("finding instructor with id: " + id);

		var instructor = appDAO.findInstructorById(id);

		System.out.println("instructor: " + instructor);

		// update the instructor
		System.out.println("updating instructor with id: " + id);

		instructor.setLastName("TESTER");

		appDAO.update(instructor);

		System.out.println("Done!");

	}

	public void deleteInstructorDetailById(AppDAO appDAO) {
		int id = 3;

		System.out.println("deleting instructor detail with id: " + id);

		appDAO.deleteInstructorDetailById(id);

		System.out.println("Done!");
	}

	public void findInstructorDetailById(AppDAO appDAO) {
		int id = 2;

		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);

		System.out.println("instructor details: " + instructorDetail);

		System.out.println("associated instructor: " + instructorDetail.getInstructor());

	}

	public void deleteInstructorById(AppDAO appDAO) {
		int id = 1;

		System.out.println("deleting instructor id: " + id);

		appDAO.deleteInstructorById(id);

		System.out.println("Done!");
	}

	public void findInstructorById(AppDAO appDAO) {
		int id = 2;

		System.out.println("Finding instructor id: " + id);

		Instructor tempInstructor = appDAO.findInstructorById(id);

		System.out.println("temp instructor: " + tempInstructor);

		System.out.println("the associated instructor details: " + tempInstructor.getInstructorDetail());
	}

	public void createInstructor(AppDAO appDAO) {
		// create the instructor
		Instructor instructor = new Instructor("shaik", "thouhid", "shaik.thouhid@gmail.com");

		// create the instructor detail
		InstructorDetail instructorDetail = new InstructorDetail("youtube.com/shaik_thouhid", "swimming");

		instructor.setInstructorDetail(instructorDetail);

		instructorDetail.setInstructor(instructor);

		System.out.println("Saving instructor: " + instructor);

		appDAO.save(instructor);

		System.out.println("Done!");
	}

	public void createInstructorWithCourses(AppDAO appDAO) {
		Instructor instructor = new Instructor("shaik", "thouhid", "shaik.thouhid@gmail.com");

		// create the instructor detail
		InstructorDetail instructorDetail = new InstructorDetail("youtube.com/shaik_thouhid", "swimming");

		instructor.setInstructorDetail(instructorDetail);

		instructorDetail.setInstructor(instructor);

		Course course1 = new Course("Air Guitar - The Ultimate Guide");
		Course course2 = new Course("The Pinball Masterclass");

		instructor.add(course1);
		instructor.add(course2);

		System.out.println("saving instructor:" + instructor);

		System.out.println("courses: " + instructor.getCourses());

		appDAO.save(instructor);

		System.out.println("Done!");
	}

	public void findInstructorWithCoursed(AppDAO appDAO) {
		int id = 1;

		System.out.println("finding instructor id: " + id);

		Instructor tempInstructor = appDAO.findInstructorById(id);

		System.out.println("temp instructor: " + tempInstructor);

		List<Course> courses = appDAO.findCoursesByInstructorId(id);

		tempInstructor.setCourses(courses);

		System.out.println("the associated instructor courses: " + tempInstructor.getCourses());

		System.out.println("Done!");
	}

	public void findInstructorWithCoursesUsingJoinFetch(AppDAO appDAO) {
		int id = 1;

		System.out.println("finding instructor id: " + id);

		Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);

		System.out.println("temporary instructor: " + instructor);

		System.out.println("associated courses: " + instructor.getCourses());

		System.out.println("Done!");
	}

}
