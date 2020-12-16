package it.solvingteam.course.olimpiadinfinite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.course.olimpiadinfinite.model.nation.Nation;


public interface NationRepository extends JpaRepository<Nation, Long> {

}
