package processor;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import strategy.QuestionAnswersSaverStrategy;
import strategy.QuestionAskerStrategy;

public class QuestionAnswersProcessorTests
{
	private QuestionAnswersProcessor processor;

	@Mock
	private MessageProcessor messageProcessor;

	@Mock
	private QuestionAnswersSaverStrategy questionAnswersSaverStrategy;

	@Mock
	private QuestionAskerStrategy questionAskerStrategy;

	@Mock
	private InputProcessor inputProcessor;

	@Before
	public void before()
	{
		MockitoAnnotations.initMocks(this);

		processor = new QuestionAnswersProcessor(messageProcessor, questionAnswersSaverStrategy, questionAskerStrategy, inputProcessor);
	}

	@Test
	public void testProcess_whenQuestionSaverStrategy()
	{
		when(inputProcessor.getUserInput())
				.thenReturn("1")
				.thenReturn("input")
				.thenReturn("3");

		processor.process();

		verify(questionAnswersSaverStrategy).run("input");

		verify(messageProcessor, times(2))
				.printInfoMessages(
						"Please select one of the options below:",
						"1. Add a question with answers into the database",
						"2. Ask a question",
						"3. Exit");
	}


	@Test
	public void testProcess_whenQuestionAskerStrategy()
	{
		when(inputProcessor.getUserInput())
				.thenReturn("2")
				.thenReturn("input")
				.thenReturn("3");

		processor.process();

		verify(questionAskerStrategy).run("input");

		verify(messageProcessor, times(2))
				.printInfoMessages(
						"Please select one of the options below:",
						"1. Add a question with answers into the database",
						"2. Ask a question",
						"3. Exit");
	}

	@Test
	public void testProcess_whenEmptyValue()
	{
		when(inputProcessor.getUserInput()).thenReturn("").thenReturn("3");

		processor.process();

		verify(messageProcessor, times(2))
				.printInfoMessages(
						"Please select one of the options below:",
						"1. Add a question with answers into the database",
						"2. Ask a question",
						"3. Exit");
		verify(messageProcessor).printErrorMessage("Option is invalid. Please enter a correct option");
	}

	@Test
	public void testProcess_whenNonValidValue()
	{
		when(inputProcessor.getUserInput()).thenReturn("sdfsdf").thenReturn("3");

		processor.process();

		verify(messageProcessor, times(2))
				.printInfoMessages(
						"Please select one of the options below:",
						"1. Add a question with answers into the database",
						"2. Ask a question",
						"3. Exit");
		verify(messageProcessor).printErrorMessage("Option is invalid. Please enter a correct option");
	}

	@Test
	public void testProcess_whenNonValidOption()
	{
		when(inputProcessor.getUserInput()).thenReturn("5").thenReturn("3");

		processor.process();

		verify(messageProcessor, times(2))
				.printInfoMessages(
				"Please select one of the options below:",
				"1. Add a question with answers into the database",
				"2. Ask a question",
				"3. Exit");
		verify(messageProcessor).printErrorMessage("Option is invalid. Please enter a correct option");
	}

	@Test
	public void testProcess_whenExit()
	{
		when(inputProcessor.getUserInput()).thenReturn("3");

		processor.process();

		verify(messageProcessor).printInfoMessages(
				"Please select one of the options below:",
				"1. Add a question with answers into the database",
				"2. Ask a question",
				"3. Exit");
	}

}
