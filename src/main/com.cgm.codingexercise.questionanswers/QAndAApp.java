import parser.QAParser;
import processor.InputProcessor;
import processor.MessageProcessor;
import processor.QuestionAnswersProcessor;
import storage.DataStorage;
import strategy.QuestionAnswersSaverStrategy;
import strategy.QuestionAskerStrategy;

public class QAndAApp
{
	public static void main(final String[] args)
	{
		final DataStorage dataStorage = new DataStorage();
		final QAParser qaParser = new QAParser();
		final MessageProcessor messageProcessor = new MessageProcessor();
		final QuestionAnswersSaverStrategy questionAnswersSaverStrategy = new QuestionAnswersSaverStrategy(messageProcessor, qaParser, dataStorage);
		final QuestionAskerStrategy questionAskerStrategy = new QuestionAskerStrategy(messageProcessor, dataStorage);
		final InputProcessor inputProcessor = new InputProcessor();
		final QuestionAnswersProcessor processor =
				new QuestionAnswersProcessor(messageProcessor, questionAnswersSaverStrategy, questionAskerStrategy, inputProcessor);
		processor.process();
	}
}
