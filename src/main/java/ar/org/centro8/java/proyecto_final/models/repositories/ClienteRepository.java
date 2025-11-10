package ar.org.centro8.java.proyecto_final.models.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import ar.org.centro8.java.proyecto_final.models.entities.Cliente;
import ar.org.centro8.java.proyecto_final.models.repositories.interfaces.IClienteRepository;
@Repository
public class ClienteRepository implements IClienteRepository {

    private final DataSource dataSource;

    private static final String SQL_CREATE = "INSERT INTO clientes (nombre, apellido, dni, domicilio, telefono) VALUES (?,?,?,?,?)";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM clientes WHERE id=?";
    private static final String SQL_FIND_ALL = "SELECT * FROM clientes";
    private static final String SQL_DELETE = "DELETE FROM clientes WHERE id=?";
    private static final String SQL_UPDATE = "UPDATE clientes SET nombre=?, apellido=?, dni=?, domicilio=?, telefono=? WHERE id=?";

    public ClienteRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void create(Cliente cliente) throws SQLException {
        try (Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS) ) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getDni());
            ps.setString(4, cliente.getDomicilio());
            ps.setString(5, cliente.getTelefono());
            ps.executeUpdate();
            try(ResultSet keys = ps.getGeneratedKeys()){
                if(keys.next()){
                    cliente.setId(keys.getInt(1));
                }
            }
        }
    }

    @Override
    public Cliente findById(int id) throws SQLException {
        try (Connection  conn= dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) { 
                if(rs.next()) return mapRow(rs);
            } 
        }return null;
    }
    
    

    @Override
    public List<Cliente> findAll() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                clientes.add(mapRow(rs));
            }
        }return clientes;
    }
    

    @Override
    public int delete(int id) throws SQLException {
        try (Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {
            ps.setInt(1, id);
            int filaAfectada = ps.executeUpdate();            
            return filaAfectada;
        } 
    }
    

    @Override
    public int update(Cliente cliente) throws SQLException {
        try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getDni());
            ps.setString(4, cliente.getDomicilio());
            ps.setString(5, cliente.getTelefono());
            ps.setInt(6, cliente.getId());
            int filaAfectadas = ps.executeUpdate(); 
            return filaAfectadas; 
    }
}

    private Cliente mapRow(ResultSet rs) throws SQLException {
        Cliente c = new Cliente();
        c.setId(rs.getInt("id"));
        c.setNombre(rs.getString("nombre"));
        c.setApellido(rs.getString("apellido"));
        c.setDni(rs.getString("dni"));
        c.setDomicilio(rs.getString("domicilio"));
        c.setTelefono(rs.getString("telefono"));
        return c;
    }

}
