package com.example.mymovie.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.mymovie.model.Movie1;
import com.example.mymovie.model.User;


@Repository
public interface MovieRepository extends JpaRepository<Movie1, Integer>  {
     
	public List<Movie1> findAll();
	public void deleteById (Integer id);
	public List<Movie1> findByUser(User user);
    public Optional<Movie1> findById(Integer id);  // Use 'id' instead of 'movieId'
	public List<Movie1> findByUserAndWatchlistTrue(User user);
	public List<Movie1> findByWatchlistTrue();
	public List<Movie1> findByUserIdAndWatchlistTrue(Integer userId);

	public List<Movie1> findByUserId(Integer userId);
	public Movie1 save(Movie1 movie);
	
	
	// Method to get movies where watchlist is true
}


