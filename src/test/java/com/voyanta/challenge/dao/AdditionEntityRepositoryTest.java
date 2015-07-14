package com.voyanta.challenge.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.voyanta.challenge.VoyantaChallengeConfiguration;
import com.voyanta.challenge.dao.entity.AdditionEntity;
import com.voyanta.challenge.dao.entity.AdditionListEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = VoyantaChallengeConfiguration.class)
public class AdditionEntityRepositoryTest {

	@Autowired
	public AdditionEntityRepository repo;

	@Autowired
	public AdditionListEntityRepository listRepo;

	@Test
	public void shouldFindByAdditionListEntityReturnPaginatedResults() {
		AdditionListEntity list = listRepo.findOne(Long.valueOf(1));
		Page<AdditionEntity> result = repo.findByAdditionListEntity(list,
				new PageRequest(1, 20));
	}

}
