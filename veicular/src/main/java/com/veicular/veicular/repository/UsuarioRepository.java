package com.veicular.veicular.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veicular.veicular.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    Optional<Usuario> findByEmail(String email);
}
