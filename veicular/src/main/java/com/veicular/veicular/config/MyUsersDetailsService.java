package com.veicular.veicular.config;

import com.veicular.veicular.model.Usuario;
import com.veicular.veicular.repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collections;

@Service
public class MyUsersDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public MyUsersDetailsService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email:" + email));
        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),
                usuario.getSenha(),
                Collections.singletonList(new SimpleGrantedAuthority(usuario.getRole()))

        );
    }

}
