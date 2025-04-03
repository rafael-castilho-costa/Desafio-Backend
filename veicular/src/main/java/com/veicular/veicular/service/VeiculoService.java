package com.veicular.veicular.service;

import com.veicular.veicular.model.Veiculo;
import com.veicular.veicular.repository.VeiculoRepository;
//import com.veicular.veicular.dto.consultaExternaDTO;


import java.security.PrivateKey;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VeiculoService {

   /*  private static final String API_EXTERNA_URL = "https://my.api.mockaroo.com/veiculos?key=55ad1cd0&placa="; */

    @Autowired
    private VeiculoRepository repository;

 
    @Autowired
      public RestTemplate restTemplate;

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

        //Busca dos dados na fonte Externa
       /* consultaExternaDTO veiculoExterno = consultarApiExterna(veiculo.getPlaca());

        if (veiculoExterno != null) {
            veiculo.setChassis(veiculoExterno.getChassis());
            veiculo.setMarca(veiculoExterno.getMarca());
            veiculo.setModelo(veiculoExterno.getModelo());
            veiculo.setUltimoLicenciamento(veiculoExterno.getUltimoLicenciamento());
        } */

        return repository.save(veiculo);
    }

 /*  private consultaExternaDTO consultarApiExterna(String placa) {
        try {
            String url = API_EXTERNA_URL + placa;
            return restTemplate.getForObject(url, consultaExternaDTO.class);
        } catch(Exception e) {
            throw new RuntimeException("Erro ao consultar API Externa" + e.getMessage());
        }
    }
 */
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
        throw new ResponseStatusException(HttpStatus.OK, "Veículo excluído com sucesso");
    }

}
