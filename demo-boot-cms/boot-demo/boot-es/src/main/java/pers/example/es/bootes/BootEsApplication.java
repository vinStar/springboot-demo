package pers.example.es.bootes;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSwagger2Doc
public class BootEsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootEsApplication.class, args);
    }

}
