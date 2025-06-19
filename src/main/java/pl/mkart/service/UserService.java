package pl.mkart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.mkart.model.User;
import pl.mkart.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailService mailService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return false; // użytkownik już istnieje
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setActive(false);

        userRepository.save(user);

        mailService.sendActivationEmail(user.getEmail(), user.getActivationCode());

        return true;
    }

    public boolean activateUser(String code) {
        Optional<User> userOptional = userRepository.findByActivationCode(code);
        if (userOptional.isEmpty()) return false;

        User user = userOptional.get();
        user.setActive(true);
        user.setActivationCode(null);
        userRepository.save(user);

        return true;
    }
}
