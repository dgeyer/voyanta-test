package com.voyanta.challenge.domain;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.voyanta.challenge.VoyantaChallengeConfiguration;
import com.voyanta.challenge.dto.coretoclient.AdditionResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = VoyantaChallengeConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class AdditionFacadeTest {

	@Autowired
	AdditionFacade additionFacade;

	@Test
	public void shouldReturnCompleteAdditionResponse() {
		Integer[] addendsArray = {};
		AdditionResponse additionResponse = additionFacade.add(
				"passEmptyArray", addendsArray);
		Assert.assertNotNull(additionResponse);
		Assert.assertNotNull(additionResponse.getId());
		Assert.assertEquals(BigDecimal.ZERO, additionResponse.getSum());
	}

	@Test(expected = IllegalArgumentException.class)
	public void passIllegalArgument() {
		AtomicInteger[] addendsArray = { new AtomicInteger(5),
				new AtomicInteger(7), new AtomicInteger(9) };
		additionFacade.add("passIllegalArgument", addendsArray);
	}

	@Test
	public void addShortNumbers() {
		Short[] addendsArray = { Short.MIN_VALUE, Short.MIN_VALUE,
				Short.MIN_VALUE };
		AdditionResponse additionResponse = additionFacade.add(
				"testAddSortNumbers", addendsArray);
		Assert.assertEquals(BigDecimal.valueOf(Short.MIN_VALUE * 3),
				additionResponse.getSum());
	}

	@Test
	public void addDoublesWithOverflow() {
		Double[] addendsArray = { Double.MAX_VALUE, Double.MAX_VALUE,
				Double.MAX_VALUE };
		AdditionResponse additionResponse = additionFacade.add(
				"testAddDoubleNumbers", addendsArray);
		Assert.assertEquals(
				BigDecimal.valueOf(Double.MAX_VALUE)
						.add(BigDecimal.valueOf(Double.MAX_VALUE))
						.add(BigDecimal.valueOf(Double.MAX_VALUE))
						.toPlainString(), additionResponse.getSum().toString());
	}

	@Test
	public void addDoubles() {
		Double[] addendsArray = { 3.3D, 4.3D, 2.2D };
		AdditionResponse additionResponse = additionFacade.add(
				"testAddDoubleNumbers", addendsArray);
		Assert.assertEquals(BigDecimal.valueOf(9.8D), additionResponse.getSum());
	}

}
