package ar.org.centro8.java.proyecto_final.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import ar.org.centro8.java.proyecto_final.models.entities.Cliente;
import ar.org.centro8.java.proyecto_final.models.repositories.interfaces.IClienteRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteService {
    private final IClienteRepository clienteRepository;

    /**
     * Obtiene una lista de todos los clientes
     * @return -> lista de objetos de la clase Cliente
     * @throws SQLException
     */
    public List<Cliente> obtenerTodosLosClientes() throws SQLException{
        return clienteRepository.findAll();
    }
    /**
     * Guarda un nuevo cliente o lo actualiza si existe
     * @param cliente -> Objeto de Cliente a guardar o actualizar
     * @return -> El objeto Cliente guardado 
     * @throws SQLException
     */
    public Cliente guardarCliente(Cliente cliente) throws SQLException{
        if(cliente.getId()!=0){
            clienteRepository.update(cliente);
            return cliente;
        } else{
            clienteRepository.create(cliente);
            return cliente;
        }
    }
    /**
     * Busca un cliente mediante su id
     * @param id -> id de objeto Cliente a buscar
     * @return -> el objeto Cliente encontrado
     * @throws SQLException
     */
    public Cliente buscarClientePorId(int id) throws SQLException{
        return clienteRepository.findById(id);
    }
    public List<Cliente> buscarClientePorNombre(String nombre) throws SQLException{
        return clienteRepository.findByNombre(nombre);
    }
    /**
     * Elimina un cliente mediante su id
     * @param id -> id de objeto Cliente a eliminar
     * @return -> Devuelve 1 si se elimino, si no 0
     * @throws SQLException
     */
    public int eliminarCliente(int id) throws SQLException{
        return clienteRepository.delete(id);
    }

}
