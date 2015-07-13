package com.voyanta.challenge.domain;

import org.springframework.stereotype.Component;

import com.voyanta.challenge.dto.coretoclient.AdditionResponse;

@Component
public class AdditionFacade {

	public AdditionResponse add(String id, Number[] addends) {
		Calculable<Number> additionCalculable = new AdditionExecution();
		Elements<Number> elements = new Addends(addends);
		Addition addition = new Addition(id, additionCalculable, elements);
		AdditionResponse additionResponse = new AdditionResponse(id,
				addition.perform());
		return additionResponse;
	}
}
