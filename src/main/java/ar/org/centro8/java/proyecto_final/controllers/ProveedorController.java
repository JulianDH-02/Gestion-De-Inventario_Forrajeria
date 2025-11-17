package ar.org.centro8.java.proyecto_final.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ar.org.centro8.java.proyecto_final.models.entities.Proveedor;
import ar.org.centro8.java.proyecto_final.services.ProveedorService;

@Controller
public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService){
        this.proveedorService = proveedorService;
    }

    @GetMapping("/proveedores")
    public String proveedores(Model model){
        try {
            // Delega la obtención de cursos al servicio
            List<Proveedor> proveedores = proveedorService.obtenerTodosLosProveedores();
            model.addAttribute("proveedores", proveedores); 

        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al cargar los cursos: " + e.getMessage());
        }
        return "proveedores-list";
    }
    @GetMapping("/proveedores/buscarPorNombre")
    public String buscarProveedoresPorNombre(@RequestParam(value = "nombre", required = false) String nombre, Model model){
        try {
            List<Proveedor> proveedores;
            if (nombre != null && !nombre.isEmpty()) {
                // Delega la búsqueda filtrada al servicio
                proveedores = proveedorService.buscarProveedorPorNombre(nombre);
            } else {
                // Delega la búsqueda de todos al servicio
                proveedores = proveedorService.obtenerTodosLosProveedores();
            }
            model.addAttribute("proveedores", proveedores);
            model.addAttribute("nombre", nombre);
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al buscar proveedores: " + e.getMessage());
        }
        return "proveedores-list";
    }
    @GetMapping("/proveedores/alta")
    public String altaProveedorForm(Model model) {
        model.addAttribute("proveedor", new Proveedor());
        try {
            List<Proveedor> proveedores = proveedorService.obtenerTodosLosProveedores();
            model.addAttribute("proveedores", proveedores);
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("errorProveedores", "Error al cargar los proveedores: " + e.getMessage());
        }
        return "proveedores-alta";
    }
    @PostMapping("/proveedores/guardar")
    public String guardarProveedor(@ModelAttribute("proveedor") Proveedor proveedor, Model model){
        try {
           
            proveedorService.guardarProveedor(proveedor);
            return "redirect:/proveedores";
           
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al guardar el proveedor: " + e.getMessage());
           
        return "redirect:/proveedores";
        }
    }
    @GetMapping("/proveedores/editar")
    public String editarproveedorForm(@RequestParam("id") int id, Model model) {
        try {
            Proveedor proveedor = proveedorService.buscarProveedorPorId(id);
            if (proveedor != null) {
                model.addAttribute("proveedor", proveedor);
                return "proveedores-editar"; 
            } else {
                return "redirect:/proveedores"; 
            } 
        }catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al cargar el proveedor para editar: " + e.getMessage());
            return "redirect:/proveedores"; 
        }
    }
    @PostMapping("/proveedores/actualizar")
    public String actualizarProveedores(@ModelAttribute("proveedor") Proveedor proveedor, Model model) {
        try {
            // Delega la actualización al servicio
            proveedorService.guardarProveedor(proveedor); // El servicio maneja si es create o update
            return "redirect:/proveedores";
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al actualizar el proveedor: " + e.getMessage());
            return "proveedores-editar";
        } catch (IllegalArgumentException e) { // Captura excepciones de validación de negocio del servicio
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "proveedores-editar";
        }
    }
    @GetMapping("/proveedores/eliminar")
    public String eliminarproveedor(@RequestParam("id") int id, Model model) {
        
        try {
            proveedorService.eliminarProveedor(id);
            return "redirect:/proveedores"; 
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al eliminar el proveedor: " + e.getMessage());
            try {
                model.addAttribute("proveedores", proveedorService.obtenerTodosLosProveedores());
            } catch (SQLException ex) {
                ex.printStackTrace();
                model.addAttribute("error", "Error al recargar proveedores después de eliminación fallida: " + ex.getMessage());
            }
            return "proveedores-list";
        }
    }

}
