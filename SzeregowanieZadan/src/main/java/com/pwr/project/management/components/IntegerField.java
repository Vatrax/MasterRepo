package com.pwr.project.management.components;

import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.TextField;

/**
 * Created by krzaczek on 27.03.15.
 */
public class IntegerField extends TextField implements TextChangeListener {

	private String lastValue;

	public IntegerField(String caption, int defaultValue) {
		super(caption);
		this.lastValue = "" + defaultValue;
		setValue(lastValue);
		setImmediate(true);
		setTextChangeEventMode(TextChangeEventMode.EAGER);
		addTextChangeListener(this);
	}

	@Override
	public void textChange(TextChangeEvent event) {
		String text = event.getText();
		try {
			new Integer(text);
			lastValue = text;
		} catch (NumberFormatException e) {
			setValue(lastValue);
		}
	}
}
