package com.voyanta.challenge.dao;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.voyanta.challenge.dao.entity.AdditionEntity;

@Repository
public interface AdditionEntityRepository extends
		CrudRepository<AdditionEntity, String> {

	@Cacheable(value = "AdditionEntityRepository.findOne", key = "#p0")
	@Override
	public AdditionEntity findOne(String id);

	@CacheEvict(value = "AdditionEntityRepository.findOne", key = "#p0")
	@Override
	public void delete(String id);

}
