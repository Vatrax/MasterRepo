package com.pwr.project.management.ui.partial;

import com.pwr.project.management.algorythm.Algorithm;
import com.pwr.project.management.listeners.AlgorithmChangeListener;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

/**
 * Created by krzaczek on 21.03.15.
 */
public class AlgorithmManagementLayout extends VerticalLayout {

	private final Label timeLabel = new Label();
	private final Label endTimeLabel = new Label();
	private final Label profitLabel = new Label();

	public AlgorithmManagementLayout(List<Algorithm> algorithms, AlgorithmChangeListener algorithmChangeListener) {
		setSizeFull();
		setMargin(true);
		setSpacing(true);
		addComponent(createAlgorithmPicker(algorithms, algorithmChangeListener));
		addComponent(timeLabel);
		addComponent(endTimeLabel);
		addComponent(profitLabel);
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
}
