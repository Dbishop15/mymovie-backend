package com.example.mymovie.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mymovie.dao.MovieRepository;
import com.example.mymovie.dao.UserRepository;
import com.example.mymovie.dao.WatchlistRepository;
import com.example.mymovie.exceptions.MovieNotFoundException;
import com.example.mymovie.exceptions.UserNotFoundException;
import com.example.mymovie.model.Movie1;
import com.example.mymovie.model.User;
import com.example.mymovie.service.MovieService;
import com.example.mymovie.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
    @Autowired
    private WatchlistRepository watchlistRepository;
    
    @Override
	public List<Movie1> getMovies() {
		Iterable<Movie1> movie1 =movieRepository.findAll();
		List<Movie1> list = new ArrayList<>();
		movie1.forEach(list::add);
		if(list.isEmpty()) {
		throw new MovieNotFoundException("No Movie Available!!");
		}
		return list;
	}

    @Override
	public Movie1 findMovieById(Integer id) {
		Optional<Movie1> movie1= movieRepository.findById(id);
		if(movie1.isPresent()) {
			return movie1.get();
		}
		else
			throw new MovieNotFoundException("No Movie Available!!");
	}
    
	@Transactional
    public Movie1 saveMovie(Movie1 movie1) {
        // Fetch the user from the database to ensure it is managed
        User user = userRepository.findById(movie1.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        movie1.setUser(user); // Set the managed user on the movie
        return movieRepository.save(movie1);
	}
	
	@Override
	public Movie1 updateMovie(Integer id, Movie1 movie1) {
		Movie1 movies= findMovieById(id);

	       if(movies!=null) {
	         	if(movie1.getTitle() != null)
	         		movies.setTitle(movie1.getTitle());
	         	if(movie1.getDirector() != null)
	         		movies.setDirector(movie1.getDirector());
	         	if(movie1.getReleaseYear() != 0)
	         		movies.setReleaseYear(movie1.getReleaseYear());
	         	if(movie1.getGenre() != null)
	         		movies.setGenre(movie1.getGenre());
	         	if(movie1.getRating() != 0)
	         		movies.setRating((long) movie1.getRating());
	       
           if (movie1.getLiked() != null) 
               movies.setLiked(movie1.getLiked());
           
           if (movie1.getWatchlist() != null) 
               movies.setWatchlist(movie1.getWatchlist());
           
	       if(movie1.getUser() != null)
	         		movies.setUser(movie1.getUser());
	
	            	movieRepository.save(movies);
	            	return  movies;
           }else 
        throw new MovieNotFoundException("Movie with given id: " + id + " is not available !!");        
}
	
    @Transactional
    public void deleteMovie(Integer id) {
        // First delete all related Watchlist entries
        watchlistRepository.deleteByMovieId(id);
        // Then delete the movie
        movieRepository.deleteById(id);
    }

	@Override
	public List<Movie1> getWatchlistMovies() {
        return movieRepository.findByWatchlistTrue();
    }
	
	public List<Movie1> getUserWatchlist(Integer userId) {
	        List<Movie1> watchlistMovies = movieRepository.findByUserIdAndWatchlistTrue(userId);
	        System.out.println("Watchlist Movies for User ID " + userId + ": " + watchlistMovies);
	        return watchlistMovies;
	    }
	 
	public Movie1 updateWatchlistStatus(Integer id, boolean status) {
        Optional<Movie1> movieOpt = movieRepository.findById(id);
        if (movieOpt.isPresent()) {
            Movie1 movie = movieOpt.get();
            movie.setWatchlist(status);
            return movieRepository.save(movie);
        }
        throw new RuntimeException("Movie not found");
    }
	
	public List<Movie1> getMoviesByUserId(Integer userId) {
        return movieRepository.findByUserId(userId);
    }

//	@Override
//	public Movie1 updateWatchlist(Integer id, boolean addToWatchlist) {
//		// TODO Auto-generated method stub
//		return null;
//	}



	
    
    
	
	

//	@Override
//	public List<Movie1> getMoviesByUser(User user) {
//	        // Assuming there is a method in the repository to get movies by user
//	        return movieRepository.findByUser(user);
//	    }
//	    
//	public Movie1 addMovieToWatchlist(Movie1 movie, Integer userId) {
//	        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//	        movie.setUser(user);
//	        movie.setWatchlist(true);
//	        return movieRepository.save(movie);
//	    }
//	
//    public List<Movie1> getAllMovies() {
//        return (List<Movie1>) movieRepository.findAll();
//    }
//
//    public Movie1 addMovieToWatchlist(Integer userId, Integer movieId) {
//        Movie1 movie = movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie not found"));
//        movie.setUser(new User());
//        movie.setWatchlist(true);
//        return movieRepository.save(movie);
//    }
   
	
//	  @Override
//	    public Movie1 updateWatchlist(int id, boolean watchlist) {
//	        Movie1 movie = findMovieById(id);
//	        if (movie != null) {
//	            movie.setWatchlist(watchlist);
//	            return movieRepository.save(movie);
//	        } else {
//	            throw new MovieNotFoundException("Movie with id " + id + " not found.");
//	        }
//	    }
//		    public Movie1 updateLiked(Integer id, boolean liked) {
//	        Movie1 movie = findMovieById(id);
//	        if (movie != null) {
//	            movie.setLiked(liked);
//	            return movieRepository.save(movie);
//	        }
//	        throw new MovieNotFoundException("This movie Id is not avaliable!!");
//	    }
}