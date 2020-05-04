package com.service.movie.dao;

import java.util.List;
import com.service.movie.model.Movie;
public interface MovieDAO {
	public List<Movie> getAllMovie() throws Exception;
	public Movie getMovieByMovieName(String movieName) throws Exception;
	public Movie getMovieByMovieId(Integer movie_id) throws Exception;
	public String addMovie(Movie movie) throws Exception;
	public String editMovie(String movie_name,Movie movie) throws Exception;	
	public String deleteMovie(String movie_name) throws Exception;

}
