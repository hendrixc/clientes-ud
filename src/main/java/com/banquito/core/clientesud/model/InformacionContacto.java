package com.banquito.core.clientesud.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InformacionContacto {

    @Id
    public String id;
    public String tipo;
    public String valor;
}
