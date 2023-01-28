package com.banquito.core.clientesud.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "clientes")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Cliente {

    @Id
    public String cod;
    public String tipoIdentificacion;
    public String identificacion;
    public String apellido;
    public String nombres;
    public String nombreCompleto;
    public LocalDateTime fechaNacimiento;
    public String estado;
    public LocalDateTime fechaCreacion;
    public List<InformacionContacto> infoContacto;
    public List<Direccion> direcciones;
    public List<Referencia> referencias;

}
