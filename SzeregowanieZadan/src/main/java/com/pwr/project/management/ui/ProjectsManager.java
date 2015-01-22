package com.pwr.project.management.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 * Created by krzaczek on 22.01.15.
 */
public class ProjectsManager extends UI {

	@Override protected void init(VaadinRequest vaadinRequest) {
		setContent(new MainLayout());

	}
}
