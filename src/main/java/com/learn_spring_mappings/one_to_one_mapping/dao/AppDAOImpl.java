package com.learn_spring_mappings.one_to_one_mapping.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.learn_spring_mappings.one_to_one_mapping.entity.Course;
import com.learn_spring_mappings.one_to_one_mapping.entity.Instructor;
import com.learn_spring_mappings.one_to_one_mapping.entity.InstructorDetail;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class AppDAOImpl implements AppDAO {

	private EntityManager entityManager;

	public AppDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public void save(Instructor instructor) {
		this.entityManager.persist(instructor);
	}

	@Override
	public Instructor findInstructorById(int id) {
		return this.entityManager.find(Instructor.class, id);
	}

	@Override
	@Transactional
	public void deleteInstructorById(int id) {
		// retrieve the instructor

		Instructor instructor = this.entityManager.find(Instructor.class, id);

		List<Course> courses = instructor.getCourses();

		for (Course course : courses) {
			course.setInstructor(null);
		}

		// delete the instructor
		this.entityManager.remove(instructor);
	}

	@Override
	public InstructorDetail findInstructorDetailById(int id) {
		return this.entityManager.find(InstructorDetail.class, id);
	}

	@Override
	@Transactional
	public void deleteInstructorDetailById(int id) {
		// retrieve instructor detail

		InstructorDetail instructorDetail = this.entityManager.find(InstructorDetail.class, id);

		// remove the associated object reference
		// break bi-directional link
		instructorDetail.getInstructor().setInstructorDetail(null);

		// delete instructor detail
		this.entityManager.remove(instructorDetail);

	}

	@Override
	public List<Course> findCoursesByInstructorId(int id) {

		// create query
		TypedQuery<Course> query = this.entityManager.createQuery("from Course where instructor.id = :id",
				Course.class);

		// set parameter
		query.setParameter("id", id);

		return query.getResultList();
	}

	@Override
	public Instructor findInstructorByIdJoinFetch(int id) {

		TypedQuery<Instructor> query = this.entityManager.createQuery(
				"select i from Instructor i "
						+ "JOIN FETCH i.courses "
						+ "JOIN FETCH i.instructorDetail "
						+ "where i.id = :id",
				Instructor.class);

		query.setParameter("id", id);

		return query.getSingleResult();

	}

	@Override
	@Transactional
	public void update(Instructor instructor) {
		this.entityManager.merge(instructor);
	}

	@Override
	@Transactional
	public void update(Course course) {
		this.entityManager.merge(course);
	}

	@Override
	public Course findCourseById(int id) {
		return this.entityManager.find(Course.class, id);
	}

}
