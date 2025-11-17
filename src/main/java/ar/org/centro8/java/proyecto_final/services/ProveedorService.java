package ar.org.centro8.java.proyecto_final.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import ar.org.centro8.java.proyecto_final.models.entities.Proveedor;
import ar.org.centro8.java.proyecto_final.models.repositories.interfaces.IProveedorRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProveedorService {
    private final IProveedorRepository proveedorRepository;

    /**
     * Obtiene una lista de todos los proveedores
     * @return -> lista de objetos de la clase Proveedor
     * @throws SQLException
    */
    public List<Proveedor> obtenerTodosLosProveedores() throws SQLException{
        return proveedorRepository.findAll();
    }
    /**
     * Guarda un nuevo Proveedor o lo actualiza si existe
     * @param proveedor -> Objeto de Proveedor a guardar o actualizar
     * @return -> el objeto Proveedor guardado
     * @throws SQLException
     */
    public Proveedor guardarProveedor(Proveedor proveedor) throws SQLException{
        if(proveedor.getId() != 0){
            proveedorRepository.update(proveedor);
            return proveedor;
        } else {
            proveedorRepository.create(proveedor);
            return proveedor;
        }
    }
    /**
     * Busca un Proveedor mediante su id
     * @param id -> id de objeto Proveedor a buscar
     * @return -> el objeto Proveedor encontrado
     * @throws SQLException
     */
    public Proveedor buscarProveedorPorId(int id) throws SQLException{
        return proveedorRepository.findById(id);
    }
    public List<Proveedor> buscarProveedorPorNombre(String nombre) throws SQLException{
        return proveedorRepository.findByName(nombre);
    }
    /**
     * Elimina un proveedor mediante su id
     * @param id -> id de objeto Proveedor a eliminar
     * @return -> Devuelve 1 si se elimino, si no 0
     * @throws SQLException
     */
    public int eliminarProveedor(int id) throws SQLException{
        return proveedorRepository.delete(id);
    }
}
