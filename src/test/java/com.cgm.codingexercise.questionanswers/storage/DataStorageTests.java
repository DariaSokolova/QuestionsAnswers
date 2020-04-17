package com.cgm.codingexercise.questionanswers.storage;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;

public class DataStorageTests
{
	private DataStorage dataStorage;

	@Test
	public void testFindAndSaveAnswers()
	{
		dataStorage = new DataStorage();

		dataStorage.saveQuestionWithAnswers("question", asList("answer"));

		final List<String> answers = dataStorage.findAnswers("question");

		assertEquals(1, answers.size());
		assertEquals("answer", answers.get(0));
	}

	@Test
	public void testFindAnswers_whenAnswersAskedWithQuestionMark()
	{
		dataStorage = new DataStorage();

		dataStorage.saveQuestionWithAnswers("question", asList("answer"));

		final List<String> answers = dataStorage.findAnswers("question?");

		assertEquals(1, answers.size());
		assertEquals("answer", answers.get(0));
	}

	@Test
	public void testFindAnswers_whenAnswersDoNotExist()
	{
		dataStorage = new DataStorage();

		final List<String> answers = dataStorage.findAnswers("question");

		assertNull(answers);
	}

	@Test
	public void testClearStorage()
	{
		dataStorage = new DataStorage();

		dataStorage.saveQuestionWithAnswers("question", asList("answer"));
		final List<String> answers = dataStorage.findAnswers("question");
		assertEquals(1, answers.size());

		dataStorage.clearStorage();

		final List<String> answersAfterClearing = dataStorage.findAnswers("question");
		assertNull(answersAfterClearing);
	}
}
