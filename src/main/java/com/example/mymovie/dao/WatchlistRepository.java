package com.example.mymovie.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mymovie.model.Movie1;
import com.example.mymovie.model.User;
import com.example.mymovie.model.Watchlist;

@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist, Integer> {
	public List<Watchlist> findByUserId(Integer userId);
	public Watchlist findByMovieId(Integer movieId);
	public boolean existsByUserIdAndMovieId(Integer userId, Integer movieId);
	public Watchlist findByUserIdAndMovieId(Integer userId, Integer movieId);
	
	public void deleteByUserIdAndMovieId(Integer userId, Integer movieId);
	public void deleteByMovieId(Integer movieId);
	public List<Watchlist> findByUser(User user);
    public Optional<Watchlist> findByUserAndMovie(User user, Movie1 movie);

}