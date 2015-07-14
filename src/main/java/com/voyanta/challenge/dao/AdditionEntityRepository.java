package com.voyanta.challenge.dao;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.voyanta.challenge.dao.entity.AdditionEntity;
import com.voyanta.challenge.dao.entity.AdditionListEntity;

@Repository
public interface AdditionEntityRepository extends
		PagingAndSortingRepository<AdditionEntity, String> {

	@Cacheable(value = "AdditionEntityRepository.findOne", key = "#p0")
	@Override
	public AdditionEntity findOne(String id);

	@CacheEvict(value = "AdditionEntityRepository.findOne", key = "#p0")
	@Override
	public void delete(String id);

	@Query("SELECT a FROM AdditionEntity a WHERE a.additionListEntity=?")
	Page<AdditionEntity> findByAdditionListEntity(
			AdditionListEntity additionListEntity, Pageable pageable);

}
