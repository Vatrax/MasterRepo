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

	private List<String> combinations;
	private String end;

	public Combinator() {
		combinations = new ArrayList<>();
	}

	public List<String> getCombinations(List<String> cities) {
		String begin = cities.get(0);
		end = cities.get(cities.size() - 1);
		cities.remove(0);
		cities.remove(cities.size() - 1);

		createPermutations(cities, begin);
		createCombinations(cities, begin);

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
