package com.pwr.project.management.algorythm;

import com.pwr.project.management.exceptions.CannotCalculateException;
import com.pwr.project.management.model.AssignedTask;
import com.pwr.project.management.model.Project;
import com.pwr.project.management.model.Task;
import com.pwr.project.management.model.Team;

import java.util.*;

/**
 * Created by krzaczek on 22.01.15.
 */
public class BasicFlowAlgorithm implements Algorithm {

	private long time;
	private Date cMax;

	@Override
	public void serialize(List<Project> projects, List<Team> teams) {
		Date startTime = new Date();
		clearTeamsTasks(teams);
		for (Project project : projects) {
			Collections.sort(project.getTasks());
			Date prevTaskEnds = new Date();
			for (Task task : project.getTasks()) {
				Team bestTeam = getMostSuitableTeam(teams, task);
				Date start = bestTeam.getEnd().before(prevTaskEnds) ? prevTaskEnds : bestTeam.getEnd();
				cMax = calculateEnd(start, task.getDuration());
				AssignedTask assignedTask = new AssignedTask(start, cMax, project.getName());
				bestTeam.getTasks().add(assignedTask);
				bestTeam.setEnd(cMax);
				prevTaskEnds = cMax;
			}
		}
		Date endTime = new Date();
		time = endTime.getTime() - startTime.getTime();

	}

	@Override public long getTime() {
		return time;
	}

	@Override public Date getCMax() {
		return cMax;
	}

	private void clearTeamsTasks(List<Team> teams) {
		for (Team team : teams) {
			team.setTasks(new ArrayList<AssignedTask>());
			team.setEnd(new Date());
		}
	}

	private Date calculateEnd(Date start, int duration) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(start);
		calendar.add(Calendar.DATE, duration);
		return calendar.getTime();

	}

	private Team getMostSuitableTeam(List<Team> teams, Task task) {
		Team bestTeam = null;
		for (Team team : teams) {
			if (team.getType().equals(task.getType())) {
				bestTeam = getFreeTeam(bestTeam, team);
			}
		}
		if (bestTeam == null) {
			throw new CannotCalculateException();
		}
		return bestTeam;
	}

	private Team getFreeTeam(Team bestTeam, Team team) {
		if (bestTeam == null) {
			bestTeam = team;
		} else if (team.getEnd().before(bestTeam.getEnd())) {
			bestTeam = team;
		}
		return bestTeam;
	}

}
