package secur.com.example.app_aut.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import secur.com.example.app_aut.DTO.RegisterRequest;
import secur.com.example.app_aut.Model.Role;
import secur.com.example.app_aut.Model.Usuario;
import secur.com.example.app_aut.Repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request){
        System.out.println("Registrando usuario: " + request.getUsername());
        if(userRepository.existsByUsername(request.getUsername())){
            System.out.println("Ya existe: " + request.getUsername());
            throw new RuntimeException("El usuario ya existe");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRole(Role.ROLE_USER);

        userRepository.save(usuario);
    }
}
