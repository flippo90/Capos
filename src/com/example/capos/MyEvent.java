package com.example.capos;


public class MyEvent {
	private int id;
	private String name;
	private String location;
	private String specials;
	
	public MyEvent(int id, String name, String specials, String location) {
		this.id = id;
		this.name = name;
		this.location = location;
		this.specials = specials;	
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSpecials() {
		return specials;
	}
	public void setSpecials(String specials) {
		specials = specials;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
