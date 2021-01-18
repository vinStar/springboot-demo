package com.example.springboot.junit;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
public class MultiIf {
    // 字典表解决多 if 问题
    Map<String, Function<String, String>> actionMappings = new HashMap<>();

    void init() {

        // When init
        actionMappings.put("type1", (someParams) -> {
            return doAction1(someParams);

        });
        actionMappings.put("type2", (someParams) -> {
            return doAction2(someParams);
        });
        actionMappings.put("type3", (someParams) -> {
            return doAction3(someParams);
        });

        actionMappings.put("type4", this::doAction1);

// 省略 null 判断
        //actionMappings.get("value1").apply("someParams");
    }

    String doAction1(String v1) {
        return v1;
    }

    String doAction2(String v1) {
        return v1;
    }

    String doAction3(String v1) {
        return v1;
    }

    @Test
    public void testMultiIf() {
        //this
        this.init();
        String result = this.actionMappings.get("type1").apply("value1");
        log.info("result :{}", result);

        // new obj
        MultiIf multiIf = new MultiIf();
        multiIf.init();
        //Function<String, String> f = multiIf.actionMappings.get("type1");
        result = actionMappings.get("type2").apply("value2");
        log.info("result :{}", result);
    }
}
