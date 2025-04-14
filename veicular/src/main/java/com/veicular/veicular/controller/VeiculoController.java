package com.veicular.veicular.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veicular.veicular.model.Veiculo;
import com.veicular.veicular.service.VeiculoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/veicular")
public class VeiculoController {

    @Autowired
    private VeiculoService service;

    @PostMapping
    public ResponseEntity<?>cadastrar(@Valid @RequestBody Veiculo veiculo) {
        try {
            Veiculo novoVeiculo = service.cadastrarVeiculo(veiculo);
            return ResponseEntity.ok(novoVeiculo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?>buscarPorId(@PathVariable Integer id) {
        Optional<Veiculo> veiculo = service.buscarPorId(id);
        return veiculo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>atualizar(@PathVariable Integer id, @Valid @RequestBody Veiculo veiculo) {
        try {
            Veiculo atualizado = service.atualizarVeiculo (id, veiculo);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        try {
            List<Veiculo> veiculos = service.listarTodos();
            if (veiculos.isEmpty()) {
                return ResponseEntity.noContent().build(); //HTTP 204
            }
            return ResponseEntity.ok(veiculos); // HTTP 200
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao listar veículos:" + e.getMessage()); //HTTP 500
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir (@PathVariable Integer id) {
        try {
            service.excluirVeiculo(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
