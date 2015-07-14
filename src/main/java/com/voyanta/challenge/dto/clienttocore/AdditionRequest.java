package com.voyanta.challenge.dto.clienttocore;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
@Scope(value = "prototype")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdditionRequest {

	@NotBlank
	private String id;
	@NotNull
	@Size(min = 2)
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
