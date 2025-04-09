package com.veicular.veicular.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.veicular.veicular.model.Usuario;
import com.veicular.veicular.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //Cadastro
    public Usuario cadastrar(Usuario usuario) {
        if (repository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado.");
        }
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return repository.save(usuario);
    }

    //Login

    public Usuario login(String email, String senha) {
        Optional<Usuario> usuarioOptional = repository.findByEmail(email);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
           /* System.out.println("Senha digitada:" + senha);
            System.out.println("Senha no banco:" + usuario.getSenha());
            System.out.println("Senha bate?" + passwordEncoder.matches(senha, usuario.getSenha()));*/

            if (passwordEncoder.matches(senha, usuario.getSenha())) {
                return usuario;
            }
        }
        throw new RuntimeException("Email ou senha inválidos.");
    }

    //Reset
    public void resetarSenha(String email, String novaSenha) {
        Usuario usuario = repository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        usuario.setSenha(passwordEncoder.encode(novaSenha));
        repository.save(usuario);
    }
    
}
