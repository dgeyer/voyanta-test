package com.voyanta.challenge.domain;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.Test;

public class AdditionTest {

	@Test
	public void testAdditionClient() {
		Number[] addends = { 1, 2, 3 };
		Number result = initAndPerform("testAdditionClient", addends);

		Assert.assertEquals(BigDecimal.valueOf(6),
				BigDecimal.valueOf(result.intValue()));
	}

	@Test
	public void testEmptyAddends() {
		Number[] addends = {};
		Number result = initAndPerform("testEmptyAddends", addends);

		Assert.assertEquals(BigDecimal.ZERO,
				BigDecimal.valueOf(result.intValue()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalAddends() {
		Number[] addends = { new AtomicInteger(1), new AtomicInteger(2),
				new AtomicInteger(3) };
		initAndPerform("testIllegalAddends", addends);
	}

	private Number initAndPerform(String id, Number[] addends) {
		Calculable<Number> additionCalculable = new AdditionExecution();
		Elements<Number> elements = new Addends(addends);
		Operation addition = new Addition(id, additionCalculable, elements);

		Number result = addition.perform();
		return result;
	}

}
