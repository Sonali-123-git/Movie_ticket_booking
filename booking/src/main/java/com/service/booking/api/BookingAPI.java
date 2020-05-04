package com.service.booking.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.service.booking.model.Booking;
import com.service.booking.model.Response;
import com.service.booking.model.Show;
import com.service.booking.service.BookingServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("booking")
public class BookingAPI {
	@Autowired
	BookingServiceImpl serviceImpl;
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<List<Show>> getAllShows() throws Exception {
		List<Show> shows=new ArrayList<Show>();
		try {
			shows=serviceImpl.getAllShows();
			return new ResponseEntity<List<Show>>(shows,HttpStatus.OK);
		} 
		catch (Exception e) {			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,	e.getMessage(), e);
		}
	}
	@RequestMapping(value="/",method=RequestMethod.POST)
	public ResponseEntity<Response> addShow(@RequestBody Show show) throws Exception{
        String message = "";
		serviceImpl.addShow(show);
		message=" Show added successfully";
		 Response resp = new Response();
			resp.setResp(message);
			ResponseEntity<Response> response = new ResponseEntity<Response>(resp, HttpStatus.CREATED);
			return response;
	}
	@RequestMapping(value="/{show_id}",method=RequestMethod.DELETE)
    public ResponseEntity<Response> deleteShow(@PathVariable Integer show_id) throws Exception  {
		try {
		serviceImpl.deleteShow(show_id);
        String message="Show deleted successfully";
		Response resp = new Response();
		resp.setResp(message);           
        ResponseEntity<Response> response=new ResponseEntity<Response>(resp,HttpStatus.OK);
        return response;}
		catch(Exception e) {
			System.out.println(e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
		}
}
	@RequestMapping(value="search/{mot}",method=RequestMethod.GET)
	public ResponseEntity<List<Show>> getShowByMoT(@PathVariable String mot) throws Exception {
		List<Show> shows=new ArrayList<Show>();
		try {
			shows=serviceImpl.getShowByMoT(mot);
			return new ResponseEntity<List<Show>>(shows,HttpStatus.OK);
			
		} 
		catch (Exception e) {			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,	e.getMessage(), e);
		}
	}
	@RequestMapping(value="my/{user_id}",method=RequestMethod.GET)
	public ResponseEntity<List<Booking>> getBookings(@PathVariable Integer user_id)throws Exception {
		List<Booking> bookings=new ArrayList<Booking>();
		try {
			bookings=serviceImpl.getBookings(user_id);
			return new ResponseEntity<List<Booking>>(bookings,HttpStatus.OK);
			
		} 
		catch (Exception e) {			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,	e.getMessage(), e);
		}

	}
	@RequestMapping(value="all",method=RequestMethod.GET)
	public ResponseEntity<List<Booking>> getAllBookings()throws Exception {
		List<Booking> bookings=new ArrayList<Booking>();
		try {
			bookings=serviceImpl.getAllBookings();
			return new ResponseEntity<List<Booking>>(bookings,HttpStatus.OK);
			
		} 
		catch (Exception e) {			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,	e.getMessage(), e);
		}

	}
	@RequestMapping(value="book/{user_id}/{seats}",method=RequestMethod.POST)
	public ResponseEntity<Response> doBooking(@RequestBody Show show,@PathVariable Integer user_id,@PathVariable Integer seats)throws Exception {
		String msg=serviceImpl.doBooking(show,user_id, seats);
		 Response resp = new Response();
			resp.setResp(msg);
			ResponseEntity<Response> response = new ResponseEntity<Response>(resp, HttpStatus.OK);
			return response;
	}
	@RequestMapping(value="cancel/{booking_id}",method=RequestMethod.PUT)
	public ResponseEntity<Response> cancelBooking(@RequestBody Booking booking,@PathVariable Integer booking_id) throws Exception{
		String msg=serviceImpl.cancelBooking(booking_id);
		Response resp = new Response();
		resp.setResp(msg);
		ResponseEntity<Response> response = new ResponseEntity<Response>(resp, HttpStatus.OK);
		return response;
	}

}
