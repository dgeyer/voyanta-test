package com.voyanta.challenge.dto.coretoclient;

import java.util.LinkedList;
import java.util.List;

public class AdditionResponseList {
	private List<AdditionResponse> responseList = new LinkedList<AdditionResponse>();

	public List<AdditionResponse> getResponseList() {
		return responseList;
	}

	public boolean addResponseList(List<AdditionResponse> responseList) {
		return this.responseList.addAll(responseList);
	}

	public boolean add(AdditionResponse responseList) {
		return this.responseList.add(responseList);
	}

}
