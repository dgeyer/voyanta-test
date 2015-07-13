package com.voyanta.challenge.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.voyanta.challenge.VoyantaChallengeConfiguration;
import com.voyanta.challenge.dto.clienttocore.AdditionRequest;
import com.voyanta.challenge.dto.clienttocore.AdditionRequestList;
import com.voyanta.challenge.dto.coretoclient.AdditionResponseList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = VoyantaChallengeConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class AdditionServiceTest {

	private static final int MAX_ADDENDS_IN_ONE_REQUEST = 1000;
	private static final int MAX_REQUEST = 1000000;
	@Autowired
	AdditionService additionService;

	@Test
	public void testAddVoidRequest() throws Exception {

		AdditionRequestList request = Mockito.mock(AdditionRequestList.class);
		AdditionResponseList response = additionService.add(request);

		Assert.assertNotNull(response);
		Assert.assertTrue(response.getResponseList().size() == 0);
	}

	@Test
	public void testAddNullRequest() throws Exception {
		AdditionResponseList response = additionService.add(null);
		Assert.assertTrue(response.getResponseList().size() == 0);
	}

	@Test
	public void testAdd() throws Exception {

		AdditionRequestList requestList = new AdditionRequestList();

		for (int i = 0; i <= MAX_REQUEST; i++) {
			AdditionRequest request = new AdditionRequest();
			Random random = new Random();
			int size = random.nextInt(MAX_ADDENDS_IN_ONE_REQUEST);
			List<Double> addendsList = new LinkedList<Double>();
			for (int j = 0; j < size; j++) {
				addendsList.add(random.nextDouble());
			}
			request.setId(String.valueOf(i));
			request.setAddends(addendsList.toArray(new Number[size]));
			requestList.add(request);
		}

		AdditionResponseList response = additionService.add(requestList);
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getResponseList().size() > 0);
	}

}
