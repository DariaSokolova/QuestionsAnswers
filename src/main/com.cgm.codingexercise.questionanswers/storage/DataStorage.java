package storage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStorage
{
	private final Map<String, List<String>> data = new HashMap<>();

	public void clearStorage()
	{
		data.clear();
	}

	public List<String> findAnswers(final String question)
	{
		final String queryQuestion = question.replace("?", "");
		return data.get(queryQuestion);
	}

	public void saveQuestionWithAnswers(final String question, final List<String> answers)
	{
		data.put(question, answers);
	}
}
