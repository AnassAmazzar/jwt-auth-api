package ma.emsi.jwtauthapi.web;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ma.emsi.jwtauthapi.dtos.LoginUserDto;
import ma.emsi.jwtauthapi.dtos.RegisterUserDto;
import ma.emsi.jwtauthapi.dtos.responses.CustomerUser;
import ma.emsi.jwtauthapi.dtos.responses.LoginResponse;
import ma.emsi.jwtauthapi.services.AuthenticationService;
import ma.emsi.jwtauthapi.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;


    @PostMapping("/signup")
    public ResponseEntity<CustomerUser> register(@RequestBody RegisterUserDto registerUserDto){
        CustomerUser customerUser = authenticationService.signUp(registerUserDto);
        return ResponseEntity.ok(customerUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto){
        CustomerUser authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = LoginResponse.builder().token(jwtToken)
                .expiresIn(jwtService.getExpirationTime()).build();
        return ResponseEntity.ok(loginResponse);
    }
}
