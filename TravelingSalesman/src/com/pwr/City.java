package com.pwr;

public class City {

    private String name;
    private int XCoordinate;
    private int YCoordinate;

    public City(String name, int XCoordinate, int YCoordinate) {
        this.name = name;
        this.XCoordinate = XCoordinate;
        this.YCoordinate = YCoordinate;
    }

    public String getName() {
        return name;
    }

    public int getXCoordinate() {
        return XCoordinate;
    }

    public int getYCoordinate() {
        return YCoordinate;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getCoordinates() {
        return XCoordinate + "," + YCoordinate;
    }

    public double getDistance(City city) {
        return Math.sqrt(Math.pow(this.XCoordinate - city.getXCoordinate(),2) + Math.pow(this.YCoordinate - city.getYCoordinate(),2));
    }
}
