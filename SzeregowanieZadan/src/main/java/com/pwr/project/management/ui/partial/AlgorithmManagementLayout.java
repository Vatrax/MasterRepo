package com.pwr.project.management.ui.partial;

import com.pwr.project.management.algorythm.Algorithm;
import com.pwr.project.management.components.IntegerField;
import com.pwr.project.management.listeners.AlgorithmChangeListener;
import com.pwr.project.management.listeners.DataGeneratorListener;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.*;

import java.util.List;

/**
 * Created by krzaczek on 21.03.15.
 */
public class AlgorithmManagementLayout extends VerticalLayout {

	private final Label timeLabel = new Label();
	private final Label endTimeLabel = new Label();
	private final Label profitLabel = new Label();
	private IntegerField numberOfProjects;

	public AlgorithmManagementLayout(List<Algorithm> algorithms, AlgorithmChangeListener algorithmChangeListener,
			DataGeneratorListener dataGeneratorListener) {
		setSizeFull();
		setMargin(true);
		setSpacing(true);
		addComponent(createAlgorithmPicker(algorithms, algorithmChangeListener));
		addComponent(timeLabel);
		addComponent(endTimeLabel);
		addComponent(profitLabel);
		addComponent(createDataGenerator(dataGeneratorListener));
	}

	private Layout createDataGenerator(DataGeneratorListener dataGeneratorListener) {
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		numberOfProjects = new IntegerField("Number of Projects", 1);
		layout.addComponent(numberOfProjects);
		Button generateProjectsButton = new Button("Generate Projects");
		generateProjectsButton.addClickListener(dataGeneratorListener);
		layout.addComponent(generateProjectsButton);
		return layout;
	}

	private ComboBox createAlgorithmPicker(List<Algorithm> algorithms,
			ValueChangeListener algorithmChangeListener) {
		ComboBox algorythmCombobox = new ComboBox("Current algorithm:");
		algorythmCombobox.setTextInputAllowed(false);
		algorythmCombobox.setNullSelectionAllowed(false);
		algorythmCombobox.setInputPrompt("Select algorithm");
		for (Algorithm algorithm : algorithms) {
			algorythmCombobox.addItem(algorithm.getClass().getSimpleName());
		}
		algorythmCombobox.addValueChangeListener(algorithmChangeListener);
		return algorythmCombobox;
	}

	public void setTime(String time) {
		timeLabel.setValue(time);
	}

	public void setDate(String date) {
		endTimeLabel.setValue(date);
	}

	public void setProfit(String profit) {
		profitLabel.setValue(profit);
	}

	public String getTextFiledValue() {
		return numberOfProjects.getValue();
	}

	public void setNumberOfProjects(int value) {
		numberOfProjects.setValue("" + value);
	}
}
