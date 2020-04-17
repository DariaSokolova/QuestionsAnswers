package com.cgm.codingexercise.questionanswers.parser;

import static java.lang.String.format;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QAParser
{
	private static final Pattern answerPattern = Pattern.compile("(\\s*\"\\w+\"\\s*)+?");

	public ParserResult parseInput(final String input)
	{
		final ParserResult result = new ParserResult();
		final String[] questionAndAnswers = input.split("\\?");
		if (questionAndAnswers.length == 2)
		{
			final String question = questionAndAnswers[0].trim();
			result.setQuestion(question);
			rejectTooLong(result, "Question", question);
			rejectEmpty(result, "Question", question);
			parseAnswers(result, questionAndAnswers[1]);
		}
		else if (questionAndAnswers.length == 1 && input.contains("?"))
		{
			result.getParserErrors().add("Question should have at least one answer");
		}
		else
		{
			result.getParserErrors().add("Input string should contain one ? symbol");
		}
		return result;
	}

	private void parseAnswers(final ParserResult result, final String value)
	{
		final String answersString = value.trim();
		final Matcher matcher = answerPattern.matcher(answersString);
		if (matcher.matches())
		{
			matcher.reset();
			while (matcher.find())
			{
				final String answer = matcher.group().trim();
				rejectTooLong(result, "Answer", answer);
				result.getAnswers().add(answer);
			}
		}
		else
		{
			result.getParserErrors().add(format("<%s> is not valid answers input", answersString));
		}
	}

	private void rejectEmpty(final ParserResult result, final String fieldName, final String value)
	{
		if (value.length() == 0)
		{
			result.getParserErrors().add(format("%s should not be empty", fieldName));
		}
	}

	private void rejectTooLong(final ParserResult result, final String fieldName, final String value)
	{
		if (value.length() > 255)
		{
			result.getParserErrors().add(format("%s length should be 255 symbols max", fieldName));
		}
	}
}
