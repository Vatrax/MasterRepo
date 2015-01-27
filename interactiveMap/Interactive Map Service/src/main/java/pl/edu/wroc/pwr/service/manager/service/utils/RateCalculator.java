package pl.edu.wroc.pwr.service.manager.service.utils;

import java.util.List;

/**
 * Created by krzaczek on 26.10.14.
 */
public class RateCalculator {

	public Double avg(List<Integer> rateValues) {

		if (rateValues == null || rateValues.size() == 0) {
			return 0.0;
		}

		Double sum = 0.0;

		for (Integer rate : rateValues) {
			sum += rate;
		}
		return sum / rateValues.size();
	}
}
