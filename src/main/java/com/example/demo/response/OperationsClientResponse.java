package com.example.demo.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperationsClientResponse {
	private double average;
	private double standardDeviation;
	
	public OperationsClientResponse() {}
	
	public OperationsClientResponse(double average, double standardDeviation) {
		this.average = average;
		this.standardDeviation = standardDeviation;
	}
}
