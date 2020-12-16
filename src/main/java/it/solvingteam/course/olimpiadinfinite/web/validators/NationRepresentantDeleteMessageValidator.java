package it.solvingteam.course.olimpiadinfinite.web.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.solvingteam.course.olimpiadinfinite.dto.messages.DeleteNationRepresentantMessageDto;
import it.solvingteam.course.olimpiadinfinite.model.nationRepresentative.NationRepresentative;
import it.solvingteam.course.olimpiadinfinite.service.NationRepresentativeService;


@Component
public class NationRepresentantDeleteMessageValidator implements Validator{
 @Autowired
 NationRepresentativeService nationRepresentativeService;
 
	@Override
	public boolean supports(Class<?> clazz) {
		 DeleteNationRepresentantMessageDto.class.isAssignableFrom(clazz);
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		DeleteNationRepresentantMessageDto deleteNationRepresentantDto = (DeleteNationRepresentantMessageDto)target;
		NationRepresentative nationRepresentative = nationRepresentativeService.getRepresentantFromDeleteDto(deleteNationRepresentantDto);
		if(nationRepresentative.getAthletes().size() > 0) {
			errors.rejectValue("idDelete", "deleteModelHasDeliveries","this National Representative has Athletes!!!!!");
		}
	}

}



