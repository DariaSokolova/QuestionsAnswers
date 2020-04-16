package com.cgm.codingexercise.questionanswers.parser;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class QAParserTests
{
	private QAParser parser = new QAParser();

	@Test
	public void testParseInput_whenNoErrors()
	{
		final String input = "question?   \"answer1\"   \"answer2\"  ";

		final ParserResult result = parser.parseInput(input);

		assertTrue(result.getParserErrors().isEmpty());
		assertEquals("question", result.getQuestion());
		assertEquals(2, result.getAnswers().size());
		assertEquals("answer1", result.getAnswers().get(0));
		assertEquals("answer2", result.getAnswers().get(1));
	}

	@Test
	public void testParseInput_whenNoQuestionMark()
	{
		final String input = "question \"answer1\" \"answer2\"";

		final ParserResult result = parser.parseInput(input);

		assertFalse(result.getParserErrors().isEmpty());
		assertEquals("Input string should contain one ? symbol", result.getParserErrors().get(0));
	}

	@Test
	public void testParseInput_whenMultipleQuestionMark()
	{
		final String input = "question? \"answer1\" ? \"answer2\"";

		final ParserResult result = parser.parseInput(input);

		assertFalse(result.getParserErrors().isEmpty());
		assertEquals("Input string should contain one ? symbol", result.getParserErrors().get(0));
	}

	@Test
	public void testParseInput_whenQuestionIsTooLong()
	{
		final String input = format("%s? \"answer1\" \"answer2\"", createLongString(256));

		final ParserResult result = parser.parseInput(input);

		assertFalse(result.getParserErrors().isEmpty());
		assertEquals("Question length should be 255 symbols max", result.getParserErrors().get(0));
	}

	@Test
	public void testParseInput_whenAnswersAreTooLong()
	{
		final String longString = createLongString(256);
		final String input = format("question? \"%s\" \"%s\"", longString, longString);

		final ParserResult result = parser.parseInput(input);

		assertFalse(result.getParserErrors().isEmpty());
		assertEquals("Answer length should be 255 symbols max", result.getParserErrors().get(0));
		assertEquals("Answer length should be 255 symbols max", result.getParserErrors().get(1));
	}

	@Test
	public void testParseInput_whenNoAnswers()
	{
		final String input = "question?";

		final ParserResult result = parser.parseInput(input);

		assertFalse(result.getParserErrors().isEmpty());
		assertEquals("Question should have at least one answer", result.getParserErrors().get(0));
	}

	@Test
	public void testParseInput_whenEmptyQuestion()
	{
		final String input = " ? \"answer\"";

		final ParserResult result = parser.parseInput(input);

		assertFalse(result.getParserErrors().isEmpty());
		assertEquals("Question should not be empty", result.getParserErrors().get(0));
	}

	@Test
	public void testParseInput_whenAnswerWithoutQuotes()
	{
		final String input = "question? fhdhdh";

		final ParserResult result = parser.parseInput(input);

		assertFalse(result.getParserErrors().isEmpty());
		assertEquals("<fhdhdh> is not valid answers input", result.getParserErrors().get(0));
	}

	@Test
	public void testParseInput_whenTextOutsideQuotes()
	{
		final String input = "question? \"fh\"dhdh";

		final ParserResult result = parser.parseInput(input);

		assertFalse(result.getParserErrors().isEmpty());
		assertEquals("<\"fh\"dhdh> is not valid answers input", result.getParserErrors().get(0));
	}

	@Test
	public void testParseInput_whenTooManyQuotes()
	{
		final String input = "question? \"fh\"dh\"dh";

		final ParserResult result = parser.parseInput(input);

		assertFalse(result.getParserErrors().isEmpty());
		assertEquals("<\"fh\"dh\"dh> is not valid answers input", result.getParserErrors().get(0));
	}

	@Test
	public void testParseInput_whenEmptyAnswer()
	{
		final String input = "question? \"  \"";

		final ParserResult result = parser.parseInput(input);

		assertFalse(result.getParserErrors().isEmpty());
		assertEquals("Answer should not be empty", result.getParserErrors().get(0));
	}

	private String createLongString(final int length)
	{
		final StringBuilder value = new StringBuilder(length);
		for (int i = 0; i < length; i++){
			value.append("a");
		}
		return value.toString();
	}
}
