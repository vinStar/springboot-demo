package com.example.applistener.demolintener.applicationListener;

import com.example.applistener.demolintener.manager.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.concurrent.atomic.AtomicBoolean;

public class SpringStartListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private Manager haManager;

    private AtomicBoolean started = new AtomicBoolean(false);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (started.compareAndSet(false, true)) {
            System.out.println("ContextRefreshedEvent -> onApplicationEvent");
            haManager.frame();
        }
    }
}
