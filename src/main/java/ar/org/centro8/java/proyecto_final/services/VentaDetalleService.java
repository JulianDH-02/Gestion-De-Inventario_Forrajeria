package ar.org.centro8.java.proyecto_final.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import ar.org.centro8.java.proyecto_final.models.entities.VentaDetalle;
import ar.org.centro8.java.proyecto_final.models.repositories.interfaces.IVentaDetalleRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VentaDetalleService {
    private final IVentaDetalleRepository ventaDetalleRepository;

    /**
     * Obtiene una lista con todas las ventas detalladas
     * @return -> lista de objetos de la clase VentaDetalle
     * @throws SQLException
     */
    public List<VentaDetalle> obtenerTodosLasVentasDetalle() throws SQLException{
        return ventaDetalleRepository.findAll();
    }
    /**
     * Guarda una nueva VentaDetalle o la actualiza si ya existe
     * @param ventaDetalle -> objeto de VentaDetalle a guardar o actualizar
     * @return -> objeto VentaDetalle guardado
     * @throws SQLException
     */
    public VentaDetalle guardarVentaDetalle(VentaDetalle ventaDetalle) throws SQLException{
        if(ventaDetalle.getIdProducto() != 0 && ventaDetalle.getIdVenta() != 0){
            ventaDetalleRepository.create(ventaDetalle);
            return ventaDetalle;
        } else {
            ventaDetalleRepository.update(ventaDetalle);
            return ventaDetalle;
        }
    }
    /**
     * Busca una Venta detallada mediante su idVenta y idProducto
     * @param idVenta -> idVenta de objeto VentaDetalle a buscar
     * @param idProducto -> idProducto de objero VentaDetalle a buscar
     * @return -> el objeto VentaDetalle encontrado
     * @throws SQLException
     */
    public VentaDetalle buscarPorIdVentaYIdProducto(int idVenta, int idProducto) throws SQLException{
        return ventaDetalleRepository.findByIdVentaAndIdProducto(idVenta, idProducto);
    }
    /**
     * Busca Ventas detalladas mediante su idVenta 
     * @param idVenta -> idVenta de objeto VentaDetalle a buscar
     * @return -> lista de objetos VentaDetalle encontrados
     * @throws SQLException
     */
    public List<VentaDetalle> buscarPorIdVenta(int idVenta) throws SQLException{
        return ventaDetalleRepository.findByIdVenta(idVenta);
    }
    /**
     * Busca Ventas detalladas mediante su idProductos
     * @param idProducto -> idProducto de objeto VentaDetalle a buscar
     * @return -> lista de objetos VentaDetalle encontrados
     * @throws SQLException
     */
    public List<VentaDetalle> buscarPorIdProducto(int idProducto) throws SQLException{
        return ventaDetalleRepository.findByIdVenta(idProducto);
    }
    /**
     * Elimina una Venta detallada mediante su idVenta y idProducto
     * @param idVenta -> idVenta de objeto VentaDetalle a eliminar
     * @param idProducto -> idProducto de objero VentaDetalle a eliminar
     * @return -> Devuelve 1 si se elimino, si no 0
     * @throws SQLException
     */
    public int eliminarVentaDetalle(int idVenta, int idProducto) throws SQLException{
        return ventaDetalleRepository.delete(idVenta, idProducto);
    }
}
