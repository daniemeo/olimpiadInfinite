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

import it.solvingteam.course.olimpiadinfinite.dto.messages.DeleteNationRepresentantMessageDto;
import it.solvingteam.course.olimpiadinfinite.dto.messages.InsertNationRepresentativeMessageDto;
import it.solvingteam.course.olimpiadinfinite.dto.messages.NationRepresentativeDto;
import it.solvingteam.course.olimpiadinfinite.dto.messages.NationRepresentativeSearchFilterDto;
import it.solvingteam.course.olimpiadinfinite.service.NationRepresentativeService;
import it.solvingteam.course.olimpiadinfinite.service.NationService;
import it.solvingteam.course.olimpiadinfinite.service.UserService;
import it.solvingteam.course.olimpiadinfinite.web.validators.NationRepresentantDeleteMessageValidator;
import it.solvingteam.course.olimpiadinfinite.web.validators.UserNationRepresentativeMessageValidator;
import it.solvingteam.course.olimpiadinfinite.web.validators.UserSignupMessageValidator;



@EnableWebSecurity
@Controller

@RequestMapping("organizer")
public class OrganizerController {
	@Autowired
	private NationRepresentativeService nationRepresentativeService;
	
	 @Autowired
	private UserSignupMessageValidator userSignupMessageValidator;
	 
	 @Autowired
	 private NationService nationService;
	 
	 @Autowired
	 private UserService userService;
	 
	 @Autowired
	 private UserNationRepresentativeMessageValidator userNationRepresentativeMessageValidator;
	 
	 @Autowired 
	 private NationRepresentantDeleteMessageValidator nationRepresentantDeleteMessageValidator;
	 
	 @Secured("ROLE_ORGANIZZATORE")
		@GetMapping("registerRappresentation")
		public String list(NationRepresentativeSearchFilterDto nationRepresentativeSearchFilterDto, Model model) {
			List<NationRepresentativeDto> allRepresentative = nationRepresentativeService.findBySearchParameter(nationRepresentativeSearchFilterDto);

			model.addAttribute("searchFilters", nationRepresentativeSearchFilterDto);
			model.addAttribute("nationRepresentative", allRepresentative);
			model.addAttribute("nations", nationService.findAll());
			
			return "organizer/registerRappresentation";
		}
	 
	@Secured("ROLE_ORGANIZZATORE")
	@GetMapping("signup")
	public String insertNationRepresentative(Model model) {
		model.addAttribute("nationRepresentativeInsertModel", new InsertNationRepresentativeMessageDto());
		model.addAttribute("nations", nationService.findAll());
		return "organizer/signup";
		
	}
	@Secured("ROLE_ORGANIZZATORE")
	@PostMapping("signup")
	public String insertNationRepresentative(@Valid @ModelAttribute("nationRepresentativeInsertModel") InsertNationRepresentativeMessageDto insertNationRepresentativeMessageDto, 
			BindingResult bindingResult, Model model, NationRepresentativeSearchFilterDto nationRepresentativeSearchFilterDto)  {
		   userSignupMessageValidator.validate(insertNationRepresentativeMessageDto.getUserSignupMessageDto(), bindingResult);
		   userNationRepresentativeMessageValidator.validate(insertNationRepresentativeMessageDto,bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("nations", nationService.findAll());
			return "organizer/signup";

		} else {
			userService.signup(insertNationRepresentativeMessageDto);
			List<NationRepresentativeDto> allRepresentative = nationRepresentativeService.findBySearchParameter(nationRepresentativeSearchFilterDto);
			model.addAttribute("searchFilters", nationRepresentativeSearchFilterDto);
			nationRepresentativeService.insertNationRepresentative(insertNationRepresentativeMessageDto);
			model.addAttribute("nationRepresentative", allRepresentative);
			model.addAttribute("nations", nationService.findAll());
			return "redirect:/organizer/registerRappresentation";
		}
	}
	
	@GetMapping("/showRepresentative")
	public String showRepresentative(@RequestParam("id") Long id, Model model) {
		if (id != null) {
			model.addAttribute("representative", nationRepresentativeService.convertRepresentative(id));
			return "organizer/showRepresentative";
		} else {
			return "redirect:/organizer";
		}

	}
	@GetMapping("/prepareDeleteNationRepresentative/{idDelete}")
	public String deleteNationRepresentative(
			@Valid @ModelAttribute("deleteModel") DeleteNationRepresentantMessageDto deleteNationRepresentantMessageDto,
			BindingResult bindingResult, Model model,NationRepresentativeSearchFilterDto nationRepresentativeSearchFilterDto) {
		if ( deleteNationRepresentantMessageDto.getIdDelete() != null && ! deleteNationRepresentantMessageDto.getIdDelete().isEmpty()) {
			nationRepresentantDeleteMessageValidator.validate( deleteNationRepresentantMessageDto, bindingResult);
			if (bindingResult.hasErrors()) {
				List<NationRepresentativeDto> allRepresentative = nationRepresentativeService.findBySearchParameter(nationRepresentativeSearchFilterDto);
				model.addAttribute("deleteModel", deleteNationRepresentantMessageDto);
				model.addAttribute("searchFilters", nationRepresentativeSearchFilterDto);
				model.addAttribute("nationRepresentative", allRepresentative);
				model.addAttribute("nations", nationService.findAll());
				return "organizer/registerRappresentation";
			}

			else {
                model.addAttribute("deleteModel",deleteNationRepresentantMessageDto);
				return "organizer/deleteNationRepresentative";
			}

		}
		model.addAttribute("deleteModel", deleteNationRepresentantMessageDto);
		model.addAttribute("searchFilters", nationRepresentativeSearchFilterDto);
		List<NationRepresentativeDto> allRepresentative = nationRepresentativeService.findBySearchParameter(nationRepresentativeSearchFilterDto);
		model.addAttribute("nationRepresentative", allRepresentative);
		model.addAttribute("nations", nationService.findAll());
		return "organizer/registerRappresentation";
//		return "redirect:/organizer";

	}

	@GetMapping("/executeDeleteNationRepresentative/{idDelete}")
	public String executeDeleteCustomer(@Valid @ModelAttribute("deleteModel") DeleteNationRepresentantMessageDto deleteNationRepresentantMessageDto,Model model,
			NationRepresentativeSearchFilterDto nationRepresentativeSearchFilterDto) {

		nationRepresentativeService.delete(Long.parseLong(deleteNationRepresentantMessageDto.getIdDelete()));
		model.addAttribute("deleteModel", deleteNationRepresentantMessageDto);
		model.addAttribute("searchFilters", nationRepresentativeSearchFilterDto);
		List<NationRepresentativeDto> allRepresentative = nationRepresentativeService.findBySearchParameter(nationRepresentativeSearchFilterDto);
		model.addAttribute("nationRepresentative", allRepresentative);
		model.addAttribute("nations", nationService.findAll());
		return "organizer/registerRappresentation";
	}






}
