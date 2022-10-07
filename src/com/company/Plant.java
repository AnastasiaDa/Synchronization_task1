package com.company;

import java.util.List;

public class Plant extends Thread implements Runnable {

    int time;
    List<Object> carList;

    public Plant(List<Object> carList, int time) {
        this.time = time;
        this.carList = carList;
    }

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            synchronized (carList) {
                carList.add("Car " + i);
                System.out.println("The plant Toyota has made 1 car ");
                carList.notify();
            }
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
