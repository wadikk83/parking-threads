package com.epamtraining.parking.entity;

import com.epamtraining.parking.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class CarTest {

    @Mock
    Parking parking;

    @BeforeEach
    void setUp() {
        parking = new Parking(3);
    }

    @Test
    void constructorParkingPlace() {
        Assertions.assertEquals(3, parking.getPlaces().size());
    }

    @Test
    void checkQueue() {
        int numbersOfCar = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numbersOfCar);
        for (int i = 0; i < numbersOfCar; i++) {
            executorService.submit(() -> {
                new Car(parking.getQueue(), parking.getPlaces()).timeout = 1000000;
            });
        }
        Assertions.assertEquals(3, parking.getQueue().size());
    }

    @Test
    void timeoutPark() {
        int numbersOfCar = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numbersOfCar);
        for (int i = 0; i < numbersOfCar; i++) {
            executorService.submit(() -> {
                Car car = new Car(parking.getQueue(), parking.getPlaces());
                car.timeout = 10000;
                car.park();
            });
        }

        System.out.println(Main.leavingCars);
        Assertions.assertEquals(Main.leavingCars,0);
    }
}