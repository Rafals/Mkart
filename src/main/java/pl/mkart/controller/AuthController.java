package pl.mkart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.mkart.model.User;
import pl.mkart.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        boolean success = userService.registerUser(user);
        return success ? "Rejestracja udana. Sprawdź e-mail, aby aktywować konto." :
                "Użytkownik o podanym e-mailu już istnieje.";
    }

    @GetMapping("/activate")
    public String activate(@RequestParam String code) {
        boolean activated = userService.activateUser(code);
        return activated ? "Konto aktywowane!" : "Nieprawidłowy kod aktywacyjny.";
    }
}
