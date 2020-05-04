package com.service.booking.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="show")
public class ShowEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition = "serial",name="show_id")
	private Integer show_id;
	@Column(name="movie_name")
	private String movie_name;
	@Column(name="theatre_name")
	private String theatre_name;
	@Column(name="time")
	private String time;
	@Column(name="date")
	private LocalDate date; 
	@Column(name="rem_seats")
	private Integer rem_seats;
	public Integer getShow_id() {
		return show_id;
	}
	public void setShow_id(Integer show_id) {
		this.show_id = show_id;
	}
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public String getTheatre_name() {
		return theatre_name;
	}
	public void setTheatre_name(String theatre_name) {
		this.theatre_name = theatre_name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Integer getRem_seats() {
		return rem_seats;
	}
	public void setRem_seats(Integer rem_seats) {
		this.rem_seats = rem_seats;
	}
}
