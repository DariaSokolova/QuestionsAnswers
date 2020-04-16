package strategy;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import processor.MessageProcessor;
import storage.DataStorage;

public class QuestionAskerStrategyTests
{
	private QuestionAskerStrategy strategy;

	@Mock
	private MessageProcessor messageProcessor;

	@Mock
	private DataStorage dataStorage;

	@Before
	public void before()
	{
		MockitoAnnotations.initMocks(this);

		strategy = new QuestionAskerStrategy(messageProcessor, dataStorage);

		dataStorage.clearStorage();
	}

	@Test
	public void testRun_WhenNoSuchQuestionExists()
	{
		final String question = "test";
		when(dataStorage.findAnswers(question)).thenReturn(null);

		strategy.run(question);

		verify(messageProcessor).printInfoMessage("Please enter exact question");
		verify(messageProcessor).printInfoMessage("the answer to life, universe and everything is 42");
	}

	@Test
	public void testRun_WhenSuchQuestionExists()
	{
		final List<String> answers = Arrays.asList("answer1", "answer2");
		final String question = "test";
		when(dataStorage.findAnswers(question)).thenReturn(answers);

		strategy.run(question);

		verify(messageProcessor).printInfoMessage("Please enter exact question");
		verify(messageProcessor).printInfoMessage("answer1");
		verify(messageProcessor).printInfoMessage("answer2");
	}
}
