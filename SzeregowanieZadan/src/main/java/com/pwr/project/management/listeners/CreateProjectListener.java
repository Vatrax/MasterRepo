package com.pwr.project.management.listeners;

import com.pwr.project.management.model.Project;
import com.pwr.project.management.model.Task;
import com.pwr.project.management.model.Type;
import com.pwr.project.management.presenter.GanttPresenter;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import org.vaadin.risto.stepper.Stepper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krzaczek on 28.01.15.
 */
public class CreateProjectListener implements Button.ClickListener {
	private final BeanContainer<String, Project> dataSource;
	private final TextField projectName;
	private final Stepper<Integer, Integer> brickLaying;
	private final Stepper<Integer, Integer> electrical;
	private final Stepper<Integer, Integer> plumbing;
	private final Stepper<Integer, Integer> renovate;
	private final GanttPresenter ganttPresenter;

	public CreateProjectListener(BeanContainer<String, Project> dataSource, TextField projectName,
			Stepper<Integer, Integer> brickLaying,
			Stepper<Integer, Integer> electrical,
			Stepper<Integer, Integer> plumbing,
			Stepper<Integer, Integer> renovate,
			GanttPresenter ganttPresenter) {
		this.dataSource = dataSource;
		this.projectName = projectName;
		this.brickLaying = brickLaying;
		this.electrical = electrical;
		this.plumbing = plumbing;
		this.renovate = renovate;
		this.ganttPresenter = ganttPresenter;
	}

	@Override public void buttonClick(Button.ClickEvent clickEvent) {
		List<Task> tasks = new ArrayList<>();
		if (projectName.getValue() != null && !projectName.getValue().isEmpty()) {
			if (brickLaying.getValue() != 0) {
				tasks.add(new Task(brickLaying.getValue(), Type.BRICKLAYER));
				brickLaying.setValue(0);
			}
			if (electrical.getValue() != 0) {
				tasks.add(new Task(electrical.getValue(), Type.ELECTRICIAN));
				electrical.setValue(0);
			}
			if (plumbing.getValue() != 0) {
				tasks.add(new Task(plumbing.getValue(), Type.PLUMBER));
				plumbing.setValue(0);
			}
			if (renovate.getValue() != 0) {
				tasks.add(new Task(renovate.getValue(), Type.RENOVATOR));
				renovate.setValue(0);
			}
			if(!tasks.isEmpty()) {
				Project project = new Project(projectName.getValue(), tasks);
				projectName.setValue("");
				dataSource.addBean(project);
				ganttPresenter.updateGantt();
			}
		}
	}
}
