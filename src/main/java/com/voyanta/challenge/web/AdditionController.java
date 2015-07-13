package com.voyanta.challenge.web;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.voyanta.challenge.dto.clienttocore.AdditionRequest;
import com.voyanta.challenge.dto.clienttocore.AdditionRequestList;
import com.voyanta.challenge.dto.coretoclient.AdditionResponse;
import com.voyanta.challenge.dto.coretoclient.AdditionResponseList;
import com.voyanta.challenge.service.AdditionService;

@RestController
@RequestMapping("/addition")
public class AdditionController {

	@Autowired
	private AdditionService additionService;

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody Callable<AdditionResponseList> add(
			@Valid @RequestBody AdditionRequestList additionRequestList)
			throws InterruptedException, ExecutionException {

		return new Callable<AdditionResponseList>() {
			public AdditionResponseList call() throws Exception {
				return additionService.add(additionRequestList);
			}
		};
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public @ResponseBody AdditionResponse search(@PathVariable("id") String id) {
		return additionService.search(id);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void delete(@PathVariable("id") String id) {
		additionService.delete(id);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public @ResponseBody AdditionResponse update(
			@Valid @RequestBody AdditionRequest additionRequest) {
		return additionService.update(additionRequest);
	}
}
