package com.banquito.core.clientesud.controller.mapper;

import java.util.ArrayList;
import java.util.List;

import com.banquito.core.clientesud.controller.dto.ClienteContactoRS;
import com.banquito.core.clientesud.controller.dto.ClienteDireccionRS;
import com.banquito.core.clientesud.controller.dto.ClienteRQ;
import com.banquito.core.clientesud.controller.dto.ClienteSoloRS;
import com.banquito.core.clientesud.controller.dto.DireccionRSRQ;
import com.banquito.core.clientesud.controller.dto.InformacionContactoRSRQ;
import com.banquito.core.clientesud.model.Cliente;
import com.banquito.core.clientesud.model.Direccion;
import com.banquito.core.clientesud.model.InformacionContacto;

public class ClienteMapper {
    
    public static Cliente clientRQtoCliente (ClienteRQ clienteRQ){
        return Cliente.builder()
            .tipoIdentificacion(clienteRQ.getTipoIdentificacion())
            .identificacion(clienteRQ.getIdentificacion())
            .apellido(clienteRQ.getApellido())
            .nombres(clienteRQ.getNombres())
            .nombreCompleto(clienteRQ.getNombres() + " " + clienteRQ.getApellido())
            .fechaNacimiento(clienteRQ.getFechaNacimiento())
            .build();
    }

    public static ClienteSoloRS toClienteSoloRS(Cliente cliente){
        return ClienteSoloRS.builder()
            .tipoIdentificacion(cliente.getTipoIdentificacion())
            .identificacion(cliente.getIdentificacion())
            .nombreCompleto(cliente.getNombreCompleto())
            .build();
    }

    public static ClienteDireccionRS toClienteDireccion(Cliente cliente){
        List<DireccionRSRQ> direcciones = new ArrayList<DireccionRSRQ>();
        for (Direccion dir : cliente.getDirecciones()) {
            direcciones.add(DireccionRSRQ.builder()
                .tipo(dir.getTipo())
                .linea1(dir.getLinea1())
                .linea2(dir.getLinea2())
                .ciudad(dir.getCiudad())
                .latitud(dir.getLatitud())
                .longitud(dir.getLongitud())
                .build()
            );
        }
        return ClienteDireccionRS.builder()
            .tipoIdentificacion(cliente.getTipoIdentificacion())
            .identificacion(cliente.getIdentificacion())
            .nombreCompleto(cliente.getNombreCompleto())
            .direcciones(direcciones)
            .build();
    }

    public static ClienteContactoRS toClienteContactoRS(Cliente cliente){
        List<InformacionContactoRSRQ> contactos = new ArrayList<InformacionContactoRSRQ>();
        for (InformacionContacto info : cliente.getInfoContacto()) {
            contactos.add(InformacionContactoRSRQ.builder()
                .tipo(info.getTipo())
                .valor(info.getValor())
                .build()
            );
        }
        return ClienteContactoRS.builder()
            .tipoIdentificacion(cliente.getTipoIdentificacion())
            .identificacion(cliente.getIdentificacion())
            .nombreCompleto(cliente.getNombreCompleto())
            .contactos(contactos)
            .build();
    }
}
