package com.mediaRatingApp;

public abstract class Media {
    private String name;
    private String genre;
    private double rating;

    public Media(String name, String genre) {
        this.name = name;
        this.genre = genre;
        this.rating = 0.0; // Default rating
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


    public abstract void displayDetails();
}
