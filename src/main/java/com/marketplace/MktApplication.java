package com.marketplace;

import com.marketplace.resource.Bid;
import com.marketplace.resource.Project;
import com.marketplace.service.MktService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@SpringBootApplication(scanBasePackages = {"com.marketplace"})
public class MktApplication extends SpringBootServletInitializer {



    public static void main(String[] args) {
        SpringApplication.run(MktApplication.class, args);
    }



}
