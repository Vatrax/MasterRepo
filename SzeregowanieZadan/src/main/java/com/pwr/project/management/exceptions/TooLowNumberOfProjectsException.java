package com.pwr.project.management.exceptions;

/**
 * Created by krzaczek on 15.05.15.
 */
public class TooLowNumberOfProjectsException extends RuntimeException {
	public TooLowNumberOfProjectsException(String message) {
		super(message);
	}
}
