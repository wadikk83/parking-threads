package com.epamtraining.parking;

import com.epamtraining.parking.entity.Car;
import com.epamtraining.parking.entity.Parking;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


@Slf4j
public class Main {


    public static void main(String[] args) throws Exception {

        final Parking parking = new Parking(5);
        int numberOfCars = 12;

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfCars);
        for (int i = 0; i < numberOfCars; i++) {
            executorService.submit(new Car(parking.getQueue(), parking.getPlaces())::park);
        }


        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);
    }


}
