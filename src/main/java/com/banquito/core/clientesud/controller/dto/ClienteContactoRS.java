package com.banquito.core.clientesud.controller.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteContactoRS {
    public String tipoIdentificacion;
    public String identificacion;
    public String nombreCompleto;
    public List<InformacionContactoRSRQ> contactos;

}
