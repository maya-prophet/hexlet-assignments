package exercise;


import exercise.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import reactor.core.publisher.Mono;

@EnableR2dbcRepositories()
@SpringBootApplication
public class App {

 

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);


    }

}
