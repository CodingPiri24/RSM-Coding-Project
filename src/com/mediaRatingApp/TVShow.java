package com.mediaRatingApp;

import java.util.ArrayList;
import java.util.List;

public class TVShow {

    private String name;
    private String genre;
    private List<Season> seasons;
    private double rating;


    public TVShow(String name, String genre) {
        this.name = name;
        this.genre = genre;
        this.seasons = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        if (rating >= 0 && rating <= 5) {
            this.rating = rating;
        } else {
            System.out.println("Rating must be between 0 and 5.");
        }
    }

    public void addSeason(Season season) {
        // Ensure no duplicate season numbers
        for (Season s : seasons) {
            if (s.getSeasonNumber() == season.getSeasonNumber()) {
                System.out.println("Season " + season.getSeasonNumber() + " already exists.");
                return;
            }
        }
        seasons.add(season);
    }

    public List<Season> getSeasons() {
        return seasons;
    }




}
