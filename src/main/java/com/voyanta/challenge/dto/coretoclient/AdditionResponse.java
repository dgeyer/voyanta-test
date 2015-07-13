package com.voyanta.challenge.dto.coretoclient;

import com.voyanta.challenge.dao.entity.AdditionEntity;

public class AdditionResponse {

	private final String id;
	private final Number sum;

	public AdditionResponse(String id, Number sum) {
		super();
		this.id = id;
		this.sum = sum;
	}

	public String getId() {
		return id;
	}

	public Number getSum() {
		return sum;
	}

	public static AdditionResponse from(AdditionEntity additionEntity) {
		return new AdditionResponse(additionEntity.getId(),
				additionEntity.getResult());
	}

}
