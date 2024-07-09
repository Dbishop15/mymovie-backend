package com.example.mymovie.model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private List<Movie1> movie1;
//  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//  private List<Movie1> movie1;
    
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Watchlist> watchlist;
    
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Movie1> movie1;

	
    
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Watchlist> watchlist = new HashSet<>();
    
    // Constructors
    public User() {}

    public User(String username, String email, String password,Set<String> roles, List<Movie1> movie1) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.movie1 = movie1;
        
      
        		
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
    public List<Movie1> getMovie1() {
        return movie1;
    }

    public void setMovie1(List<Movie1> movie1) {
        this.movie1 = movie1;
    }
  

}

