package com.voyanta.challenge.domain;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.Test;

public class AdditionExecutionTest {

	@Test
	public void shouldReturnZeroIfAddendsArrayIsEmpty() {
		Integer[] addendsArray = {};
		Elements<Number> addends = new Addends(addendsArray);
		Calculable<Number> additionExecution = new AdditionExecution();
		Assert.assertEquals(BigDecimal.ZERO,
				additionExecution.calculate(addends));
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentIfAndAddendIsAtomicInteger() {
		AtomicInteger[] addendsArray = { new AtomicInteger(5),
				new AtomicInteger(7), new AtomicInteger(9) };
		Elements<Number> addends = new Addends(addendsArray);
		Calculable<Number> additionExecution = new AdditionExecution();
		additionExecution.calculate(addends);
	}

	@Test
	public void shouldSumShortAddends() {
		Short[] addendsArray = { Short.MIN_VALUE, Short.MIN_VALUE,
				Short.MIN_VALUE };
		Elements<Number> addends = new Addends(addendsArray);
		Calculable<Number> additionExecution = new AdditionExecution();
		Assert.assertEquals(BigDecimal.valueOf(Short.MIN_VALUE * 3),
				additionExecution.calculate(addends));
	}

	@Test
	public void shouldAddBigValues() {
		BigDecimal[] addendsArray = { BigDecimal.valueOf(Double.MAX_VALUE),
				BigDecimal.valueOf(Double.MAX_VALUE),
				BigDecimal.valueOf(Double.MAX_VALUE) };
		Elements<Number> addends = new Addends(addendsArray);
		Calculable<Number> additionExecution = new AdditionExecution();
		Assert.assertEquals(
				BigDecimal.valueOf(Double.MAX_VALUE)
						.add(BigDecimal.valueOf(Double.MAX_VALUE))
						.add(BigDecimal.valueOf(Double.MAX_VALUE))
						.toPlainString(), additionExecution.calculate(addends)
						.toString());
	}

	@Test
	public void ShouldAddDoubles() {
		Double[] addendsArray = { 3.3D, 4.3D, 2.2D };
		Elements<Number> addends = new Addends(addendsArray);
		Calculable<Number> additionExecution = new AdditionExecution();
		Assert.assertEquals(BigDecimal.valueOf(9.8D),
				additionExecution.calculate(addends));
	}

}
