package com.example.demo.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultResponse {
	private int id;
	
	public ResultResponse() {}
	
	public ResultResponse(int id) {
		this.id = id;
	}
}
