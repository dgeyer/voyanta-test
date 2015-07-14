package com.voyanta.challenge.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdditionTest {

	@Mock
	private AdditionExecution additionExecution;
	@Mock
	private Elements<Number> addends;

	@Test
	public void shouldDelegatetoCalculable() {
		Addition addition = new Addition("shouldDelegatetoCalculableId",
				additionExecution, addends);
		addition.perform();
		Mockito.verify(additionExecution, Mockito.times(1)).calculate(addends);

	}

}
