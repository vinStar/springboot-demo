package com.example.springboot.junit;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * @author lt
 * @date 2019/11/8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class ObservePattern {
    @Test
    public void observeTest() {
        SimpleObservable doc = new SimpleObservable("Hello Java");
        SimpleObserver view = new SimpleObserver(doc);
        doc.changeInstate("Hello Scala");
    }

}

// 被观察者
class SimpleObservable extends Observable {
    private String inState;

    public SimpleObservable(String inState) {
        this.inState = inState;
    }

    public String getState() {
        return inState;
    }

    public void changeInstate(String newState) {
        if (!newState.equals(inState)) {
            this.inState = newState;
            //setChanged() 后 notifyObservers() 才生效
            setChanged();
        }
        notifyObservers();
    }
}

// 观察者
class SimpleObserver implements Observer {
    public SimpleObserver(SimpleObservable so) {
        so.addObserver(this);
    }

    public void update(Observable o, Object arg) {
        System.out.println("Data has changed to" + ((SimpleObservable) o).getState());
    }
}
