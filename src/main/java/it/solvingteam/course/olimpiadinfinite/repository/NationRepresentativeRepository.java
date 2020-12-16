package it.solvingteam.course.olimpiadinfinite.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.course.olimpiadinfinite.model.nationRepresentative.NationRepresentative;
import it.solvingteam.course.olimpiadinfinite.model.user.User;

public interface NationRepresentativeRepository extends JpaRepository<NationRepresentative, Long> {
	  Optional<NationRepresentative> findByFiscalCode(String fiscalCode);

}
