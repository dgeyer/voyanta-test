package com.voyanta.challenge.domain;


final class Addition extends Operation {

	public Addition(String id, Calculable<Number> execution,
			Elements<Number> elements) {
		super(id, execution, elements);
	}

	@Override
	public Number perform() {
		return this.execution.calculate(elements);
	}

}
