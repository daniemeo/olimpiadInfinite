package it.solvingteam.course.olimpiadinfinite.model.competition;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import it.solvingteam.course.olimpiadinfinite.model.enrollment.Enrollment;
import it.solvingteam.course.olimpiadinfinite.model.sport.Sport;

@Entity
public class Competition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer numberAthletes;
	private LocalDate competitionDate;
	private Integer scoreModifier;
	@Enumerated(EnumType.STRING)
	private StatusCompetition competitionStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sport_id", nullable = false)
	private Sport sport;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "competition", orphanRemoval = true)
	private Set<Enrollment> enrollments = new TreeSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumberAthletes() {
		return numberAthletes;
	}

	public void setNumberAthletes(Integer numberAthletes) {
		this.numberAthletes = numberAthletes;
	}

	public LocalDate getCompetitionDate() {
		return competitionDate;
	}

	public void setCompetitionDate(LocalDate competitionDate) {
		this.competitionDate = competitionDate;
	}

	public Integer getScoreModifier() {
		return scoreModifier;
	}

	public void setScoreModifier(Integer scoreModifier) {
		this.scoreModifier = scoreModifier;
	}

	public StatusCompetition getCompetitionStatus() {
		return competitionStatus;
	}

	public void setCompetitionStatus(StatusCompetition competitionStatus) {
		this.competitionStatus = competitionStatus;
	}

	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	public Set<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(Set<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}
	
	
}
