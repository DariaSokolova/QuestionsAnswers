package com.cgm.codingexercise.questionanswers.strategy;

import com.cgm.codingexercise.questionanswers.parser.ParserResult;
import com.cgm.codingexercise.questionanswers.parser.QAParser;
import com.cgm.codingexercise.questionanswers.processor.InputProcessor;
import com.cgm.codingexercise.questionanswers.processor.MessageProcessor;
import com.cgm.codingexercise.questionanswers.storage.DataStorage;

public class QuestionAnswersSaverStrategy implements QAStrategy
{
	private QAParser qaParser;
	private MessageProcessor messageProcessor;
	private DataStorage dataStorage;
	private InputProcessor inputProcessor;

	public QuestionAnswersSaverStrategy(final MessageProcessor messageProcessor, final QAParser qaParser, final DataStorage dataStorage, final InputProcessor inputProcessor)
	{
		this.messageProcessor = messageProcessor;
		this.qaParser = qaParser;
		this.dataStorage = dataStorage;
		this.inputProcessor = inputProcessor;
	}

	@Override
	public void run()
	{
		messageProcessor.printInfoMessage(
				"Please input question with answers as per pattern: "
						+ "<question>? \"<answer1>\" \"<answer2>\" \"<answerX>\"");
		final String inputString = inputProcessor.getUserInput();
		final ParserResult parserResult = qaParser.parseInput(inputString);
		if (parserResult.getParserErrors().isEmpty())
		{
			dataStorage.saveQuestionWithAnswers(parserResult.getQuestion(), parserResult.getAnswers());
			messageProcessor.printInfoMessage("The question with answers was added into the database");
		}
		else
		{
			parserResult.getParserErrors().forEach(messageProcessor::printErrorMessage);
		}
	}
}
