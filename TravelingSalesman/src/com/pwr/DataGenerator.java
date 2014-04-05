/*
 * Copyright (c) 2014 Nokia Solutions and Networks. All rights reserved.
 */
package com.pwr;

import java.util.*;

/**
 * @author: Wojciech Krzaczek (wojciech.krzaczek@nsn.com)
 */
public class DataGenerator {

    private int numberOfCities;

    public DataGenerator(int numberOfCities) {
        this.numberOfCities = numberOfCities;
    }

    public List<String> generateCityNames() {

        List<String> cityNames = new ArrayList<>();
		List<City> cities = prepareCities();

        for (int i = 0; i < numberOfCities; i++) {
			cityNames.add(cities.get(i).getName());
		}

		return cityNames;
	}

    public List<City> prepareCities() {
        // Zakładamy, że miasta leżą na płaszczyźnie [0,200] x [0,200]
        List<City> cities = new LinkedList<>();

        cities.add(new City("A", 25, 100));
        cities.add(new City("B", 87, 22));
        cities.add(new City("C", 150, 65));
        cities.add(new City("D", 190, 194));
        cities.add(new City("E", 72, 80));
        cities.add(new City("F", 51, 180));
        cities.add(new City("G", 170, 100));
        cities.add(new City("H", 41, 120));
        cities.add(new City("I", 65, 200));
        cities.add(new City("J", 10, 168));

        return cities;
    }

    public void displayDistanceMatrix() {
        List<City> cities = prepareCities();

        for (int i = 0; i < numberOfCities; i++) {
            for (int j = 0; j < numberOfCities; j++) {
                System.out.printf("%5.1f\t", cities.get(i).getDistance(cities.get(j)));
            }

            System.out.println();
        }
    }

    public void displayCoordinates() {
        List<City> cities = prepareCities();

        for (int i = 0; i < numberOfCities; i++) {
            System.out.println(cities.get(i).getCoordinates());
        }
    }

}
