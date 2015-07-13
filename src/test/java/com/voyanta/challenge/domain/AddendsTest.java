package com.voyanta.challenge.domain;

import org.junit.Assert;
import org.junit.Test;

public class AddendsTest {

	@Test
	public void createShortAddends() {
		Short[] addendsArray = { Short.MIN_VALUE, Short.MIN_VALUE,
				Short.MIN_VALUE };
		Elements<Number> addends = createAddends(addendsArray);
		Assert.assertTrue(addendsArray == addends.getElements());
	}

	@Test
	public void createIntegerAddends() {
		Integer[] addendsArray = { Integer.MIN_VALUE, Integer.MIN_VALUE,
				Integer.MIN_VALUE };
		Elements<Number> addends = createAddends(addendsArray);
		Assert.assertTrue(addendsArray == addends.getElements());
	}

	@Test
	public void createLongAddends() {
		Long[] addendsArray = { Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE };
		Elements<Number> addends = createAddends(addendsArray);
		Assert.assertTrue(addendsArray == addends.getElements());
	}

	@Test
	public void createDoubleAddends() {
		Double[] addendsArray = { Double.MIN_VALUE, Double.MIN_VALUE,
				Double.MIN_VALUE };
		Elements<Number> addends = createAddends(addendsArray);
		Assert.assertTrue(addendsArray == addends.getElements());

		Assert.assertTrue(addends.getElements()[0].equals(Double.MIN_VALUE));
	}

	@Test
	public void createFloatAddends() {
		Float[] addendsArray = { Float.MIN_VALUE, Float.MIN_VALUE,
				Float.MIN_VALUE };
		Elements<Number> addends = createAddends(addendsArray);
		Assert.assertTrue(addendsArray == addends.getElements());

		Assert.assertTrue(addends.getElements()[0].equals(Float.MIN_VALUE));
	}

	private Elements<Number> createAddends(Number[] addendsArray) {
		Elements<Number> addends = new Addends(addendsArray);
		return addends;
	}

}
