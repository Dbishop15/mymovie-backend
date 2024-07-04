package com.example.mymovie.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.example.mymovie.MymovieApplication;
import com.example.mymovie.dao.MovieRepository;
import com.example.mymovie.dao.UserRepository;
import com.example.mymovie.dao.WatchlistRepository;
import com.example.mymovie.model.Movie1;
import com.example.mymovie.model.User;
import com.example.mymovie.model.Watchlist;
import com.example.mymovie.service.MovieService;
import com.example.mymovie.service.UserService;
import com.example.mymovie.service.WatchlistService;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WatchlistController {
	
	

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private WatchlistService watchlistService;
    
    private final Logger logger = LoggerFactory.getLogger(WatchlistController.class);

//    @GetMapping("/watchlist/{userId}")
//    public List<Watchlist> getWatchlist(@PathVariable Integer userId) {
//        return watchlistService.getWatchlistByUserId(userId);
    
  
  @GetMapping("/watchlist/{userId}")
  public ResponseEntity<List<Movie1>> getWatchlist(@PathVariable Integer userId) {
      List<Movie1> watchlist = watchlistService.getWatchlistByUserId(userId);
      return new ResponseEntity<>(watchlist, HttpStatus.OK);
  }

    @PostMapping("/watchlist")
    public ResponseEntity<Watchlist> addToWatchlist(@RequestBody Watchlist watchlist) {
        Watchlist savedWatchlist = watchlistService.addToWatchlist(watchlist);
        return new ResponseEntity<>(savedWatchlist, HttpStatus.OK);
    }

    @PutMapping("/watchlist/{movieId}")
    public ResponseEntity<Void> updateWatchlistStatus(
            @PathVariable Integer movieId,
            @RequestParam boolean status) {
        watchlistService.updateWatchlistStatus(movieId, status);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/watchlist/{userId}/{movieId}")
    public ResponseEntity<Void> deleteFromWatchlist(@PathVariable Integer userId, @PathVariable Integer movieId) {
        watchlistService.deleteFromWatchlist(userId, movieId);
        return ResponseEntity.ok().build();
        
    }
//    @PostMapping("/watchlist")
//    public Watchlist addToWatchlist(@RequestBody Watchlist watchlist) {
//        return watchlistService.addToWatchlist(watchlist);
//    }
    @GetMapping("/watchlist")
    public ResponseEntity<List<Movie1>> getWatchlistMovies() {
        List<Movie1> movies = movieService.getWatchlistMovies();
        return ResponseEntity.ok(movies);
    }
    
}


//    @GetMapping("/watchlist")
//    public String watchlist(@RequestParam("userId") Integer userId, Model model) {
//        User user = userService.findUserById(userId);
//        List<Movie1> watchlist = movieService.getWatchlistByUser(user);
//        model.addAttribute("user", user);
//        model.addAttribute("userId", user.getId());
//        model.addAttribute("movies", watchlist);
//        return "watchlist";
//    }
//}