package com.voyanta.challenge.service;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.voyanta.challenge.dao.AdditionEntityRepository;
import com.voyanta.challenge.dao.AdditionListEntityRepository;
import com.voyanta.challenge.dao.entity.AdditionEntity;
import com.voyanta.challenge.dao.entity.AdditionListEntity;
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
	private AdditionFacade additionFacade;
	@Autowired
	private AdditionUtil additionUtil;
	@Autowired
	private AdditionEntityRepository additionRepo;
	@Autowired
	private AdditionListEntityRepository additionListRepo;
	@Value("${voyanta.max-operations}")
	private String maxOperations;
	@Value("${voyanta.max-operation-list-size}")
	private String maxOperationsListSize;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public AdditionResponseList processSync(
			AdditionRequestList additionRequestList)
			throws InterruptedException, ExecutionException {
		validateSyncRequestSize(additionRequestList);
		AdditionResponseList response = new AdditionResponseList();
		List<AdditionResponse> tempList = new LinkedList<AdditionResponse>();
		List<Future<AdditionResponse>> results = processAsync(additionRequestList);

		for (Future<AdditionResponse> future : results) {
			tempList.add(future.get());
		}
		response.addResponseList(tempList);
		return response;
	}

	private void validateSyncRequestSize(AdditionRequestList additionRequestList) {
		if (additionRequestList.getAdditionList().size() > Integer
				.valueOf(maxOperationsListSize)) {
			throw new IllegalArgumentException("this service max list size is "
					+ maxOperationsListSize + " use /addition/async instead");
		}
		for (AdditionRequest request : additionRequestList.getAdditionList()) {
			if (request.getAddends().length > Integer.valueOf(maxOperations)) {
				throw new IllegalArgumentException(
						"this service max addends per addition is "
								+ maxOperations
								+ " use /addition/async instead");
			}
		}
	}

	@Override
	public List<Future<AdditionResponse>> processAsync(
			AdditionRequestList additionRequestList)
			throws InterruptedException {
		markAdditionResquestListStatus(additionRequestList,
				HttpStatus.PROCESSING.value());
		List<Future<AdditionResponse>> results = new LinkedList<Future<AdditionResponse>>();
		if (additionRequestList != null) {
			for (AdditionRequest additionRequest : additionRequestList
					.getAdditionList()) {
				additionRequest.setAdditionListId(additionRequestList.getId());
				results.add(additionServiceAsync.add(additionRequest));
			}
		}
		markAdditionResquestListStatus(additionRequestList,
				HttpStatus.SEE_OTHER.value());
		return results;
	}

	@Override
	public int findAdditionListStatus(Long additionListId) {
		AdditionListEntity additionListEntity = additionListRepo
				.findOne(additionListId);
		int httpStatus;
		if (additionListEntity == null) {
			httpStatus = HttpStatus.NOT_FOUND.value();
		} else {
			httpStatus = additionListEntity.getHttpStatus();
		}
		return httpStatus;
	}

	private void markAdditionResquestListStatus(
			AdditionRequestList additionRequestList, int httpStatusValue) {
		AdditionListEntity additionListEntity = new AdditionListEntity(
				additionRequestList.getId(), httpStatusValue);
		additionListRepo.save(additionListEntity);
	}

	@Override
	public AdditionResponse findOneAddition(String id) {
		validateIdIsNotNull(id);
		logger.debug("search additionEntity with id {}", id);
		AdditionEntity additionEntity = additionRepo.findOne(id);
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

	@Override
	public void deleteOneAddition(String id) {
		validateIdIsNotNull(id);
		additionRepo.delete(id);
	}

	@Override
	@CacheEvict(value = "AdditionEntityRepository.findOne", key = "#p0.getId()")
	public AdditionResponse updateOneAddition(AdditionRequest additionRequest) {
		AdditionEntity additionEntity = additionRepo.findOne(additionRequest
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

	@Override
	public Page<AdditionResponse> findAdditionsThatBelongToList(Long id,
			int page, int size) {
		AdditionListEntity additionListEntity = additionListRepo.findOne(id);

		if (additionListEntity == null) {
			throw new IllegalArgumentException(
					"The deferred addition operation does not exist");
		}

		Page<AdditionEntity> additionEntities = additionRepo
				.findByAdditionListEntity(additionListEntity, new PageRequest(
						page, size));
		return AdditionResponseList.from(additionEntities).getResponsePage();

	}
}
