package strategy;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import parser.ParserResult;
import parser.QAParser;
import processor.MessageProcessor;
import storage.DataStorage;

public class QuestionAnswersSaverStrategyTests
{
	private QuestionAnswersSaverStrategy strategy;

	@Mock
	private MessageProcessor messageProcessor;

	@Mock
	private DataStorage dataStorage;

	@Mock
	private QAParser qaParser;

	@Before
	public void before()
	{
		MockitoAnnotations.initMocks(this);

		strategy = new QuestionAnswersSaverStrategy(messageProcessor, qaParser, dataStorage);
	}

	@Test
	public void testRun_WhenNoParserErrors()
	{
		final String inputString = "test question? \"answer\"";
		final ParserResult result = new ParserResult();
		result.setQuestion("test question?");
		result.getAnswers().add("answer");
		when(qaParser.parseInput(inputString)).thenReturn(result);

		strategy.run(inputString);

		verify(messageProcessor).printInfoMessage("Please input question with answers as per pattern: "
				+ "<question>? “<answer1>” “<answer2>” “<answerX>”");

		verify(dataStorage).saveQuestionWithAnswers(result.getQuestion(), result.getAnswers());

		assertEquals(result.getParserErrors().size(), 0);
	}

	@Test
	public void testRun_WhenParserErrors()
	{
		final String inputString = "test question? \"answer\"";
		final ParserResult result = new ParserResult();
		result.getParserErrors().add("Error 1");
		result.getParserErrors().add("Error 2");
		when(qaParser.parseInput(inputString)).thenReturn(result);

		strategy.run(inputString);

		verify(messageProcessor).printInfoMessage("Please input question with answers as per pattern: "
				+ "<question>? “<answer1>” “<answer2>” “<answerX>”");

		verify(dataStorage, never()).saveQuestionWithAnswers(result.getQuestion(), result.getAnswers());

		verify(messageProcessor).printErrorMessage("Error 1");
		verify(messageProcessor).printErrorMessage("Error 2");
	}
}