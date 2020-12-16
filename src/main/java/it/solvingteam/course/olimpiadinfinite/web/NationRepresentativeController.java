package it.solvingteam.course.olimpiadinfinite.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.solvingteam.course.olimpiadinfinite.dto.messages.InsertNationRepresentativeMessageDto;
import it.solvingteam.course.olimpiadinfinite.dto.messages.NationRepresentativeDto;
import it.solvingteam.course.olimpiadinfinite.dto.messages.NationRepresentativeSearchFilterDto;
import it.solvingteam.course.olimpiadinfinite.service.NationRepresentativeService;
import it.solvingteam.course.olimpiadinfinite.service.NationService;
import it.solvingteam.course.olimpiadinfinite.service.UserService;
import it.solvingteam.course.olimpiadinfinite.web.validators.UserSignupMessageValidator;


@EnableWebSecurity
@Controller

@RequestMapping("nationRepresentative")
public class NationRepresentativeController {
	@Autowired
	private NationRepresentativeService nationalRepresentativeService;
	
	 @Autowired
	private UserSignupMessageValidator userSignupMessageValidator;
	 
	 @Autowired
	 private NationService nationService;
	 
	 @Autowired
	 private UserService userService;
	 
	 @Secured("ROLE_ORGANIZZATORE")
		@GetMapping("registerRappresentation")
		public String list(NationRepresentativeSearchFilterDto nationRepresentativeSearchFilterDto, Model model) {
			List<NationRepresentativeDto> allRepresentative = nationalRepresentativeService.findBySearchParameter(nationRepresentativeSearchFilterDto);

			model.addAttribute("searchFilters", nationRepresentativeSearchFilterDto);
			model.addAttribute("nationRepresentative", allRepresentative);
			model.addAttribute("nations", nationService.findAll());
			
			return "nationRepresentative/registerRappresentation";
		}
	 
	@Secured("ROLE_ORGANIZZATORE")
	@GetMapping("signup")
	public String insertNationRepresentative(Model model) {
		model.addAttribute("nationRepresentativeInsertModel", new InsertNationRepresentativeMessageDto());
		model.addAttribute("nations", nationService.findAll());
		return "nationRepresentative/signup";
		
	}
	@Secured("ROLE_ORGANIZZATORE")
	@PostMapping("signup")
	public String insertNationRepresentative(@Valid @ModelAttribute("nationRepresentativeInsertModel") InsertNationRepresentativeMessageDto insertNationRepresentativeMessageDto, 
			BindingResult bindingResult, Model model)  {
		   userSignupMessageValidator.validate(insertNationRepresentativeMessageDto.getUserSignupMessageDto(), bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("nations", nationService.findAll());
			return "nationRepresentative/signup";

		} else {
			userService.signup(insertNationRepresentativeMessageDto);
			nationalRepresentativeService.insertNationRepresentative(insertNationRepresentativeMessageDto);
			return "nationRepresentative/registerRappresentation";
		}
	}
	
	@GetMapping("/showRepresentative")
	public String showRepresentative(@RequestParam("id") Long id, Model model) {
		if (id != null) {
			model.addAttribute("representative", nationalRepresentativeService.convertRepresentative(id));
			return "nationRepresentative/showRepresentative";
		} else {
			return "redirect:/nationRepresentative";
		}

	}



}
