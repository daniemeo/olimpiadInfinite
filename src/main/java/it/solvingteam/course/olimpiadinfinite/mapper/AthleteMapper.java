package it.solvingteam.course.olimpiadinfinite.mapper;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import it.solvingteam.course.olimpiadinfinite.dto.messages.AthleteDto;
import it.solvingteam.course.olimpiadinfinite.model.athlete.Athlete;
@Component
public class AthleteMapper extends AbstractMapper<Athlete, AthleteDto> {

	@Override
	public AthleteDto convertEntityToDto(Athlete entity) {
		if (entity == null) {
			return null;
		}

		AthleteDto athleteDto = new AthleteDto();

		athleteDto.setId(String.valueOf(entity.getId()));
		athleteDto.setName(entity.getName());
		athleteDto.setSurname(entity.getSurname());
		athleteDto.setFiscalCode(entity.getFiscalCode());
		athleteDto.setTotalScore(String.valueOf(entity.getTotalScore()));
		athleteDto.setGoldMedals(String.valueOf(entity.getGoldMedals()));
		athleteDto.setSilverMedals(String.valueOf(entity.getSilverMedals()));
		athleteDto.setBrozeMedals(String.valueOf(entity.getBrozeMedals()));

		return athleteDto;
	}
	
	public Set<AthleteDto> convertEntityToListDto(Set<Athlete> entity) {
		if (entity == null) {
			return null;
		}
     
		Set<AthleteDto> athleteDtoList = new TreeSet<AthleteDto>();
        for(AthleteDto athleteDto : athleteDtoList) {
        	for(Athlete athlete : entity) {
		athleteDto.setId(String.valueOf(athlete.getId()));
		athleteDto.setName(athlete.getName());
		athleteDto.setSurname(athlete.getSurname());
		athleteDto.setFiscalCode(athlete.getFiscalCode());
		athleteDto.setTotalScore(String.valueOf(athlete.getTotalScore()));
		athleteDto.setGoldMedals(String.valueOf(athlete.getGoldMedals()));
		athleteDto.setSilverMedals(String.valueOf(athlete.getSilverMedals()));
		athleteDto.setBrozeMedals(String.valueOf(athlete.getBrozeMedals()));
		
		athleteDtoList.add(athleteDto);
        	}
        }
		return athleteDtoList;
	}
	
	

	@Override
	public Athlete convertDtoToEntity(AthleteDto dto) {
		if (dto == null) {
			return null;
		}
		Athlete athlete = new Athlete();

		if (dto.getId() != null) {
			athlete.setId(Long.valueOf(dto.getId()));
		}

		athlete.setName(dto.getName());
		athlete.setSurname(dto.getSurname());
		athlete.setFiscalCode(dto.getFiscalCode());
		athlete.setTotalScore(Integer.parseInt(dto.getTotalScore()));
		athlete.setGoldMedals(Integer.parseInt(dto.getGoldMedals()));
		athlete.setSilverMedals(Integer.parseInt(dto.getSilverMedals()));
		athlete.setBrozeMedals(Integer.parseInt(dto.getBrozeMedals()));
		return athlete;
	}

}
