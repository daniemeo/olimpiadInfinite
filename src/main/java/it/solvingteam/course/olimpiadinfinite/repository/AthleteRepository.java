package it.solvingteam.course.olimpiadinfinite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.course.olimpiadinfinite.model.athlete.Athlete;


public interface AthleteRepository extends JpaRepository<Athlete, Integer>{

}
