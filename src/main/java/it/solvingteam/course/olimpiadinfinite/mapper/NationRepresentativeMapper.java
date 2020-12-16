package it.solvingteam.course.olimpiadinfinite.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.course.olimpiadinfinite.dto.messages.InsertNationRepresentativeMessageDto;
import it.solvingteam.course.olimpiadinfinite.dto.messages.NationRepresentativeDto;
import it.solvingteam.course.olimpiadinfinite.dto.messages.NationRepresentativeShowDto;
import it.solvingteam.course.olimpiadinfinite.model.athlete.Athlete;
import it.solvingteam.course.olimpiadinfinite.model.nation.Nation;
import it.solvingteam.course.olimpiadinfinite.model.nationRepresentative.NationRepresentative;
import it.solvingteam.course.olimpiadinfinite.model.user.User;
@Component
public class NationRepresentativeMapper  extends AbstractMapper<NationRepresentative, NationRepresentativeDto> {
    @Autowired
     UserMapper userMapper;
    
    @Autowired
    NationMapper nationMapper;
    
    @Autowired 
    AthleteMapper athleteMapper;
    
	@Override
	public NationRepresentativeDto convertEntityToDto(NationRepresentative entity) {
		if (entity == null) {
			return null;
		}
        
		NationRepresentativeDto nationRepresentativeDto = new NationRepresentativeDto();
		nationRepresentativeDto.setId(String.valueOf(entity.getId()));
		nationRepresentativeDto.setName(entity.getName());
		nationRepresentativeDto.setSurname(entity.getSurname());
		nationRepresentativeDto.setFiscalCode(entity.getFiscalCode());
		nationRepresentativeDto.setUserDto(userMapper.convertEntityToDto(entity.getUser()));
		nationRepresentativeDto.setNationDto(nationMapper.convertEntityToDto(entity.getNation()));
	
		return nationRepresentativeDto;
	}
	
	public NationRepresentativeShowDto convertEntityToDtoShow(NationRepresentative entity) {
		if (entity == null) {
			return null;
		}
        
		NationRepresentativeShowDto nationRepresentativeDto = new NationRepresentativeShowDto();
		nationRepresentativeDto.setId(String.valueOf(entity.getId()));
		nationRepresentativeDto.setName(entity.getName());
		nationRepresentativeDto.setSurname(entity.getSurname());
		nationRepresentativeDto.setFiscalCode(entity.getFiscalCode());
		nationRepresentativeDto.setUserDto(userMapper.convertEntityToDto(entity.getUser()));
		nationRepresentativeDto.setNationDto(nationMapper.convertEntityToDto(entity.getNation()));
		nationRepresentativeDto.setAthleteDto(athleteMapper.convertEntityToListDto(entity.getAthletes()));
		
		return nationRepresentativeDto;
	}

	@Override
	public NationRepresentative convertDtoToEntity(NationRepresentativeDto dto) {
		if (dto == null) {
			return null;
		}
		
		NationRepresentative nationRepresentative = new NationRepresentative();
		
		if (dto.getId() != null) {
			nationRepresentative.setId(Long.valueOf(dto.getId()));
		}

		nationRepresentative.setName(dto.getName());
		nationRepresentative.setSurname(dto.getSurname());
		nationRepresentative.setFiscalCode(dto.getFiscalCode());
		return nationRepresentative;
	}
	
	public NationRepresentative convertDtoToEntity(InsertNationRepresentativeMessageDto dto) {
		if (dto == null) {
			return null;
		}
		
		NationRepresentative nationRepresentative = new NationRepresentative();
		
		nationRepresentative.setName(dto.getName());
		nationRepresentative.setSurname(dto.getSurname());
		nationRepresentative.setFiscalCode(dto.getFiscalCode());
		User user = new User();
		user.setName(dto.getName());
		user.setSurname(dto.getSurname());
		user.setFiscalCode(dto.getFiscalCode());
		user.setUsername(dto.getUserSignupMessageDto().getUsername());
		user.setPassword(dto.getUserSignupMessageDto().getPassword());
		nationRepresentative.setUser(user);
		Nation nation = new Nation();
		nation.setId(Long.parseLong(dto.getNation()));
		nationRepresentative.setNation(nation);
		
		return nationRepresentative;
	}

}
