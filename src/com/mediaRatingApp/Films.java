package com.mediaRatingApp;

public class Films extends Media {
    private int year;
    private int runtime;

    public Films(String name, int year, String genre, int runtime) {
        super(name, genre); // Call superclass constructor
        this.year = year;
        this.runtime = runtime;
    }

    public int getYear() {
        return year;
    }

    public int getRuntime() {
        return runtime;
    }

    @Override
    public void displayDetails() {
        System.out.println("Film: " + getName() + ", Year: " + year + ", Genre: " + getGenre() +
                ", Rating: " + getRating() + ", Runtime: " + runtime + " minutes");
    }

}
