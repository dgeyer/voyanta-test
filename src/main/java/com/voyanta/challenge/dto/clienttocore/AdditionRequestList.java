package com.voyanta.challenge.dto.clienttocore;

import java.util.LinkedList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdditionRequestList {
	@NotNull
	private long id;
	@NotNull
	private List<AdditionRequest> additionList = new LinkedList<AdditionRequest>();

	public List<AdditionRequest> getAdditionList() {
		return additionList;
	}

	public void setAdditionList(List<AdditionRequest> requestsList) {
		this.additionList = requestsList;
	}

	public boolean add(AdditionRequest additionRequest) {
		return this.additionList.add(additionRequest);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
