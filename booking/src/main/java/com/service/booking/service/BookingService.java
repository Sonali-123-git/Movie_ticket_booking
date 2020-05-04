package com.service.booking.service;

import java.util.List;

import com.service.booking.model.Booking;
import com.service.booking.model.Show;

public interface BookingService {
	public List<Show> getAllShows() throws Exception;
	public String addShow(Show show) throws Exception;
	public String deleteShow(Integer show_id) throws Exception;
	public List<Show> getShowByMoT(String mot) throws Exception;
	public List<Booking> getAllBookings() throws Exception;
	public List<Booking> getBookings(Integer user_id) throws Exception;
	//public Integer doBooking(Integer user_id,Integer seats,Show show) throws Exception;
	public String doBooking(Show show,Integer user_id,Integer seats) throws Exception;
	public String cancelBooking(Integer booking_id) throws Exception;



}
