package com.service.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.service.movie.dao.MovieDAO;
import com.service.movie.model.Movie;
import com.service.movie.service.MovieService;
@Service(value="MovieServiceImpl")
@Transactional
public class MovieServiceImpl implements MovieService{

	@Autowired
	private MovieDAO movieDao; 

	@Override
	public List<Movie> getAllMovie() throws Exception {
		return movieDao.getAllMovie();
	}
	@Override
	public Movie getMovieByMovieName(String movieName) throws Exception {
        Movie searched_mov=null;
        searched_mov=movieDao.getMovieByMovieName(movieName);
        if (searched_mov == null) {
            throw new Exception("Service.MOVIE_NOT_FOUND");
        }

        return searched_mov;
	}
	
	@Override
	public Movie getMovieByMovieId(Integer movie_id) throws Exception {
        Movie searched_mov=null;
        searched_mov=movieDao.getMovieByMovieId(movie_id);
        if (searched_mov == null) {
            throw new Exception("Service.MOVIE_NOT_FOUND");
        }

        return searched_mov;
	}
	
	@Override
	public String addMovie(Movie movie) throws Exception{
        return movieDao.addMovie(movie);

	}
	@Override
	public String editMovie(String movie_name,Movie movie) throws Exception{
		return movieDao.editMovie(movie_name,movie);

	}
	@Override
	public String deleteMovie(String movie_name) throws Exception{
		Movie movie=movieDao.getMovieByMovieName(movie_name);
        if (movie == null) {
            throw new Exception("Service.MOVIE_NOT_FOUND");
}                              

        else 
        	return movieDao.deleteMovie(movie_name);

	}

}
