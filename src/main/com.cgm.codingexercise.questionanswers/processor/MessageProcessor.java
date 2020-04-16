package processor;

import java.util.stream.Stream;

public class MessageProcessor
{
	public void printErrorMessage(final String message)
	{
		System.err.println(message);
	}

	public void printInfoMessage(final String message)
	{
		System.out.println(message);
	}

	public void printInfoMessages(final String... messages)
	{
		Stream.of(messages).forEach(System.out::println);
	}
}
