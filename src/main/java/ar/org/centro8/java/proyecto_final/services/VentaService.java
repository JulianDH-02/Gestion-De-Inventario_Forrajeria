package ar.org.centro8.java.proyecto_final.services;

import java.sql.SQLException;
import java.util.List;


import org.springframework.stereotype.Service;

import ar.org.centro8.java.proyecto_final.models.entities.Producto;
import ar.org.centro8.java.proyecto_final.models.entities.Venta;
import ar.org.centro8.java.proyecto_final.models.entities.VentaDetalle;
import ar.org.centro8.java.proyecto_final.models.repositories.interfaces.IProductoRepository;
import ar.org.centro8.java.proyecto_final.models.repositories.interfaces.IVentaDetalleRepository;
import ar.org.centro8.java.proyecto_final.models.repositories.interfaces.IVentaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VentaService {
    private final IVentaRepository ventaRepository;
    private final IVentaDetalleRepository ventaDetalleRepository;
    private final IProductoRepository productoRepository;

    /**
     * Obtiene una lista de todas las ventas
     * @return -> lista de objetos de la clase Venta
     * @throws SQLException
     */
    public List<Venta> obtenerTodasLasVentas() throws SQLException{
        return ventaRepository.findAll();
    }
    public void crearVenta(Venta venta, List<VentaDetalle> detalles) throws SQLException {
                // 1) Crear la venta principal
                ventaRepository.create(venta);

                // 2) Recorrer los detalles y crearlos
                for (VentaDetalle d : detalles) {
                    d.setIdVenta(venta.getId());

                    // Encontrar el producto
                    Producto p = productoRepository.findById(d.getIdProducto());
                    

                    if (p == null) {
                        throw new SQLException("Producto no encontrado ID: " + d.getIdProducto());
                    }

                    // Validar stock
                    if (p.getStock() < d.getCantidad()) {
                        throw new SQLException("No hay stock para " + p.getNombre());
                    }

                    // Restar stock
                    p.setStock(p.getStock() - d.getCantidad());
                    productoRepository.update(p);
                    

                    // Guardar detalle
                    ventaDetalleRepository.create(d);
                }
                List<VentaDetalle> ventaDetalle = ventaDetalleRepository.findByIdVenta(venta.getId());
                int precio_total = 0;
                for(VentaDetalle vd : ventaDetalle){
                    precio_total += vd.getPrecioTotal();
                }
                venta.setPrecioTotalVenta(precio_total);
                ventaRepository.update(venta);                
    }

    /**
     * Busca una Venta mediante su id
     * @param id -> id de objeto Venta a buscar
     * @return -> el objeto Venta encontrado
     * @throws SQLException
     */
    public Venta buscarVentaPorId(int id) throws SQLException{
        return ventaRepository.findById(id);
    }
    /**
     * Busca Ventas mediante su idCliente
     * @param idCliente -> idCliente de objeto Venta a buscar
     * @return -> el objeto Venta encontrado
     * @throws SQLException
     */
    public List<Venta> buscarPorIdCliente(int idCliente) throws SQLException{
        return ventaRepository.findByIdCliente(idCliente);
    }
    /**
     * Elimina una venta mediante su id
     * @param id -> id de objeto Venta a eliminar
     * @return -> Devuelve 1 si se elimino, si no 0
     * @throws SQLException
     */
    public void eliminarVenta(int idVenta) throws SQLException {
        ventaDetalleRepository.delete(idVenta);
        ventaRepository.delete(idVenta);
    }
    public List<VentaDetalle> buscarDetalles(int idVenta) throws SQLException {
        return ventaDetalleRepository.findByIdVenta(idVenta);
    }
    public List<VentaDetalle> getDetallesByIdVenta(int idVenta) throws SQLException{
    return ventaDetalleRepository.findByIdVenta(idVenta);
    }
}
