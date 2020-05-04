package com.service.movie.api;

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
import com.service.movie.model.Movie;
import com.service.movie.service.MovieServiceImpl;
import com.service.movie.model.Response;

@CrossOrigin
@RestController
@RequestMapping("movie")
public class MovieAPI {
	@Autowired
	Environment environment;
	@Autowired
	MovieServiceImpl serviceImpl;
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<List<Movie>> getAllMovie() throws Exception {
		List<Movie> movies=new ArrayList<Movie>();
		try {
			movies=serviceImpl.getAllMovie();
			return new ResponseEntity<List<Movie>>(movies,HttpStatus.OK);
		} 
		catch (Exception e) {			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,	e.getMessage(), e);
		}
	}
	@RequestMapping(value="/{movie_name}",method=RequestMethod.GET)
	public ResponseEntity <Movie> getMovieByMovieName(@PathVariable String movie_name) throws Exception{
		try {
            Movie movie=null;
            movie=serviceImpl.getMovieByMovieName(movie_name);
            ResponseEntity<Movie> response=new ResponseEntity<Movie>(movie, HttpStatus.OK);
            return response;
			}
		catch(Exception e) {
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
			}
	}
	
//	@RequestMapping(value="id/{movie_id}",method=RequestMethod.GET)
//	public ResponseEntity <Movie> getMovieByMovieId(@PathVariable Integer movie_id) throws Exception{
//		try {
//            Movie movie=null;
//            movie=serviceImpl.getMovieByMovieId(movie_id);
//            ResponseEntity<Movie> response=new ResponseEntity<Movie>(movie, HttpStatus.OK);
//            return response;
//			}
//		catch(Exception e) {
//            System.out.println(e);
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
//			}
//	}
	
	@RequestMapping(value="/",method=RequestMethod.POST)
	public ResponseEntity<Response> addMovie(@RequestBody Movie movie) throws Exception{
        String message = "";
		serviceImpl.addMovie(movie);
		message=" Movie added successfully";
		 Response resp = new Response();
			resp.setResp(message);
			ResponseEntity<Response> response = new ResponseEntity<Response>(resp, HttpStatus.CREATED);
			return response;
	}
	
	@RequestMapping(value="/{movie_name}",method=RequestMethod.PUT)
	public ResponseEntity <Response> editMovie(@RequestBody Movie movie,@PathVariable ("movie_name") String movie_name) throws Exception{
		try {
		serviceImpl.editMovie(movie_name,movie);
        String message="Movie updated successfully";
        Response resp = new Response();
		resp.setResp(message);
        ResponseEntity<Response> response=new ResponseEntity<Response>(resp, HttpStatus.OK);
        return response;}
		catch(Exception e){
			System.out.println(e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
		}
	}

	
	@RequestMapping(value="/{movie_name}",method=RequestMethod.DELETE)

    public ResponseEntity<Response> deleteMovie(@PathVariable String movie_name) throws Exception  {
		try {
		serviceImpl.deleteMovie(movie_name);
        String message="Movie deleted successfully";
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