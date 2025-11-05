package ar.org.centro8.java.curso.models.repositories.interfaces;

import java.sql.SQLException;
import java.util.List;

import ar.org.centro8.java.curso.models.entities.VentaDetalle;

public interface IVentaDetalleRepository {

    public void create(VentaDetalle ventaDetalle) throws SQLException;
    public VentaDetalle findByIdVenta(int id) throws SQLException;
     public VentaDetalle findByIdProducto(int id) throws SQLException;
    public List<VentaDetalle> findAll() throws SQLException;
    public int delete(int id) throws SQLException;
    public int update(VentaDetalle ventaDetalle) throws SQLException;

}
