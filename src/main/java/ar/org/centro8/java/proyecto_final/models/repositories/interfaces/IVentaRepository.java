package ar.org.centro8.java.proyecto_final.models.repositories.interfaces;

import java.sql.SQLException;
import java.util.List;

import ar.org.centro8.java.proyecto_final.models.entities.Venta;

public interface IVentaRepository {
    /**
     * Crea un registro de una venta en la base de datos
     * @param venta -> objeto de Venta a ingresar
     * @throws SQLException
     */
    public void create(Venta venta) throws SQLException;
    /**
     * Busca una venta por su id dentro de la base de datos
     * @param id -> id de una Venta a buscar
     * @return -> Devuelve una Venta encontrada
     * @throws SQLException
     */
    public Venta findById(int id) throws SQLException;
    /**
     * Busca una venta por su idVenta dentro de la base de datos
     * @param idVenta -> id de un Venta de una Venta a buscar
     * @return -> Devuelve una lista de Ventas encontrada
     * @throws SQLException
     */
    public List<Venta> findByIdCliente(int idV) throws SQLException;
    /**
     * Llama a todos los registros de ventas que haya en la base de datos
     * @return -> Devuelve una lista con todos las ventas encontradas
     * @throws SQLException
     */
    public List<Venta> findAll() throws SQLException;
    /**
     * Elimina una venta por su id dentro de la base de datos
     * @param id -> objeto de venta que se va eliminar
     * @return -> Devuelve 1 si hubo cambios, si no 0
     * @throws SQLException
     */
    public int delete(int id) throws SQLException;
    /**
     * Actualiza una venta por su id dentro de la base de datos
     * @param venta -> objeto venta que se va actualizar
     * @return -> Devuelve 1 si hubo cambios, si no 0
     * @throws SQLException
     */
    public int update(Venta venta) throws SQLException;
}
