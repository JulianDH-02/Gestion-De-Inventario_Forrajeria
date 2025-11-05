package ar.org.centro8.java.curso.models.repositories.interfaces;

import java.sql.SQLException;
import java.util.List;

import ar.org.centro8.java.curso.models.entities.Proveedor;

public interface IProveedorRepository {
    public void create(Proveedor proveedor) throws SQLException;
    public Proveedor findById(int id) throws SQLException;
    public List<Proveedor> findAll() throws SQLException;
    public int delete(int id) throws SQLException;
    public int update(Proveedor proveedor) throws SQLException;
} 
