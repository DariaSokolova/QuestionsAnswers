package com.cgm.codingexercise.questionanswers.parser;

import java.util.ArrayList;
import java.util.List;

public class ParserResult
{
	private List<String> parserErrors = new ArrayList<>();
	private String question;
	private List<String> answers = new ArrayList<>();

	public List<String> getParserErrors()
	{
		return parserErrors;
	}

	public void setParserErrors(List<String> parserErrors)
	{
		this.parserErrors = parserErrors;
	}

	public String getQuestion()
	{
		return question;
	}

	public void setQuestion(String question)
	{
		this.question = question;
	}

	public List<String> getAnswers()
	{
		return answers;
	}

	public void setAnswers(List<String> answers)
	{
		this.answers = answers;
	}
}
