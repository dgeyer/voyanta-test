package com.voyanta.challenge.web;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
				return additionService.processSync(additionRequestList);
			}
		};
	}

	@RequestMapping(method = RequestMethod.POST, value = "/async", consumes = "application/json", produces = "application/json")
	public void acceptAddition(
			@Valid @RequestBody AdditionRequestList additionRequestList,
			HttpServletResponse response) throws InterruptedException,
			ExecutionException {
		additionService.processAsync(additionRequestList);
		response.setHeader(HttpHeaders.LOCATION, "addition/queue/"
				+ additionRequestList.getId());
		response.setStatus(HttpStatus.ACCEPTED.value());

	}

	@RequestMapping(method = RequestMethod.GET, value = "/async/queue/{id}")
	public void readStatus(@PathVariable("id") Long id,
			HttpServletResponse response) throws InterruptedException,
			ExecutionException {
		int httpStatus = additionService.findAdditionListStatus(id);

		response.setStatus(httpStatus);
		response.setHeader(HttpHeaders.LOCATION, "addition/async/" + id);

	}

	@RequestMapping(method = RequestMethod.GET, value = "async/{id}")
	public @ResponseBody Page<AdditionResponse> searchAdditionList(
			@PathVariable("id") Long id, @RequestParam int page,
			@RequestParam int size) {

		return additionService.findAdditionsThatBelongToList(id, page, size);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public @ResponseBody AdditionResponse search(@PathVariable("id") String id) {
		return additionService.findOneAddition(id);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void delete(@PathVariable("id") String id) {
		additionService.deleteOneAddition(id);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public @ResponseBody AdditionResponse update(
			@Valid @RequestBody AdditionRequest additionRequest) {
		return additionService.updateOneAddition(additionRequest);
	}
}
