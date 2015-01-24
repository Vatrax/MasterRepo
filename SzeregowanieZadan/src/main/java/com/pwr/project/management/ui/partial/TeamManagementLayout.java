package com.pwr.project.management.ui.partial;

import com.pwr.project.management.listeners.CreateTeamListener;
import com.pwr.project.management.listeners.RemoveTeamClickListener;
import com.pwr.project.management.model.Team;
import com.pwr.project.management.model.Type;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.*;

/**
 * Created by krzaczek on 22.01.15.
 */
public class TeamManagementLayout extends HorizontalLayout {

	private BeanContainer<String, Team> dataSource;

	public TeamManagementLayout(BeanContainer<String, Team> dataSource) {
		this.dataSource = dataSource;
		setSizeFull();
		Layout removeLayout = createRemoveLayout();
		Layout createLayout = createCreateLayout();
		addComponent(createLayout);
		addComponent(removeLayout);
		setExpandRatio(createLayout, 0.5F);
		setExpandRatio(removeLayout, 0.5F);
	}

	private Layout createCreateLayout() {
		FormLayout createLayout = new FormLayout();
		createLayout.setSizeFull();
		createLayout.setMargin(true);
		createLayout.setSizeFull();
		TextField teamName = new TextField("Name:");
		createLayout.addComponent(teamName);
		ComboBox typeSelector = new ComboBox("Type:");
		typeSelector.setInputPrompt("Select Type");
		typeSelector.setTextInputAllowed(false);
		typeSelector.setNullSelectionAllowed(false);
		for (Type type : Type.values()) {
			typeSelector.addItem(type);
		}
		Button create = new Button("Create Team");
		createLayout.addComponent(typeSelector);
		createLayout.addComponent(create);
		createLayout.setComponentAlignment(create, Alignment.MIDDLE_CENTER);
		create.addClickListener(new CreateTeamListener(dataSource, typeSelector, teamName));
		return createLayout;
	}

	private Layout createRemoveLayout() {
		FormLayout chooseLayout = new FormLayout();
		chooseLayout.setSizeFull();
		chooseLayout.setMargin(true);
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
