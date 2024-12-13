package com.mediaRatingApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StreamingServiceManager {

    private List<StreamingService> services;
    ;



    public StreamingServiceManager(){
        this.services = new ArrayList<>();
    }
    public void addStreamingService(Scanner scanner) {
        //this function will take the inputs from the user and add the streaming service

        System.out.print("Enter the name of the new streaming service: ");
        String name_of_streaming_service = scanner.nextLine();
        System.out.print("Enter the price of the streaming service: ");
        double price_of_streaming_service = scanner.nextDouble();
        scanner.nextLine();

        //here new streamingsite is created with the details provided
        StreamingService newService = new StreamingService(name_of_streaming_service, price_of_streaming_service);
        services.add(newService);
        System.out.println("Streaming service added successfully!");
    }

    public List<StreamingService> accessListOfStreamingSites() {
        return services;
    }






}
