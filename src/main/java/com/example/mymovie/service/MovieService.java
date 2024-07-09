package com.example.mymovie.service;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.mymovie.model.Movie1;
import com.example.mymovie.model.User;

public interface MovieService {

	public List<Movie1> getMovies();
	public Movie1 findMovieById(Integer id);
	public Movie1 saveMovie(Movie1 movie1);
	public Movie1 updateMovie(Integer id, Movie1 movie1);
	public void deleteMovie(Integer id);
	public Movie1 updateWatchlistStatus(Integer id, boolean status);
	public List<Movie1> getUserWatchlist(Integer userId);
	public List<Movie1> getWatchlistMovies();
	public List<Movie1> getMoviesByUserId(Integer userId);






	
	



	

	

}
