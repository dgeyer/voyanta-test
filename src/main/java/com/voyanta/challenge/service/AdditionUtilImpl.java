package com.voyanta.challenge.service;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voyanta.challenge.dao.AdditionEntityRepository;
import com.voyanta.challenge.dao.entity.AdditionEntity;
import com.voyanta.challenge.domain.AdditionFacade;
import com.voyanta.challenge.dto.clienttocore.AdditionRequest;
import com.voyanta.challenge.dto.coretoclient.AdditionResponse;

@Service
public class AdditionUtilImpl implements AdditionUtil {

	@Autowired
	private AdditionEntityRepository repository;
	@Autowired
	AdditionFacade additionFacade;

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	public AdditionResponse calculateAndSave(AdditionRequest additionRequest) {
		AdditionResponse response = additionFacade.add(additionRequest.getId(),
				additionRequest.getAddends());
		AdditionEntity additionEntity = new AdditionEntity(response.getId(),
				(BigDecimal) response.getSum());
		additionEntity = this.repository.save(additionEntity);
		logger.debug("saved addition entity with id {}", additionEntity.getId());
		return response;

	}

}
