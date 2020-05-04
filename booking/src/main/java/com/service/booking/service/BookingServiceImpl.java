package com.service.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.service.booking.dao.BookingDAO;
import com.service.booking.model.Booking;
import com.service.booking.model.Show;
@Service(value="BookingServiceImpl")
@Transactional
public class BookingServiceImpl implements BookingService{
	@Autowired
	private BookingDAO bookingDao;
	@Override
	public List<Show> getAllShows() throws Exception{
		return bookingDao.getAllShows();
	}
	@Override
	public String addShow(Show show) throws Exception{
		return bookingDao.addShow(show);

	}
	@Override
	public String deleteShow(Integer show_id) throws Exception{
		return bookingDao.deleteShow(show_id);

	}
	@Override
	public List<Show> getShowByMoT(String mot) throws Exception{
		if(bookingDao.getShowByMoT(mot).size()==0)
           throw new Exception("Service.SEARCH_NOT_FOUND");
		return bookingDao.getShowByMoT(mot);
	}
	@Override
	public List<Booking> getAllBookings() throws Exception{
		return bookingDao.getAllBookings();
	}
	@Override
	public List<Booking> getBookings(Integer user_id) throws Exception{
		if(bookingDao.getBookings(user_id).size()==0)
	           throw new Exception("Service.NO_BOOKINGS");
			return bookingDao.getBookings(user_id);
	}
	@Override
	public String doBooking(Show show,Integer user_id,Integer seats) throws Exception{
		String s=bookingDao.doBooking(show,user_id, seats);
		if (s.equals("Seats booked successfully"))
			return s;
		else
	        throw new Exception("Service.BOOKING NOT SUCCESSFUL");	
	}
	@Override
	public String cancelBooking(Integer booking_id) throws Exception{
		return bookingDao.cancelBooking(booking_id);
	}



}
