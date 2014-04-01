/*
 * Copyright (c) 2014 Nokia Solutions and Networks. All rights reserved.
 */
package com.pwr;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Wojciech Krzaczek (wojciech.krzaczek@nsn.com)
 */
public class DataGenerator {

	public List<String> generateCities(int numberOfCities) {
		List<String> cities = new ArrayList<>();
		for (int i = 0; i < numberOfCities; i++) {
			cities.add("" + i);
		}
		return cities;
	}

}
