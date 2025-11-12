package ar.org.centro8.java.proyecto_final.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import ar.org.centro8.java.proyecto_final.models.entities.Venta;
import ar.org.centro8.java.proyecto_final.models.repositories.interfaces.IVentaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VentaService {
    private final IVentaRepository ventaRepository;

    /**
     * Obtiene una lista de todas las ventas
     * @return -> lista de objetos de la clase Venta
     * @throws SQLException
     */
    public List<Venta> obtenerTodasLasVentas() throws SQLException{
        return ventaRepository.findAll();
    }
    /**
     * Guarda una nueva venta o la actualiza si ya existe
     * @param venta -> objeto de Venta a guardar o actulizar
     * @return -> el objeto Venta guardado
     * @throws SQLException
     */
    public Venta guardarVenta(Venta venta) throws SQLException{
        if(venta.getId() != 0){
            ventaRepository.create(venta);
            return venta;
        } else {
            ventaRepository.update(venta);
            return venta;
        }
    }
    /**
     * Busca una Venta mediante su id
     * @param id -> id de objeto Venta a buscar
     * @return -> el objeto Venta encontrado
     * @throws SQLException
     */
    public Venta buscarPorId(int id) throws SQLException{
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
    public int eliminarVenta(int id) throws SQLException{
        return ventaRepository.delete(id);
    }
}
