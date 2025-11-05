package ar.org.centro8.java.curso.models.repositories.interfaces;

import java.sql.SQLException;
import java.util.List;

import ar.org.centro8.java.curso.models.entities.Producto;

public interface IProductoRepository {
    public void create(Producto producto) throws SQLException;
    public Producto findById(int id) throws SQLException;
    public List<Producto> findAll() throws SQLException;
    public int delete(int id) throws SQLException;
    public int update(Producto producto) throws SQLException;
}
