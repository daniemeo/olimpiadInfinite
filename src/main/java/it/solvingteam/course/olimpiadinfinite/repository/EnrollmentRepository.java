package it.solvingteam.course.olimpiadinfinite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.course.olimpiadinfinite.model.enrollment.Enrollment;


public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

}
