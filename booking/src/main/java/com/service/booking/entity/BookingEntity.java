package com.service.booking.entity;
//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="booking")
public class BookingEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition = "serial",name="booking_id")
	private Integer booking_id;
	@Column(name="user_id")
	private Integer user_id; 
	@ManyToOne
	//@ManyToOne(cascade=CascadeType.ALL)

	@JoinColumn(name="show_id")
	private ShowEntity show;
	@Column(name="seats")
	private Integer seats; 
	@Column(name="amount")
	private Integer amount; 
	@Column(name="status")
	private String status;
	public Integer getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(Integer booking_id) {
		this.booking_id = booking_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public ShowEntity getShow() {
		return show;
	}
	public void setShow(ShowEntity show) {
		this.show = show;
	}
	public Integer getSeats() {
		return seats;
	}
	public void setSeats(Integer seats) {
		this.seats = seats;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
