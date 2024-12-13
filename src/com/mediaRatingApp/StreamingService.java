package com.mediaRatingApp;

import java.util.ArrayList;
import java.util.List;

public class StreamingService {
    private String servicename;
    private double price;
    private List<Films> films;
    private List<TVShow> tvshows;
    private  List<Media> listofMedia;



    
    public StreamingService(String name, double price) {
        this.servicename = name;
        this.price = price;
        this.films = new ArrayList<>();
        this.tvshows = new ArrayList<>();
        this.listofMedia = new ArrayList<>();;
    }

    public String getName() {
        return servicename;
    }

    public double getPrice() {
        return price;
    }

    public List<Films> getFilms() {
        List<Films> filmsList = new ArrayList<>();

        // Iterate through the list of media and only add objects of type films
        for (Media media : listofMedia) {
            if (media instanceof Films) {
                filmsList.add((Films) media);
            }
        }

        // Return the list of Films objects
        return filmsList;
    }

    public List<TVShow> getTvshowslist(){
        List<TVShow> tvshowlist = new ArrayList<>();

        // Iterate through the list of media and only add objects of type films
        for (Media media : listofMedia) {
            if (media instanceof TVShow) {
                tvshowlist.add((TVShow) media);
            }
        }

        // Return the list of Films objects
        return tvshowlist;
    }


    public boolean containsFilm(Films newFilm) {
        if (films.contains(newFilm)) {
            return true;
        }
        return false;
    }

    public void addMedia(Media media) {
        // Check if the media is either a Film or a TVShow

        if (media instanceof Films) {

            //Looking through the list of films checking if any of the items of type films match the film passed into add film

            for (Films existingFilm : films) {
                boolean condition = ((existingFilm.getName() == existingFilm.getName()) && (existingFilm.getYear() == existingFilm.getYear()));
                if (condition) {
                    System.out.println("Another films matches the name of this film duplicate film");
                    return;
                }

            }

            // If film isnt found in list of current stored films then it is added to the list
            listofMedia.add(media);

        } else if(media instanceof TVShow) {
            listofMedia.add(media);
        }else{
            System.out.println("Cannot add the media. Only Films or TVShows are allowed.");

        }
    }

    public void removeMedia(Media media){

        //function depending on the type of Media passed in removes the film or tvshow


        //function to remove a film
        if (media instanceof Films) {
            for (int i = 0; i < listofMedia.size(); i++) {
                Media media2 = listofMedia.get(i);
                if (media2 instanceof Films) {
                    Films existingFilm = (Films) media2;
                    if (existingFilm.getName().equals(existingFilm.getName()) && existingFilm.getYear() == existingFilm.getYear()) {
                        listofMedia.remove(i);

                    }
                }
            }


         //function to remove a tvshow
        }else if(media instanceof TVShow){
            for (int i = 0; i < listofMedia.size(); i++) {
                Media media2 = listofMedia.get(i);
                if (media2 instanceof TVShow) {
                    TVShow existingShow = (TVShow) media2;
                    if (existingShow.getName().equals(existingShow.getName()) && existingShow.getGenre().equals(existingShow.getGenre())) {
                        listofMedia.remove(i);
                    }
                }
            }
        }else{
            System.out.println("Cannot remove the media. Only Films or TVShows are allowed.");

        }
    }

    public List<Media> getMediaList(){
        return listofMedia;
    }

}
