package com.example.mymovie.controller;

import com.example.mymovie.model.Watchlist;
import com.example.mymovie.service.MovieService;
import com.example.mymovie.service.UserService;
import com.example.mymovie.service.WatchlistService;
import com.example.mymovie.model.Movie1;
import com.example.mymovie.model.User;
import com.example.mymovie.dao.WatchlistRepository;
import com.example.mymovie.dao.MovieRepository;
import com.example.mymovie.dao.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WatchlistController {

    @Autowired
    private WatchlistRepository watchlistRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private MovieService movieService;
    
    @Autowired
    private WatchlistService watchlistService;
    
    @Autowired
    private UserService userService;

    @GetMapping("/watchlist")
    public ResponseEntity<List<Movie1>> getWatchlistMovies() {
        List<Movie1> movies = movieService.getWatchlistMovies();
        return ResponseEntity.ok(movies);
    }
    
    @PostMapping("/watchlist")
    public ResponseEntity<?> addToWatchlist(@RequestBody WatchlistRequest request) {
        User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));
        Movie1 movie = movieRepository.findById(request.getMovieId())
            .orElseThrow(() -> new RuntimeException("Movie not found"));

        Watchlist watchlist = new Watchlist();
        watchlist.setUser(user);
        watchlist.setMovie(movie);

        watchlistRepository.save(watchlist);
        return ResponseEntity.ok("Movie added to watchlist");
    }
  
    @GetMapping("/watchlist/{userId}")
    public ResponseEntity<List<Movie1>> getWatchlist(@PathVariable Integer userId) {
        List<Movie1> watchlist = watchlistService.getWatchlistByUserId(userId);
        return new ResponseEntity<>(watchlist, HttpStatus.OK);
    }
    
    @PutMapping("/watchlist/{movieId}")
    public ResponseEntity<?> updateWatchlistStatus(
        @PathVariable Integer movieId, 
        @RequestParam boolean status, 
        @RequestBody User user
    ) {
        try {
            userService.updateWatchlistStatus(user.getId(), movieId, status);
            return ResponseEntity.ok("Watchlist status updated successfully");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/watchlist/{userId}/{movieId}")
    public ResponseEntity<Void> deleteFromWatchlist(@PathVariable Integer userId, @PathVariable Integer movieId) {
        watchlistService.deleteFromWatchlist(userId, movieId);
        return ResponseEntity.ok().build();
    }

    public static class WatchlistRequest {
        private Integer userId;
        private Integer movieId;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Integer getMovieId() {
            return movieId;
        }

        public void setMovieId(Integer movieId) {
            this.movieId = movieId;
        }
    }
}
