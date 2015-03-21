package com.pwr.project.management.ui.partial;

import com.pwr.project.management.model.AssignedTask;
import com.pwr.project.management.model.Team;
import org.tltv.gantt.Gantt;
import org.tltv.gantt.client.shared.Step;

import java.util.*;

/**
 * Created by krzaczek on 22.01.15.
 */
public class TeamView extends Gantt {

	private static final String[] COLORS = new String[] { "FF0000", "FFFF4C", "9c4ade",
			"11FF11" };

	public TeamView() {
		init();
	}

	private void init() {
		setSizeFull();
		setResizableSteps(false);
		setMovableSteps(false);
		setLocale(Locale.ENGLISH);
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		setStartDate(cal.getTime());
		cal.add(Calendar.YEAR, 1);
		setEndDate(cal.getTime());
		cal.setTime(new Date());

	}

	public void drawSteps(List<Team> teams) {
		removeSteps();
		Collections.sort(teams);
		Collections.reverse(teams);
		for (Team team : teams) {
			for (AssignedTask task : team.getTasks()) {
				addStep(createStep(team.getName(), team.getType().getPriority(), task));
			}
		}
	}

	private Step createStep(String teamName, int priority, AssignedTask task) {
		Step step = new Step();
		step.setStartDate(task.getStart());
		step.setEndDate(task.getEnd());
		step.setCaption("[" + teamName + "]" + task.getProjectName());
		step.setBackgroundColor(COLORS[priority - 1]);
		return step;
	}

}