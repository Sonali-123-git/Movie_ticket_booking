package com.service.theatre.api;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.service.theatre.model.Theatre;
import com.service.theatre.service.TheatreServiceImpl;
import com.service.theatre.model.Response;
@CrossOrigin
@RestController
@RequestMapping("theatre")
public class TheatreAPI {
	@Autowired
	Environment environment;
	@Autowired
	TheatreServiceImpl serviceImpl;
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<List<Theatre>> getAllTheatre() throws Exception {
		List<Theatre> theatres=new ArrayList<Theatre>();
		try {
			theatres=serviceImpl.getAllTheatre();
			return new ResponseEntity<List<Theatre>>(theatres,HttpStatus.OK);
		} 
		catch (Exception e) {			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,	e.getMessage(), e);
		}
	}
	@RequestMapping(value="/{theatre_name}",method=RequestMethod.GET)
	public ResponseEntity <Theatre> getTheatreByTheatreName(@PathVariable String theatre_name) throws Exception{
		try {
			Theatre theatre=null;
			theatre=serviceImpl.getTheatreByTheatreName(theatre_name);
            ResponseEntity<Theatre> response=new ResponseEntity<Theatre>(theatre, HttpStatus.OK);
            return response;
			}
		catch(Exception e) {
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
			}
	}
//	@RequestMapping(value="id/{theatre_id}",method=RequestMethod.GET)
//	public ResponseEntity <Theatre> getTheatreByTheatreId(@PathVariable Integer theatre_id) throws Exception{
//		try {
//			Theatre theatre=null;
//			theatre=serviceImpl.getTheatreByTheatreId(theatre_id);
//            ResponseEntity<Theatre> response=new ResponseEntity<Theatre>(theatre, HttpStatus.OK);
//            return response;
//			}
//		catch(Exception e) {
//            System.out.println(e);
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
//			}
//	}
	@RequestMapping(value="/",method=RequestMethod.POST)
	public ResponseEntity<Response> addTheatre(@RequestBody Theatre theatre) throws Exception{
        String message = "";
		serviceImpl.addTheatre(theatre);
		message=" Theatre added successfully";
		 Response resp = new Response();
			resp.setResp(message);
			ResponseEntity<Response> response = new ResponseEntity<Response>(resp, HttpStatus.CREATED);
			return response;
	}
	@RequestMapping(value="/{theatre_name}",method=RequestMethod.PUT)
	public ResponseEntity <Response> editTheatre(@RequestBody Theatre theatre,@PathVariable ("theatre_name") String theatre_name) throws Exception{
		try {
		serviceImpl.editTheatre(theatre_name,theatre);
        String message="Theatre updated successfully";
        Response resp = new Response();
		resp.setResp(message);
        ResponseEntity<Response> response=new ResponseEntity<Response>(resp, HttpStatus.OK);
        return response;}
		catch(Exception e){
			System.out.println(e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
		}
	}
	@RequestMapping(value="/{theatre_name}",method=RequestMethod.DELETE)

    public ResponseEntity<Response> deleteTheatre(@PathVariable String theatre_name) throws Exception  {
		try {
		serviceImpl.deleteTheatre(theatre_name);
        String message="Theatre deleted successfully";
		Response resp = new Response();
		resp.setResp(message);           
        ResponseEntity<Response> response=new ResponseEntity<Response>(resp,HttpStatus.OK);
        return response;}
		catch(Exception e) {
			System.out.println(e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
		}
}
}
