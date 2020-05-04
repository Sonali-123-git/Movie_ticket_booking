package com.service.theatre.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.service.theatre.dao.TheatreDAO;
import com.service.theatre.model.Theatre;
import com.service.theatre.service.TheatreService;

@Service(value="TheatreServiceImpl")
@Transactional
public class TheatreServiceImpl implements TheatreService{
	@Autowired
	private TheatreDAO theatreDao;
	@Override
	public List<Theatre> getAllTheatre() throws Exception{
		return theatreDao.getAllTheatre();
	}
	@Override
	public Theatre getTheatreByTheatreName(String theatre_name) throws Exception{
		Theatre searched_theatre=null;
		searched_theatre=theatreDao.getTheatreByTheatreName(theatre_name);
        if (searched_theatre == null) {
            throw new Exception("Service.THEATRE_NOT_FOUND");
        }

        return searched_theatre;
	}
	@Override
	public Theatre getTheatreByTheatreId(Integer theatre_id) throws Exception{
		Theatre searched_theatre=null;
		searched_theatre=theatreDao.getTheatreByTheatreId(theatre_id);
        if (searched_theatre == null) {
            throw new Exception("Service.THEATRE_NOT_FOUND");
        }

        return searched_theatre;
	}
	@Override
	public String addTheatre(Theatre theatre) throws Exception{
		return theatreDao.addTheatre(theatre);
	}
	@Override
	public String editTheatre(String theatre_name,Theatre theatre) throws Exception{
		return theatreDao.editTheatre(theatre_name,theatre);

	}
	@Override	
	public String deleteTheatre(String theatre_name) throws Exception{
		Theatre theatre=theatreDao.getTheatreByTheatreName(theatre_name);
        if (theatre == null) {
            throw new Exception("Service.THEATRE_NOT_FOUND");
}                              

        else 
        	return theatreDao.deleteTheatre(theatre_name);
	}
}
