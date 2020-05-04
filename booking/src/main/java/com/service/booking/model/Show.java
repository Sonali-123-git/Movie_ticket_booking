package com.service.booking.model;
import java.time.LocalDate;

public class Show {
	private Integer show_id; 
	private String movie_name;
	private String theatre_name;
	private String time;
	private LocalDate date; 
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
