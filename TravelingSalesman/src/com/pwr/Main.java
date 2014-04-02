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

    private static Combinator combinator;
    private static long startTime, endTime;

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		System.out.println("Give me number of Cities:");

        int numberOfCities = new Scanner(System.in).nextInt();
		List<String> generatedCities = new DataGenerator().generateCities(numberOfCities);

        combinator = new Combinator(generatedCities);
        combinator.prepareData();

        startTime = System.nanoTime();
		List<String> combinations = combinator.getCombinations();
        endTime = System.nanoTime();

		for (String combination : combinations) {
			System.out.println(combination);
		}

        System.out.println("Elapsed time: " + (endTime - startTime)/1000 + " ms");

	}
}
