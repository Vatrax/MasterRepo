package com.pwr.project.management.ui.partial;

import com.pwr.project.management.listeners.CreateProjectListener;
import com.pwr.project.management.listeners.RemoveProjectClickListener;
import com.pwr.project.management.model.Project;
import com.pwr.project.management.presenter.GanttPresenter;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.*;
import org.vaadin.risto.stepper.IntStepper;

import java.util.List;

/**
 * Created by krzaczek on 22.01.15.
 */
public class ProjectManagementLayout extends HorizontalLayout {

	private BeanContainer<String, Project> dataContainer;
	private ComboBox projectsCombobox;
	private TextField projectName;
	private IntStepper brickLaying;
	private IntStepper electrical;
	private IntStepper plumbing;
	private IntStepper renovate;

	public ProjectManagementLayout(List<Project> dataSource, RemoveProjectClickListener removeListener,
			CreateProjectListener createProjectListener) {
		this.dataContainer = new BeanContainer<>(Project.class);
		this.dataContainer.setBeanIdProperty("name");
		this.dataContainer.addAll(dataSource);
		setSizeFull();
		setMargin(true);
		Layout removeLayout = createRemoveLayout(removeListener);
		Layout createLayout = createCreationLayout(createProjectListener);
		addComponent(createLayout);
		addComponent(removeLayout);
	}

	private Layout createCreationLayout(Button.ClickListener createProjectListener) {
		FormLayout creationLayout = new FormLayout();
		creationLayout.setSizeFull();
		creationLayout.setMargin(true);
		projectName = new TextField("Project name:");
		creationLayout.addComponent(projectName);
		brickLaying = createStepper("Bricklaying[Days]:");
		creationLayout.addComponent(brickLaying);
		electrical = createStepper("Electrical[Days]:");
		creationLayout.addComponent(electrical);
		plumbing = createStepper("Plumbing[Days]:");
		creationLayout.addComponent(plumbing);
		renovate = createStepper("Renovate[Days]:");
		creationLayout.addComponent(renovate);
		Button createButton = new Button("Create project");
		createButton.addClickListener(createProjectListener);
		creationLayout.addComponent(createButton);
		return creationLayout;
	}

	private IntStepper createStepper(String caption) {
		IntStepper renovate = new IntStepper(caption);
		renovate.setMinValue(0);
		return renovate;
	}

	private Layout createRemoveLayout(Button.ClickListener removeListener) {
		FormLayout chooseLayout = new FormLayout();
		chooseLayout.setSizeFull();
		chooseLayout.setMargin(true);
		ComboBox teams = createComboBox();
		chooseLayout.addComponent(teams);
		Button remove = new Button("Remove");
		remove.addClickListener(removeListener);
		chooseLayout.addComponent(remove);
		chooseLayout.setComponentAlignment(remove, Alignment.MIDDLE_CENTER);
		return chooseLayout;
	}

	private ComboBox createComboBox() {
		projectsCombobox = new ComboBox("Projects:");
		projectsCombobox.setInputPrompt("Select Project");
		projectsCombobox.setTextInputAllowed(false);
		projectsCombobox.setNullSelectionAllowed(false);
		projectsCombobox.setContainerDataSource(dataContainer);
		return projectsCombobox;
	}

	public void synchronizeProjects(List<Project> projects) {
		dataContainer.removeAllItems();
		dataContainer.addAll(projects);
	}

	public Object getComboboxValue() {
		return projectsCombobox.getValue();
	}

	public void setComboboxValue(Project project) {
		projectsCombobox.setValue(project);
	}

	public String getPojectName() {
		return projectName.getValue();
	}

	public void setProjectName(String name) {
		projectName.setValue(name);
	}

	public int getBrickLayingValues() {
		return brickLaying.getValue();
	}

	public int getElectricalValues() {
		return electrical.getValue();
	}

	public int getPlumbingValues() {
		return plumbing.getValue();
	}

	public int getRenovateValues() {
		return renovate.getValue();
	}

	public void setBrickLayingValues(int value) {
		brickLaying.setValue(value);
	}

	public void setElectricalValues(int value) {
		electrical.setValue(value);
	}

	public void setPlumbingValues(int value) {
		plumbing.setValue(value);
	}

	public void setRenovateValues(int value) {
		renovate.setValue(value);
	}
}
