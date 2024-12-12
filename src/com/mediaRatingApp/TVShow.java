package com.mediaRatingApp;

import java.util.ArrayList;
import java.util.List;

public class TVShow extends Media {
    private List<Season> seasons;

    public TVShow(String name, String genre) {
        super(name, genre); // Call superclass constructor
        this.seasons = new ArrayList<>();
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

    @Override
    public void displayDetails() {
        System.out.println("TV Show: " + getName() + ", Genre: " + getGenre() +
                ", Rating: " + getRating() + ", Seasons: " + seasons.size());
    }

}