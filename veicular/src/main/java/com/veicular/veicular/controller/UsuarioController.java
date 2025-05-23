package com.veicular.veicular.controller;

import com.veicular.veicular.dto.UsuarioResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.veicular.veicular.dto.LoginDTO;
import com.veicular.veicular.dto.ResetSenhaDTO;
import com.veicular.veicular.dto.UsuarioCadastroDTO;
import com.veicular.veicular.model.Usuario;
import com.veicular.veicular.service.UsuarioService;


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
        usuario.setRole(dto.getRole());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));

        Usuario novoUsuario = service.cadastrar(usuario);
        return ResponseEntity.ok(novoUsuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        Usuario usuario = service.login(dto.getEmail(), dto.getSenha());

        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO(
                usuario.getId().longValue(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole()
        );
        return ResponseEntity.ok(responseDTO);

    }

    @PutMapping("/resetar-senha")
    public ResponseEntity<?> resetarSenha(@RequestBody ResetSenhaDTO dto) {
        service.resetarSenha(dto.getEmail(), dto.getNovaSenha());
        return ResponseEntity.ok("Senha Atualizada com Sucesso");
    }
    
    @GetMapping("/teste-publico")
    public ResponseEntity<String>testePublico(){
        return ResponseEntity.ok("Acesso Publico");
    }
    
}
