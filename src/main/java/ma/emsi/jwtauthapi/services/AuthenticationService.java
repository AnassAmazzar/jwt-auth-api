package ma.emsi.jwtauthapi.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ma.emsi.jwtauthapi.dao.entities.Customer;
import ma.emsi.jwtauthapi.dao.repository.CustomerRepository;
import ma.emsi.jwtauthapi.dtos.LoginUserDto;
import ma.emsi.jwtauthapi.dtos.RegisterUserDto;
import ma.emsi.jwtauthapi.dtos.responses.CustomerUser;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public CustomerUser signUp(RegisterUserDto input){
        Customer customer = Customer.builder()
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .fullName(input.getFullName()).build();
        customer = customerRepository.save(customer);
        return CustomerUser.builder().customer(customer).build();
    }

    public CustomerUser authenticate(LoginUserDto input){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        Customer customer = customerRepository.findCustomerByEmail(input.getEmail()).orElseThrow();
        return CustomerUser.builder().customer(customer).build();
    }

}
