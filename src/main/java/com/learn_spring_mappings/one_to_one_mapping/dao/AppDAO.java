package com.learn_spring_mappings.one_to_one_mapping.dao;

import java.util.List;

import com.learn_spring_mappings.one_to_one_mapping.entity.Course;
import com.learn_spring_mappings.one_to_one_mapping.entity.Instructor;
import com.learn_spring_mappings.one_to_one_mapping.entity.InstructorDetail;

public interface AppDAO {

	void save(Instructor instructor);

	Instructor findInstructorById(int id);

	void deleteInstructorById(int id);

	InstructorDetail findInstructorDetailById(int id);

	void deleteInstructorDetailById(int id);

	List<Course> findCoursesByInstructorId(int id);

	Instructor findInstructorByIdJoinFetch(int id);

	void update(Instructor instructor);

	void update(Course course);

	Course findCourseById(int id);

}
