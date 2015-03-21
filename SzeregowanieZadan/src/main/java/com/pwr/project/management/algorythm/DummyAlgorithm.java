package com.pwr.project.management.algorythm;

import com.pwr.project.management.algorythm.Algorithm;
import com.pwr.project.management.model.Project;
import com.pwr.project.management.model.Team;

import java.util.Date;
import java.util.List;

/**
 * Created by krzaczek on 21.03.15.
 */
public class DummyAlgorithm implements Algorithm {
	@Override public void serialize(List<Project> projects, List<Team> teams) {

	}

	@Override public long getTime() {
		return 20;
	}

	@Override public Date getCMax() {
		return new Date();
	}

	@Override public double getProfit() {
		return 10;
	}
}
