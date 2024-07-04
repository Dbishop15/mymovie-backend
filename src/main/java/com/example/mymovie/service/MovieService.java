package com.example.mymovie.service;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.mymovie.model.Movie1;
import com.example.mymovie.model.User;

public interface MovieService extends CrudRepository<Movie1,Integer>  {

	public List<Movie1> getMovies();


	public Movie1 findMovieById(Integer id);

	public String deleteMovieById(Integer id);

	public Movie1 saveMovie(Movie1 movie1);
	public Movie1 updateMovie(Integer id, Movie1 movie1);
	public Movie1 updateLiked(Integer id, boolean liked);
//	public Movie1 updateWatchlist(Integer id, boolean watchlist);
	 Movie1 updateMovieField(Integer id, String field, String value);

	public List<Movie1> getMoviesByUser(User user);
	public Movie1 updateWatchlist(Integer id, boolean addToWatchlist);
	public List<Movie1> getWatchlistByUser(User user);


	public Movie1 updateWatchlist(int id, boolean watchlist);




	public Movie1 updateWatchlistStatus(Integer id, boolean status);


	public List<Movie1> getUserWatchlist(Integer userId);


	public List<Movie1> getWatchlistMovies();


	public Movie1 updateWatchlistStatus(Integer id, boolean status, Integer userId);





	public List<Movie1> getMoviesByUserId(Integer userId);


	public void deleteMovie(Integer id);
	



	

	

}
