package com.banquito.core.clientesud.controller.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteSoloRS implements Serializable{
    
    public String tipoIdentificacion;
    public String identificacion;
    public String nombreCompleto;
}
