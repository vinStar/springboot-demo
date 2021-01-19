package com.example.springboot.handler.mqmessage;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AxxHandler implements IxxHandler {

    String name = "axx";

    @Override
    public void handlerMessage() {
        log.info("Axx handle message");
    }

    public void sendA() {
        log.info("name axxhandler");
    }
}
