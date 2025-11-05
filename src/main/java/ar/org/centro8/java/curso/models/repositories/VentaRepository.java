package ar.org.centro8.java.curso.models.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import ar.org.centro8.java.curso.models.entities.Venta;
import ar.org.centro8.java.curso.models.repositories.interfaces.IVentaRepository;
@Repository
public class VentaRepository implements IVentaRepository {

       private final DataSource dataSource;

    private static final String SQL_CREATE = "INSERT INTO ventas (id_cliente, precio_total_venta, fecha_venta) VALUES (?,?,?,?,?,?)";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM ventas WHERE id=?";
    private static final String SQL_FIND_ALL = "SELECT * FROM ventas";
    private static final String SQL_DELETE = "DELETE FROM ventas WHERE id=?";
    private static final String SQL_UPDATE = "UPDATE FROM ventas SET id_cliente=?, precio_total_venta=?, fecha_venta=? WHERE id=?";

    public VentaRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void create(Venta cliente) throws SQLException {
        try (Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS) ) {
            ps.setInt(1, cliente.getIdCliente());
            ps.setInt(5, cliente.getPrecioTotalVenta());
            ps.setDate(6, cliente.getFechaVenta());
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
        }return null;
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
            ps.setInt(5, cliente.getPrecioTotalVenta());
            ps.setDate(6, cliente.getFechaVenta());
            ps.setInt(7, cliente.getId());
            int filaAfectadas = ps.executeUpdate(); // almacena el resultado de la ejecucion
            return filaAfectadas; //devuelve el numero de filas afectadas
    }
}

    private Venta mapRow(ResultSet rs) throws SQLException {
        Venta c = new Venta();
        c.setId(rs.getInt("id"));
        c.setIdCliente(rs.getInt("id_cliente"));
        c.setPrecioTotalVenta(rs.getInt("precio_total"));
        c.setFechaVenta(rs.getDate("fecha_venta"));
        return c;
    }

}
