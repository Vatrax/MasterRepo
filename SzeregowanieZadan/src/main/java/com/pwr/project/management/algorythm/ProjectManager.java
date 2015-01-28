package com.pwr.project.management.algorythm;

import com.pwr.project.management.exceptions.CannotCalculateException;
import com.pwr.project.management.model.AssignedTask;
import com.pwr.project.management.model.Project;
import com.pwr.project.management.model.Task;
import com.pwr.project.management.model.Team;
import com.vaadin.data.util.BeanContainer;

import java.util.*;

/**
 * Created by krzaczek on 22.01.15.
 */
public class ProjectManager {

	public void serialize(BeanContainer<String, Project> projects, BeanContainer<String, Team> teams) {
		clearTeamsTasks(teams);
		for (String id : projects.getItemIds()) {
			Project project = projects.getItem(id).getBean();
			Collections.sort(project.getTasks());
			Date prevTaskEnds = new Date();
			for (Task task : project.getTasks()) {
				Team bestTeam = getMostSuitableTeam(teams, task);
				Date start = bestTeam.getEnd().before(prevTaskEnds) ? prevTaskEnds : bestTeam.getEnd();
				Date taskEnds = calculateEnd(start, task.getDuration());
				AssignedTask assignedTask = new AssignedTask(start, taskEnds, project.getName());
				bestTeam.getTasks().add(assignedTask);
				bestTeam.setEnd(taskEnds);
				prevTaskEnds = taskEnds;
			}
		}
	}

	private void clearTeamsTasks(BeanContainer<String, Team> teams) {
		for (String id : teams.getItemIds()) {
			Team team = teams.getItem(id).getBean();
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

	private Team getMostSuitableTeam(BeanContainer<String, Team> teams, Task task) {
		Team bestTeam = null;

		for (Iterator i = teams.getItemIds().iterator(); i.hasNext(); ) {
			Team team = teams.getItem(i.next()).getBean();
			if (team.getType().equals(task.getType())) {
				if (bestTeam == null) {
					bestTeam = team;
				} else if (team.getEnd().before(bestTeam.getEnd())) {
					bestTeam = team;
				}
			}
		}
		if(bestTeam == null){
			throw new CannotCalculateException();
		}
		return bestTeam;
	}

}
