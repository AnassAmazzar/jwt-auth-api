package ma.emsi.jwtauthapi.config;


import lombok.RequiredArgsConstructor;
import ma.emsi.jwtauthapi.dao.repository.CustomerRepository;
import ma.emsi.jwtauthapi.dtos.responses.CustomerUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {
    private final CustomerRepository customerRepository;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> CustomerUser.builder()
                .customer(customerRepository.findCustomerByEmail(username)
                        .orElseThrow(()-> new UsernameNotFoundException("User Not Found"))
                ).build();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
}
