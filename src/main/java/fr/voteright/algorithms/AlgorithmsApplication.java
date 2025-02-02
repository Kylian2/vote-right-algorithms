package fr.voteright.algorithms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class AlgorithmsApplication {

    public static String key;
    public static String baseUrl;

    public static void main(final String[] args) {
        SpringApplication.run(AlgorithmsApplication.class, args);
        Dotenv dotenv = Dotenv.load();
        key =  dotenv.get("KEY");
        baseUrl =  dotenv.get("BASE_URL");
    }

}
