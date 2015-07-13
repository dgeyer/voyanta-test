package com.voyanta.challenge.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

final class AdditionExecution implements Calculable<Number> {

	@Override
	public Number calculate(Elements<Number> elements) {
		BigDecimal result = BigDecimal.ZERO;
		// TODO: improve this
		for (Number addend : elements.getElements()) {
			result = add(result, addend);
		}
		return result;
	}

	private BigDecimal add(BigDecimal result, Number addend) {
		if (addend instanceof Integer) {
			result = result.add(BigDecimal.valueOf(addend.intValue()));
		} else if (addend instanceof Double) {
			result = result.add(BigDecimal.valueOf(addend.doubleValue()));
		} else if (addend instanceof Short) {
			result = result.add(BigDecimal.valueOf(addend.shortValue()));
		} else if (addend instanceof Long) {
			result = result.add(BigDecimal.valueOf(addend.longValue()));
		} else if (addend instanceof Float) {
			result = result.add(BigDecimal.valueOf(addend.floatValue()));
		} else if (addend instanceof BigInteger) {
			result = result.add(new BigDecimal((BigInteger) addend));
		} else if (addend instanceof BigDecimal) {
			result = result.add((BigDecimal) addend);
		} else if (addend instanceof Byte) {
			result = result.add(BigDecimal.valueOf(addend.intValue()));
		} else {
			throw new IllegalArgumentException(
					"Addition not implemented for Number value");
		}
		return result;
	}
}
