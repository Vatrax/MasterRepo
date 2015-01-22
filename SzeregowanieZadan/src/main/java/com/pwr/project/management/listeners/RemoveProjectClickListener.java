package com.pwr.project.management.listeners;

import com.vaadin.data.Container;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;

/**
 * Created by krzaczek on 22.01.15.
 */
public class RemoveProjectClickListener implements Button.ClickListener {
	private final Container dataSource;
	private final ComboBox projects;

	public RemoveProjectClickListener(Container dataSource, ComboBox projects) {
		this.dataSource = dataSource;
		this.projects = projects;
	}

	@Override public void buttonClick(Button.ClickEvent clickEvent) {
		dataSource.removeItem(projects.getValue());
	}
}
