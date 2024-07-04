//package com.example.mymovie.service.impl;
//
//import com.example.mymovie.model.Movie1;
//import com.example.mymovie.model.User;
//import com.example.mymovie.model.Watchlist;
//import com.example.mymovie.dao.MovieRepository;
//import com.example.mymovie.dao.UserRepository;
//import com.example.mymovie.dao.WatchlistRepository;
//import com.example.mymovie.service.WatchlistService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class WatchlistServiceImpl implements WatchlistService {
//
//    @Autowired
//    private WatchlistRepository watchlistRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private MovieRepository movieRepository;
//    
////    public List<Movie1> findMoviesByUserId(Integer userId) {
////        return watchlistRepository.findByUserId(userId);
////    }
////
////    public void addMovieToWatchlist(Integer userId, Integer movieId) {
////        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
////        Movie1 movie = movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie not found"));
////        user.getWatchlist().add(movie);
////        userRepository.save(user);
////    }
////
////    public void removeMovieFromWatchlist(Integer userId, Integer movieId) {
////        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
////        Movie1 movie = movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie not found"));
////        user.getWatchlist().remove(movie);
////        userRepository.save(user);
////    }
//    @Override
//    public List<Watchlist> getWatchlistByUser(Integer userId) {
//        return watchlistRepository.findByUserId(userId);
//    }
//
//    @Override
//    public Watchlist addToWatchlist(Integer userId, Integer movieId, Boolean liked) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//        Movie1 movie = movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie not found"));
//
//        Watchlist watchlist = new Watchlist(user, movie, liked);
//        return watchlistRepository.save(watchlist);
//    }
////    public List<Movie1> findMoviesByUserId(Integer userId) {
////        return watchlistRepository.findByUserId1(userId);
////    }
//
//    @Override
//    public void removeFromWatchlist(Integer userId, Integer movieId) {
//        List<Watchlist> watchlistEntries = watchlistRepository.findByUserId(userId);
//        for (Watchlist entry : watchlistEntries) {
//            if (entry.getMovie().getId() == movieId) {
//                watchlistRepository.delete(entry);
//                break;
//            }
//        }
//    }
//
//	@Override
//	public void removeMovieFromWatchlist(Integer userId, Integer movieId) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void addMovieToWatchlist(Integer userId, Integer movieId) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public List<Movie1> findMoviesByUserId(Integer userId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}