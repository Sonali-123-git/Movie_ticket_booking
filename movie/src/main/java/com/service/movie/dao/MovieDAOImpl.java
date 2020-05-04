package com.service.movie.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.service.movie.entity.MovieEntity;

import com.service.movie.model.Movie;


@Repository
public class MovieDAOImpl implements MovieDAO{
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public List<Movie> getAllMovie() throws Exception{
		Query query = entityManager.createQuery("select movie FROM MovieEntity movie");
		List<Movie> movies = null;			
		List<MovieEntity> movieEntities = query.getResultList();		
		movies = new ArrayList<Movie>();
		for (MovieEntity movieEntity  : movieEntities) {
			Movie movie = new Movie();
			movie.setMovie_id(movieEntity.getMovie_id());
			movie.setMovie_name(movieEntity.getMovie_name());
			movie.setDirector(movieEntity.getDirector());
			movie.setRating(movieEntity.getRating());
			movie.setGenre(movieEntity.getGenre());
			movies.add(movie);
		}
		return movies;
	}
	@Override
	public Movie getMovieByMovieName(String movieName) throws Exception{
		Movie searched_movie=null;
		MovieEntity movieEntity=entityManager.createQuery("SELECT m from MovieEntity m WHERE m.movie_name= :movie_name",MovieEntity.class).
        		setParameter("movie_name",movieName).getSingleResult();
        if(movieEntity !=null) {
            System.out.println("hello user entity");            
            searched_movie=new Movie();
            searched_movie.setMovie_id(movieEntity.getMovie_id());
            searched_movie.setMovie_name(movieEntity.getMovie_name());
            searched_movie.setDirector(movieEntity.getDirector());
            searched_movie.setRating(movieEntity.getRating());
            searched_movie.setGenre(movieEntity.getGenre());
        }
        return searched_movie;
	}
	
	@Override
	public Movie getMovieByMovieId(Integer movie_id) throws Exception{
		Movie searched_movie=null;
		MovieEntity movieEntity=entityManager.find(MovieEntity.class,movie_id);
        if(movieEntity !=null) {
            System.out.println("hello user entity");            
            searched_movie=new Movie();
            searched_movie.setMovie_id(movieEntity.getMovie_id());
            searched_movie.setMovie_name(movieEntity.getMovie_name());
            searched_movie.setDirector(movieEntity.getDirector());
            searched_movie.setRating(movieEntity.getRating());
            searched_movie.setGenre(movieEntity.getGenre());
        }
        return searched_movie;
	}
	
	@Override
	public String addMovie(Movie movie) throws Exception{
		String message = "";
		MovieEntity entity = new MovieEntity();
		entity.setMovie_name(movie.getMovie_name());
        entity.setDirector(movie.getDirector());
        entity.setRating(movie.getRating());
        entity.setGenre(movie.getGenre());
        entityManager.persist(entity);
        message = "Movie added successfully";
        return message;
	}
	@Override
	public String editMovie(String movie_name,Movie movie) throws Exception{
		MovieEntity pe = entityManager.createQuery("SELECT m from MovieEntity m WHERE m.movie_name= :movie_name",MovieEntity.class).
        		setParameter("movie_name",movie_name).getSingleResult();
		if(movie.getMovie_name()!=null)
			pe.setMovie_name(movie.getMovie_name());
		if(movie.getDirector()!=null)
			pe.setDirector(movie.getDirector());
		if(movie.getRating()!=null)
			pe.setRating(movie.getRating());
		if(movie.getGenre()!=null)
			pe.setGenre(movie.getGenre());
		entityManager.persist(pe);
		return "Movie details updated successfully";
	}
	@Override
	public String deleteMovie(String movie_name) throws Exception{
		MovieEntity movieEntity=entityManager.createQuery("SELECT m from MovieEntity m WHERE m.movie_name= :movie_name",MovieEntity.class).
        		setParameter("movie_name",movie_name).getSingleResult();
        entityManager.remove(movieEntity);
        return "Movie deleted successfully";

	}




}

