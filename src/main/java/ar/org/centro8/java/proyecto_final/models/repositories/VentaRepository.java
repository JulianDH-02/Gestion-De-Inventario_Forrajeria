package ar.org.centro8.java.proyecto_final.models.repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import ar.org.centro8.java.proyecto_final.models.entities.Venta;
import ar.org.centro8.java.proyecto_final.models.repositories.interfaces.IVentaRepository;
@Repository
public class VentaRepository implements IVentaRepository {

       private final DataSource dataSource;

    private static final String SQL_CREATE = "INSERT INTO ventas (id_cliente, precio_total_venta, fecha_venta) VALUES (?,?,?)";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM ventas WHERE id=?";
    private static final String SQL_FIND_ALL = "SELECT * FROM ventas";
    private static final String SQL_DELETE = "DELETE FROM ventas WHERE id=?";
    private static final String SQL_UPDATE = "UPDATE ventas SET id_cliente=?, precio_total_venta=?, fecha_venta=? WHERE id=?";

    public VentaRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void create(Venta cliente) throws SQLException {
        try (Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS) ) {
            ps.setInt(1, cliente.getIdCliente());
            ps.setInt(2, cliente.getPrecioTotalVenta());
            ps.setDate(3, Date.valueOf(cliente.getFechaVenta()));
            ps.executeUpdate();

            try(ResultSet keys = ps.getGeneratedKeys()){
                if(keys.next()){
                    cliente.setId(keys.getInt(1));
                }
            }
        }
    }

    @Override
    public Venta findById(int id) throws SQLException {
        try (Connection  conn= dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) { 
                if(rs.next()) return mapRow(rs);
            } 
        }return null;
    }
    
    

    @Override
    public List<Venta> findAll() throws SQLException {
        List<Venta> ventas = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ventas.add(mapRow(rs));
            }
        }return ventas;
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
    public int update(Venta cliente) throws SQLException {
        try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {
            ps.setInt(1, cliente.getIdCliente());
            ps.setInt(2, cliente.getPrecioTotalVenta());
            ps.setDate(3, Date.valueOf(cliente.getFechaVenta()));
            ps.setInt(4, cliente.getId());
            int filaAfectadas = ps.executeUpdate(); 
            return filaAfectadas; 
    }
}

    private Venta mapRow(ResultSet rs) throws SQLException {
        Venta v = new Venta();
        v.setId(rs.getInt("id"));
        v.setIdCliente(rs.getInt("id_cliente"));
        v.setPrecioTotalVenta(rs.getInt("precio_total_venta"));
        v.setFechaVenta(rs.getDate("fecha_venta").toLocalDate());
        return v;
    }

}
