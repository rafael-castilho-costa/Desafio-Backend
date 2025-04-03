package com.veicular.veicular.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;



@Entity
@Table(name  = "veiculos")
@Getter
@Setter
public class Veiculo {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @NotBlank(message = "O nome do proprietário é obrigatorio")
    @Column(nullable = false)
    private String proprietario;

    @NotBlank(message = "O CPF do proprietário é obrigatorio")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve ter 11 dígitos")
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @NotBlank(message = "A Placa é obrigatoria")
    @Pattern(regexp = "[A-Z]{3}[0-9]{4}", message = "Formato de placa inválida (ex: ABC8I23)")
    @Column(nullable = false, unique = true , length = 7)
    private String placa;

    @Column(nullable = false, unique = true)
    private String chassis;

    private String marca;
    
    private String modelo;

    
    private Boolean ultimoLicenciamento;
}
