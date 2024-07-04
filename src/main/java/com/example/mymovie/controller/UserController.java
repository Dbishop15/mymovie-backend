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
import com.example.mymovie.model.User;
import com.example.mymovie.service.MovieService;
import com.example.mymovie.service.UserService;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private MovieService movieService;

	private final Logger logger = LoggerFactory.getLogger(UserController.class);    


	    @PostMapping("/register")
	    public ResponseEntity<?> saveUser(@RequestBody User user) {
	        try {
	            User savedUser = userService.saveUser(user);
	            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	        } catch (IllegalArgumentException e) {
	            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	        }
	    }
	
	    @PostMapping("/login")
	    public ResponseEntity<User> login(@RequestBody User user) {
	        try {
	            User loggedInUser = userService.loginUser(user.getUsername(), user.getPassword());
	            return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
	        } catch (IllegalArgumentException e) {
	            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	        }
	    }
	    
	    @GetMapping("/users")
	    public ResponseEntity<List<User>> getUsers() {
	        List<User> users = userService.findAllUsers();
	        return new ResponseEntity<>(users, HttpStatus.OK);
	    }
	    
	    @GetMapping("/user/{id}")
	    public ResponseEntity<User> getUserById(@PathVariable int id) {
	        User user = userService.findUserById(id);
	        return new ResponseEntity<>(user, HttpStatus.OK);
	    }
	    
	    
	    @PutMapping("/user/{id}")
	    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
	        User updatedUser = userService.updateUser(id, user);
	        if (updatedUser != null) {
	            return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    @DeleteMapping("/user/{id}")
	    public ResponseEntity<String> deleteUserById(@PathVariable int id) {
	        String response = userService.deleteUserById(id);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	    
	    
	    
	    
//		 @GetMapping("/profile")
//	    public String profile(@RequestParam("userId") Integer userId, Model model) {
//	        User user = userService.findUserById(userId);
//	        if (user != null) {
//	        	List<Movie1> allMovies = movieService.getMoviesByUser(user);
//	            model.addAttribute("username", user.getUsername());
//	            model.addAttribute("movies", allMovies);
//	            model.addAttribute("userId", userId);
//	        } else {
//	            model.addAttribute("errorMessage", "User not found.");
//	            return "error";
//	        }
//	        return "profile";
//	    }
//
//	   @GetMapping("/signup")
//	    public String signup(Model model) {
//	        return "signup";
//	    }
//	
//	    @GetMapping("/login")
//	    public String login(Model model) {
//	        return "login";
//	    }
//	
//	    @GetMapping("/logout")
//	    public String logout(HttpServletRequest request, RedirectAttributes redirectAttributes) {
//	        HttpSession session = request.getSession(false);
//	        if (session != null) {
//	            session.invalidate();
//	        }
//	        redirectAttributes.addFlashAttribute("message", "You have been successfully logged out.");
//	        return "redirect:/";
//	
	  
}

