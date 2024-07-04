package com.example.mymovie.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.mymovie.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public Optional<User> findById(int id);
	public User save(User user);
	public void deleteById (int id);
	public List<User> findAll();
	
	public User findByUsername(String username);
	public User findByEmail(String email);
	public Optional<User> findUserById(int id);
	public boolean existsByUsername(String username);
	public boolean existsByEmail(String email);
	
	




	  //public Customer findById(int id);

	  //public List<Customer> findByFirstName(String firstName);
	  
	  }