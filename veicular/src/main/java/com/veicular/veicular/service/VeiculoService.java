package com.veicular.veicular.service;

import com.veicular.veicular.model.Veiculo;
import com.veicular.veicular.repository.VeiculoRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {
    @Autowired
    private VeiculoRepository repository;

    public List<Veiculo> listarTodos() {
        return repository.findAll();
    }

    public Optional<Veiculo> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public Veiculo cadastrarVeiculo(Veiculo veiculo) {

        if (repository.findByCpf(veiculo.getCpf()).isPresent()) {
            throw new RuntimeException("Já existe um veículo cadastrado com este CPF");
            
        }
        if (repository.findByPlaca(veiculo.getPlaca()).isPresent()) {
            throw new RuntimeException("Já existe um veículo cadastrado com esta Placa");
        }
        return repository.save(veiculo);
    }

    public Veiculo atualizarVeiculo (Integer id, Veiculo veiculo) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Veículo não encontrado");
        }
        veiculo.setId(id);
        return repository.save(veiculo);
        
    }

    public void excluirVeiculo(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Veículo não encontrado");
        }
        repository.deleteById(id);
    }

}
