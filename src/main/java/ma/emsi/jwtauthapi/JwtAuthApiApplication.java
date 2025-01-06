package ma.emsi.jwtauthapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;;

@SpringBootApplication
@CrossOrigin("*")
public class JwtAuthApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtAuthApiApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API Documentation")
                        .version("1.0.0")
                        .description("Description de l'API"))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }

}
