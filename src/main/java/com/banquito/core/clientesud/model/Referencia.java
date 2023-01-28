package com.banquito.core.clientesud.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Referencia {

    @Id
    public String id;
    public String tipo;
    public String nombreCompleto;
    public String direccion;
    public String telefonoCasa;
    public String telefonoMovil;
    public String correoElectronico;

}
