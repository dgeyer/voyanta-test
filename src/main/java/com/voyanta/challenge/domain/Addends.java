package com.voyanta.challenge.domain;


class Addends extends Elements<Number> {

	private final Number[] addends;

	public Addends(Number[] addends) {
		this.addends = addends;
	}

	@Override
	public Number[] getElements() {
		return this.addends;
	}

}
