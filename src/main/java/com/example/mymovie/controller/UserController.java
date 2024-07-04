package com.example.mymovie.controller;
import org.springframework.ui.Model;


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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mymovie.MymovieApplication;
import com.example.mymovie.model.Movie1;
import com.example.mymovie.model.User;
import com.example.mymovie.service.MovieService;
import com.example.mymovie.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

//import ch.qos.logback.core.model.Model;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
//@SessionAttributes({"username", "email"})
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private MovieService movieService;

	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
//	 @GetMapping("/")
//	    public String index(Model model) {
//	        List<Movie1> movies = movieService.getMovies();
//	        model.addAttribute("movies", movies);
//	        return "index";
//	    }
	
//	@GetMapping("/profile")
//    public String profile(@RequestParam(value = "username", required = true) String username, Model model) {
//        if (username == null || username.isEmpty()) {
//            model.addAttribute("errorMessage", "Username is required to view the profile.");
//            return "error";
//        }
//
//        User user = userService.findByUsername(username);
//        if (user != null) {
//            List<Movie1> movies = movieService.getMoviesByUser(user);
//            model.addAttribute("username", user.getUsername());
//            model.addAttribute("movies", movies);
//        } else {
//            model.addAttribute("errorMessage", "User not found.");
//            return "error";
//        }
//        return "profile";
//    }

	 @GetMapping("/profile")
	    public String profile(@RequestParam("userId") Integer userId, Model model) {
	        User user = userService.findUserById(userId);
	        if (user != null) {
	        	List<Movie1> allMovies = movieService.getMoviesByUser(user);
	            model.addAttribute("username", user.getUsername());
	            model.addAttribute("movies", allMovies);
	            model.addAttribute("userId", userId);
	        } else {
	            model.addAttribute("errorMessage", "User not found.");
	            return "error";
	        }
	        return "profile";
	    }
//	  @GetMapping("/watchlist")
//	    public String watchlist(@RequestParam(value = "username", required = false) String username, Model model) {
//	        if (username == null || username.isEmpty()) {
//	            model.addAttribute("errorMessage", "Username is required to view the watchlist.");
//	            return "error";
//	        }
//
//	        User user = userService.findByUsername(username);
//	        if (user != null) {
//	            List<Movie1> movies = user.getWatchlistMovies().stream().toList();
//	            model.addAttribute("username", user.getUsername());
//	            model.addAttribute("movies", movies);
//	        } else {
//	            model.addAttribute("errorMessage", "User not found.");
//	            return "error";
//	        }
//	        return "watchlist";
//	    }
	
	   @GetMapping("/signup")
	    public String signup(Model model) {
	        return "signup";
	    }
//
//	    @PostMapping("/register")
//	    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String email, Model model) {
//	        try {
//	            User newUser = new User(username, email, password, null);
//	            userService.saveUser(newUser);
//	            model.addAttribute("successMessage", "Signup successful! Please login.");
//	        } catch (Exception e) {
//	            model.addAttribute("errorMessage", "Signup failed. User might already exist.");
//	        }
//	        return "signup";
//	    }
	
	    @GetMapping("/login")
	    public String login(Model model) {
	        return "login";
	    }


//	    @PostMapping("/login")
//	    public String login(@RequestParam("username") String username,
//	                        @RequestParam("password") String password,
//	                        RedirectAttributes redirectAttributes, Model model) {
//	        User user = userService.loginUser(username, password);
//	        if (user != null) {
//	            redirectAttributes.addAttribute("userId", user.getId());
//	            return "redirect:/profile";
//	        } else {
//	            model.addAttribute("errorMessage", "Invalid username or password.");
//	            return "login";
//	        }
//	    }
	
	    @GetMapping("/logout")
	    public String logout(HttpServletRequest request, RedirectAttributes redirectAttributes) {
	        HttpSession session = request.getSession(false);
	        if (session != null) {
	            session.invalidate();
	        }
	        redirectAttributes.addFlashAttribute("message", "You have been successfully logged out.");
	        return "redirect:/";
	    }
	
//	    @PostMapping("/login")
//	    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
//	        try {
//	            User user = userService.loginUser(username, password);
//	            if(user != null) {
//	            model.addAttribute("userId", user.getId());
//	            model.addAttribute("username", user.getUsername());
//	            }
//	            return "login_success";  // Redirect to login success page
//	        } catch (Exception e) {
//	            model.addAttribute("errorMessage", "Invalid username or password");
//	            return "login";  // Stay on login page with error message
//	        }
//	    }
//	    
	   @GetMapping("/users")
	    public ResponseEntity<List<User>> getUsers() {
	        List<User> users = userService.findAllUsers();
	        return new ResponseEntity<>(users, HttpStatus.OK);
	    }
//
//	    @PostMapping("/register")
//	    public ResponseEntity<User> saveUser(@RequestBody User user) {
//	        User savedUser = userService.saveUser(user);
//	        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	    

	    @PostMapping("/register")
	    public ResponseEntity<?> saveUser(@RequestBody User user) {
	        try {
	            User savedUser = userService.saveUser(user);
	            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	        } catch (IllegalArgumentException e) {
	            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	        }
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
	    
//	    @PostMapping("/signup")
//	    public ResponseEntity<User> signup(@RequestBody User user) {
//	        try {
//	            User newUser = userService.saveUser(user);
//	            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
//	        } catch (IllegalArgumentException e) {
//	            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//	        }
//	    }

	    @PostMapping("/login")
	    public ResponseEntity<User> login(@RequestBody User user) {
	        try {
	            User loggedInUser = userService.loginUser(user.getUsername(), user.getPassword());
	            return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
	        } catch (IllegalArgumentException e) {
	            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	        }
	    }
}

