package it.solvingteam.course.olimpiadinfinite.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.course.olimpiadinfinite.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
	  Optional<User> findByUsername(String username);

}
