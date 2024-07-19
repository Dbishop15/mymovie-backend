package com.example.mymovie.controller;
import com.example.mymovie.model.Movie1;
import com.example.mymovie.service.MovieService;
import com.example.mymovie.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MovieController {
	
    @Autowired
    MovieService movieService;
    
    @Autowired
    UserService userService;
    
    
//    private final Logger logger = LoggerFactory.getLogger(MovieController.class);


    @GetMapping("/movies")
    public ResponseEntity<List<Movie1>> getMovies() {
        List<Movie1> movies = movieService.getMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
        
    }
    
    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie1> getMovieById(@PathVariable Integer id) {
        Movie1 movie = movieService.findMovieById(id);
        if (movie != null) {
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/movies")
    public ResponseEntity<Movie1> saveMovie(@RequestBody Movie1 movie1) {
        Movie1 savedMovie = movieService.saveMovie(movie1);
        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
    }
  
    @PutMapping("/movies/{id}")
    public ResponseEntity<Movie1> updateUser(@PathVariable Integer id, @RequestBody Movie1 movie1) {
        Movie1 updatedMoive = movieService.updateMovie(id, movie1);
        if (updatedMoive != null) {
            return new ResponseEntity<>(updatedMoive, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/movies/watchlist")
    public ResponseEntity<List<Movie1>> getMovies(@RequestParam(value = "watchlist", required = false) Boolean watchlist) {
        List<Movie1> movies;
        if (watchlist != null && watchlist) {
            movies = movieService.getWatchlistMovies();
        } else {
            movies = movieService.getMovies();
        }
        return ResponseEntity.ok(movies);
        
    }
    
    @GetMapping("/movies/user/{userId}/watchlist")
    public ResponseEntity<?> getUserWatchlist(@PathVariable Integer userId) {
        try {
            List<Movie1> watchlist = userService.getUserWatchlist(userId);
            System.out.println("Watchlist data: " + watchlist); // Debugging log
            if (watchlist == null) {
                return new ResponseEntity<>("Watchlist not found", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(watchlist);
        } catch (Exception e) {
            System.err.println("Error fetching watchlist: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    
    @PutMapping("/movies/{id}/watchlist")
    public ResponseEntity<Movie1> updateWatchlistStatus(@PathVariable Integer id, @RequestParam boolean status) {
        Movie1 updatedMovie = movieService.updateWatchlistStatus(id, status);
        return ResponseEntity.ok(updatedMovie);
    }
    
    @GetMapping("/movies/user/{userId}")
    public ResponseEntity<List<Movie1>> getMoviesByUserId(@PathVariable Integer userId) {
        List<Movie1> movies = movieService.getMoviesByUserId(userId);
        return ResponseEntity.ok(movies);
    }
    
    
//    @PostMapping("/movie/{id}/like")
//    public String updateMovieLiked(@PathVariable int id, @RequestParam boolean liked, @RequestParam Integer userId, RedirectAttributes redirectAttributes) {
//        Movie1 updatedMovie = movieService.updateLiked(id, liked);
//        if (updatedMovie != null) {
//            redirectAttributes.addFlashAttribute("message", "Movie liked status updated successfully");
//        } else {
//            redirectAttributes.addFlashAttribute("errorMessage", "Movie not found");
//        }
//        return "redirect:/profile?userId=" + userId;
//    }
// 
//    @PostMapping("/movie/{id}/watchlist")
//    public String updateMovieWatchlist(@PathVariable int id, @RequestParam boolean watchlist, @RequestParam Integer userId, RedirectAttributes redirectAttributes) {
//        Movie1 updatedMovie = movieService.updateWatchlist(id, watchlist);
//        if (updatedMovie != null) {
//            String message = watchlist ? "Movie added to watchlist" : "Movie removed from watchlist";
//            redirectAttributes.addFlashAttribute("message", message);
//        } else {
//            redirectAttributes.addFlashAttribute("errorMessage", "Movie not found");
//        }
//        return "redirect:/watchlist?userId=" + userId;
//    }
//    
//
//    @GetMapping("/{id}/edit")
//    public String editMovieForm(@PathVariable Integer id, Model model) {
//        Movie1 movie = movieService.findMovieById(id);
//        model.addAttribute("movie", movie);
//        return "profile";
//    }
//
//    @PostMapping("/{id}/update")
//    public String updateMovieField(@PathVariable Integer id, @RequestParam String field, @RequestParam String value, Model model) {
//        movieService.updateMovieField(id, field, value);
//        return "redirect:/movies";
//    }
    
}

