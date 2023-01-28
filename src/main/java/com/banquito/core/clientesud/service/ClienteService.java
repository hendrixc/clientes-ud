package com.banquito.core.clientesud.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.clientesud.controller.dto.InformacionContactoRSRQ;
import com.banquito.core.clientesud.model.Cliente;
import com.banquito.core.clientesud.model.Direccion;
import com.banquito.core.clientesud.model.InformacionContacto;
import com.banquito.core.clientesud.model.Referencia;
import com.banquito.core.clientesud.repository.ClienteRepository;
import com.banquito.excepcion.CRUDException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clientRepository) {
        this.clienteRepository = clientRepository;
    }

    public List<Cliente> obtenerTodos() {
        return this.clienteRepository.findAll();
    }

    public Cliente buscarClientePorTipoIdentificacionAndIdentificacion(String tipoIdentificacion, String identificacion) {
        log.info("Va a recuperar cliente por Tipo identificacion: {} y identeificacion:{}", tipoIdentificacion,identificacion);
        Cliente cliente = this.clienteRepository.findByTipoIdentificacionAndIdentificacion(tipoIdentificacion,identificacion);
        if (cliente==null) {
            log.info("Cliente no obtenido por identificacion: {} y identeificacion:{}", tipoIdentificacion,identificacion);
            throw new RuntimeException("The client does not exist");
        }
        log.info("Cliente obtenido por codigo por Tipo identificacion: {} y identeificacion:{} ->{}", tipoIdentificacion,identificacion,cliente);
        return cliente;
    }

    public void crearCliente(Cliente nuevoCliente) throws CRUDException {
        log.info("Va a crear un cliente");
        Cliente cliente = this.clienteRepository.findByTipoIdentificacionAndIdentificacion(nuevoCliente.tipoIdentificacion,nuevoCliente.identificacion);

        try {
            if (cliente!=null) {
                log.info("Cliente existente con identificacion: {} y identeificacion:{}", cliente.tipoIdentificacion,cliente.identificacion);
                throw new RuntimeException("El cliente existente");
            }
            nuevoCliente.setEstado("activo");
            nuevoCliente.setFechaCreacion(LocalDateTime.now());
            this.clienteRepository.save(nuevoCliente);
            log.debug("Datos del cliente creada: {}", cliente);
        } catch (Exception e) {
            log.error("Error al crear cliente: {}, con los datos: {}", e.getMessage(), nuevoCliente);
            throw new CRUDException("No se pudo crear la agencia, error:" + e.getMessage(), e);
        }
    }

    public void actualizaCliente(Cliente actualizarCliente)throws CRUDException{
        log.info("Va a actualizar el cliente con la siguiete informacion {}", actualizarCliente);
        try {
            Cliente cliente = this.clienteRepository.findByTipoIdentificacionAndIdentificacion(actualizarCliente.tipoIdentificacion,actualizarCliente.identificacion);
            if (cliente==null) {
                log.info("El Cliente no existente con tipo de identificacion: {} y identificacion:{}", cliente.tipoIdentificacion,cliente.identificacion);
                throw new RuntimeException("El cliente existente");
            }
            this.clienteRepository.save(actualizarCliente);
            log.debug("El cliente {} ha sido actualizada con la siguiente informacion {}",actualizarCliente.getIdentificacion(),actualizarCliente);
        } catch (Exception e) {
            log.error("Error al actualizar el cliente: {}, con los datos: {}", e.getMessage(), actualizarCliente);
            throw new CRUDException("No se pudo actualizar el cliente, error:" + e.getMessage(), e);
        }
    }

    public void agregarContacto(String tipoIdentificacion, String identificacion, InformacionContacto nuevoContacto)throws CRUDException {
        try {
        log.info("Va a agregar un nuevo contacto con la siguiete informacion {}", nuevoContacto);
        Cliente cliente = this.clienteRepository.findByTipoIdentificacionAndIdentificacion(tipoIdentificacion,identificacion);
        Optional<InformacionContacto> contactoTemp = cliente.getInfoContacto().stream()
                .filter(p -> p.getTipo().equals(nuevoContacto.getTipo())
                        && p.getValor().equals(nuevoContacto.getValor()))
                .findFirst();
        if(contactoTemp.isPresent()){
            throw new RuntimeException("El contacto existente");
        }
        
        cliente.getInfoContacto().add(nuevoContacto);
        this.clienteRepository.save(cliente);
        log.debug("Datos del contacto creada: {}", nuevoContacto);
        } catch (Exception e) {
            log.error("Error al actualizar el cliente: {}, con los datos: {}", e.getMessage(), nuevoContacto);
            throw new CRUDException("No se pudo agregar el contacto, error:" + e.getMessage(), e);
        }
    }

    public void agregarDireccion(String tipoIdentificacion, String identificacion, Direccion direccion)throws CRUDException {
        try {
        log.info("Va a agregar una nueva direccion con la siguiete informacion {}", direccion);
        Cliente cliente = this.clienteRepository.findByTipoIdentificacionAndIdentificacion(tipoIdentificacion,identificacion);
        Optional<Direccion> direccionTemp = cliente.getDirecciones().stream()
                .filter(p -> p.getTipo().equals(direccion.getTipo())
                        && p.getLinea1().equals(direccion.getLinea1())
                        && p.getLinea2().equals(direccion.getLinea2())
                        &&p.getCiudad().equals(direccion.getCiudad())
                        && p.getLatitud().equals(direccion.getLatitud())
                        && p.getLongitud().equals(direccion.getLongitud()))
                .findFirst();
        if(direccionTemp.isPresent()){
            throw new RuntimeException("La direccion ya existe");
        }
        
        cliente.getDirecciones().add(direccion);
        this.clienteRepository.save(cliente);
        log.debug("Direccion del contacto creada: {}", direccion);
        } catch (Exception e) {
            log.error("Error al actualizar el cliente: {}, con los datos: {}", e.getMessage(), direccion);
            throw new CRUDException("No se pudo agregar la direccion, error:" + e.getMessage(), e);
        }
    }

    public void agregarReferencia(String tipoIdentificacion, String identificacion, Referencia nuevaReferencia)throws CRUDException {
        try {
        log.info("Va a agregar un nuevo contacto con la siguiete informacion {}", nuevaReferencia);
        Cliente cliente = this.clienteRepository.findByTipoIdentificacionAndIdentificacion(tipoIdentificacion,identificacion);
        Optional<Referencia> referenciaTemp = cliente.getReferencias().stream()
                .filter(p -> p.getTipo().equals(nuevaReferencia.getTipo())
                        && p.getNombreCompleto().equals(nuevaReferencia.getNombreCompleto())
                        && p.getDireccion().equals(nuevaReferencia.getDireccion())
                        && p.getTelefonoCasa().equals(nuevaReferencia.getTelefonoCasa())
                        && p.getTelefonoMovil().equals(nuevaReferencia.getTelefonoMovil())
                        && p.getCorreoElectronico().equals(nuevaReferencia.getCorreoElectronico()))
                .findFirst();

        if(referenciaTemp.isPresent()){
            throw new RuntimeException("La referencia existe");
        }
        
        cliente.getReferencias().add(nuevaReferencia);
        this.clienteRepository.save(cliente);
        log.debug("Datos de la referencia creada: {}", nuevaReferencia);
        } catch (Exception e) {
            log.error("Error al agregar la referencia: {}, con los datos: {}", e.getMessage(), nuevaReferencia);
            throw new CRUDException("No se pudo agregar el contacto, error:" + e.getMessage(), e);
        }
    }

}
