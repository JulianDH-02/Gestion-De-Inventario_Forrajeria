package ar.org.centro8.java.proyecto_final.models.repositories.interfaces;

import java.sql.SQLException;
import java.util.List;

import ar.org.centro8.java.proyecto_final.models.entities.VentaDetalle;

public interface IVentaDetalleRepository {
     /**
     * Crea un registro de una venta detallada en la base de datos
     * @param proveedor -> objeto de VentaDetalle a ingresar
     * @throws SQLException
     */
    public void create(VentaDetalle ventaDetalle) throws SQLException;
    /**
     * Busca una venta detallada por su id_venta y id_producto dentro de la base de datos
     * @param id_venta -> id_venta de una VentaDetalle
     * @param id_producto -> id_producto de una VentaDetalle
     * @return -> Devuelve una VentaDetalle encontrada
     * @throws SQLException
     */
    public VentaDetalle findByIdVentaAndIdProducto(int id_venta, int id_producto) throws SQLException;
    /**
     * Llama a todos los registros de ventas detalladas que haya en la base de datos
     * @return -> Devuelve una lista de todos los VentaDetalle que haya encontrado
     * @throws SQLException
     */
    public List<VentaDetalle> findAll() throws SQLException;
    /**
     * Elimina una venta detallada por su id dentro de la base de datos
     * @param id_venta -> id_venta de una VentaDetalle que se va eliminar
     * @param id_producto -> id_producto de una VentaDetalle que se va eliminar
     * @return -> Devuelve 1 si hubo cambios, si no 0
     * @throws SQLException
     */
    public int delete(int id_venta, int id_producto) throws SQLException;
     /**
     * Actualiza una venta detallada por su id dentro de la base de datos
     * @param id_venta -> id_venta de una VentaDetalle que se va actualizar
     * @param id_producto -> id_producto de una VentaDetalle que se va actualizar
     * @return -> Devuelve 1 si hubo cambios, si no 0
     * @throws SQLException
     */
    public int update(VentaDetalle ventaDetalle) throws SQLException;

}
