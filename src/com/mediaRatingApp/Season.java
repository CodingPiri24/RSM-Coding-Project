package com.mediaRatingApp;

public class Season {

    private int seasonNumber;
    private int yearOfRelease;
    private int numberOfEpisodes;

    public Season(int seasonNumber, int yearOfRelease, int numberOfEpisodes) {
        this.seasonNumber = seasonNumber;
        this.yearOfRelease = yearOfRelease;
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

}
