package it.solvingteam.course.olimpiadinfinite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.course.olimpiadinfinite.model.nationRepresentative.NationRepresentative;

public interface NationRepresentativeRepository extends JpaRepository<NationRepresentative, Long> {

}
