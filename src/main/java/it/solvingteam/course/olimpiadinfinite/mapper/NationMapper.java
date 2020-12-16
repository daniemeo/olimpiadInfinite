package it.solvingteam.course.olimpiadinfinite.mapper;

import org.springframework.stereotype.Component;

import it.solvingteam.course.olimpiadinfinite.dto.messages.NationDto;
import it.solvingteam.course.olimpiadinfinite.model.nation.Nation;
@Component
public class NationMapper extends AbstractMapper<Nation, NationDto>{

	@Override
	public NationDto convertEntityToDto(Nation entity) {
		if (entity == null) {
			return null;
		}
		NationDto nationDto = new NationDto();
		nationDto.setId(String.valueOf(entity.getId()));
		nationDto.setName(entity.getName());
		return nationDto;
	}

	@Override
	public Nation convertDtoToEntity(NationDto dto) {
		if (dto == null) {
			return null;
		}
		Nation nation = new Nation();
		nation.setId(Long.parseLong(dto.getId()));
		nation.setName(dto.getName());
		return nation;
	}

}
