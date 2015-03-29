package com.pwr.project.management.ui.partial;

import com.pwr.project.management.components.IntegerField;
import com.pwr.project.management.listeners.CreateTeamListener;
import com.pwr.project.management.listeners.RemoveTeamClickListener;
import com.pwr.project.management.model.Team;
import com.pwr.project.management.model.Type;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;

import java.util.List;

/**
 * Created by krzaczek on 22.01.15.
 */
public class TeamManagementLayout extends HorizontalLayout {

	private BeanContainer<String, Team> dataContainer;
	private TextField teamName;
	private TextField teamPrice;
	private ComboBox typeSelector;

	public TeamManagementLayout(List<Team> dataSource, CreateTeamListener createTeamListener,
			RemoveTeamClickListener removeTeamClickListener) {
		this.dataContainer = new BeanContainer<>(Team.class);
		this.dataContainer.setBeanIdProperty("name");
		this.dataContainer.addAll(dataSource);
		setSizeFull();
		setMargin(true);
		Layout removeLayout = createRemoveLayout(removeTeamClickListener);
		Layout createLayout = createCreateLayout(createTeamListener);
		addComponent(createLayout);
		addComponent(removeLayout);
		setExpandRatio(createLayout, 0.5F);
		setExpandRatio(removeLayout, 0.5F);
	}

	private Layout createCreateLayout(CreateTeamListener createTeamListener) {
		VerticalLayout createLayout = new VerticalLayout();
		createLayout.setSpacing(true);
		createLayout.setSizeFull();
		teamName = new TextField("Name:");
		createLayout.addComponent(teamName);
		typeSelector = new ComboBox("Type:");
		typeSelector.setInputPrompt("Select Type");
		typeSelector.setTextInputAllowed(false);
		typeSelector.setNullSelectionAllowed(false);
		for (Type type : Type.values()) {
			typeSelector.addItem(type);
		}
		teamPrice = new IntegerField("Price per day[$]:", 100);
		createLayout.addComponent(teamPrice);
		Button create = new Button("Create Team");
		createLayout.addComponent(typeSelector);
		createLayout.addComponent(create);
		createLayout.setComponentAlignment(create, Alignment.MIDDLE_CENTER);
		create.addClickListener(createTeamListener);
		return createLayout;
	}

	private Layout createRemoveLayout(RemoveTeamClickListener removeTeamClickListener) {
		VerticalLayout chooseLayout = new VerticalLayout();
		chooseLayout.setMargin(new MarginInfo(false, false, false, true));
		chooseLayout.setSizeFull();
		chooseLayout.setSpacing(true);
		ComboBox teams = createComboBox();
		chooseLayout.addComponent(teams);
		Button remove = new Button("Remove");
		removeTeamClickListener.setTeams(teams);
		remove.addClickListener(removeTeamClickListener);
		chooseLayout.addComponent(remove);
		chooseLayout.setComponentAlignment(remove, Alignment.MIDDLE_CENTER);
		return chooseLayout;
	}

	private ComboBox createComboBox() {
		ComboBox comboBox = new ComboBox("Teams:");
		comboBox.setInputPrompt("Select Team");
		comboBox.setTextInputAllowed(false);
		comboBox.setNullSelectionAllowed(false);
		comboBox.setContainerDataSource(dataContainer);
		return comboBox;
	}

	public void synchronizeTeams(List<Team> dataSource) {
		dataContainer.removeAllItems();
		dataContainer.addAll(dataSource);
	}

	public String getTeamName() {
		return teamName.getValue();
	}

	public Type getType() {
		return (Type) typeSelector.getValue();
	}

	public void setTeamName(String teamName) {
		this.teamName.setValue(teamName);
	}

	public String getPrice() {
		return teamPrice.getValue();
	}

	public void setDefaultTeamPrice(Integer teamPrice) {
		this.teamPrice.setValue("" + teamPrice);
	}
}
