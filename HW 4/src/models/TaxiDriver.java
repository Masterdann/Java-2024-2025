package models;

import interfaces.Taxi;
import interfaces.Dispatcher;
import java.util.Random;


public class TaxiDriver implements Taxi, Runnable {
    private final String name;
    private final Dispatcher dispatcher;
    private volatile boolean busy;

    public TaxiDriver(String name, Dispatcher dispatcher) {
        this.name = name;
        this.dispatcher = dispatcher;
        this.busy = false;
    }

    @Override
    public void fulfillOrder(String orderDetails) throws InterruptedException {
        busy = true;
        String currentOrder = orderDetails;

        System.out.println(name + " received order: " + orderDetails);
        Random random = new Random();
        int sleepTime = 1000 + random.nextInt(4000);
        Thread.sleep(sleepTime);

        System.out.println(name + " completed order: " + orderDetails);
        busy = false;
        currentOrder = null;

        dispatcher.notifyTaxiFree(this);
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (!busy) {
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            System.err.println(name + " was interrupted.");
            Thread.currentThread().interrupt();
        }
    }

    public boolean isBusy() {
        return busy;
    }

    public String getName() {
        return name;
    }
}