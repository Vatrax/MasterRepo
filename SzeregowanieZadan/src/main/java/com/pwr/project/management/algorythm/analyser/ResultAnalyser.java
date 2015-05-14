package com.pwr.project.management.algorythm.analyser;

import com.pwr.project.management.model.AssignedTask;
import com.pwr.project.management.model.Project;
import com.pwr.project.management.model.Team;
import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.Date;
import java.util.List;

/**
 * Created by krzaczek on 13.05.15.
 */
public class ResultAnalyser {

	public int calculateProfit(List<Team> teams, List<Project> projects) {
		int profit = calculateRowProjectProfit(projects);
		profit -= calculateCostOfTeams(teams);
		profit += calculateBonus(teams, projects);
		return profit;
	}

	private int calculateBonus(List<Team> teams, List<Project> projects) {
		int profit = 0;
		for (Project project : projects) {
			DateTime end = new DateTime(getProjectEndDate(teams, project));
			DateTime expectedEnd = new DateTime(project.getExpectedEndDate());
			int bonus = expectedEnd.isBefore(end) ? -project.getPunishment() : project.getBonus();
			profit += Days.daysBetween(end, expectedEnd).getDays() * bonus;
		}
		return profit;
	}

	private Date getProjectEndDate(List<Team> teams, Project project) {
		//TODO optimize implementation for using Map<Project,List<Task>>
		Date endDate = new Date(Long.MAX_VALUE);
		for (Team team : teams) {
			for (AssignedTask assignedTask : team.getTasks()) {
				if (assignedTask.getProjectName().equals(project.getName())) {
					Date taskEnd = assignedTask.getEnd();
					endDate = endDate.before(taskEnd) ? endDate : taskEnd;
				}
			}
		}
		return endDate;
	}

	private int calculateCostOfTeams(List<Team> teams) {
		int cost = 0;
		for (Team team : teams) {
			int days = calculateNumberOfDays(team);
			cost += days * team.getCost();
		}
		return cost;
	}

	private int calculateNumberOfDays(Team team) {
		Date teamStartDate = getTeamStartDate(team);
		Date teamFinishDate = getTeamFinishDate(team);
		if (teamStartDate == null || teamFinishDate == null) {
			return 0;
		}
		DateTime startDate = new DateTime(teamStartDate);
		DateTime endDate = new DateTime(teamFinishDate);
		return Days.daysBetween(startDate, endDate).getDays();
	}

	private Date getTeamStartDate(Team team) {
		List<AssignedTask> tasks = team.getTasks();
		if (tasks.isEmpty()) {
			return null;
		}
		Date startDate = tasks.get(0).getStart();
		for (AssignedTask assignedTask : tasks) {
			startDate = startDate.before(assignedTask.getStart()) ? startDate : assignedTask.getStart();
		}
		return startDate;
	}

	private int calculateRowProjectProfit(List<Project> projects) {
		int profit = 0;
		for (Project project : projects) {
			profit += project.getPrice();
		}
		return profit;
	}

	private Date getTeamFinishDate(Team team) {
		Date endDate = new Date();
		for (AssignedTask assignedTask : team.getTasks()) {
			endDate = endDate.after(assignedTask.getEnd()) ? endDate : assignedTask.getEnd();
		}
		return endDate;
	}

}
