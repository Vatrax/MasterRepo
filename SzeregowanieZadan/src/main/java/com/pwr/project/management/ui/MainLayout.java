package com.pwr.project.management.ui;

import com.pwr.project.management.model.Project;
import com.pwr.project.management.model.Team;
import com.pwr.project.management.ui.partial.ProjectManagementLayout;
import com.pwr.project.management.ui.partial.TeamManagementLayout;
import com.pwr.project.management.ui.partial.TeamView;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by krzaczek on 22.01.15.
 */
public class MainLayout extends VerticalLayout {

	private BeanContainer<String, Team> teams;
	private BeanContainer<String, Project> projects;

	public MainLayout() {
		init();
	}

	private void init() {
		teams = new BeanContainer<String, Team>(Team.class);
		teams.setBeanIdProperty("name");
		projects = new BeanContainer<String, Project>(Project.class);
		projects .setBeanIdProperty("name");

		TeamManagementLayout teamCreationLayout = new TeamManagementLayout(teams);
		ProjectManagementLayout projectManagementLayout = new ProjectManagementLayout(projects);
		TeamView teamView = new TeamView();

		addComponent(teamView);
		HorizontalLayout managementLayout = new HorizontalLayout();
		managementLayout.addComponent(teamCreationLayout);
		managementLayout.addComponent(projectManagementLayout);

		addComponent(managementLayout);

	}
}
