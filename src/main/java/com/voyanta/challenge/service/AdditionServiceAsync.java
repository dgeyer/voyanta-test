package com.voyanta.challenge.service;

import java.util.concurrent.Future;

import com.voyanta.challenge.dto.clienttocore.AdditionRequest;
import com.voyanta.challenge.dto.coretoclient.AdditionResponse;

public interface AdditionServiceAsync {

	public Future<AdditionResponse> add(AdditionRequest additionRequest)
			throws InterruptedException;

}