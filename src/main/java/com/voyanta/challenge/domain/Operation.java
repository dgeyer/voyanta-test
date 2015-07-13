package com.voyanta.challenge.domain;

abstract class Operation {
	protected final String id;
	protected final Calculable<Number> execution;
	protected final Elements<Number> elements;

	protected Operation(String id, Calculable<Number> execution,
			Elements<Number> elements) {
		super();
		this.id = id;
		this.execution = execution;
		this.elements = elements;
	}

	public abstract Number perform();

}
