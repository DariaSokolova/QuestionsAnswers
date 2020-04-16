package strategy;

import java.util.List;

import processor.MessageProcessor;
import storage.DataStorage;

public class QuestionAskerStrategy implements QAStrategy
{
	private MessageProcessor messageProcessor;
	private DataStorage dataStorage;

	public QuestionAskerStrategy(final MessageProcessor messageProcessor, final DataStorage dataStorage)
	{
		this.messageProcessor = messageProcessor;
		this.dataStorage = dataStorage;
	}

	@Override
	public void run(final String question)
	{
		messageProcessor.printInfoMessage("Please enter exact question");
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
