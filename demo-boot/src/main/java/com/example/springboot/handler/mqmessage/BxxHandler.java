package com.example.springboot.handler.mqmessage;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BxxHandler implements IxxHandler {

    @Override
    public void handlerMessage() {
        log.info("Bxx handle message");
    }
}
