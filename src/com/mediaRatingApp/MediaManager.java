package com.mediaRatingApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MediaManager {

    private StreamingService filmManagersStreamingService;
    Scanner scanner;
    private List<StreamingService> serviclistofStreamingServiceses;
    private List<TVShow> tvshowslist;
    private  List<Media> listofMedia;

    public MediaManager(StreamingService selectedService, List<StreamingService> services,List<TVShow> tvshows, List<Media> mediaList) {
        this.filmManagersStreamingService = selectedService;
        this.serviclistofStreamingServiceses = services;
        this.scanner = new Scanner(System.in);
        this.tvshowslist = tvshows;
        this.listofMedia = mediaList;
    }


    private void filmManage(Films filmselection,StreamingService streamingService){
        System.out.println("\n1. Rate film");
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
                streamingService.removeMedia(filmselection);
                break;

        }
    }


    public boolean isSeasonInAnyService(String tvShowName, Season newSeason) {
        for (StreamingService service : serviclistofStreamingServiceses) {


            if (service.getTvshowslist() == null || service.getTvshowslist().isEmpty()) {
                continue;
            }
            for (TVShow tvShow : service.getTvshowslist()) {

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



    public void tvoptions(TVShow selectedTVshow){
        System.out.println("\n1. Rate TV show");
        System.out.println("2. Delete TV show");
        System.out.println("3. View seasons");
        System.out.println("4. Exit");
        System.out.println("Choose a option please: ");

        int choice4 = scanner.nextInt();
        scanner.nextLine();


        if(choice4 == 1){
            System.out.print("\nPlease enter a rating: ");
            double choice5 = scanner.nextInt();
            selectedTVshow.setRating(choice5);
            System.out.println("Successfully added rating ");
        }else if(choice4 == 2){
            //calling media method to remove type of media such as tv show here
            filmManagersStreamingService.removeMedia(selectedTVshow);
            System.out.println("Successfully deleted show: " + " " + selectedTVshow.getName());
        }else if(choice4 == 3){
            List<Season> seasonList = selectedTVshow.getSeasons();

            //presenting all the seasons to the user
            for (int i = 0; i < seasonList.size(); i++) {
                System.out.println((i) + ". " + seasonList.get(i).getSeasonNumber() + " " +seasonList.get(i).getYearOfRelease());
            }
        }else if(choice4 == 4){
            System.out.println("Returning to the previous menu...");
            return;
        }else{
            System.out.println("Invalid Rating enter a number from the option above");
        }
    }


    public void additionOfSeasons(int numberofseasons,TVShow newtvShow){
        //function called everytime we want to add a season
        for(int i = 0;i<numberofseasons;i++){

            System.out.print("\nEnter the year of release of season " + i + ": ");
            int yearofSeasonrelease = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter the number of Episodes for season " + i + ": ");
            int numberofEpisodes = scanner.nextInt();
            scanner.nextLine();

            Season newSeason = new Season(i,yearofSeasonrelease,numberofEpisodes);

            //condition to check if the season of a tv show exists in any other streaming service
            boolean condition = (isSeasonInAnyService(newtvShow.getName(),newSeason));

            if(condition){
                System.out.println("Couldnt add season");
            }else{

                newtvShow.addSeason(newSeason);
                System.out.println("Successfully added season to tv service");
            }



        }
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
                    List<Films> filmsList = filmManagersStreamingService.getFilms();
                    if (filmsList.isEmpty()) {
                        System.out.println("No available films.");
                        continue;
                    } else {
                        System.out.println("\nChoose a film ");
                        System.out.println("Available Films:");

                        //presenting every film to the user to pick from
                        for (int i = 0; i < filmsList.size(); i++) {
                            System.out.println((i + 1) + ". " + filmsList.get(i).getName());
                        }

                        System.out.print("Which film would you like to view: ");

                        int choice2 = scanner.nextInt();

                        scanner.nextLine();

                        //depending on the film selected method is called to get the film object from the list
                        Films filmselection = filmsList.get(choice2 - 1);

                        //film manage controls the adding of films to streaming services
                        filmManage(filmselection,filmManagersStreamingService);
                        break;
                    }


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

                    //create a new object of type film with the details entered by the user
                    Films newFilm = new Films(filmName, releaseYear,genre,runtime);

                    boolean filmExists = false;


                    //checking if film already exists in another streaming service
                    for (StreamingService service : serviclistofStreamingServiceses) {
                        if (service.containsFilm(newFilm)) {
                            System.out.println("Cannot add film as it already exists in another streaming service.");
                            filmExists = true;
                            break;
                        }
                    }

                    //only if it doesnt exist anywhere else it is added to media
                    if (!filmExists) {
                        filmManagersStreamingService.addMedia(newFilm);
                        System.out.println("Film added successfully!");
                    }
                    break;




                case 3:
                    System.out.println("\nTV SHOWS");

                    if(filmManagersStreamingService.getTvshowslist().isEmpty()){
                        System.out.println("No TV shows exist");
                        continue;
                    }

                    //presents out the tvshows that the user added to present the options available
                    for (int i = 0; i < filmManagersStreamingService.getTvshowslist().size(); i++) {
                        System.out.println((i + 1) + ". " + filmManagersStreamingService.getTvshowslist().get(i).getName());
                    }

                    System.out.print("Choose a TV show to view ");
                    int choice3 = scanner.nextInt();

                    scanner.nextLine();
                    TVShow selectedTVshow = filmManagersStreamingService.getTvshowslist().get(choice3 - 1);

                    //once a tvoption is selected a method is called to give options to either rate or remove film
                    tvoptions(selectedTVshow);

                    break;






                case 4:
                    System.out.print("\nEnter the name of the TV show: ");
                    String tvshowname = scanner.nextLine();


                    System.out.print("Enter the genre of the tv show: ");
                    String tvshowgenre = scanner.nextLine();

                    System.out.print("Enter the number of seasons for the tv show ");
                    int numberofseasons = scanner.nextInt();

                    scanner.nextLine();

                    /*tv show object is created with user input then passed into a method which handles method of creating
                    and adding a new season with checks in place*/

                    TVShow newtvShow = new TVShow(tvshowname,tvshowgenre);
                    filmManagersStreamingService.addMedia(newtvShow);

                    additionOfSeasons(numberofseasons,newtvShow);
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