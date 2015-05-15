package com.pwr.project.management.algorythm.analyser;

import com.pwr.project.management.algorythm.Algorithm;
import com.pwr.project.management.model.AssignedTask;
import com.pwr.project.management.model.Project;
import com.pwr.project.management.model.Team;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by krzaczek on 22.03.15.
 */
public class AlgorithmAnalyser {

	private long time;
	private Date endDate;
	private int profit;

	public void runWithAnalyse(Algorithm algorithm, List<Project> projects, List<Team> teams) {
		if (algorithm != null) {
			Date start = new Date();
			algorithm.serialize(projects, teams);
			Date end = new Date();
			time = end.getTime() - start.getTime();
			endDate = calculateEndTime(teams);
			profit = new ResultAnalyser().calculateProfit(teams, projects);
		}
	}

	private Date calculateEndTime(List<Team> teams) {
		Date endDate = new Date();
		for (Team team : teams) {
			Date teamFinishDate = getTeamFinishDate(team);
			endDate = endDate.before(teamFinishDate) ? teamFinishDate : endDate;
		}
		return addMonth(endDate);
	}

	private Date addMonth(Date endDate) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(endDate);
		instance.add(Calendar.MONTH, 1);
		return instance.getTime();
	}

	private Date getTeamFinishDate(Team team) {
		Date endDate = new Date();
		for (AssignedTask assignedTask : team.getTasks()) {
			endDate = endDate.after(assignedTask.getEnd()) ? endDate : assignedTask.getEnd();
		}
		return endDate;
	}

	public long getTime() {
		return time;
	}

	public Date getEndDate() {
		return endDate;
	}

	public int getProfit() {
		return profit;
	}
}
