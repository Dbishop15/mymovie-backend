package com.example.mymovie.service;

import java.util.List;
import java.util.Optional;

import com.example.mymovie.model.Movie1;
import com.example.mymovie.model.User;

public interface UserService {

	public List<User> findAllUsers();
	
	public User findUserById(Integer id);

	public User updateUser(Integer id, User user);
	
	public String deleteUserById(Integer id);;

	public User saveUser(User user);
	
//    public User findByUsername(String username);
//    
//    public User findByEmail(String email);

	public User loginUser(String username, String password);

	public Optional<User> findUserByUsername(String username);

	public List<Movie1> getUserWatchlist(Integer userId);

	public void updateWatchlistStatus(Integer id, Integer movieId, boolean status);
    

}
