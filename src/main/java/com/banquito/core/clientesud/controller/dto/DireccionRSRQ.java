package com.banquito.core.clientesud.controller.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DireccionRSRQ implements Serializable{
    
    public String tipo;
    public String linea1;
    public String linea2;
    public String ciudad;
    public Float latitud;
    public Float longitud;
}
