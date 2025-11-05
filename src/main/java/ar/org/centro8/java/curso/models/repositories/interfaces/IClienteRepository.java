package ar.org.centro8.java.curso.models.repositories.interfaces;

import java.sql.SQLException;
import java.util.List;



import ar.org.centro8.java.curso.models.entities.Cliente;

public interface IClienteRepository {
    public void create(Cliente cliente) throws SQLException;
    public Cliente findById(int id) throws SQLException;
    public List<Cliente> findAll() throws SQLException;
    public int delete(int id) throws SQLException;
    public int update(Cliente cliente) throws SQLException;
    
}
