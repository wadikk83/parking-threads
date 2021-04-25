package com.epamtraining.parking;

import com.epamtraining.parking.entity.Car;
import com.epamtraining.parking.entity.Parking;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


@Slf4j
public class Main {
    public static final int NUMBER_OF_PLACES = 5;
    public static final int NUMBERS_OF_CAR = 12;


    public static void main(String[] args) throws Exception {

        log.info("Application Parking is started at " +
                new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
        System.out.println("Start application");

        final Parking parking = new Parking(NUMBER_OF_PLACES);
        log.info("Create parking with " + NUMBER_OF_PLACES + " places");

        ExecutorService executorService = Executors.newFixedThreadPool(NUMBERS_OF_CAR);
        for (int i = 0; i < NUMBERS_OF_CAR; i++) {
            executorService.submit(new Car(parking.getQueue(), parking.getPlaces())::park);
        }

        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);

        log.info("Application Parking  is finished at " +
                new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
        log.info("==========================================================================");
    }


}
