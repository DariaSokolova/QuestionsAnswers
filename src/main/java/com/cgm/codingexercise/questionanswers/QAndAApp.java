package com.cgm.codingexercise.questionanswers;

import com.cgm.codingexercise.questionanswers.parser.QAParser;
import com.cgm.codingexercise.questionanswers.processor.InputProcessor;
import com.cgm.codingexercise.questionanswers.processor.MessageProcessor;
import com.cgm.codingexercise.questionanswers.processor.QuestionAnswersProcessor;
import com.cgm.codingexercise.questionanswers.storage.DataStorage;
import com.cgm.codingexercise.questionanswers.strategy.QuestionAnswersSaverStrategy;
import com.cgm.codingexercise.questionanswers.strategy.QuestionAskerStrategy;

public class QAndAApp
{
	public static void main(final String[] args)
	{
		final DataStorage dataStorage = new DataStorage();
		final QAParser qaParser = new QAParser();
		final MessageProcessor messageProcessor = new MessageProcessor();
		final InputProcessor inputProcessor = new InputProcessor();
		final QuestionAnswersSaverStrategy questionAnswersSaverStrategy = new QuestionAnswersSaverStrategy(messageProcessor, qaParser, dataStorage, inputProcessor);
		final QuestionAskerStrategy questionAskerStrategy = new QuestionAskerStrategy(messageProcessor, dataStorage, inputProcessor);
		final QuestionAnswersProcessor processor =
				new QuestionAnswersProcessor(messageProcessor, questionAnswersSaverStrategy, questionAskerStrategy, inputProcessor);
		processor.process();
	}
}
