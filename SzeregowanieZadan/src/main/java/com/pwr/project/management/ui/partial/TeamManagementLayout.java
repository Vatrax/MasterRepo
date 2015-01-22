package com.pwr.project.management.ui.partial;

import com.pwr.project.management.listeners.CreateTeamListener;
import com.pwr.project.management.listeners.RemoveTeamClickListener;
import com.pwr.project.management.model.Team;
import com.pwr.project.management.model.Type;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;

/**
 * Created by krzaczek on 22.01.15.
 */
public class TeamManagementLayout extends FormLayout {

	private BeanContainer<String, Team> dataSource;

	public TeamManagementLayout(BeanContainer<String, Team> dataSource) {
		this.dataSource = dataSource;
		setMargin(true);
		addComponent(createChooseLayout());
		addComponent(createCreateLayout());
	}

	private Component createCreateLayout() {
		VerticalLayout createLayout = new VerticalLayout();
		createLayout.setMargin(new MarginInfo(true, false, false, false));
		TextField teamName = new TextField("Name:");
		createLayout.addComponent(teamName);
		ComboBox typeSelector = new ComboBox("Type:");
		typeSelector.setInputPrompt("Select Type");
		typeSelector.setTextInputAllowed(false);
		typeSelector.setNullSelectionAllowed(false);
		for (Type type : Type.values()) {
			typeSelector.addItem(type);
		}
		Button create = new Button("Create");
		createLayout.addComponent(typeSelector);
		createLayout.addComponent(create);
		createLayout.setComponentAlignment(create, Alignment.MIDDLE_CENTER);
		create.addClickListener(new CreateTeamListener(dataSource, typeSelector, teamName));
		return createLayout;
	}

	private Layout createChooseLayout() {
		VerticalLayout chooseLayout = new VerticalLayout();
		ComboBox teams = createComboBox();
		chooseLayout.addComponent(teams);
		Button remove = new Button("Remove");
		remove.addClickListener(new RemoveTeamClickListener(dataSource, teams));
		chooseLayout.addComponent(remove);
		chooseLayout.setComponentAlignment(remove, Alignment.MIDDLE_CENTER);
		return chooseLayout;
	}

	private ComboBox createComboBox() {
		ComboBox comboBox = new ComboBox("Teams:");
		comboBox.setInputPrompt("Select Team");
		comboBox.setTextInputAllowed(false);
		comboBox.setNullSelectionAllowed(false);
		comboBox.setContainerDataSource(dataSource);
		return comboBox;
	}
}
