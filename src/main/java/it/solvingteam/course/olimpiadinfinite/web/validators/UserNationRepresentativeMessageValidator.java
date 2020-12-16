package it.solvingteam.course.olimpiadinfinite.web.validators;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.solvingteam.course.olimpiadinfinite.dto.messages.InsertNationRepresentativeMessageDto;
import it.solvingteam.course.olimpiadinfinite.model.user.User;
import it.solvingteam.course.olimpiadinfinite.service.UserService;

@Component
public class UserNationRepresentativeMessageValidator implements Validator{
	
	   @Autowired
	    private UserService userService;
	   
	@Override
	public boolean supports(Class<?> clazz) {
		 return InsertNationRepresentativeMessageDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		InsertNationRepresentativeMessageDto insertNationRepresentativeMessageDto = (InsertNationRepresentativeMessageDto)o;
			
		
		
	        if (!insertNationRepresentativeMessageDto.getUserSignupMessageDto().getPassword().equals(insertNationRepresentativeMessageDto.getUserSignupMessageDto().getRepeatePassword())) {
	            errors.rejectValue("repeatePassword", "passwordsDoesntMatch", "Password doesn't match");
	        }

	        Optional<User> user = userService.findUserByUSername(insertNationRepresentativeMessageDto.getUserSignupMessageDto().getUsername());
	        if (user.isPresent()) {
	            errors.rejectValue("username", "usernameAlreadyExists", "Username already exists");
	        }
		
	}

}
