package pl.polo.devicerentspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import org.springframework.context.annotation.Bean;
import pl.polo.devicerentspring.controller.ApplicationController;

import java.util.Scanner;

@SpringBootApplication
public class DevicerentspringApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(DevicerentspringApplication.class, args);
        ApplicationController applicationController = ctx.getBean(ApplicationController.class);
        applicationController.loop();

    }
    @Bean
     Scanner scanner(){
        return new Scanner(System.in);
    }

}
