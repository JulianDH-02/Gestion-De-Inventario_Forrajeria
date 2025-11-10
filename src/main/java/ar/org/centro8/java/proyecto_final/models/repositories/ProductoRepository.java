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

import ar.org.centro8.java.proyecto_final.models.entities.Producto;
import ar.org.centro8.java.proyecto_final.models.repositories.interfaces.IProductoRepository;
@Repository
public class ProductoRepository implements IProductoRepository {

    private final DataSource dataSource;

    private static final String SQL_CREATE = "INSERT INTO productos (nombre, precio_venta, precio_compra, stock, descripcion, categoria, id_proveedor) VALUES (?,?,?,?,?,?,?)";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM productos WHERE id=?";
    private static final String SQL_FIND_ALL = "SELECT * FROM productos";
    private static final String SQL_DELETE = "DELETE FROM productos WHERE id=?";
    private static final String SQL_UPDATE = "UPDATE productos SET nombre=?, precio_venta=?, precio_compra=?, stock=?, descripcion=?, categoria=?, id_proveedor=? WHERE id=?";

    public ProductoRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void create(Producto producto) throws SQLException {
        try (Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS) ) {
            ps.setString(1, producto.getNombre());
            ps.setInt(2, producto.getPrecioVenta());
            ps.setInt(3, producto.getPrecioCompra());
            ps.setInt(4, producto.getStock());
            ps.setString(5, producto.getDescripcion());
            ps.setString(6, producto.getCategoria());
            ps.setInt(7, producto.getIdProveedor());
            ps.executeUpdate();
            try(ResultSet keys = ps.getGeneratedKeys()){
                if(keys.next()){
                    producto.setId(keys.getInt(1));
                }
            }
        }
    }

    @Override
    public Producto findById(int id) throws SQLException {
        try (Connection  conn= dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) { 
                if(rs.next()) return mapRow(rs);
            } 
        }return null;
    }
    
    

    @Override
    public List<Producto> findAll() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                productos.add(mapRow(rs));
            }
        }return productos;
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
    public int update(Producto cliente) throws SQLException {
        try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {
            ps.setString(1, cliente.getNombre());
            ps.setInt(2, cliente.getPrecioVenta());
            ps.setInt(3, cliente.getPrecioCompra());
            ps.setInt(4, cliente.getStock());
            ps.setString(5, cliente.getDescripcion());
            ps.setString(6, cliente.getCategoria());
            ps.setInt(7, cliente.getIdProveedor());
            ps.setInt(8, cliente.getId());
            int filaAfectadas = ps.executeUpdate(); 
            return filaAfectadas; 
    }
}

    private Producto mapRow(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getInt("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecioVenta(rs.getInt("precio_venta"));
        p.setPrecioCompra(rs.getInt("precio_compra"));
        p.setStock(rs.getInt("stock"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setCategoria(rs.getString("categoria"));
        p.setIdProveedor(rs.getInt("id_proveedor"));
        return p;
    }

}
