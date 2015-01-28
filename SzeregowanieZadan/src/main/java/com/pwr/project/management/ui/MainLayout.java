package com.pwr.project.management.ui;

import com.pwr.project.management.algorythm.ProjectManager;
import com.pwr.project.management.model.Project;
import com.pwr.project.management.model.Task;
import com.pwr.project.management.model.Team;
import com.pwr.project.management.model.Type;
import com.pwr.project.management.presenter.GanttPresenter;
import com.pwr.project.management.ui.partial.ProjectManagementLayout;
import com.pwr.project.management.ui.partial.TeamManagementLayout;
import com.pwr.project.management.ui.partial.TeamView;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krzaczek on 22.01.15.
 */
public class MainLayout extends VerticalLayout {

	private BeanContainer<String, Team> teams;
	private BeanContainer<String, Project> projects;
	private TeamView teamView;

	public MainLayout() {
		init();
		teams.addBean(new Team("MainBrickLayer", Type.BRICKLAYER));
		teams.addBean(new Team("MainElectrician", Type.ELECTRICIAN));
		teams.addBean(new Team("MainPlumber", Type.PLUMBER));
		teams.addBean(new Team("MainRenovator", Type.RENOVATOR));
		teams.addBean(new Team("SecondaryRenovator", Type.RENOVATOR));
		List<Task> tasks = new ArrayList<>();
		tasks.add(new Task(10, Type.BRICKLAYER));
		tasks.add(new Task(7, Type.ELECTRICIAN));
		tasks.add(new Task(10, Type.PLUMBER));
		tasks.add(new Task(21, Type.RENOVATOR));
		projects.addBean(new Project("KrzaQ's Villa", tasks));
		projects.addBean(new Project("Szymons's Cottage", tasks));
		new ProjectManager().serialize(projects, teams);
		teamView.drawSteps(teams);
	}

	private void init() {
		setSizeFull();
		teams = new BeanContainer<>(Team.class);
		teams.setBeanIdProperty("name");
		projects = new BeanContainer<>(Project.class);
		projects.setBeanIdProperty("name");

		teamView = new TeamView();
		addComponent(teamView);

		GanttPresenter ganttPresenter = new GanttPresenter(teamView, teams, projects);
		TeamManagementLayout teamCreationLayout = new TeamManagementLayout(teams, ganttPresenter);
		ProjectManagementLayout projectManagementLayout = new ProjectManagementLayout(projects, ganttPresenter);
		HorizontalLayout managementLayout = new HorizontalLayout();
		managementLayout.addComponent(teamCreationLayout);
		managementLayout.addComponent(projectManagementLayout);
		addComponent(managementLayout);
		managementLayout.setHeight(350, Unit.PIXELS);
	}
}
