package com.voyanta.challenge.service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.data.domain.Page;

import com.voyanta.challenge.dto.clienttocore.AdditionRequest;
import com.voyanta.challenge.dto.clienttocore.AdditionRequestList;
import com.voyanta.challenge.dto.coretoclient.AdditionResponse;
import com.voyanta.challenge.dto.coretoclient.AdditionResponseList;

public interface AdditionService {

	public AdditionResponseList processSync(
			AdditionRequestList additionRequestList)
			throws InterruptedException, ExecutionException;

	public List<Future<AdditionResponse>> processAsync(
			AdditionRequestList additionRequestList)
			throws InterruptedException;

	public AdditionResponse findOneAddition(String id);

	public void deleteOneAddition(String id);

	public AdditionResponse updateOneAddition(AdditionRequest additionRequest);

	public int findAdditionListStatus(Long additionListId);

	public Page<AdditionResponse> findAdditionsThatBelongToList(Long id,
			int page, int size);

}