package secur.com.example.app_aut.Repository;

import secur.com.example.app_aut.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario, Long> {
Optional<Usuario> findByUsername(String username);
boolean existsByUsername(String username); }