package demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lt
 * @date 2020/11/9
 */
@RestController
@RefreshScope
public class SampleController {

    @Value("${user.name}")
    String userName;
//
//    @Value("${user.age}")
//    int age;

    @RequestMapping("/get")
    public String get() {
        return userName;
    }


}
