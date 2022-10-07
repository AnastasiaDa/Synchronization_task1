package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    final static int TIME_TO_PRODUCE = 1000;
    final static int TIME_TO_RETURN = 1300;

    public static void main(String[] args) throws InterruptedException, IndexOutOfBoundsException {

        List<Object> cars = new ArrayList<>();

        ThreadGroup group = new ThreadGroup("Group");
        Plant plant = new Plant(cars, TIME_TO_PRODUCE);
        Buyer buyer = new Buyer(cars, TIME_TO_RETURN);

        Thread threadPlant = new Thread(group, plant);

        Thread threadBuyer1 = new Thread(group, buyer, "Buyer 1");
        Thread threadBuyer2 = new Thread(group, buyer, "Buyer 2");
        Thread threadBuyer3 = new Thread(group, buyer, "Buyer 3");

        threadBuyer1.start();
        threadBuyer2.start();
        threadBuyer3.start();

        threadPlant.start();
        threadPlant.join();

        group.interrupt();

    }
}
