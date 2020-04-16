package util;

public class Utils
{
	public static int getIntegerValue(final String value)
	{
		int result;
		try
		{
			result = Integer.parseInt(value);
		}
		catch (final NumberFormatException e)
		{
			result = -1;
		}
		return result;
	}
}
