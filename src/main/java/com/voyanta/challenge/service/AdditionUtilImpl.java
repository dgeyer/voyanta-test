package com.voyanta.challenge.service;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.voyanta.challenge.dao.AdditionEntityRepository;
import com.voyanta.challenge.dao.AdditionListEntityRepository;
import com.voyanta.challenge.dao.entity.AdditionEntity;
import com.voyanta.challenge.dao.entity.AdditionListEntity;
import com.voyanta.challenge.domain.AdditionFacade;
import com.voyanta.challenge.dto.clienttocore.AdditionRequest;
import com.voyanta.challenge.dto.coretoclient.AdditionResponse;

@Service
public class AdditionUtilImpl implements AdditionUtil {

	@Autowired
	private AdditionEntityRepository additionEntityRepo;
	@Autowired
	private AdditionListEntityRepository additionListEntityRepo;
	@Autowired
	private AdditionFacade additionFacade;

	final static Logger logger = LoggerFactory
			.getLogger(AdditionUtilImpl.class);

	@CacheEvict(value = "AdditionEntityRepository.findOne", key = "#p0.getId()")
	public AdditionResponse calculateAndSave(AdditionRequest additionRequest) {
		AdditionResponse response = additionFacade.add(additionRequest.getId(),
				additionRequest.getAddends());
		AdditionEntity additionEntity = buildAdditionEntity(additionRequest,
				response);
		additionEntity = this.additionEntityRepo.save(additionEntity);
		logger.debug("saved addition entity with id {}", additionEntity.getId());
		return response;

	}

	private AdditionEntity buildAdditionEntity(AdditionRequest additionRequest,
			AdditionResponse response) {
		AdditionListEntity additionListEntity = additionListEntityRepo
				.findOne(additionRequest.getAdditionListId());
		AdditionEntity additionEntity;
		if (additionListEntity == null) {
			additionEntity = new AdditionEntity(response.getId(),
					(BigDecimal) response.getSum());
		} else {
			additionEntity = new AdditionEntity(response.getId(),
					(BigDecimal) response.getSum(), additionListEntity);
		}
		return additionEntity;
	}

}
