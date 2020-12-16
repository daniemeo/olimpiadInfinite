package it.solvingteam.course.olimpiadinfinite.dto.messages;

import java.util.Set;

public class NationRepresentativeShowDto extends PersonDto{
	private Set<AthleteDto> athleteDto;
	private UserDto userDto;
    private NationDto nationDto;
    
	public Set<AthleteDto> getAthleteDto() {
		return athleteDto;
	}
	public void setAthleteDto(Set<AthleteDto> athleteDto) {
		this.athleteDto = athleteDto;
	}
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	public NationDto getNationDto() {
		return nationDto;
	}
	public void setNationDto(NationDto nationDto) {
		this.nationDto = nationDto;
	} 
    
}
