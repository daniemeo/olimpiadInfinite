package it.solvingteam.course.olimpiadinfinite.web.validators;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.solvingteam.course.olimpiadinfinite.dto.messages.InsertNationRepresentativeMessageDto;
import it.solvingteam.course.olimpiadinfinite.model.nationRepresentative.NationRepresentative;
import it.solvingteam.course.olimpiadinfinite.service.NationRepresentativeService;

@Component
public class UserNationRepresentativeMessageValidator implements Validator{
	
   @Autowired
    private NationRepresentativeService nationRepresentativeService;
	   
	@Override
	public boolean supports(Class<?> clazz) {
		 return InsertNationRepresentativeMessageDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		InsertNationRepresentativeMessageDto insertNationRepresentativeMessageDto = (InsertNationRepresentativeMessageDto)o;
		List<NationRepresentative> nationRepresentatives = nationRepresentativeService.findAll();
		for(NationRepresentative nationRepresentative : nationRepresentatives) {
		
			if(insertNationRepresentativeMessageDto.getNation().equals(nationRepresentative.getNation().getId().toString())) {
				errors.rejectValue("nation", "nationAlreadyExists", "Nation already has a representative");
			}
		}
	        
	}

}
