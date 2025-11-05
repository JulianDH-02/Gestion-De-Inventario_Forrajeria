package ar.org.centro8.java.curso.models.repositories.interfaces;

import java.sql.SQLException;
import java.util.List;

import ar.org.centro8.java.curso.models.entities.Venta;

public interface IVentaRepository {
    public void create(Venta venta) throws SQLException;
    public Venta findById(int id) throws SQLException;
    public List<Venta> findAll() throws SQLException;
    public int delete(int id) throws SQLException;
    public int update(Venta venta) throws SQLException;
}
