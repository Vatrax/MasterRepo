package com.pwr.project.management.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 * Created by krzaczek on 22.01.15.
 */
@Theme("project-manager")
@VaadinServletConfiguration(productionMode = false, ui = ProjectsManager.class, widgetset="com.pwr.project.management.ui.TesterWidgetset")
public class ProjectsManager extends UI {

	@Override protected void init(VaadinRequest vaadinRequest) {
		setContent(new MainLayout());
	}
}
