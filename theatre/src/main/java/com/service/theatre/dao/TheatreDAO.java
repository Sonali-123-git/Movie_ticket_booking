package com.service.theatre.dao;
import java.util.List;
import com.service.theatre.model.Theatre;
public interface TheatreDAO {
	public List<Theatre> getAllTheatre() throws Exception;
	public Theatre getTheatreByTheatreName(String theatre_name) throws Exception;
	public Theatre getTheatreByTheatreId(Integer theatre_id) throws Exception;
	public String addTheatre(Theatre theatre) throws Exception;
	public String editTheatre(String theatre_name,Theatre theatre) throws Exception;	
	public String deleteTheatre(String theatre_name) throws Exception;
}
