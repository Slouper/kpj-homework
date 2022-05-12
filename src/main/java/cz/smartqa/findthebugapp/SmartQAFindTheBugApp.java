package cz.smartqa.findthebugapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SmartQAFindTheBugApp {

    public static void main(String[] args) {
        SpringApplication.run(SmartQAFindTheBugApp.class, args);
    }

}