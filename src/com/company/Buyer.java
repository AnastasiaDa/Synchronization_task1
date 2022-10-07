package com.company;

import java.util.List;

public class Buyer extends Thread {

    int time;
    List<Object> carList;

    public Buyer(List<Object> carList, int time) {
        this.time = time;
        this.carList = carList;
    }

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            synchronized (carList) {
                System.out.println("The " + Thread.currentThread().getName() + " has entered the car dealership");
                if (carList.isEmpty()) {
                    System.out.println("There are no cars");
                    try {
                        carList.wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }
                System.out.println("The buyer " + Thread.currentThread().getName() + " has driven away in a new car");
                try {
                    carList.remove(0);
                } catch (IndexOutOfBoundsException ex) {
//выдает ошибку, на одной машине уезжают сразу 2 покупателя. Не знаю как исправить
                }
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }
}

