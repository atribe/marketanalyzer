package com.ar.marketanalyzer.REST.tasks;

public class ProcessingStatus {

	private String status;
	private int processingTimeMs;
	
	public ProcessingStatus() {
	status = "UNKNOWN";
	processingTimeMs = -1;
	}
	
	public ProcessingStatus(String status, int processingTimeMs) {
	this.status = status;
	this.processingTimeMs = processingTimeMs;
	}
	
	public String getStatus() {
	return status;
	}
	
	public int getProcessingTimeMs() {
	return processingTimeMs;
	}
}
