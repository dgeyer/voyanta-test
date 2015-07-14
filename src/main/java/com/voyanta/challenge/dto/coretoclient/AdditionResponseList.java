package com.voyanta.challenge.dto.coretoclient;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.voyanta.challenge.dao.entity.AdditionEntity;

public class AdditionResponseList {
	private Page<AdditionResponse> responsePage = new PageImpl<AdditionResponse>(
			new LinkedList<AdditionResponse>());

	public AdditionResponseList() {
		super();
	}

	public AdditionResponseList(Page<AdditionResponse> responsePage) {
		super();
		this.responsePage = responsePage;
	}

	public List<AdditionResponse> getResponseList() {
		return responsePage.getContent();
	}

	public Page<AdditionResponse> getResponsePage() {
		return responsePage;
	}

	public void addResponseList(List<AdditionResponse> responseList) {
		this.responsePage = new PageImpl<AdditionResponse>(responseList);
	}

	public static AdditionResponseList from(
			Page<AdditionEntity> additionEntityList) {
		List<AdditionResponse> tempList = new LinkedList<AdditionResponse>();
		for (AdditionEntity additionEntity : additionEntityList) {
			tempList.add(AdditionResponse.from(additionEntity));
		}
		AdditionResponseList response = new AdditionResponseList();
		response.addResponseList(tempList);
		return response;
	}

}
