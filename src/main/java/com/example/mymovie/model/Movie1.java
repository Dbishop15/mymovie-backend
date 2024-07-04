package com.example.mymovie.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie1{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "director", nullable = false)
    private String director;

    @Column(name = "releaseYear", nullable = false)
    private int releaseYear;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "rating")
    private float rating;

    @Column(name = "url")
    private String url;

    @Column(name = "liked", nullable = false)
    private Boolean liked = false;

    @Column(name = "watchlist", nullable = false)
    private Boolean watchlist = false;
 
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Watchlist> watchlists;

//    @ManyToOne(fetch = FetchType.EAGER)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",nullable = true)
    private User user;
    
    
//    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Watchlist> watchlistEntries = new HashSet<>();
    
//    @ManyToMany(mappedBy = "likedMovies")
//    private Set<User> usersWhoLiked = new HashSet<>();
//
//    @ManyToMany(mappedBy = "watchlistMovies")
//    private Set<User> usersWhoWatchlisted = new HashSet<>();
    
    // Constructors
    public Movie1() {}

    public Movie1(String title, String director, int releaseYear, String genre, float rating, String url, Boolean liked, Boolean watchlist, User user) {
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.rating = rating;
        this.url = url;
        this.liked = liked;
        this.watchlist= watchlist;
        this.user = user;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public Boolean getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(Boolean watchlist) {
        this.watchlist = watchlist;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
    
}