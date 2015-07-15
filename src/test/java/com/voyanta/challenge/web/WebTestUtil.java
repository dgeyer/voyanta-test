package com.voyanta.challenge.web;

import java.io.IOException;
import java.util.Random;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voyanta.challenge.dto.clienttocore.AdditionRequest;
import com.voyanta.challenge.dto.clienttocore.AdditionRequestList;

public class WebTestUtil {

	public static final MediaType APPLICATION_JSON = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype());

	public static byte[] convertObjectToJsonBytes(Object object)
			throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}

	static AdditionRequestList generateRandomDoubleAdditionRequestList(
			int listSize, int maxAddendsSize, Long listId) {
		AdditionRequestList additionRequestList = new AdditionRequestList();
		additionRequestList.setId(listId);
		Random random = new Random();
		int addendsSize = random.nextInt(maxAddendsSize) + 1;
		for (int i = 0; i < listSize; i++) {
			Number[] addends = new Double[addendsSize];
			for (int j = 0; j < addendsSize; j++) {
				addends[j] = Double.valueOf(random.nextDouble());
			}
			AdditionRequest request = new AdditionRequest(String.valueOf(i),
					addends);
			additionRequestList.add(request);
		}
		return additionRequestList;
	}

	static AdditionRequestList generateMaxDoubleAdditionRequestList(
			int listSize, int maxAddendsSize, Long listId) {
		AdditionRequestList additionRequestList = new AdditionRequestList();
		additionRequestList.setId(listId);
		Random random = new Random();
		int addendsSize = random.nextInt(maxAddendsSize) + 1;
		for (int i = 0; i < listSize; i++) {
			Number[] addends = new Double[addendsSize];
			for (int j = 0; j < addendsSize; j++) {
				addends[j] = Double.MAX_VALUE;
			}
			AdditionRequest request = new AdditionRequest(String.valueOf(i),
					addends);
			additionRequestList.add(request);
		}
		return additionRequestList;
	}
}
