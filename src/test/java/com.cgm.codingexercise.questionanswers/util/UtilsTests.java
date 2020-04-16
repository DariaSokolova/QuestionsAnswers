package com.cgm.codingexercise.questionanswers.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UtilsTests
{
	@Test
	public void testGetIntegerValue_whenValueIsValidInteger()
	{
		final String value = "3";

		final int result = Utils.getIntegerValue(value);

		assertEquals(3, result);
	}

	@Test
	public void testGetIntegerValue_whenValueIsInvalidInteger()
	{
		final String value = "3sdg";

		final int result = Utils.getIntegerValue(value);

		assertEquals(-1, result);
	}

	@Test
	public void testGetIntegerValue_whenValueIsNull()
	{
		final String value = null;

		final int result = Utils.getIntegerValue(value);

		assertEquals(-1, result);
	}

	@Test
	public void testGetIntegerValue_whenValueIsEmpty()
	{
		final String value = "";

		final int result = Utils.getIntegerValue(value);

		assertEquals(-1, result);
	}
}
