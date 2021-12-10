package com.kea.cinemaxx;

import com.kea.cinemaxx.configuration.DBSetup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
public class CinemaXXX {

    public static void main(String[] args) {
        DBSetup dbsetup = new DBSetup();
        SpringApplication.run(CinemaXXX.class, args);
    }

}
