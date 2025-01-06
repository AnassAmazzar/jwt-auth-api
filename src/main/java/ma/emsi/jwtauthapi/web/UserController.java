package ma.emsi.jwtauthapi.web;

import lombok.RequiredArgsConstructor;
import ma.emsi.jwtauthapi.dtos.responses.CustomerUser;
import ma.emsi.jwtauthapi.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<CustomerUser> authenticatedUser() {
        System.out.println("authentication");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomerUser currentUser = (CustomerUser) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }
    @GetMapping("/all-users")
    public ResponseEntity<List<CustomerUser>> allUsers() {
        List<CustomerUser> users = userService.allUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/test")
    public String getTest(){
        return "Hello world Ahmed";
    }

}
