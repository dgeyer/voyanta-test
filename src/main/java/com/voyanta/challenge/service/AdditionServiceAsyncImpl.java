package com.voyanta.challenge.service;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.voyanta.challenge.dao.AdditionEntityRepository;
import com.voyanta.challenge.dao.entity.AdditionEntity;
import com.voyanta.challenge.dto.clienttocore.AdditionRequest;
import com.voyanta.challenge.dto.coretoclient.AdditionResponse;

@Service
public class AdditionServiceAsyncImpl implements AdditionServiceAsync {

	@Autowired
	private AdditionEntityRepository repository;
	@Autowired
	private AdditionUtil additionUtil;

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Async
	public Future<AdditionResponse> add(AdditionRequest additionRequest)
			throws InterruptedException {
		logger.debug("Executing addition in thread {}", Thread.currentThread()
				.getName());
		logger.debug("findOne of additionEntity id {}", additionRequest.getId());
		AdditionResponse response = buildAdditionResponse(additionRequest);
		return new AsyncResult<AdditionResponse>(response);
	}

	private AdditionResponse buildAdditionResponse(
			AdditionRequest additionRequest) {
		AdditionEntity additionEntity = this.repository.findOne(additionRequest
				.getId());
		AdditionResponse response = buildAdditionResponse(additionRequest,
				additionEntity);
		return response;
	}

	private AdditionResponse buildAdditionResponse(
			AdditionRequest additionRequest, AdditionEntity additionEntity) {
		AdditionResponse response;
		if (additionEntity == null) {
			logger.debug("additionEntity is null");
			response = additionUtil.calculateAndSave(additionRequest);
		} else {
			logger.debug("additionEntity is not null", additionEntity.getId());
			response = AdditionResponse.from(additionEntity);
		}
		return response;
	}
}
