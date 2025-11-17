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

import ar.org.centro8.java.proyecto_final.models.entities.Proveedor;
import ar.org.centro8.java.proyecto_final.models.repositories.interfaces.IProveedorRepository;

@Repository
public class ProveedorRepository implements IProveedorRepository {

    private final DataSource dataSource;

    private static final String SQL_CREATE = "INSERT INTO proveedores (nombre, telefono, email, observaciones) VALUES (?,?,?,?)";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM proveedores WHERE id=?";
    private static final String SQL_FIND_BY_NAME = "SELECT * FROM proveedores WHERE nombre LIKE ?";
    private static final String SQL_FIND_ALL = "SELECT * FROM proveedores";
    private static final String SQL_DELETE = "DELETE FROM proveedores WHERE id=?";
    private static final String SQL_UPDATE = "UPDATE proveedores SET nombre=?, telefono=?, email=?, observaciones=? WHERE id=?";

    public ProveedorRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void create(Proveedor proveedor) throws SQLException {
        try (Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS) ) {
            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getTelefono());
            ps.setString(3, proveedor.getEmail());
            ps.setString(4, proveedor.getObservaciones());
            ps.executeUpdate();
            try(ResultSet keys = ps.getGeneratedKeys()){
                if(keys.next()){
                    proveedor.setId(keys.getInt(1));
                }
            }
        }
    }

    @Override
    public Proveedor findById(int id) throws SQLException {
        try (Connection  conn= dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) { 
                if(rs.next()) return mapRow(rs);
            } 
        }return null;
    }
    @Override
    public List<Proveedor> findByName(String nombre) throws SQLException{
        List<Proveedor> proveedores = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_NAME);){
                ps.setString(1,"%" + nombre + "%");
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()) proveedores.add(mapRow(rs));
            }
        }return proveedores;
    }
    

    @Override
    public List<Proveedor> findAll() throws SQLException {
        List<Proveedor> proveedores = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                proveedores.add(mapRow(rs));
            }
        }return proveedores;
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
    public int update(Proveedor Proveedor) throws SQLException {
        try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {
            ps.setString(1, Proveedor.getNombre());
            ps.setString(2, Proveedor.getTelefono());
            ps.setString(3, Proveedor.getEmail());
            ps.setString(4, Proveedor.getObservaciones());
            ps.setInt(5, Proveedor.getId());
            int filaAfectadas = ps.executeUpdate(); 
            return filaAfectadas; 
    }
}

    private Proveedor mapRow(ResultSet rs) throws SQLException {
        Proveedor pr = new Proveedor();
        pr.setId(rs.getInt("id"));
        pr.setNombre(rs.getString("nombre"));
        pr.setTelefono(rs.getString("telefono"));
        pr.setEmail(rs.getString("email"));
        pr.setObservaciones(rs.getString("Observaciones"));
        return pr;
    }

}
