package com.banquito.core.clientesud.controller.dto;

import java.util.List;

import com.banquito.core.clientesud.model.Direccion;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteDireccionRS {
    
    public String tipoIdentificacion;
    public String identificacion;
    public String nombreCompleto;
    public List<DireccionRSRQ> direcciones;
}
