package com.banquito.core.clientesud.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {
    
    @Id
    public String id;
    public String tipo;
    public String linea1;
    public String linea2;
    public String ciudad;
    public Float latitud;
    public Float longitud;


}
