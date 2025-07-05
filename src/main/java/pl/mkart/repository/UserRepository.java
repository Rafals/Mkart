package pl.mkart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mkart.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // Znajdź użytkownika po adresie e-mail
    User findByEmail(String email);

    // Znajdź użytkownika po kodzie aktywacyjnym
    User findByActivationCode(String activationCode);

    // Znajdź użytkownika po nazwie użytkownika
    User findByUsername(String username);

    // Sprawdź, czy istnieje użytkownik o danym adresie e-mail
    boolean existsByEmail(String email);

    // Sprawdź, czy istnieje użytkownik o danej nazwie użytkownika
    boolean existsByUsername(String username);
}
