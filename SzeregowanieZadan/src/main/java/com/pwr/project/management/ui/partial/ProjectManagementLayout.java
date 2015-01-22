package com.pwr.project.management.ui.partial;

import com.pwr.project.management.listeners.RemoveProjectClickListener;
import com.pwr.project.management.model.Project;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.*;

/**
 * Created by krzaczek on 22.01.15.
 */
public class ProjectManagementLayout extends FormLayout {

	private BeanContainer<String, Project> dataSource;

	public ProjectManagementLayout(BeanContainer<String, Project> dataSource) {
		this.dataSource = dataSource;
		setMargin(true);
		addComponent(createChooseLayout());
		addComponent(projectCreationLayout());
	}

	private VerticalLayout projectCreationLayout() {
		VerticalLayout creationLayout = new VerticalLayout();
//		creationLayout.addComponent(new Spinner);
		return creationLayout;
	}

	private Layout createChooseLayout() {
		VerticalLayout chooseLayout = new VerticalLayout();
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
