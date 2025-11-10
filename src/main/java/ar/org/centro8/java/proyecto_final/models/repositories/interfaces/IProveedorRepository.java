package ar.org.centro8.java.proyecto_final.models.repositories.interfaces;

import java.sql.SQLException;
import java.util.List;

import ar.org.centro8.java.proyecto_final.models.entities.Proveedor;

public interface IProveedorRepository {
    /**
     * Crea un registro de un proveedor en la base de datos
     * @param proveedor -> objeto de Proveedor a ingresar
     * @throws SQLException
     */
    public void create(Proveedor proveedor) throws SQLException;
    /**
     * Busca un proveedor por su id dentro de la base de datos
     * @param id -> id de un proveedor a buscar
     * @return -> Devuelve un Proveedor encontrado 
     * @throws SQLException
     */
    public Proveedor findById(int id) throws SQLException;
    /**
     * Llama a todos los registros de proveedores que haya en la base de datos
     * @return -> Devuelve una lista con todos los proveedores encontrados
     * @throws SQLException
     */
    public List<Proveedor> findAll() throws SQLException;
    /**
     * Elimina un proveedor por su id dentro de la base de datos
     * @param id -> id de un Proveedor a eliminar
     * @return -> Devuelve 1 si hubo cambios, si no 0
     * @throws SQLException
     */
    public int delete(int id) throws SQLException;
    /**
     * Actualiza un proveedor por su id dentro de la base de datos
     * @param proveedor -> Objeto de Proveedor que se va actualizar
     * @return -> Devuelve 1 si hubo cambios, si no 0
     * @throws SQLException
     */
    public int update(Proveedor proveedor) throws SQLException;
} 
