package com.voyanta.challenge.domain;

interface Calculable<T> {

	public T calculate(Elements<T> elements);

}
