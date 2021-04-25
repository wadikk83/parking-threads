package com.epamtraining.parking.entity;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Car {
    long id;
    long timeout;
    private final BlockingQueue<Integer> queue;
    private final CopyOnWriteArrayList<ParkingPlace> places;

    public Car(BlockingQueue<Integer> queue, CopyOnWriteArrayList<ParkingPlace> places) {
        timeout = (new Random().nextInt(10) + 1) * 100L;
        this.queue = queue;
        this.places = places;
    }

    public void park() {
        id = Thread.currentThread().getId();
        Integer place;

        try {
            System.out.println("Машина с id " + id + " прибыла на стоянку со временем ожидания в очереди " + timeout);


            //place = queue.take();

            place = queue.poll(timeout, TimeUnit.MILLISECONDS);
            if (place == null) {

                System.out.println("Машина с id " + id + " устала ждать и уезжает");
                Thread.currentThread().interrupt();
                //Thread.currentThread().stop();
            }

        } catch (InterruptedException e) {
            log.error(e.toString());
            return;
        }

        if (!Thread.currentThread().isInterrupted()) {
            long parkingTimeout = (new Random().nextInt(10) + 1) * 100L;
            System.out.println("Машина с id " + id + " занимает " + parkingTimeout + " миллисекунд на месте " + place);

            ParkingPlace parkingPlace = new ParkingPlace(parkingTimeout, id);
            try {
                places.set(place, parkingPlace);
                try {
                    Thread.sleep(timeout);
                } catch (InterruptedException e) {
                    log.error(e.toString());
                }
            } catch (Exception e) {
                log.error(e.toString());
            } finally {
                System.out.println("Машина с id " + id + " покидает парковочное место " + place);
                places.set(place, null);
                queue.add(place);
            }
        }
    }
}
