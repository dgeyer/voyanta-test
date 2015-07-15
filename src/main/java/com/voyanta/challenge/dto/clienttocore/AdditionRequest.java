package com.voyanta.challenge.dto.clienttocore;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdditionRequest {

	@NotBlank(message = "must specify Id")
	private String id;
	@NotNull(message = "must specify addends")
	@NotEmpty(message = "must specify addends")
	Number[] addends;
	private Long additionListId;

	public AdditionRequest() {
		super();
	}

	public AdditionRequest(String id, Number[] addends, Long additionListId) {
		super();
		this.id = id;
		this.addends = addends;
		this.additionListId = additionListId;
	}

	public AdditionRequest(String id, Number[] addends) {
		super();
		this.id = id;
		this.addends = addends;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Number[] getAddends() {
		return addends;
	}

	public void setAddends(Number[] addends) {
		this.addends = addends;
	}

	public Long getAdditionListId() {
		return additionListId;
	}

	public void setAdditionListId(Long additionListId) {
		this.additionListId = additionListId;
	}

}
