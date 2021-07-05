package com.company;


import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Manager { //singleton
    private static Manager ourInstance = new Manager();

    private final List restaurantTables = new ArrayList
            (10);
    private int currentIndex = 0;

    private final Queue orderQueue = new ConcurrentLinkedQueue(); // очередь с заказами
    private final Queue dishesQueue = new ConcurrentLinkedQueue(); // очередь с готовыми блюдами

    public synchronized static Manager getInstance() {
        return ourInstance;
    }

    private Manager() { // создаем 10 столов
        for (int i = 0; i < 10; i++) {
            restaurantTables.add(new Table());
        }
    }

    public synchronized Table getNextTable() { // официант ходит по кругу от 1 стола к 10
        Table table = (Table) restaurantTables.get(currentIndex);
        currentIndex = (currentIndex + 1) % 10;
        return table;
    }

    public Queue getOrderQueue() {
        return orderQueue;
    }

    public Queue getDishesQueue() {
        return dishesQueue;
    }
}