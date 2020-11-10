package demo.controller;

import demo.fegin.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    UserClient userClient;

    @Value("${user.name}")
    String userName;
//
//    @Value("${user.age}")
//    int age;

    @RequestMapping("/get")
    public String get() {
        return userClient.hi();
    }


}
