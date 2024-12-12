package com.mediaRatingApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MediaManager {

    private StreamingService filmManagersStreamingService;
    Scanner scanner;
    private List<StreamingService> serviclistofStreamingServiceses;
    private List<TVShow> tvshowslist;

    public MediaManager(StreamingService selectedService, List<StreamingService> services,List<TVShow> tvshows) {
        this.filmManagersStreamingService = selectedService;
        this.serviclistofStreamingServiceses = services;
        this.scanner = new Scanner(System.in);
        this.tvshowslist = tvshows;
    }

    private void filmManage(Films filmselection,StreamingService streamingService){
        System.out.println("1. Rate film");
        System.out.println("2. Remove film");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");

        int choice3 = scanner.nextInt();
        scanner.nextLine();

        switch(choice3){
            case 1:
                System.out.print("Enter the rating of the film: ");
                double rating = scanner.nextDouble();
                filmselection.setRating(rating);
                break;
            case 2:
                streamingService.removeFilm(filmselection);
                break;

        }
    }

    public boolean isSeasonInAnyService(String tvShowName, Season newSeason) {
        for (StreamingService service : serviclistofStreamingServiceses) {


            if (service.getTvshowslist() == null || service.getTvshowslist().isEmpty()) {
                System.out.println("tvlist is empty");
                continue;
            }
            for (TVShow tvShow : service.getTvshowslist()) {
                System.out.println("tvlist is not empty");

                // Check if the TV show name matches
                if (tvShow.getName().equals(tvShowName)) {

                    // Check if the season exists
                    for (Season season : tvShow.getSeasons()) {
                        if (season.getSeasonNumber() == newSeason.getSeasonNumber()) {
                            System.out.println("Season " + newSeason.getSeasonNumber() +
                                    " already exists in TV show \"" + tvShowName +
                                    "\" on service \"" + service.getName() + "\".");
                            return true;
                        }
                    }
                }
            }
        }

        // No matching season was found
        return false;
    }






    public void accessMedia() {

        while (true) {
            System.out.println("\n" + filmManagersStreamingService.getName());
            System.out.println("1. View Existing Films");
            System.out.println("2. Add new Film");
            System.out.println("3. View Existing TV shows");
            System.out.println("4. Add new TV show");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("\nChoose a film ");
                    List<Films> filmsList = filmManagersStreamingService.getFilms();
                    if (filmsList.isEmpty()) {
                        System.out.println("No available films.");
                    } else {
                        System.out.println("\nAvailable Films:");
                        for (int i = 0; i < filmsList.size(); i++) {
                            System.out.println((i) + ". " + filmsList.get(i).getName());
                        }

                        System.out.println("Which film would you like to view: ");

                        int choice2 = scanner.nextInt();

                        scanner.nextLine();
                        Films filmselection = filmsList.get(choice2);
                        filmManage(filmselection,filmManagersStreamingService);


                    }
                    break;

                case 2:

                    System.out.print("\nEnter the name of the film: ");
                    String filmName = scanner.nextLine();

                    System.out.print("Enter the release year of the film: ");
                    int releaseYear = scanner.nextInt();

                    scanner.nextLine();

                    System.out.print("Enter the genre of the film: ");
                    String genre = scanner.nextLine();


                    System.out.print("Enter the runtime of the film: ");
                    int runtime = scanner.nextInt();

                    scanner.nextLine();

                    Films newFilm = new Films(filmName, releaseYear,genre,runtime);

                    boolean filmExists = false;


                    for (StreamingService service : serviclistofStreamingServiceses) {
                        if (service.containsFilm(newFilm)) {
                            System.out.println("Cannot add film as it already exists in another streaming service.");
                            filmExists = true;
                            break;
                        }
                    }

                    if (!filmExists) {
                        filmManagersStreamingService.addFilm(newFilm);
                        System.out.println("Film added successfully!");
                    }
                    break;
                case 3:
                    System.out.println("\nTV SHOWS");

                    for (int i = 0; i < tvshowslist.size(); i++) {
                        System.out.println((i) + ". " + tvshowslist.get(i).getName());
                    }
                    System.out.print("###Choose a TV show to view### ");
                    int choice3 = scanner.nextInt();

                    scanner.nextLine();
                    TVShow selectedTVshow = tvshowslist.get(choice3);

                    System.out.print("1. Rate TV show");
                    System.out.print("2. Delete TV show");
                    System.out.print("Choose a option please: ");

                    int choice4 = scanner.nextInt();
                    scanner.nextLine();

                    if(choice4 == 1){
                        System.out.println("Please enter a rating: ");
                        double choice5 = scanner.nextInt();
                        selectedTVshow.setRating(choice5);
                    }else if(choice == 2){
                        filmManagersStreamingService.removeTvShow(selectedTVshow);
                    }
                case 4:
                    System.out.print("\nEnter the name of the TV show: ");
                    String tvshowname = scanner.nextLine();


                    System.out.print("Enter the genre of the tv show: ");
                    String tvshowgenre = scanner.nextLine();

                    System.out.print("Enter the number of seasons for the tv show ");
                    int numberofseasons = scanner.nextInt();

                    scanner.nextLine();

                    TVShow newtvShow = new TVShow(tvshowname,tvshowgenre);
                    filmManagersStreamingService.addtvshow(newtvShow);


                    for(int i = 0;i<numberofseasons;i++){

                        System.out.println("Enter the year of release of season " + i + ": ");
                        int yearofSeasonrelease = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Enter the numberof Episodes " + i + ": ");
                        int numberofEpisodes = scanner.nextInt();
                        scanner.nextLine();

                        Season newSeason = new Season(i,yearofSeasonrelease,numberofEpisodes);

                        boolean condition = (isSeasonInAnyService(newtvShow.getName(),newSeason));

                            if(condition){
                                System.out.println("unlucky");
                            }else{

                                newtvShow.addSeason(newSeason);
                                System.out.println("success");
                            }



                    }
                    break;

                case 5:
                    // Exit to Previous Menu
                    System.out.println("Returning to the previous menu...");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }



}
