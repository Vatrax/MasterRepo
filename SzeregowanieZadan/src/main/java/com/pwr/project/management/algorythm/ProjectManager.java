package com.pwr.project.management.algorythm;

import com.pwr.project.management.model.AssignedTask;
import com.pwr.project.management.model.Project;
import com.pwr.project.management.model.Task;
import com.pwr.project.management.model.Team;
import com.vaadin.data.util.BeanContainer;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by krzaczek on 22.01.15.
 */
public class ProjectManager {

	public void serialize(BeanContainer<String, Project> projects, BeanContainer<String, Team> teams) {
		//Todo RESULTS
		for (Iterator i = projects.getItemIds().iterator(); i.hasNext(); ) {
			Project project = projects.getItem(i.next()).getBean();
			Collections.sort(project.getTasks());
			long prevTaskEnds = 0;
			for (Task task : project.getTasks()) {
				Team bestTeam = getMostSuitableTeam(teams, task);
				Long start = Math.max(bestTeam.getEnd(), prevTaskEnds);
				long taskEnds = calculateEnd(start, task.getDuration());
				AssignedTask assignedTask = new AssignedTask(new Date(start), new Date(taskEnds), project.getName());
				bestTeam.getTasks().add(assignedTask);
				bestTeam.setEnd(taskEnds);
				prevTaskEnds = taskEnds;
			}
		}
	}

	private Long calculateEnd(Long start, int duration) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(start);
		calendar.add(Calendar.DATE, duration);
		return calendar.getTimeInMillis();

	}

	private Team getMostSuitableTeam(BeanContainer<String, Team> teams, Task task) {
		Team bestTeam = null;

		for (Iterator i = teams.getItemIds().iterator(); i.hasNext(); ) {
			Team team = teams.getItem(i.next()).getBean();
			if (team.getType().equals(task.getType())) {
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
