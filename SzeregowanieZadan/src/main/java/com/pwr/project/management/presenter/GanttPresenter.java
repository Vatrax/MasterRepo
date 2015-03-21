package com.pwr.project.management.presenter;

import com.pwr.project.management.algorythm.Algorithm;
import com.pwr.project.management.algorythm.BasicFlowAlgorithm;
import com.pwr.project.management.exceptions.CannotCalculateException;
import com.pwr.project.management.model.Project;
import com.pwr.project.management.model.Team;
import com.pwr.project.management.ui.partial.TeamView;
import com.vaadin.data.util.BeanContainer;

import java.util.List;

/**
 * Created by krzaczek on 28.01.15.
 */
public class GanttPresenter {

	private TeamView view;
	private final List<Team> teams;
	private final List<Project> projects;
	private Algorithm algorithm;

	public GanttPresenter(TeamView view, List<Team> teams, List<Project> projects) {
		this.view = view;
		this.teams = teams;
		this.projects = projects;
	}

	public void updateGantt() {
		try {
			algorithm.serialize(projects, teams);
			view.drawSteps(teams);
		} catch (CannotCalculateException e) {
			view.removeSteps();
		}
	}

	public void setAlgorithm(Algorithm algorithm) {
		this.algorithm = algorithm;
	}
}
