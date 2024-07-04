package com.example.mymovie.service;

import com.example.mymovie.dao.WatchlistRepository;
import com.example.mymovie.model.Movie1;
import com.example.mymovie.model.Watchlist;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WatchlistService {
    @Autowired
    private WatchlistRepository watchlistRepository;
    
    @Autowired
    public WatchlistService(WatchlistRepository watchlistRepository) {
        this.watchlistRepository = watchlistRepository;
    }
    
//    public List<Watchlist> getWatchlistByUserId(Integer userId) {
//        return watchlistRepository.findByUserId(userId);
//    }
//
//    public Watchlist addToWatchlist(Watchlist watchlist) {
//        return watchlistRepository.save(watchlist);
//    }
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

	

}