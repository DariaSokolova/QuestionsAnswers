package com.cgm.codingexercise.questionanswers.strategy;

import java.util.List;

import com.cgm.codingexercise.questionanswers.processor.InputProcessor;
import com.cgm.codingexercise.questionanswers.processor.MessageProcessor;
import com.cgm.codingexercise.questionanswers.storage.DataStorage;

public class QuestionAskerStrategy implements QAStrategy
{
	private MessageProcessor messageProcessor;
	private DataStorage dataStorage;
	private InputProcessor inputProcessor;

	public QuestionAskerStrategy(final MessageProcessor messageProcessor, final DataStorage dataStorage, final InputProcessor inputProcessor)
	{
		this.messageProcessor = messageProcessor;
		this.dataStorage = dataStorage;
		this.inputProcessor = inputProcessor;
	}

	@Override
	public void run()
	{
		messageProcessor.printInfoMessage("Please enter exact question");
		final String question = inputProcessor.getUserInput();
		final List<String> answers = dataStorage.findAnswers(question);
		if (answers != null)
		{
			answers.forEach(messageProcessor::printInfoMessage);
		}
		else
		{
			messageProcessor.printInfoMessage("the answer to life, universe and everything is 42");
		}
	}
}
