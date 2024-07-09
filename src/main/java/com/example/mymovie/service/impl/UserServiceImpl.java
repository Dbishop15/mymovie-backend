package com.example.mymovie.service.impl;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.mymovie.exceptions.UserNotFoundException;
import com.example.mymovie.model.Movie1;
import com.example.mymovie.model.User;
import com.example.mymovie.service.UserService;
import com.example.mymovie.dao.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRoles() == null) {
            user.setRoles(new HashSet<>());
            user.getRoles().add("ROLE_USER");
        }
        return userRepository.save(user);
    }
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public User loginUser(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException("Invalid username or password");
        }

        User user = optionalUser.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UserNotFoundException("Invalid username or password");
        }

        return user;
    }
	
//	public User saveUser(User user) {
//        if (userRepository.existsByUsername(user.getUsername())) {
//            throw new IllegalArgumentException("Username already exists");
//        }
//        if (userRepository.existsByEmail(user.getEmail())) {
//            throw new IllegalArgumentException("Email already exists");
//        }
//        return userRepository.save(user);
//    }
//	
//	@Override
//    public User loginUser(String username, String password) {
//        User user = userRepository.findByUsername(username);
//        if (user == null || !user.getPassword().equals(password)) {
//            throw new UserNotFoundException("Invalid username or password");
//        }
//        return user;
//    }
	
	@Override
	public List<User> findAllUsers() {
		Iterable<User> users =userRepository.findAll();
		List<User> list = new ArrayList<>();
		users.forEach(list::add);
		return list;
		
	}
	
	@Override
	public User findUserById(Integer id) {
		Optional<User> cust= userRepository.findById(id);
		if(cust.isPresent()) {
			return cust.get();
		}else {
		
		}
		throw new UserNotFoundException("No User Available!!");
	}

	@Override
	public User updateUser(Integer id, User user) {

		User cust = (User) findUserById(id);
       if(cust!=null) {
         	if(user.getUsername() != null)
         		cust.setUsername(user.getUsername());
         	if(user.getEmail() != null)
         		cust.setEmail(user.getEmail());
           
            if(user.getPassword() != null)
            	cust.setPassword(user.getPassword());

            	userRepository.save(cust);
            	return  cust;
       }
       else{
           throw new UserNotFoundException("User with given id: " + id +  " is not available !!");
       }
	}
	
	@Override
	public String deleteUserById(Integer id) {
		
		User user = findUserById(id);
        if(user!=null){
            userRepository.deleteById(id);
            return  "User Deleted Successfully";
        }else{
        	throw new UserNotFoundException("No User Available!!");	
        }
		
	}
	@Override
	public List<Movie1> getUserWatchlist(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateWatchlistStatus(Integer id, Integer movieId, boolean status) {
		// TODO Auto-generated method stub
		
	}


	 
	 

//	@Override
//	 public User findByUsername(String username) {
//	        return userRepository.findByUsername(username);
//	    }
//	    
//
//	@Override
//	public User findByEmail(String email) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
