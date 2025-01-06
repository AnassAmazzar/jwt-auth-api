package ma.emsi.jwtauthapi.services;


import lombok.RequiredArgsConstructor;
import ma.emsi.jwtauthapi.dao.repository.CustomerRepository;
import ma.emsi.jwtauthapi.dtos.responses.CustomerUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final CustomerRepository customerRepository;

    public List<CustomerUser> allUsers(){
        List<CustomerUser> users = customerRepository.findAll().stream()
                .map(customer -> CustomerUser.builder().customer(customer).build())
                .collect(Collectors.toList());
        return users;
    }
}
