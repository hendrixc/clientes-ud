package com.banquito.core.clientesud.controller.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InformacionContactoRSRQ implements Serializable{
    
    public String tipo;
    public String valor;
}
