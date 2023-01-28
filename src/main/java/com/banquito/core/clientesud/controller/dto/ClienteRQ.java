package com.banquito.core.clientesud.controller.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteRQ implements Serializable {

    public String tipoIdentificacion;
    public String identificacion;
    public String apellido;
    public String nombres;
    public LocalDateTime fechaNacimiento;

}
