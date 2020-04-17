package com.cgm.codingexercise.questionanswers.strategy;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cgm.codingexercise.questionanswers.parser.ParserResult;
import com.cgm.codingexercise.questionanswers.parser.QAParser;
import com.cgm.codingexercise.questionanswers.processor.InputProcessor;
import com.cgm.codingexercise.questionanswers.processor.MessageProcessor;
import com.cgm.codingexercise.questionanswers.storage.DataStorage;

public class QuestionAnswersSaverStrategyTests
{
	private QuestionAnswersSaverStrategy strategy;

	@Mock
	private MessageProcessor messageProcessor;

	@Mock
	private DataStorage dataStorage;

	@Mock
	private QAParser qaParser;

	@Mock
	private InputProcessor inputProcessor;

	@Before
	public void before()
	{
		MockitoAnnotations.initMocks(this);

		strategy = new QuestionAnswersSaverStrategy(messageProcessor, qaParser, dataStorage, inputProcessor);
	}

	@Test
	public void testRun_WhenNoParserErrors()
	{
		final String inputString = "test question? \"answer\"";
		final ParserResult result = new ParserResult();
		result.setQuestion("test question?");
		result.getAnswers().add("answer");
		when(qaParser.parseInput(inputString)).thenReturn(result);
		when(inputProcessor.getUserInput()).thenReturn(inputString);

		strategy.run();

		verify(messageProcessor).printInfoMessage("Please input question with answers as per pattern: "
				+ "<question>? \"<answer1>\" \"<answer2>\" \"<answerX>\"");
		verify(messageProcessor).printInfoMessage("The question with answers was added into the database");

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
		when(inputProcessor.getUserInput()).thenReturn(inputString);

		strategy.run();

		verify(messageProcessor).printInfoMessage("Please input question with answers as per pattern: "
				+ "<question>? \"<answer1>\" \"<answer2>\" \"<answerX>\"");

		verify(dataStorage, never()).saveQuestionWithAnswers(result.getQuestion(), result.getAnswers());

		verify(messageProcessor).printErrorMessage("Error 1");
		verify(messageProcessor).printErrorMessage("Error 2");
	}
}
