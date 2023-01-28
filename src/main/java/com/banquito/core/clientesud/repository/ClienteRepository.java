package com.banquito.core.clientesud.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.banquito.core.clientesud.model.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
    Cliente findByTipoIdentificacionAndIdentificacion(String tipoIdentificacion, String identificacion);

    List<Cliente> findByApellidoOrderByApellido(String apellido);

    List<Cliente> findByApellidoLikeOrderByApellido(String apellido);

    List<Cliente> findByNombresOrderByNombres(String nombres);

    List<Cliente> findByNombresLikeOrderByNombres(String nombres);

}
