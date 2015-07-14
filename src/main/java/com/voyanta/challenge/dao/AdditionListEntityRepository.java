package com.voyanta.challenge.dao;

import org.springframework.data.repository.CrudRepository;

import com.voyanta.challenge.dao.entity.AdditionListEntity;

public interface AdditionListEntityRepository extends
		CrudRepository<AdditionListEntity, Long> {

}
