package com.mediaRatingApp;

import java.util.ArrayList;
import java.util.List;

public class StreamingService {
    private String servicename;
    private double price;
    private List<Films> films;
    private List<TVShow> tvshows;

    public StreamingService(String name, double price) {
        this.servicename = name;
        this.price = price;
        this.films = new ArrayList<>();
        this.tvshows = new ArrayList<>();
    }

    public String getName() {
        return servicename;
    }

    public double getPrice() {
        return price;
    }

    public List<Films> getFilms() {
        return new ArrayList<>(films);
    }

    public boolean addFilm(Films film) {
        //Looking through the list of films checking if any of the items of type films match the film passed into add film
        for (Films existingFilm : films) {
            boolean condition = ((existingFilm.getName() == film.getName()) && (existingFilm.getYear() == film.getYear()));
            if (condition) {
                System.out.println("Another films matches the name of this film duplicate film");
                return false;
            }

        }
        // If film isnt found in list of current stored films then it is added to the list
        films.add(film);
        return true;
    }

    public boolean removeFilm(Films film) {
        return films.remove(film);
    }

    public boolean containsFilm(Films newFilm) {
        if (films.contains(newFilm)) {
            return true;
        }
        return false;
    }

    public List<TVShow> getTvshowslist(){
        return tvshows;
    }

    public boolean removeTvShow(TVShow showname){
        return tvshows.remove(showname);
    }

    public void addtvshow(TVShow tvshowname){
        tvshows.add(tvshowname);
    }
}
