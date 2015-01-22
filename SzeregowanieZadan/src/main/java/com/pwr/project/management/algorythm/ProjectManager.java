package com.pwr.project.management.algorythm;

import com.pwr.project.management.model.Project;
import com.pwr.project.management.model.Task;
import com.pwr.project.management.model.Team;
import com.pwr.project.management.model.Type;

import java.util.Collections;
import java.util.List;

/**
 * Created by krzaczek on 22.01.15.
 */
public class ProjectManager {

	public void serialize(List<Project> projects, List<Team> teams) {
		for (Project project : projects) {
			Collections.sort(project.getTasks());
			int prevTaskEnds = 0;
			for (Task task : project.getTasks()) {
				Team bestTeam = getMostSuitableTeam(teams, task);
				Integer start = Math.max(bestTeam.getEnd(), prevTaskEnds);
				bestTeam.getTasks().put(task, start);
				int taskEnds = start + task.getDuration();
				bestTeam.setEnd(taskEnds);
				prevTaskEnds = taskEnds;
			}
		}
	}

	private Team getMostSuitableTeam(List<Team> teams, Task task) {
		Team bestTeam = null;
		for (Team team : teams) {
			Type taskType = task.getType();
			if (team.getType().equals(taskType)) {
				if (bestTeam == null) {
					bestTeam = team;
				} else if (team.getEnd() < bestTeam.getEnd()) {
					bestTeam = team;
				}
			}
		}
		return bestTeam;
	}
}
