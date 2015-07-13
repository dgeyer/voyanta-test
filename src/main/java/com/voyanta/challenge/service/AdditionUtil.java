package com.voyanta.challenge.service;

import com.voyanta.challenge.dto.clienttocore.AdditionRequest;
import com.voyanta.challenge.dto.coretoclient.AdditionResponse;

public interface AdditionUtil {

	public AdditionResponse calculateAndSave(AdditionRequest additionRequest);
}