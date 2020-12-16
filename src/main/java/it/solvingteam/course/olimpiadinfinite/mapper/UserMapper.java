package it.solvingteam.course.olimpiadinfinite.mapper;

import org.springframework.stereotype.Component;

import it.solvingteam.course.olimpiadinfinite.dto.messages.UserDto;
import it.solvingteam.course.olimpiadinfinite.model.user.User;
@Component
public class UserMapper extends AbstractMapper<User, UserDto>{

	@Override
	public UserDto convertEntityToDto(User entity) {
		if (entity == null) {
			return null;
		}
      UserDto userDto = new UserDto();
      userDto.setId(String.valueOf(entity.getId()));
      userDto.setName(entity.getName());
      userDto.setSurname(entity.getSurname());
      userDto.setFiscalCode(entity.getFiscalCode());
      userDto.setUsername(entity.getUsername());
      userDto.setPassword(entity.getPassword());
      
      return userDto;
	}

	@Override
	public User convertDtoToEntity(UserDto dto) {
		if (dto== null) {
			return null;
		}
		
		User user = new User();
		user.setId(Long.parseLong(dto.getId()));
		user.setName(dto.getName());
		user.setSurname(dto.getSurname());
		user.setFiscalCode(dto.getFiscalCode());
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		return user;
	}

}
