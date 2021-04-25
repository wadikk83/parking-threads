package com.epamtraining.parking.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingTest {

    @Test
    void createParking() {
        Parking parking = new Parking(5);
        Assertions.assertEquals(parking.getPlaces().size(),5);


    }

}