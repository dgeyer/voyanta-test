package com.voyanta.challenge.service;

import java.util.concurrent.ExecutionException;

import com.voyanta.challenge.dto.clienttocore.AdditionRequest;
import com.voyanta.challenge.dto.clienttocore.AdditionRequestList;
import com.voyanta.challenge.dto.coretoclient.AdditionResponse;
import com.voyanta.challenge.dto.coretoclient.AdditionResponseList;

public interface AdditionService {

	public AdditionResponseList add(AdditionRequestList additionRequestList)
			throws InterruptedException, ExecutionException;

	public AdditionResponse search(String id);

	public void delete(String id);

	public AdditionResponse update(AdditionRequest additionRequest);

}