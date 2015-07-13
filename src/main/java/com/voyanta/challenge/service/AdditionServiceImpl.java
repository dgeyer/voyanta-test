package com.voyanta.challenge.service;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.voyanta.challenge.dao.AdditionEntityRepository;
import com.voyanta.challenge.dao.entity.AdditionEntity;
import com.voyanta.challenge.domain.AdditionFacade;
import com.voyanta.challenge.dto.clienttocore.AdditionRequest;
import com.voyanta.challenge.dto.clienttocore.AdditionRequestList;
import com.voyanta.challenge.dto.coretoclient.AdditionResponse;
import com.voyanta.challenge.dto.coretoclient.AdditionResponseList;

@Service
public class AdditionServiceImpl implements AdditionService {

	@Autowired
	private AdditionServiceAsync additionServiceAsync;
	@Autowired
	private AdditionEntityRepository repository;
	@Autowired
	AdditionFacade additionFacade;
	@Autowired
	AdditionUtil additionUtil;

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public AdditionResponseList add(AdditionRequestList additionRequestList)
			throws InterruptedException, ExecutionException {
		List<Future<AdditionResponse>> results = new LinkedList<Future<AdditionResponse>>();
		AdditionResponseList response = new AdditionResponseList();

		if (additionRequestList != null) {
			for (AdditionRequest additionRequest : additionRequestList
					.getAdditionList()) {
				results.add(additionServiceAsync.add(additionRequest));
			}
		}

		for (Future<AdditionResponse> future : results) {
			response.add(future.get());
		}

		return response;
	}

	public AdditionResponse search(String id) {
		validateIdIsNotNull(id);
		logger.debug("search additionEntity with id {}", id);
		AdditionEntity additionEntity = repository.findOne(id);
		if (additionEntity != null) {
			return AdditionResponse.from(additionEntity);
		} else {
			throw new IllegalArgumentException("Addition id does not exist");
		}
	}

	private void validateIdIsNotNull(String id) {
		if (id == null) {
			throw new IllegalArgumentException("Addition id cannot be null");
		}
	}

	public void delete(String id) {
		validateIdIsNotNull(id);
		repository.delete(id);
	}

	@CacheEvict(value = "AdditionEntityRepository.findOne", key = "#p0.getId()")
	public AdditionResponse update(AdditionRequest additionRequest) {
		AdditionEntity additionEntity = repository.findOne(additionRequest
				.getId());
		AdditionResponse additionResponse;
		if (additionEntity != null) {
			additionResponse = additionUtil.calculateAndSave(additionRequest);
		} else {
			throw new IllegalArgumentException(
					"Addition request does not exist");
		}

		return additionResponse;
	}

}
