package com.mediaRatingApp;

public class Films {
    private String name;
    private int year;
    private String genre;
    private double rating;
    private int runtime;

    public Films(String name, int year, String genre, int runtime) {
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.runtime = runtime;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }



    public double getRating() {
        return rating;
    }

    public int getRuntime(){
        return runtime;
    }

    public void setRating(double rating) {
        if (rating >= 0 && rating <= 5) {
            this.rating = rating;
        } else {
            System.out.println("Please give a rating between 0 and 5");
        }
    }






}
