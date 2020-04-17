package com.cgm.codingexercise.questionanswers.parser;

import java.util.ArrayList;
import java.util.List;

public class ParserResult
{
	private List<String> parserErrors;
	private String question;
	private List<String> answers;

	public List<String> getParserErrors()
	{
		if (parserErrors == null)
		{
			parserErrors = new ArrayList<>();
		}
		return parserErrors;
	}

	public void setParserErrors(final List<String> parserErrors)
	{
		this.parserErrors = parserErrors;
	}

	public String getQuestion()
	{
		return question;
	}

	public void setQuestion(final String question)
	{
		this.question = question;
	}

	public List<String> getAnswers()
	{
		if (answers == null)
		{
			answers = new ArrayList<>();
		}
		return answers;
	}

	public void setAnswers(final List<String> answers)
	{
		this.answers = answers;
	}
}
