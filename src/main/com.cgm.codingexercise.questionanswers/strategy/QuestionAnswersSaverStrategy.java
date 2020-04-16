package strategy;

import parser.ParserResult;
import parser.QAParser;
import processor.MessageProcessor;
import storage.DataStorage;

public class QuestionAnswersSaverStrategy implements QAStrategy
{
	private QAParser qaParser;
	private MessageProcessor messageProcessor;
	private DataStorage dataStorage;

	public QuestionAnswersSaverStrategy(final MessageProcessor messageProcessor, final QAParser qaParser, final DataStorage dataStorage)
	{
		this.messageProcessor = messageProcessor;
		this.qaParser = qaParser;
		this.dataStorage = dataStorage;
	}

	@Override
	public void run(final String inputString)
	{
		messageProcessor.printInfoMessage(
				"Please input question with answers as per pattern: "
						+ "<question>? “<answer1>” “<answer2>” “<answerX>”");
		final ParserResult parserResult = qaParser.parseInput(inputString);
		if (parserResult.getParserErrors().isEmpty())
		{
			dataStorage.saveQuestionWithAnswers(parserResult.getQuestion(), parserResult.getAnswers());
		}
		else
		{
			parserResult.getParserErrors().forEach(messageProcessor::printErrorMessage);
		}
	}
}
