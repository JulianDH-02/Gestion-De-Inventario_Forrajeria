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
import ar.org.centro8.java.curso.models.entities.VentaDetalle;
import ar.org.centro8.java.curso.models.repositories.interfaces.IVentaDetalleRepository;

@Repository
public class VentaDetalleRepository implements IVentaDetalleRepository {
    private final DataSource dataSource;

    private static final String SQL_CREATE = "INSERT INTO ventas_detalle (id_venta, id_producto, cantidad, precio_total) VALUES (?,?,?,?)";
    private static final String SQL_FIND_BY_ID_VENTA = "SELECT * FROM ventas_detalle WHERE id_venta=?";
    private static final String SQL_FIND_BY_ID_PRODUCTO = "SELECT * FROM ventas WHERE id_producto=?";
    private static final String SQL_FIND_ALL = "SELECT * FROM ventas_detalle";
    private static final String SQL_DELETE = "DELETE FROM ventas WHERE id=?";
    private static final String SQL_UPDATE = "UPDATE ventas_detalle SET cantidad=?,precio_total=? WHERE id_venta=? and id_producto=?";

    public VentaDetalleRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void create(VentaDetalle ventaDetalle) throws SQLException {
        try (Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_CREATE) ) {
            ps.setInt(1, ventaDetalle.getIdVenta());
            ps.setInt(2, ventaDetalle.getIdProducto());
            ps.setInt(3, ventaDetalle.getCantidad());
            ps.setInt(4, ventaDetalle.getPrecioTotal());
            ps.executeUpdate();
            }
        }

    @Override
    public VentaDetalle findByIdVenta(int id) throws SQLException {
        try (Connection  conn= dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ID_VENTA)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) { 
                if(rs.next()) return mapRow(rs);
            } 
        }return null;
    }
    @Override
    public VentaDetalle findByIdProducto(int id) throws SQLException {
        try (Connection  conn= dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ID_PRODUCTO)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) { 
                if(rs.next()) return mapRow(rs);
            } 
        }return null;
    }
    
    

    @Override
    public List<VentaDetalle> findAll() throws SQLException {
        List<VentaDetalle> ventasDetalles = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ventasDetalles.add(mapRow(rs));
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
    public int update(VentaDetalle ventaDetalle) throws SQLException {
        try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {
            ps.setInt(1, ventaDetalle.getIdVenta());
            ps.setInt(2, ventaDetalle.getIdProducto());
            ps.setInt(3, ventaDetalle.getCantidad());
            ps.setInt(4, ventaDetalle.getPrecioTotal());
            int filaAfectadas = ps.executeUpdate(); // almacena el resultado de la ejecucion
            return filaAfectadas; //devuelve el numero de filas afectadas
    }
}

    private VentaDetalle mapRow(ResultSet rs) throws SQLException {
        VentaDetalle vd = new VentaDetalle();
        vd.setIdVenta(rs.getInt("id_venta"));
        vd.setIdProducto(rs.getInt("id_producto"));
        vd.setCantidad(rs.getInt("cantidad"));
        vd.setPrecioTotal(rs.getInt("precio_total"));
        return vd;
    }

}
