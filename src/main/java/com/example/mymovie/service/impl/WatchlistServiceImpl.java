package com.example.mymovie.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mymovie.model.Movie1;
import com.example.mymovie.model.Watchlist;
import com.example.mymovie.service.WatchlistService;

import jakarta.transaction.Transactional;

import com.example.mymovie.dao.WatchlistRepository;

@Service
public class WatchlistServiceImpl implements WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;
    
    
    public List<Movie1> getWatchlistByUserId(Integer userId) {
        return watchlistRepository.findByUserId(userId)
                                  .stream()
                                  .map(Watchlist::getMovie)
                                  .collect(Collectors.toList());
    }

    public Watchlist addToWatchlist(Watchlist watchlist) {
        return watchlistRepository.save(watchlist);
    }

    public void updateWatchlistStatus(Integer movieId, boolean status) {
        Watchlist watchlist = watchlistRepository.findByMovieId(movieId);
        if (watchlist != null) {
            watchlist.setStatus(status);
            watchlistRepository.save(watchlist);
        }
    }
    
    @Transactional
    public void deleteFromWatchlist(Integer userId, Integer movieId) {
        watchlistRepository.deleteByUserIdAndMovieId(userId, movieId);
    }

	@Override
	public Movie1 updateWatchlistStatus(Integer id, boolean status, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}


}