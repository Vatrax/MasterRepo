package com.pwr.project.management.ui.partial;

import com.pwr.project.management.components.IntegerField;
import com.pwr.project.management.listeners.CreateProjectListener;
import com.pwr.project.management.listeners.RemoveProjectClickListener;
import com.pwr.project.management.model.Project;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickListener;
import org.vaadin.risto.stepper.IntStepper;

import java.util.Date;
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
	private DateField expectedEndDate;
	private IntegerField profit;
	private IntegerField bonus;
	private IntegerField punishment;

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

	private Layout createCreationLayout(ClickListener createProjectListener) {
		GridLayout creationLayout = new GridLayout(2, 6);
		creationLayout.setSizeFull();
		creationLayout.setSpacing(true);
		projectName = new TextField("Project name:");
		creationLayout.addComponent(projectName, 0, 0, 1, 0);
		addTasksSpinners(creationLayout);
		expectedEndDate = new DateField("Expected end date");
		creationLayout.addComponent(expectedEndDate);
		profit = new IntegerField("Profit", 10000);
		creationLayout.addComponent(profit);
		bonus = new IntegerField("Bonus[$/day]", 1000);
		creationLayout.addComponent(bonus);
		punishment = new IntegerField("Punishment[$/day]", 500);
		creationLayout.addComponent(punishment);
		creationLayout.addComponent(createCreationButton(createProjectListener));
		return creationLayout;
	}

	private Button createCreationButton(ClickListener createProjectListener) {
		Button createButton = new Button("Create project");
		createButton.addClickListener(createProjectListener);
		return createButton;
	}

	private void addTasksSpinners(GridLayout creationLayout) {
		brickLaying = createStepper("Bricklaying[Days]:");
		creationLayout.addComponent(brickLaying);
		electrical = createStepper("Electrical[Days]:");
		creationLayout.addComponent(electrical);
		plumbing = createStepper("Plumbing[Days]:");
		creationLayout.addComponent(plumbing);
		renovate = createStepper("Renovate[Days]:");
		creationLayout.addComponent(renovate);
	}

	private IntStepper createStepper(String caption) {
		IntStepper renovate = new IntStepper(caption);
		renovate.setMinValue(0);
		return renovate;
	}

	private Layout createRemoveLayout(ClickListener removeListener) {
		VerticalLayout chooseLayout = new VerticalLayout();
		chooseLayout.setSpacing(true);
		chooseLayout.setSizeFull();
		chooseLayout.setMargin(new MarginInfo(false, false, false, true));
		chooseLayout.addComponent(createComboBox());
		Button removeButton = createRemoveButton(removeListener);
		chooseLayout.addComponent(removeButton);
		chooseLayout.setComponentAlignment(removeButton, Alignment.MIDDLE_CENTER);
		return chooseLayout;
	}

	private Button createRemoveButton(ClickListener removeListener) {
		Button remove = new Button("Remove");
		remove.addClickListener(removeListener);
		return remove;
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

	public String getProjectName() {
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

	public String getPriceValue() {
		return profit.getValue();
	}

	public Date getExpectedEndDateValue() {
		return expectedEndDate.getValue();
	}

	public String getBonusValue() {
		return bonus.getValue();
	}

	public String getPunishmentValue() {
		return punishment.getValue();
	}

	public void setDateValue(Date date) {
		expectedEndDate.setValue(date);
	}

	public void setPunishmentValue(int punishmentValue) {
		punishment.setValue("" + punishmentValue);
	}

	public void setBonusValue(int bonusValue) {
		bonus.setValue("" + bonusValue);
	}

	public void setProfitValue(int profitValue) {
		profit.setValue("" + profitValue);
	}
}
