package com.pwr.project.management.presenter;

import com.pwr.project.management.algorythm.ProjectManager;
import com.pwr.project.management.exceptions.CannotCalculateException;
import com.pwr.project.management.model.Project;
import com.pwr.project.management.model.Team;
import com.pwr.project.management.ui.partial.TeamView;
import com.vaadin.data.util.BeanContainer;

/**
 * Created by krzaczek on 28.01.15.
 */
public class GanttPresenter {

	private TeamView view;
	private final BeanContainer<String, Team> teams;
	private final BeanContainer<String, Project> projects;
	private ProjectManager manager = new ProjectManager();

	public GanttPresenter(TeamView view, BeanContainer<String, Team> teams, BeanContainer<String, Project> projects) {
		this.view = view;
		this.teams = teams;
		this.projects = projects;
	}

	public void updateGantt() {
		try {
			manager.serialize(projects, teams);
			view.drawSteps(teams);
		} catch (CannotCalculateException e) {
			view.removeSteps();
		}
	}

}
