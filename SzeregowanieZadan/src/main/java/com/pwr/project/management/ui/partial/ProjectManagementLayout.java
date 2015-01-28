package com.pwr.project.management.ui.partial;

import com.pwr.project.management.listeners.CreateProjectListener;
import com.pwr.project.management.listeners.RemoveProjectClickListener;
import com.pwr.project.management.model.Project;
import com.pwr.project.management.presenter.GanttPresenter;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.*;
import org.vaadin.risto.stepper.IntStepper;

/**
 * Created by krzaczek on 22.01.15.
 */
public class ProjectManagementLayout extends HorizontalLayout {

	private BeanContainer<String, Project> dataSource;

	public ProjectManagementLayout(BeanContainer<String, Project> dataSource, GanttPresenter ganttPresenter) {
		this.dataSource = dataSource;
		setSizeFull();
		setMargin(true);
		Layout removeLayout = createRemoveLayout(ganttPresenter);
		Layout createLayout = createCreationLayout(ganttPresenter);
		addComponent(createLayout);
		addComponent(removeLayout);
	}

	private Layout createCreationLayout(GanttPresenter ganttPresenter) {
		FormLayout creationLayout = new FormLayout();
		creationLayout.setSizeFull();
		creationLayout.setMargin(true);
		TextField projectName = new TextField("Project name:");
		creationLayout.addComponent(projectName);
		IntStepper brickLaying = createStepper("Bricklaying[Days]:");
		creationLayout.addComponent(brickLaying);
		IntStepper electrical = createStepper("Electrical[Days]:");
		creationLayout.addComponent(electrical);
		IntStepper plumbing = createStepper("Plumbing[Days]:");
		creationLayout.addComponent(plumbing);
		IntStepper renovate = createStepper("Renovate[Days]:");
		creationLayout.addComponent(renovate);
		Button createButton = new Button("Create project");
		createButton.addClickListener(
				new CreateProjectListener(dataSource, projectName, brickLaying, electrical, plumbing, renovate,
						ganttPresenter));
		creationLayout.addComponent(createButton);
		return creationLayout;
	}

	private IntStepper createStepper(String caption) {
		IntStepper renovate = new IntStepper(caption);
		renovate.setMinValue(0);
		return renovate;
	}

	private Layout createRemoveLayout(GanttPresenter ganttPresenter) {
		FormLayout chooseLayout = new FormLayout();
		chooseLayout.setSizeFull();
		chooseLayout.setMargin(true);
		ComboBox teams = createComboBox();
		chooseLayout.addComponent(teams);
		Button remove = new Button("Remove");
		remove.addClickListener(new RemoveProjectClickListener(dataSource, teams, ganttPresenter));
		chooseLayout.addComponent(remove);
		chooseLayout.setComponentAlignment(remove, Alignment.MIDDLE_CENTER);
		return chooseLayout;
	}

	private ComboBox createComboBox() {
		ComboBox comboBox = new ComboBox("Projects:");
		comboBox.setInputPrompt("Select Team");
		comboBox.setTextInputAllowed(false);
		comboBox.setNullSelectionAllowed(false);
		comboBox.setContainerDataSource(dataSource);
		return comboBox;
	}
}
