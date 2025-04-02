package com.veicular.veicular.repository;

import com.veicular.veicular.model.Veiculo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;



public interface VeiculoRepository extends JpaRepository<Veiculo, Integer>{
    Optional<Veiculo> findByCpf(String cpf);
    Optional<Veiculo> findByPlaca(String placa);
    
}
