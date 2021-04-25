package com.epamtraining.parking.entity;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;

@Slf4j
public class Parking {
    private final BlockingQueue<Integer> queue;
    private final CopyOnWriteArrayList<ParkingPlace> places;

    public Parking(int numberOfPlaces) {
        places = new CopyOnWriteArrayList<>();
        queue = new LinkedBlockingDeque<>(numberOfPlaces);
        for (int i = 0; i < numberOfPlaces; i++) {
            places.add(null);
            queue.add(i);
        }
    }

    public BlockingQueue<Integer> getQueue() {
        return queue;
    }

    public CopyOnWriteArrayList<ParkingPlace> getPlaces() {
        return places;
    }

    public List<ParkingPlace> getState() {
        return places;
    }

}
