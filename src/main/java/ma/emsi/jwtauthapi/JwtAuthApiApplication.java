package ma.emsi.jwtauthapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;;

@SpringBootApplication
@CrossOrigin("*")
public class JwtAuthApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtAuthApiApplication.class, args);
    }

}
