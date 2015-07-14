package com.voyanta.challenge.service;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.voyanta.challenge.VoyantaChallengeConfiguration;
import com.voyanta.challenge.dao.AdditionEntityRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = VoyantaChallengeConfiguration.class)
public class AdditionServiceAsyncTest {

	@InjectMocks
	private AdditionServiceAsyncImpl additionAsynService;
	@Mock
	private AdditionEntityRepository repository;
	@Mock
	private AdditionUtil additionUtil;

	// @Test
	// public void testDoSomethingAsynchronouslyUsingDoAnswer() {
	// final List<String> results = Arrays.asList("One", "Two", "Three");
	// // Let's do a synchronous answer for the callback
	// doAnswer(new Answer() {
	// @Override
	// public Object answer(InvocationOnMock invocation) throws Throwable {
	// ((DummyCallback) invocation.getArguments()[0])
	// .onSuccess(results);
	// return null;
	// }
	// }).when(mockDummyCollaborator).doSomethingAsynchronously(
	// any(DummyCallback.class));
	//
	// // Let's call the method under test
	// dummyCaller.doSomethingAsynchronously();
	//
	// // Verify state and interaction
	// verify(mockDummyCollaborator, times(1)).doSomethingAsynchronously(
	// any(DummyCallback.class));
	// assertThat(dummyCaller.getResult(), is(equalTo(results)));
	// }

}
