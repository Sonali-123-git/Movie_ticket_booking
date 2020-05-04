package com.service.booking.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.service.booking.entity.ShowEntity;

import com.service.booking.model.Booking;
import com.service.booking.model.Show;
import com.service.booking.entity.TheatreEntity;
import com.service.booking.entity.BookingEntity;


@Repository
public class BookingDAOImpl implements BookingDAO{
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public List<Show> getAllShows() throws Exception{
		Query query = entityManager.createQuery("select show from ShowEntity show");
		List<Show> shows=null;
		List<ShowEntity> showEntities = query.getResultList();		
		shows = new ArrayList<Show>();
		for(ShowEntity showEntity: showEntities) {
			Show show=new Show();
			if(showEntity.getTheatre_name()!=null && showEntity.getMovie_name()!=null) {
				show.setDate(showEntity.getDate());
				show.setMovie_name(showEntity.getMovie_name());
				show.setRem_seats(showEntity.getRem_seats());
				show.setShow_id(showEntity.getShow_id());
				show.setTime(showEntity.getTime());
				show.setTheatre_name(showEntity.getTheatre_name());
				shows.add(show);
			}
		}
		return shows;
	}
	
	@Override
	public String addShow(Show show) throws Exception{
		String message = "";
		ShowEntity entity = new ShowEntity();
		entity.setTheatre_name(show.getTheatre_name());
        entity.setDate(show.getDate());
        entity.setMovie_name(show.getMovie_name());
        Integer s=0;
        TheatreEntity theatreEntity=entityManager.createQuery("SELECT t from TheatreEntity t WHERE t.theatre_name= :theatre_name",TheatreEntity.class).
        		setParameter("theatre_name",show.getTheatre_name()).getSingleResult();
        s=theatreEntity.getSeats();
        entity.setRem_seats(s);
        entity.setTime(show.getTime());
        entityManager.persist(entity);
        message = "Show added successfully";
        return message;
	}
	
	@Override	
	public String deleteShow(Integer show_id) throws Exception{
		ShowEntity pe = entityManager.find(ShowEntity.class,show_id);
        entityManager.remove(pe);
        return "Show deleted successfully";
	}
	
	public String minusSeats(Show show,Integer seats) throws Exception{
		String message = "";
        Integer s=show.getRem_seats();
		if(s<seats) {
        	message="Not enough seats";
        }
		else {
			ShowEntity entity = new ShowEntity();
			entity.setShow_id(show.getShow_id());
			entity.setTheatre_name(show.getTheatre_name());
	        entity.setDate(show.getDate());
	        entity.setMovie_name(show.getMovie_name());
	        entity.setRem_seats(s-seats);
	        entity.setTime(show.getTime());
	        entityManager.merge(entity);
	        message = "Seats booked successfully";
		}
		
        return message;
	}
	@Override
	public List<Show> getShowByMoT(String mot) throws Exception{
		List<Show> shows=null;
		List<ShowEntity> showEntities1=entityManager.createQuery("SELECT s from ShowEntity s WHERE s.movie_name= :movie_name",ShowEntity.class).
        		setParameter("movie_name",mot).getResultList();
		shows = new ArrayList<Show>();
        if(showEntities1.size()>=1) {
        	for(ShowEntity showEntity: showEntities1) {
    			Show show=new Show();
    			if(showEntity.getTheatre_name()!=null) {
    				show.setDate(showEntity.getDate());
    				show.setMovie_name(showEntity.getMovie_name());
    				show.setRem_seats(showEntity.getRem_seats());
    				show.setShow_id(showEntity.getShow_id());
    				show.setTime(showEntity.getTime());
    				show.setTheatre_name(showEntity.getTheatre_name());
    				shows.add(show);
    			}
    		}
            return shows;
        }
        else {
        	List<ShowEntity> showEntities2=entityManager.createQuery("SELECT o from ShowEntity o WHERE o.theatre_name= :theatre_name",ShowEntity.class).
            		setParameter("theatre_name",mot).getResultList();
        	shows = new ArrayList<Show>();
        	if(showEntities2.size() >=1) {
            	for(ShowEntity showEntity: showEntities2) {
        			Show show=new Show();
        			if(showEntity.getMovie_name()!=null) {
        				show.setDate(showEntity.getDate());
        				show.setMovie_name(showEntity.getMovie_name());
        				show.setRem_seats(showEntity.getRem_seats());
        				show.setShow_id(showEntity.getShow_id());
        				show.setTime(showEntity.getTime());
        				show.setTheatre_name(showEntity.getTheatre_name());
        				shows.add(show);
        			}
        		}
                return shows;
            }
        	return null;
        }
	}
	@Override
	public List<Booking> getAllBookings() throws Exception{
		Query query = entityManager.createQuery("select booking from BookingEntity booking");
		List<Booking> bookings=null;
		List<BookingEntity> bookingEntities = query.getResultList();		
		bookings = new ArrayList<Booking>();
		for(BookingEntity bookingEntity: bookingEntities) {
			Booking booking=new Booking();
			booking.setBooking_id(bookingEntity.getBooking_id());
			booking.setAmount(bookingEntity.getAmount());
			booking.setSeats(bookingEntity.getSeats());
			booking.setStatus(bookingEntity.getStatus());
			booking.setUser_id(bookingEntity.getUser_id());			
			ShowEntity showEntity = bookingEntity.getShow();
			if (showEntity != null) {
				Show show = new Show();
				show.setDate(showEntity.getDate());
				show.setShow_id(showEntity.getShow_id());
				show.setMovie_name(showEntity.getMovie_name());
				show.setTheatre_name(showEntity.getTheatre_name());
				show.setTime(showEntity.getTime());
				show.setRem_seats(showEntity.getRem_seats());
				booking.setShow(show);
			}
			bookings.add(booking);
		}
		return bookings;
	}
	@Override
	public List<Booking> getBookings(Integer user_id) throws Exception{
		List<BookingEntity> bookingEntities= entityManager.createQuery("SELECT o from BookingEntity o WHERE o.user_id= :user_id",
				BookingEntity.class).setParameter("user_id",user_id).getResultList();
		List<Booking> bookings=null;	
		bookings = new ArrayList<Booking>();
		for(BookingEntity bookingEntity: bookingEntities) {
			Booking booking=new Booking();
			booking.setBooking_id(bookingEntity.getBooking_id());
			booking.setAmount(bookingEntity.getAmount());
			booking.setSeats(bookingEntity.getSeats());
			booking.setStatus(bookingEntity.getStatus());
			booking.setUser_id(bookingEntity.getUser_id());
			
			ShowEntity showEntity = bookingEntity.getShow();
			if (showEntity != null) {
				Show show = new Show();
				show.setDate(showEntity.getDate());
				show.setShow_id(showEntity.getShow_id());
				show.setMovie_name(showEntity.getMovie_name());
				show.setTheatre_name(showEntity.getTheatre_name());
				show.setTime(showEntity.getTime());
				show.setRem_seats(showEntity.getRem_seats());
				booking.setShow(show);
			}
			bookings.add(booking);
		}
		return bookings;
	}
	@Override
	public String doBooking(Show show,Integer user_id,Integer seats) throws Exception{
		BookingEntity be=new BookingEntity();
		be.setUser_id(user_id);
		be.setSeats(seats);
		ShowEntity showEntity=entityManager.find(ShowEntity.class,show.getShow_id());
		be.setShow(showEntity);
		be.setStatus("Booked");
		String t=showEntity.getTheatre_name();
		TheatreEntity theatreEntity=entityManager.createQuery("SELECT o from TheatreEntity o WHERE o.theatre_name= :theatre_name",
				TheatreEntity.class).setParameter("theatre_name",t).getSingleResult();
		Integer amt=theatreEntity.getRate();
		be.setAmount(amt*seats);
		entityManager.persist(be);
		String s=minusSeats(show,seats);
		return s;		
	}
	@Override
	public String cancelBooking(Integer booking_id) throws Exception{
		BookingEntity be=entityManager.find(BookingEntity.class, booking_id);
		String s="Booking not cancelled";
		if(be.getStatus()=="Booked") {
			be.setStatus("Cancelled");
			ShowEntity showEntity=new ShowEntity();
			showEntity=be.getShow();
			Integer sh=showEntity.getShow_id();
			Integer st=be.getSeats();
			s=addCancelled(sh,st);
		}
		return s;

	}
	public String addCancelled(Integer sh_id,Integer st) throws Exception{
		ShowEntity se=entityManager.find(ShowEntity.class,sh_id);
		Integer d=se.getRem_seats();
		se.setRem_seats(d+st);
		entityManager.persist(se);
		return "Booking cancelled successfully";

	}


}
