package it.solvingteam.course.olimpiadinfinite.dto.messages;

import javax.validation.constraints.NotNull;

public class DeleteNationRepresentantMessageDto {
	@NotNull(message = "il campo non esiste")
	  private String idDelete;

	public String getIdDelete() {
		return idDelete;
	}

	public void setIdDelete(String idDelete) {
		this.idDelete = idDelete;
	}
}
