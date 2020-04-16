package com.cgm.codingexercise.questionanswers.processor;

import java.util.Scanner;

public class InputProcessor
{
	public String getUserInput()
	{
		final Scanner input = new Scanner(System.in);
		return input.nextLine();
	}
}
