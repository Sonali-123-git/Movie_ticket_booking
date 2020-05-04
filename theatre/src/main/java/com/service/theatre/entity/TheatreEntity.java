package com.service.theatre.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="theatre")
public class TheatreEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition = "serial",name="theatre_id")
	private Integer theatre_id;
	@Column(name="theatre_name")
	private String theatre_name;
	@Column(name="location")
	private String location;
	@Column(name="rate")
	private Integer rate;
	@Column(name="seats")
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
