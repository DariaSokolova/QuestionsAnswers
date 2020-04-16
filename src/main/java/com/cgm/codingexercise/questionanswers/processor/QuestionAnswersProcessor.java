package com.cgm.codingexercise.questionanswers.processor;

import com.cgm.codingexercise.questionanswers.strategy.QAStrategy;
import com.cgm.codingexercise.questionanswers.strategy.QuestionAnswersSaverStrategy;
import com.cgm.codingexercise.questionanswers.strategy.QuestionAskerStrategy;
import com.cgm.codingexercise.questionanswers.util.Utils;

public class QuestionAnswersProcessor
{
	private MessageProcessor messageProcessor;
	private QuestionAnswersSaverStrategy questionAnswersSaverStrategy;
	private QuestionAskerStrategy questionAskerStrategy;
	private InputProcessor inputProcessor;

	public QuestionAnswersProcessor(final MessageProcessor messageProcessor,
			final QuestionAnswersSaverStrategy questionAnswersSaverStrategy,
			final QuestionAskerStrategy questionAskerStrategy,
			final InputProcessor inputProcessor)
	{
		this.messageProcessor = messageProcessor;
		this.questionAnswersSaverStrategy = questionAnswersSaverStrategy;
		this.questionAskerStrategy = questionAskerStrategy;
		this.inputProcessor = inputProcessor;
	}

	public void process()
	{
		printOptions();

		final int exitOption = 3;
		int userOption = readIntegerValue();
		while (userOption != exitOption)
		{
			final QAStrategy strategy = getStrategy(userOption);
			if (strategy != null)
			{
				strategy.run(inputProcessor.getUserInput());
			}
			else
			{
				messageProcessor.printErrorMessage("Option is invalid. Please enter a correct option");
			}
			printOptions();
			userOption = readIntegerValue();
		}
	}

	private QAStrategy getStrategy(final int option)
	{
		QAStrategy strategy = null;
		switch (option)
		{
			case 1:
				strategy = questionAnswersSaverStrategy;
				break;
			case 2:
				strategy = questionAskerStrategy;
				break;
		}
		return strategy;
	}

	private int readIntegerValue()
	{
		return Utils.getIntegerValue(inputProcessor.getUserInput());
	}

	private void printOptions()
	{
		messageProcessor.printInfoMessages(
				"Please select one of the options below:",
				"1. Add a question with answers into the database",
				"2. Ask a question",
				"3. Exit");
	}
}
