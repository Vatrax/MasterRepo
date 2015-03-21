package com.pwr.project.management.ui;

import com.pwr.project.management.algorythm.Algorithm;
import com.pwr.project.management.algorythm.BasicFlowAlgorithm;
import com.pwr.project.management.model.Project;
import com.pwr.project.management.model.Task;
import com.pwr.project.management.model.Team;
import com.pwr.project.management.model.Type;
import com.pwr.project.management.presenter.GanttPresenter;
import com.pwr.project.management.ui.partial.ProjectManagementLayout;
import com.pwr.project.management.ui.partial.TeamManagementLayout;
import com.pwr.project.management.ui.partial.TeamView;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krzaczek on 22.01.15.
 */
public class MainLayout extends VerticalLayout {

	private List<Team> teams = new ArrayList<>();
	private TeamView teamView;
	private List<Project> projects = new ArrayList<>();
	private Algorithm currentAlgorithm = new BasicFlowAlgorithm();

	public MainLayout() {
		teams.add(new Team("MainBrickLayer", Type.BRICKLAYER));
		teams.add(new Team("MainElectrician", Type.ELECTRICIAN));
		teams.add(new Team("MainPlumber", Type.PLUMBER));
		teams.add(new Team("MainRenovator", Type.RENOVATOR));
		teams.add(new Team("SecondaryRenovator", Type.RENOVATOR));
		List<Task> tasks = new ArrayList<>();
		tasks.add(new Task(10, Type.BRICKLAYER));
		tasks.add(new Task(7, Type.ELECTRICIAN));
		tasks.add(new Task(10, Type.PLUMBER));
		tasks.add(new Task(21, Type.RENOVATOR));
		projects.add(new Project("KrzaQ's Villa", tasks));
		projects.add(new Project("Szymons's Cottage", tasks));
		currentAlgorithm.serialize(projects, teams);
		init();
		teamView.drawSteps(teams);
	}

	private void init() {
		setSizeFull();

		teamView = new TeamView();
		addComponent(teamView);
		GanttPresenter ganttPresenter = new GanttPresenter(teamView, teams, projects, currentAlgorithm);
		TeamManagementPresenter teamManagementPresenter = new TeamManagementPresenter(teams, ganttPresenter);
		ProjctManagementPresenter projctManagementPresenter = new ProjctManagementPresenter(projects, ganttPresenter);
		HorizontalLayout managementLayout = new HorizontalLayout();
		managementLayout.addComponent(teamManagementPresenter.createView());
		managementLayout.addComponent(projctManagementPresenter.createLayout());
		addComponent(managementLayout);
		setExpandRatio(teamView, 1F);
		managementLayout.setHeight(350, Unit.PIXELS);
	}
}
