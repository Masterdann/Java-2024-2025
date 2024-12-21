package models;

import interfaces.Dispatcher;
import exceptions.NoAvailableTaxiException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaxiDispatcher implements Dispatcher {
    private final Queue<TaxiDriver> availableTaxis;
    private final Lock lock;
    private final Condition taxiAvailableCondition;

    public TaxiDispatcher() {
        this.availableTaxis = new ArrayDeque<>();
        this.lock = new ReentrantLock();
        this.taxiAvailableCondition = lock.newCondition();
    }

    @Override
    public void placeOrder(String orderDetails) throws NoAvailableTaxiException, InterruptedException {
        lock.lock();
        try {
            while (availableTaxis.isEmpty()) {
                System.out.println("waiting for available tacis");
                taxiAvailableCondition.await();
            }

            TaxiDriver taxi = availableTaxis.poll();
            System.out.println("assigned to: " + taxi.getName());
            new Thread(() -> {
                try {
                    taxi.fulfillOrder(orderDetails);
                } catch (InterruptedException e) {
                    System.err.println("interrupted for " + taxi.getName());
                    Thread.currentThread().interrupt();
                }
            }).start();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void notifyTaxiFree(TaxiDriver taxi) {
        lock.lock();
        try {
            availableTaxis.offer(taxi);
            System.out.println(taxi.getName() + " is free");
            taxiAvailableCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void registerTaxi(TaxiDriver taxi) {
        lock.lock();
        try {
            availableTaxis.offer(taxi);
            System.out.println("Taxi registered: " + taxi.getName());
            taxiAvailableCondition.signal();
        } finally {
            lock.unlock();
        }
    }
}