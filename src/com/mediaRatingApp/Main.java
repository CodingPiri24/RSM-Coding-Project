package com.mediaRatingApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    private List<StreamingService> services;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StreamingServiceManager serviceManager = new StreamingServiceManager();


        while (true) {
            //Main menu to present options to user
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Add a New Streaming Service");
            System.out.println("2. Access an Existing Streaming Service");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();


            switch (choice) {
                case 1:
                    // Add new streaming service
                    serviceManager.addStreamingService(scanner);
                    break;
                case 2:
                    List<StreamingService> allServices = serviceManager.accessListOfStreamingSites();
                    if (allServices.isEmpty()) {
                        System.out.println("No services available.");
                    } else {
                        //If list isnt empty then services they have added are presented
                        System.out.println("\nFrom these options please pick which streaming service to access");
                        for (int i = 0; i < allServices.size(); i++) {
                            System.out.println((i + 1) + ". " + allServices.get(i).getName());
                        }

                        System.out.print("Enter your choice: ");
                        //store users choice into server choice
                        int serviceChoice = scanner.nextInt();
                        scanner.nextLine();
                        StreamingService selectedService = allServices.get(serviceChoice - 1);
                        String nameofStreamingService = selectedService.getName();

                        //creating a list of services and tvshowslist as these will be changes in mediamanager accordingly

                        List<StreamingService> services = serviceManager.accessListOfStreamingSites();
                        List<TVShow> tvshowslist = selectedService.getTvshowslist();
                        List<Media> mediaList = selectedService.getMediaList();
                        MediaManager mediaManager = new MediaManager(selectedService,services,tvshowslist,mediaList);

                        //call to mediaManager class as that class handles the rest of the requests with the tv shows and films1
                        mediaManager.accessMedia();

                    }
                    break;
                case 3:
                    // Exit the program
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }
    }

}