/*
 * Copyright (c) 2014 Nokia Solutions and Networks. All rights reserved.
 */
package com.pwr;

import java.util.List;
import java.util.Scanner;

/**
 * @author: Wojciech Krzaczek (wojciech.krzaczek@nsn.com)
 */
public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		System.out.println("Give me number of Cities:");
		int numberOfCities = new Scanner(System.in).nextInt();
		List<String> generateCities = new DataGenerator().generateCities(numberOfCities);

	}
}
