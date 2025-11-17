package ar.org.centro8.java.proyecto_final.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import ar.org.centro8.java.proyecto_final.models.entities.Producto;
import ar.org.centro8.java.proyecto_final.models.repositories.interfaces.IProductoRepository;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class ProductoService {
    private final IProductoRepository productoRepository;
    /**
     * Obtiene una lista de todos los productos
     * @return -> lista de objetos de la clase Producto
     * @throws SQLException
     */
    public List<Producto> obtenerTodosLosProductos() throws SQLException{
        return productoRepository.findAll();
    }
    /**
     * Guarda un nuevo producto o lo actualiza si existe
     * @param producto -> Objeto de Producto a guardar o actualizar
     * @return -> El objeto Producto guardado 
     * @throws SQLException
     */
    public Producto guardarProducto(Producto producto) throws SQLException{
        if(producto.getId()!=0){
            productoRepository.update(producto);
            return producto;
        } else{
            productoRepository.create(producto);
            return producto;
        }
    }
    /**
     * Busca un producto mediante su id
     * @param id -> id de objeto Producto a buscar
     * @return -> el objeto Producto encontrado
     * @throws SQLException
     */
    public Producto buscarProductoPorId(int id) throws SQLException {
        return productoRepository.findById(id);
    }
    /**
     * Busca Productos que tengan un mismo idProveedor
     * @param idProveedor -> idProveedor de un objeto Producto a buscar
     * @return -> lista de objetos de la clase Producto
     * @throws SQLException
     */
    public List<Producto> buscarProductosPorIdProveedor(int idProveedor) throws SQLException{
        return productoRepository.findByIdProveedor(idProveedor);
    }
    /**
     * Busca Productos que tengan una misma categoria
     * @param categoria -> categoria de un objeto Producto a buscar
     * @return -> lista de objetos de la clase Producto
     * @throws SQLException
     */
    public List<Producto> buscarProductoPorCategoria(String categoria) throws SQLException{
        return productoRepository.findByCategoria(categoria);
    }
    /**
     * Elimina un producto mediante su id
     * @param id -> id de objeto producto a eliminar
     * @return -> Devuelve 1 si se elimino, si no 0
     * @throws SQLException
     */
    public int eliminarProducto(int id) throws SQLException{
        return productoRepository.delete(id);
    }
}
