package com.pwr.project.management.ui.partial;

import com.vaadin.ui.VerticalLayout;
import org.tltv.gantt.Gantt;

/**
 * Created by krzaczek on 22.01.15.
 */
public class TeamView extends VerticalLayout {

	public TeamView() {
		addComponent(new Gantt());
	}
}
