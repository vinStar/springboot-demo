package com.example.demo.task;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

@Slf4j
public class GoodsTask implements Callable<Boolean> {

    private String res;

    public GoodsTask(String res) {
        this.res = res;

    }

    @Override
    public Boolean call() throws Exception {
        try {
            String[] goodsIds = res.split(",");
            for (String goodsId : goodsIds) {

                log.info("goods id :  {}", goodsId);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
