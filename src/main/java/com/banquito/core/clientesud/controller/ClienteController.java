package com.banquito.core.clientesud.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.clientesud.controller.dto.ClienteRQ;
import com.banquito.core.clientesud.controller.dto.ClienteSoloRS;
import com.banquito.core.clientesud.controller.mapper.ClienteMapper;
import com.banquito.core.clientesud.model.Cliente;
import com.banquito.core.clientesud.service.ClienteService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteSoloRS>> obtenerTodos() {
        List<Cliente> clientes = this.clienteService.obtenerTodos();
        List<ClienteSoloRS> clientesS = new ArrayList<>();
        for (Cliente cliente : clientes) {
            clientesS.add(ClienteMapper.toClienteSoloRS(cliente));
        }
        return ResponseEntity.ok(clientesS);
    }

    @GetMapping("/{tipoIdentificacion}/{identificacion}/")
    public ResponseEntity <ClienteSoloRS> obtenerPorTipoIdentificacionAndIdentificacion(
        @PathVariable("tipoIdentificacion") String tipoIdentificacion,  
        @PathVariable("identificacion") String identificacion){
        try {
            Cliente cliente = this.clienteService.buscarClientePorTipoIdentificacionAndIdentificacion(tipoIdentificacion, identificacion);
            ClienteSoloRS response = ClienteMapper.toClienteSoloRS(cliente);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    

    }

    @PostMapping
    public ResponseEntity crearCliente (ClienteRQ cliente){
        try {
            this.clienteService.crearCliente(ClienteMapper.clientRQtoCliente(cliente));
            return ResponseEntity.ok("Cliente Creado");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }


    }


    @PutMapping("/{tipoIdentificacion}/{identificacion}/")
    public ResponseEntity <ClienteSoloRS> updateCliente(
        @PathVariable("tipoIdentificacion") String tipoIdentificacion,  
        @PathVariable("identificacion") String identificacion,
        @RequestBody ClienteRQ clienteRQ){
            try {
                Cliente cliente = ClienteMapper.clientRQtoCliente(clienteRQ);
                cliente.setIdentificacion(identificacion);
                cliente.setTipoIdentificacion(tipoIdentificacion);
                this.clienteService.actualizaCliente(cliente);  
                return ResponseEntity.ok().build();
            } catch (Exception e) {
                return ResponseEntity.internalServerError().build();
            }

    }
    
    
}
