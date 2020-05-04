package com.service.theatre.dao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.service.theatre.model.Theatre;
import com.service.theatre.entity.TheatreEntity;
@Repository
public class TheatreDAOImpl implements TheatreDAO {
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public List<Theatre> getAllTheatre() throws Exception{
		Query query = entityManager.createQuery("select theatre FROM TheatreEntity theatre");
		List<Theatre> theatres = null;			
		List<TheatreEntity> theatreEntities = query.getResultList();		
		theatres = new ArrayList<Theatre>();
		for (TheatreEntity theatreEntity  : theatreEntities) {
			Theatre theatre = new Theatre();
			theatre.setTheatre_id(theatreEntity.getTheatre_id());
			theatre.setTheatre_name(theatreEntity.getTheatre_name());
			theatre.setLocation(theatreEntity.getLocation());
			theatre.setRate(theatreEntity.getRate());
			theatre.setSeats(theatreEntity.getSeats());
			theatres.add(theatre);
		}
		return theatres;
	}
	@Override
	public Theatre getTheatreByTheatreName(String theatre_name) throws Exception{
		Theatre searched_theatre=null;
		TheatreEntity theatreEntity=entityManager.createQuery("SELECT t from TheatreEntity t WHERE t.theatre_name= :theatre_name",TheatreEntity.class).
        		setParameter("theatre_name",theatre_name).getSingleResult();
        if(theatreEntity !=null) {
        	searched_theatre=new Theatre();
        	searched_theatre.setTheatre_id(theatreEntity.getTheatre_id());
        	searched_theatre.setTheatre_name(theatreEntity.getTheatre_name());
        	searched_theatre.setLocation(theatreEntity.getLocation());
        	searched_theatre.setRate(theatreEntity.getRate());
        	searched_theatre.setSeats(theatreEntity.getSeats());
        }
        return searched_theatre;
	}
	@Override
	public Theatre getTheatreByTheatreId(Integer theatre_id) throws Exception{
		Theatre searched_theatre=null;
		TheatreEntity theatreEntity=entityManager.find(TheatreEntity.class,theatre_id);
        if(theatreEntity !=null) {
        	searched_theatre=new Theatre();
        	searched_theatre.setTheatre_id(theatreEntity.getTheatre_id());
        	searched_theatre.setTheatre_name(theatreEntity.getTheatre_name());
        	searched_theatre.setLocation(theatreEntity.getLocation());
        	searched_theatre.setRate(theatreEntity.getRate());
        	searched_theatre.setSeats(theatreEntity.getSeats());
        }
        return searched_theatre;
	}
	@Override
	public String addTheatre(Theatre theatre) throws Exception{
		String message = "";
		TheatreEntity entity = new TheatreEntity();
		entity.setTheatre_name(theatre.getTheatre_name());
        entity.setLocation(theatre.getLocation());
        entity.setRate(theatre.getRate());
        entity.setSeats(theatre.getSeats());
        entityManager.persist(entity);
        message = "Theatre added successfully";
        return message;
	}
	@Override
	public String editTheatre(String theatre_name,Theatre theatre) throws Exception{
		TheatreEntity pe = entityManager.createQuery("SELECT t from TheatreEntity t WHERE t.theatre_name= :theatre_name",TheatreEntity.class).
        		setParameter("theatre_name",theatre_name).getSingleResult();
		if(theatre.getTheatre_name()!=null)
			pe.setTheatre_name(theatre.getTheatre_name());
		if(theatre.getLocation()!=null)
			pe.setLocation(theatre.getLocation());
		if(theatre.getRate()!=null)
			pe.setRate(theatre.getRate());
		if(theatre.getSeats()!=null)
			pe.setSeats(theatre.getSeats());
		entityManager.persist(pe);
		return "Theatre details updated successfully";
	}
	@Override	
	public String deleteTheatre(String theatre_name) throws Exception{
		TheatreEntity pe = entityManager.createQuery("SELECT t from TheatreEntity t WHERE t.theatre_name= :theatre_name",TheatreEntity.class).
        		setParameter("theatre_name",theatre_name).getSingleResult();
        entityManager.remove(pe);
        return "Theatre deleted successfully";
	}
}
