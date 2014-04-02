/*
 * Copyright (c) 2014 Nokia Solutions and Networks. All rights reserved.
 */
package com.pwr;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Wojciech Krzaczek (wojciech.krzaczek@nsn.com)
 */
public class Combinator {

    private List<String> cities;
	private List<String> combinations;
	private String start, end;

	public Combinator(List<String> generatedCities) {
		cities = new ArrayList<>(generatedCities);
        combinations = new ArrayList<>();
	}

    public void prepareData() {
        // Save start and end points
        start = cities.get(0);
        end = cities.get(cities.size() - 1);

        // Remove them from array
        cities.remove(0);
        cities.remove(cities.size() - 1);
    }

	public List<String> getCombinations() {
		createPermutations(cities, start);
		createCombinations(cities, start);

		return combinations;
	}

	private void createCombinations(List<String> cities, String begin) {
		for (String city : cities) {
			List<String> citiesLeft = new ArrayList<>(cities);
			citiesLeft.remove(city);
			createPermutations(citiesLeft, begin);
			createCombinations(citiesLeft, begin);
		}

	}

	private void createPermutations(List<String> cities, String begin) {
		if (cities.isEmpty()) {
			if (!combinations.contains(begin + end)) {
				combinations.add(begin + end);
			}
		} else {
			for (String city : cities) {
				List<String> citiesLeft = new ArrayList<>(cities);
				citiesLeft.remove(city);
				createPermutations(citiesLeft, begin + city);
			}
		}

	}
}
