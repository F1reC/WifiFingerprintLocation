package cn.shoppingguide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = {"cn.gzx.ressiweb.controller","cn.gzx.ressiweb.KNN"})
public class ShoppingGuideApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingGuideApplication.class, args);
    }

}
