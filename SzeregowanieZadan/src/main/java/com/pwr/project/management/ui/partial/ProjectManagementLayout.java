package com.pwr.project.management.ui.partial;

import com.pwr.project.management.listeners.RemoveProjectClickListener;
import com.pwr.project.management.model.Project;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.*;
import org.vaadin.risto.stepper.IntStepper;

/**
 * Created by krzaczek on 22.01.15.
 */
public class ProjectManagementLayout extends HorizontalLayout {

	private BeanContainer<String, Project> dataSource;

	public ProjectManagementLayout(BeanContainer<String, Project> dataSource) {
		this.dataSource = dataSource;
		setSizeFull();
		Layout removeLayout = createRemoveLayout();
		Layout createLayout = createCreationLayout();
		addComponent(createLayout);
		addComponent(removeLayout);
		setExpandRatio(createLayout, 0.5F);
		setExpandRatio(removeLayout, 0.5F);
	}

	private Layout createCreationLayout() {
		FormLayout creationLayout = new FormLayout();
		creationLayout.setSizeFull();
		creationLayout.setMargin(true);
		TextField projectName = new TextField("Project name:");
		creationLayout.addComponent(projectName);
		//	BRICKLAYER(1), ELECTRICIAN(2), PLUMBER(3), RENOVATOR(4);

//		creationLayout.addComponent(new IntStepper("Bricklaying:"));
//		creationLayout.addComponent(new IntStepper("Electrical:"));
//		creationLayout.addComponent(new IntStepper("Plumbing:"));
//		creationLayout.addComponent(new IntStepper("Renovate:"));
		Button createButton = new Button("Create project");
		creationLayout.addComponent(createButton);
		return creationLayout;
	}

	private Layout createRemoveLayout() {
		FormLayout chooseLayout = new FormLayout();
		chooseLayout.setSizeFull();
		chooseLayout.setMargin(true);
		ComboBox teams = createComboBox();
		chooseLayout.addComponent(teams);
		Button remove = new Button("Remove");
		remove.addClickListener(new RemoveProjectClickListener(dataSource, teams));
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
