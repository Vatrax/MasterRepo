package com.pwr.project.management.ui;

import com.pwr.project.management.model.Project;
import com.pwr.project.management.model.Task;
import com.pwr.project.management.model.Team;
import com.pwr.project.management.model.Type;
import com.pwr.project.management.presenter.AlgorythmManagementPresenter;
import com.pwr.project.management.presenter.GanttPresenter;
import com.pwr.project.management.presenter.ProjectManagementPresenter;
import com.pwr.project.management.presenter.TeamManagementPresenter;
import com.pwr.project.management.ui.partial.TeamView;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalSplitPanel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by krzaczek on 22.01.15.
 */
public class MainLayout extends VerticalSplitPanel {

	private List<Team> teams = new ArrayList<>();
	private TeamView teamView;
	private List<Project> projects = new ArrayList<>();

	public MainLayout() {
		teams.add(new Team("MainBrickLayer", Type.BRICKLAYER, 200));
		teams.add(new Team("MainElectrician", Type.ELECTRICIAN, 300));
		teams.add(new Team("MainPlumber", Type.PLUMBER,  400));
		teams.add(new Team("MainRenovator", Type.RENOVATOR, 350));
		teams.add(new Team("SecondaryRenovator", Type.RENOVATOR, 400));
		List<Task> tasks = new ArrayList<>();
		tasks.add(new Task(10, Type.BRICKLAYER));
		tasks.add(new Task(7, Type.ELECTRICIAN));
		tasks.add(new Task(10, Type.PLUMBER));
		tasks.add(new Task(21, Type.RENOVATOR));
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.MONTH, 1);
		projects.add(new Project("KrzaQ's Villa", tasks, 200000, instance.getTime(),200, 400));
		instance.add(Calendar.MONTH, 1);
		projects.add(new Project("Szymons's Cottage", tasks, 100000, instance.getTime(),100,0));
		init();
		teamView.drawSteps(teams);
	}

	private void init() {
		setSizeFull();
		teamView = new TeamView();
		addComponent(teamView);
		GanttPresenter ganttPresenter = new GanttPresenter(teamView, teams, projects);
		AlgorythmManagementPresenter algorythmManagementPresenter = new AlgorythmManagementPresenter(ganttPresenter);
		TeamManagementPresenter teamManagementPresenter = new TeamManagementPresenter(teams, ganttPresenter,
				algorythmManagementPresenter);
		ProjectManagementPresenter projectManagementPresenter = new ProjectManagementPresenter(projects, ganttPresenter,
				algorythmManagementPresenter);
		HorizontalLayout managementLayout = new HorizontalLayout();
		managementLayout.addComponent(teamManagementPresenter.createView());
		managementLayout.addComponent(projectManagementPresenter.createLayout());
		managementLayout.addComponent(algorythmManagementPresenter.createLayout());
		addComponent(managementLayout);
		setSplitPosition(45, Unit.PERCENTAGE);
	}
}
