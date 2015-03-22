package com.pwr.project.management.listeners;

import com.pwr.project.management.algorythm.Algorithm;
import com.pwr.project.management.algorythm.analyser.AlgorithmAnalyser;
import com.pwr.project.management.presenter.AlgorythmManagementPresenter;
import com.pwr.project.management.presenter.GanttPresenter;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;

import java.util.List;

/**
 * Created by krzaczek on 21.03.15.
 */
public class AlgorithmChangeListener implements ValueChangeListener {

	private final AlgorythmManagementPresenter algorythmManagementPresenter;
	private final GanttPresenter ganttPresenter;
	private final List<Algorithm> algorithms;

	public AlgorithmChangeListener(AlgorythmManagementPresenter algorythmManagementPresenter,
			GanttPresenter ganttPresenter, List<Algorithm> algorithms) {
		this.algorythmManagementPresenter = algorythmManagementPresenter;
		this.ganttPresenter = ganttPresenter;
		this.algorithms = algorithms;
	}

	@Override public void valueChange(ValueChangeEvent event) {
		String algorithmName = event.getProperty().getValue().toString();
		for (Algorithm algorithm : algorithms) {
			if (algorithmName.equals(algorithm.getClass().getSimpleName())) {
				ganttPresenter.setAlgorithm(algorithm);
				ganttPresenter.updateGantt();
				AlgorithmAnalyser algorithmAnalyser = ganttPresenter.getAlgorithmAnalyser();
				algorythmManagementPresenter.setTime(algorithmAnalyser.getTime());
				algorythmManagementPresenter.setDate(algorithmAnalyser.getEndDate());
				algorythmManagementPresenter.setProfit(algorithmAnalyser.getProfit());
				break;
			}
		}
	}
}
