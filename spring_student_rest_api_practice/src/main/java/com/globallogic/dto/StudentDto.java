package com.globallogic.dto;

import javax.validation.constraints.NotEmpty;

public class StudentDto {

	private long id;

	@NotEmpty(message = "Name Required")
	private String name;

	private double fees;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

}
