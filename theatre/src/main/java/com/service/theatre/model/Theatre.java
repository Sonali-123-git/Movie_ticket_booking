package com.service.theatre.model;

public class Theatre {
	private Integer theatre_id; 
	private String theatre_name;
	private String location;
	private Integer rate;
	private Integer seats;
	public Integer getTheatre_id() {
		return theatre_id;
	}
	public void setTheatre_id(Integer theatre_id) {
		this.theatre_id = theatre_id;
	}
	public String getTheatre_name() {
		return theatre_name;
	}
	public void setTheatre_name(String theatre_name) {
		this.theatre_name = theatre_name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	public Integer getSeats() {
		return seats;
	}
	public void setSeats(Integer seats) {
		this.seats = seats;
	}
	
}
