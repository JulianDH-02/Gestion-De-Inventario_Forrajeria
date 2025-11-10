package ar.org.centro8.java.proyecto_final.models.repositories.interfaces;

import java.sql.SQLException;
import java.util.List;

import ar.org.centro8.java.proyecto_final.models.entities.Producto;

public interface IProductoRepository {
    /**
     * Crea un registro de un producto en la base de datos
     * @param producto -> objeto de Producto a ingresar
     * @throws SQLException
     */
    public void create(Producto producto) throws SQLException;
    /**
     * Busca un producto por su id dentro de la base de datos
     * @param id -> id de un Producto a buscar
     * @return -> Devuelve un Producto encontrado 
     * @throws SQLException
     */
    public Producto findById(int id) throws SQLException;
    /**
     * Llama a todos los registros de productos que haya en la base de datos
     * @return -> Devuelve una lista con todos los productos encontrados
     * @throws SQLException
     */
    public List<Producto> findAll() throws SQLException;
    /**
     * Elimina un producto por su id dentro de la base de datos
     * @param id -> id de un Producto a eliminar
     * @return -> Devuelve 1 si hubo cambios, si no 0
     * @throws SQLException
     */
    public int delete(int id) throws SQLException;
    /**
     * Actualiza un producto por su id dentro de la base de datos
     * @param producto -> Objeto de Producto que se va actualizar
     * @return -> Devuelve 1 si hubo cambios, si no 0
     * @throws SQLException
     */
    public int update(Producto producto) throws SQLException;
}
