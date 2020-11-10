package demo.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lt
 * @date 2020/11/10
 */
@FeignClient(value = "nacos-provider-example")
public interface UserClient {
    @GetMapping("/get")
    String hi();
}





