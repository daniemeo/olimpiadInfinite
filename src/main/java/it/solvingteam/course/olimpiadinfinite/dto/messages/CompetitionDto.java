package it.solvingteam.course.olimpiadinfinite.dto.messages;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class CompetitionDto {
	@NotNull(message = "Required field")
	private String id;
	
	@NotEmpty(message = "Required field")
	private String numberAthletes;
	
	@NotEmpty(message = "Required field")
	private String competitionDate;
	
	@NotEmpty(message = "Required field")
	private String scoreModifier;
	
	@NotEmpty(message = "Required field")
	private String competitionStatus;
	
	private SportDto sportDto;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumberAthletes() {
		return numberAthletes;
	}

	public void setNumberAthletes(String numberAthletes) {
		this.numberAthletes = numberAthletes;
	}

	public String getCompetitionDate() {
		return competitionDate;
	}

	public void setCompetitionDate(String competitionDate) {
		this.competitionDate = competitionDate;
	}

	public String getScoreModifier() {
		return scoreModifier;
	}

	public void setScoreModifier(String scoreModifier) {
		this.scoreModifier = scoreModifier;
	}

	public String getCompetitionStatus() {
		return competitionStatus;
	}

	public void setCompetitionStatus(String competitionStatus) {
		this.competitionStatus = competitionStatus;
	}

	public SportDto getSportDto() {
		return sportDto;
	}

	public void setSportDto(SportDto sportDto) {
		this.sportDto = sportDto;
	}
	
	
	
	
}
