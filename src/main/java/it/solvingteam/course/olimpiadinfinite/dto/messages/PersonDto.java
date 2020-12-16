package it.solvingteam.course.olimpiadinfinite.dto.messages;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public abstract class PersonDto {
	@NotNull(message = "The id doesn't exist!")
	private String id;
	@NotEmpty(message = "Required field")
	private String name;
	@NotEmpty(message = "Required field")
	private String surname;
	@NotEmpty(message = "Required field")
	private String fiscalCode;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getFiscalCode() {
		return fiscalCode;
	}
	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}
	
	
}
