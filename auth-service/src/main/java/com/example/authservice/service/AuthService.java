package com.example.authservice.service;

import com.example.authservice.dto.LoginRequest;
import com.example.authservice.dto.LoginResponse;
import com.example.authservice.dto.RegisterRequest;
import com.example.authservice.model.Usuario;
import com.example.authservice.repository.UsuarioRepository;
import com.example.authservice.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest loginRequest){
        Usuario usuario = usuarioRepository.findByCorreo(loginRequest.getCorreo()).orElseThrow();

        if(!encoder.matches(loginRequest.getClave(),usuario.getClave())){
            throw new UsernameNotFoundException("Credenciales invalidas");
        }

        String token  = jwtService.generateToken(usuario);

        return new LoginResponse(token);
    }

    public Usuario register(RegisterRequest request){
        if(usuarioRepository.findByCorreo(request.getCorreo()).isPresent()){
            throw new UsernameNotFoundException("El correo ya existe");
        }
        Usuario user = new Usuario();
        user.setNombres(request.getNombres());
        user.setApellidos(request.getApellidos());
        user.setCorreo(request.getCorreo());
        user.setClave(encoder.encode(request.getClave()));
        user.setEstado(true);

        user.setRol(request.getRol());

        return usuarioRepository.save(user);
    }
}
