package it.solvingteam.course.olimpiadinfinite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.course.olimpiadinfinite.model.competition.Competition;


public interface CompetitionRepository extends JpaRepository<Competition, Integer> {

}
