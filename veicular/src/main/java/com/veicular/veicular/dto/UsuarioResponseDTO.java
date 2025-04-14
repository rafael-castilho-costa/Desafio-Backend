package com.veicular.veicular.dto;


import lombok.Data;

@Data
public class UsuarioResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String role;

    public UsuarioResponseDTO(Long id, String nome, String email, String role){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.role = role;
    }
}
