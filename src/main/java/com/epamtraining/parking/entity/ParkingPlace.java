package com.epamtraining.parking.entity;

public class ParkingPlace {
    private final long id;
    private final long parkingTimeout;

    public ParkingPlace(long timeout, long id) {
        this.parkingTimeout = timeout;
        this.id = id;
    }

    @Override
    public String toString() {
        return "ParkingPlace - ID->" + id + ", timeout->" + parkingTimeout;
    }
}
