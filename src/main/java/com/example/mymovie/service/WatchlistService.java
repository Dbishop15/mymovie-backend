package com.example.mymovie.service;

import com.example.mymovie.model.Movie1;
import com.example.mymovie.model.Watchlist;

import java.util.List;

public interface WatchlistService {
	public Movie1 updateWatchlistStatus(Integer id, boolean status, Integer userId);
	public List<Movie1> getWatchlistByUserId(Integer userId); 
	public Watchlist addToWatchlist(Watchlist watchlist);
	public void updateWatchlistStatus(Integer movieId, boolean status);
	public void deleteFromWatchlist(Integer userId, Integer movieId);
}