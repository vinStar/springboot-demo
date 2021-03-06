package com.example.springboot.manager;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Manager {

    private Runnable haTask = new Runnable() {

        @Override
        public void run() {
            while (true) {
                try {
                    log.info("===  run  ===");

                    TimeUnit.SECONDS.sleep(5L);
                } catch (Throwable e) {

                }
            }
        }
    };

    public Manager(long sleepSecondsBeforeStartup, long sleepSecondsBeforeExit, String locks) {
        log.info("Manager::{},{},{}", sleepSecondsBeforeExit, sleepSecondsBeforeStartup, locks);
        this.haFrameThread = new Thread(haTask, "ha-frame");
        //haFrameThread.start();
    }

    private Thread haFrameThread;
}
