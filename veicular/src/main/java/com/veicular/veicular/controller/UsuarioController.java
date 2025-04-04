package com.veicular.veicular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veicular.veicular.dto.LoginDTO;
import com.veicular.veicular.dto.ResetSenhaDTO;
import com.veicular.veicular.dto.UsuarioCadastroDTO;
import com.veicular.veicular.model.Usuario;
import com.veicular.veicular.service.UsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar (@RequestBody UsuarioCadastroDTO dto) {

       

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));

        Usuario novoUsuario = service.cadastrar(usuario);
        return ResponseEntity.ok(novoUsuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        Usuario usuario = service.login(dto.getEmail(), dto.getSenha());
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/resetar-senha")
    public ResponseEntity<?> resetarSenha(@RequestBody ResetSenhaDTO dto) {
        service.resetarSenha(dto.getEmail(), dto.getNovaSenha());
        return ResponseEntity.ok("Senha Atualizada com Sucesso");
    }
    
    
    
}
