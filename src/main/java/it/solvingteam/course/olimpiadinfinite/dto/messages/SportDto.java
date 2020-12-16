package it.solvingteam.course.olimpiadinfinite.dto.messages;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SportDto {
	@NotNull(message = "The id doesn't exist!")
	private Long id;
	@NotEmpty(message = "Required field")
	private String name;

}
