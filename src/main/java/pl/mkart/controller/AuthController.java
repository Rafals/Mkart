package pl.mkart.controller;

import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletRequest servletRequest) {
        System.out.println("HEADERS:");
        servletRequest.getHeaderNames().asIterator().forEachRemaining(header ->
                System.out.println(header + ": " + servletRequest.getHeader(header))
        );

        System.out.println("BODY:");
        System.out.println(request.getEmail() + " / " + request.getPassword());

        boolean loggedIn = userService.loadUserByUsername(request.getEmail()) != null;

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
