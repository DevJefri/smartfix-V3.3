package cl.ms_cliente.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cl.ms_cliente.dto.AuthRequestDTO;
import cl.ms_cliente.dto.AuthResponseDTO;
import cl.ms_cliente.exception.ResourceNotFoundException;
import cl.ms_cliente.exception.DuplicateRutException;
import cl.ms_cliente.model.User;
import cl.ms_cliente.repository.UserRepository;
import cl.ms_cliente.security.JwtUtil;


/** Autenticacion de usuarios: registro, login y emision de JWT. */
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    /** Registra un nuevo usuario (username unico) y devuelve su JWT. */
    public AuthResponseDTO register(AuthRequestDTO dto) {
        if (userRepository.existsByUsername(dto.username())) {
            throw new DuplicateRutException("User '" + dto.username() + "' already exists");
        }
        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRole("USER");
        userRepository.save(user);
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
        return new AuthResponseDTO(token, user.getUsername(), user.getRole());
    }

    /** Valida las credenciales del usuario y devuelve un nuevo JWT. */
    public AuthResponseDTO login(AuthRequestDTO dto) {
        User user = userRepository.findByUsername(dto.username())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new ResourceNotFoundException("Invalid credentials");
        }
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
        return new AuthResponseDTO(token, user.getUsername(), user.getRole());
    }
}
