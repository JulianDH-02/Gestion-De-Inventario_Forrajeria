package ar.org.centro8.java.proyecto_final.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.org.centro8.java.proyecto_final.models.entities.Producto;
import ar.org.centro8.java.proyecto_final.models.entities.Proveedor;
import ar.org.centro8.java.proyecto_final.services.ProductoService;
import ar.org.centro8.java.proyecto_final.services.ProveedorService;

@Controller
public class ProductoController {

    private final ProductoService productoService;
    private final ProveedorService proveedorService;

    public ProductoController(ProductoService productoService, ProveedorService proveedorService){
        this.productoService = productoService;
        this.proveedorService = proveedorService;
    }

    @GetMapping("/productos")
    public String productos(Model model){
        try {
            // Delega la obtención de proveedors al servicio
            List<Producto> productos = productoService.obtenerTodosLosProductos();
            model.addAttribute("productos", productos);
            List<Proveedor> proveedores = proveedorService.obtenerTodosLosProveedores();
            model.addAttribute("proveedores",proveedores); 

        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al cargar los proveedores: " + e.getMessage());
        }
        return "productos-list";
    }
    @GetMapping("/productos/alta")
    public String altaproductoForm(Model model) {
        model.addAttribute("producto", new Producto());
        try {
            List<Proveedor> proveedores = proveedorService.obtenerTodosLosProveedores();
            model.addAttribute("proveedores", proveedores);
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("errorProveedores", "Error al cargar los proveedores: " + e.getMessage());
        }
        return "productos-alta";
    }
    @PostMapping("/productos/guardar")
    public String guardarProducto(@ModelAttribute("producto") Producto producto, Model model) {
        try {
            productoService.guardarProducto(producto);
            return "redirect:/productos";
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al guardar el producto: " + e.getMessage());
            try {

                model.addAttribute("proveedores", proveedorService.obtenerTodosLosProveedores());
            } catch (SQLException ex) {
                ex.printStackTrace();
                model.addAttribute("errorProveedores", "Error al recargar los proveedores: " + ex.getMessage());
            }
            return "productos-alta";
        } catch (IllegalArgumentException e) { 
            e.printStackTrace();
            model.addAttribute("error", e.getMessage()); 
            try {
                model.addAttribute("proveedores", proveedorService.obtenerTodosLosProveedores());
            } catch (SQLException ex) {
                ex.printStackTrace();
                model.addAttribute("errorProveedores", "Error al recargar los proveedores: " + ex.getMessage());
            }
            return "productos-alta";
        }
    }
    @GetMapping("/productos/eliminar")
    public String eliminarProducto(@RequestParam("id") int id, Model model) {
        try {
            
            productoService.eliminarProducto(id);
            return "redirect:/productos"; 
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al eliminar el producto: " + e.getMessage());
            try {
                model.addAttribute("productos", productoService.obtenerTodosLosProductos());
            } catch (SQLException ex) {
                ex.printStackTrace();
                model.addAttribute("error", "Error al recargar productos después de eliminación fallida: " + ex.getMessage());
            }
            return "productos-list";
        }
    }
    @GetMapping("/productos/editar")
    public String editarProductoForm(@RequestParam("id") int id, Model model) {
        try {
            Producto producto = productoService.buscarProductoPorId(id);
            if (producto != null) {
                model.addAttribute("producto", producto);
                List<Proveedor> proveedores = proveedorService.obtenerTodosLosProveedores();
                model.addAttribute("proveedores", proveedores);
                return "productos-editar"; 
            } else {
                return "redirect:/productos"; 
            } 
        }catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al cargar el producto para editar: " + e.getMessage());
            return "redirect:/productos"; 
        }
    }
    @PostMapping("/productos/actualizar")
    public String actualizarProducto(@ModelAttribute("producto") Producto producto, Model model) {
        try {
            // Delega la actualización al servicio
            productoService.guardarProducto(producto); // El servicio maneja si es create o update
            return "redirect:/productos";
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al actualizar el producto: " + e.getMessage());
            try {
                model.addAttribute("proveedores", proveedorService.obtenerTodosLosProveedores());
            } catch (SQLException ex) {
                ex.printStackTrace();
                model.addAttribute("errorProveedores", "Error al recargar los proveedores: " + ex.getMessage());
            }
            return "productos-editar";
        } catch (IllegalArgumentException e) { // Captura excepciones de validación de negocio del servicio
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            try {
                model.addAttribute("proveedores", proveedorService.obtenerTodosLosProveedores());
            } catch (SQLException ex) {
                ex.printStackTrace();
                model.addAttribute("errorProveedores", "Error al recargar los proveedores: " + ex.getMessage());
            }
            return "productos-editar";
        }
    }
    @GetMapping("/productos/buscarPorProveedor")
    public String buscarProductosPorproveedor(@RequestParam(value = "idProveedor", required = false) Integer idProveedor, Model model) {
        //se lee el atributo "idproveedor" que viene por GET en  la URL. En este caso, como hay dos parámetros, tenemos que indicar explícitamente
        //que ese atributo es el value. El segundo parámetro indica que el value no es obligatorio en la URL. 
        //Se declara una variable llamada idproveedor de tipo Integer. Se usa Integer (la clase envoltorio) en lugar de int (el tipo primitivo) porque 
        //Integer puede ser null, lo cual es necesario cuando required = false.
        try {
            List<Producto> productos;
            if (idProveedor != null && idProveedor != 0) {
                // Delega la búsqueda filtrada al servicio
                productos = productoService.buscarProductosPorIdProveedor(idProveedor);
            } else {
                // Delega la búsqueda de todos al servicio
                productos = productoService.obtenerTodosLosProductos();
            }
            model.addAttribute("productos", productos);
            List<Proveedor> proveedores = proveedorService.obtenerTodosLosProveedores();
            model.addAttribute("proveedores", proveedores);
            model.addAttribute("selectedProveedorId", idProveedor);
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al buscar productos: " + e.getMessage());
        }
        return "productos-list";
    }
}
