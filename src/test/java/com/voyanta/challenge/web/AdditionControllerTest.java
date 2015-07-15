package com.voyanta.challenge.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.voyanta.challenge.VoyantaChallengeConfiguration;
import com.voyanta.challenge.dto.clienttocore.AdditionRequestList;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = VoyantaChallengeConfiguration.class)
@WebAppConfiguration
public class AdditionControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Value("${voyanta.max-operations}")
	private String maxOperations;
	@Value("${voyanta.max-operation-list-size}")
	private String maxOperationsListSize;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void moreOperationsThatMaxShouldThrowIllegalArgumentException()
			throws Exception {
		AdditionRequestList request = WebTestUtil
				.generateRandomDoubleAdditionRequestList(
						Integer.valueOf(maxOperationsListSize) + 1,
						Integer.valueOf(maxOperations), Long.valueOf(1));
		byte[] json = WebTestUtil.convertObjectToJsonBytes(request);

		MvcResult result = mockMvc
				.perform(
						post("/addition").contentType(
								WebTestUtil.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andReturn();

		Assert.assertEquals(
				"java.lang.IllegalArgumentException: this service max list size is "
						+ this.maxOperationsListSize
						+ " use /addition/async instead", result
						.getAsyncResult().toString());
	}

	@Test
	public void moreAddendsThatMaxShouldThrowIllegalArgumentException()
			throws Exception {
		AdditionRequestList request = WebTestUtil
				.generateRandomDoubleAdditionRequestList(
						Integer.valueOf(maxOperationsListSize),
						Integer.valueOf(maxOperations) + 1, Long.valueOf(2));
		byte[] json = WebTestUtil.convertObjectToJsonBytes(request);

		MvcResult result = mockMvc
				.perform(
						post("/addition").contentType(
								WebTestUtil.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andReturn();

		Assert.assertEquals(
				"java.lang.IllegalArgumentException: this service max addends per addition is "
						+ this.maxOperations + " use /addition/async instead",
				result.getAsyncResult().toString());
	}

	@Test
	public void shouldAddBigDecimal() throws Exception {
		AdditionRequestList request = WebTestUtil
				.generateMaxDoubleAdditionRequestList(5, 5, Long.valueOf(3));
		byte[] json = WebTestUtil.convertObjectToJsonBytes(request);

		MvcResult result = mockMvc
				.perform(
						post("/addition").contentType(
								WebTestUtil.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andReturn();

		Assert.assertEquals(
				"java.util.concurrent.ExecutionException: java.lang.IllegalArgumentException: additions of really big numbers are not supported yet!",
				result.getAsyncResult().toString());
	}

}
