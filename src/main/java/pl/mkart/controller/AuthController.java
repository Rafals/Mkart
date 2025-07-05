package pl.mkart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mkart.model.User;
import pl.mkart.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        boolean loggedIn = userService.loadUserByUsername(loginRequest.getEmail()) != null;
        if (loggedIn) {
            return ResponseEntity.ok("Zalogowano pomyślnie.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Niepoprawny email lub hasło.");
        }
    }
}

class LoginRequest {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Gettery i settery

}
