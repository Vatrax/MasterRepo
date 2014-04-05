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
    private static DataGenerator dataGenerator;
    private static long startTime, endTime;

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		System.out.println("Give me number of Cities:");
        int numberOfCities = new Scanner(System.in).nextInt();

        dataGenerator = new DataGenerator(numberOfCities);

		List<String> generatedCities = dataGenerator.generateCityNames();

        combinator = new Combinator(generatedCities);
        combinator.prepareData();

        startTime = System.nanoTime();
		List<String> combinations = combinator.getCombinations();
        endTime = System.nanoTime();

        System.out.println("COMBINATIONS:");

//        for (String combination : combinations) {
//			System.out.println("\t"+ combination);
//		}

        System.out.println("Elapsed time: " + (endTime - startTime)/1000000 + " ms");
	}
}
