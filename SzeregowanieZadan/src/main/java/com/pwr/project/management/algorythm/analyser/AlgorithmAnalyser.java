package com.pwr.project.management.algorythm.analyser;

import com.pwr.project.management.algorythm.Algorithm;
import com.pwr.project.management.model.AssignedTask;
import com.pwr.project.management.model.Project;
import com.pwr.project.management.model.Team;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by krzaczek on 22.03.15.
 */
public class AlgorithmAnalyser {

	private long time;
	private Date endDate;
	private double profit;

	public void runWithAnalyse(Algorithm algorithm, List<Project> projects, List<Team> teams) {
		Date start = new Date();
		algorithm.serialize(projects,teams);
		Date end = new Date();
		time = end.getTime() - start.getTime();
		endDate = calculateEndTime(teams);
	}

	private Date calculateEndTime(List<Team> teams) {
		Date endDate = new Date();
		for (Team team : teams) {
			for (AssignedTask assignedTask : team.getTasks()) {
				endDate = endDate.after(assignedTask.getEnd()) ?  endDate : assignedTask.getEnd();
			}
		}
		return addMonth(endDate);
	}

	private Date addMonth(Date endDate) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(endDate);
		instance.add(Calendar.MONTH, 1);
		return instance.getTime();
	}

	public long getTime() {
		return time;
	}

	public Date getEndDate() {
		return endDate;
	}

	public double getProfit() {
		return profit;
	}
}
