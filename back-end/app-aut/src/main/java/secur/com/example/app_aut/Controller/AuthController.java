package secur.com.example.app_aut.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import secur.com.example.app_aut.DTO.RegisterRequest;
import secur.com.example.app_aut.Security.JwtUtil;
import secur.com.example.app_aut.Service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import secur.com.example.app_aut.Service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private  UserDetailsServiceImpl userDetailsService;
    @Autowired
    private  UserService userService;
    @Autowired
    private  JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtUtil.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
       try {
           userService.register(request);
           return ResponseEntity.ok(Map.of("message", "Usuario registrado exitosamente"));
       }catch (RuntimeException e){
           return ResponseEntity.status(409).body("El usuario ya existe");
       }

    }
}
