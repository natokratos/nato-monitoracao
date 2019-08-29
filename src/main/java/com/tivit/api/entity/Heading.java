package com.tivit.api.entity;

public class Heading {
	private String header;
	private String name;
	private String type;
	
	public Heading() {
		this.header = "";
		this.name = "";
		this.type = "";
	}
	
	public Heading(String header, String name, String type) {
		this.header = header;
		this.name = name;
		this.type = type;
	}

	public String getHeader() {
		return header;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}

	public void setHeader(String header) {
		this.header = header;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public boolean isEmpty() {
		return (this.header.isEmpty() && this.name.isEmpty() && this.type.isEmpty());
	}
	
}
