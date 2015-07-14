package com.voyanta.challenge.service;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.voyanta.challenge.VoyantaChallengeConfiguration;
import com.voyanta.challenge.dao.AdditionEntityRepository;
import com.voyanta.challenge.dao.entity.AdditionEntity;
import com.voyanta.challenge.domain.AdditionFacade;
import com.voyanta.challenge.dto.clienttocore.AdditionRequest;
import com.voyanta.challenge.dto.coretoclient.AdditionResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = VoyantaChallengeConfiguration.class)
public class AditionUtilTest {

	@InjectMocks
	private AdditionUtilImpl additionUtil;
	@Mock
	private AdditionEntityRepository repository;
	@Mock
	private AdditionFacade additionFacade;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void calcuateAndSaveshouldUseAdditionfacadeAndSave() {
		Integer[] addendsArray = {};
		AdditionRequest additionRequest = new AdditionRequest(
				"testAdditionUtil", addendsArray, Long.valueOf(1));
		AdditionResponse response = new AdditionResponse(
				additionRequest.getId(), BigDecimal.ZERO);
		AdditionEntity entity = new AdditionEntity(response.getId(),
				(BigDecimal) response.getSum());

		Mockito.when(
				additionFacade.add(additionRequest.getId(),
						additionRequest.getAddends())).thenReturn(response);

		Mockito.when(repository.save(Mockito.any(AdditionEntity.class)))
				.thenReturn(entity);

		response = additionUtil.calculateAndSave(additionRequest);
		Mockito.verify(additionFacade, Mockito.times(1)).add(
				additionRequest.getId(), additionRequest.getAddends());

		Mockito.verify(repository, Mockito.times(1)).save(
				Mockito.any(AdditionEntity.class));
	}
}
