package it.solvingteam.course.olimpiadinfinite.dto.messages;

import javax.validation.constraints.NotEmpty;

public class UserDto extends PersonDto {
	@NotEmpty(message = "Required field")
	private String username;
	@NotEmpty(message = "Required field")
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
