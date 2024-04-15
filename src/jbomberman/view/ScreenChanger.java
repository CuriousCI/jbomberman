package jbomberman.view;

import java.util.ArrayList;
import jbomberman.util.*;

public class ScreenChanger implements Observable<Screen> {
    ArrayList<Observer<Screen>> subscribers = new ArrayList<>();

    @SafeVarargs
    public ScreenChanger(Observer<Screen>... observers) {
        for (var observer : observers)
            subscribe(observer);
    }

    @Override
    public void notifySubscribers(Screen data) {
        for (var subscriber : subscribers)
            subscriber.actionPerformed(data);
    }

    @Override
    public void subscribe(Observer<Screen> subscriber) {
        this.subscribers.add(subscriber);
    }
}
