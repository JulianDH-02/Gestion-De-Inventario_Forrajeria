package ar.org.centro8.java.proyecto_final.models.repositories.interfaces;

import java.sql.SQLException;
import java.util.List;



import ar.org.centro8.java.proyecto_final.models.entities.Cliente;

public interface IClienteRepository {
    /**
     * Crea un registro de un cliente en la base de datos
     * @param cliente -> objeto de Cliente a ingresar
     * @throws SQLException
     */
    public void create(Cliente cliente) throws SQLException;
    /**
     * Busca un cliente por su id dentro de la base de datos
     * @param id -> id de un Cliente a buscar
     * @return -> Devuelve un Cliente encontrado 
     * @throws SQLException
     */
    public Cliente findById(int id) throws SQLException;
    /**
     * Busca clientes que tengan un cierto nombre dentro de la base de datos
     * @param nombre -> nombre de un Cliente a buscar
     * @return -> Devuelve lista de objetos Cliente encontrados 
     * @throws SQLException
     */
    public List<Cliente> findByNombre(String nombre) throws SQLException;
    /**
     * Llama a todos los registros de clientes que haya en la base de datos
     * @return -> Devuelve una lista con todos los clientes encontrados
     * @throws SQLException
     */
    public List<Cliente> findAll() throws SQLException;
    /**
     * Elimina un cliente por su id dentro de la base de datos
     * @param id -> id de cliente a eliminar
     * @return -> Devuelve 1 si hubo cambios, si no 0
     * @throws SQLException
     */
    public int delete(int id) throws SQLException;
    /**
     * Actualiza un cliente por su id dentro de la base de datos
     * @param cliente -> Objeto de Cliente que se va actualizar
     * @return -> Devuelve 1 si hubo cambios, si no 0
     * @throws SQLException
     */
    public int update(Cliente cliente) throws SQLException;
    
}
