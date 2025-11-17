package ar.org.centro8.java.proyecto_final.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.org.centro8.java.proyecto_final.models.entities.Cliente;
import ar.org.centro8.java.proyecto_final.services.ClienteService;

@Controller
public class ClienteController {

    private final ClienteService clienteService;
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    
    @GetMapping("/clientes")
    public String clientes(Model model){
        try {
            
            List<Cliente> clientes = clienteService.obtenerTodosLosClientes();
            model.addAttribute("clientes", clientes); 

        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al cargar los clientes: " + e.getMessage());
        }
        return "clientes-list";
    }
    @GetMapping("/clientes/buscarPorNombre")
    public String buscarClientesPorNombre(@RequestParam(value = "nombre", required = false) String nombre, Model model){
        try {
            List<Cliente> clientes;
            if (nombre != null && !nombre.isEmpty()) {
                // Delega la búsqueda filtrada al servicio
                clientes = clienteService.buscarClientePorNombre(nombre);
            } else {
                // Delega la búsqueda de todos al servicio
                clientes = clienteService.obtenerTodosLosClientes();
            }
            model.addAttribute("clientes", clientes);
            model.addAttribute("nombre", nombre);
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al buscar clientes: " + e.getMessage());
        }
        return "clientes-list";
    }
    @PostMapping("/clientes/guardar")
    public String guardarCliente(@ModelAttribute("cliente") Cliente cliente, Model model){
        try {
           
            clienteService.guardarCliente(cliente);
            return "redirect:/clientes";
           
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al guardar el cliente: " + e.getMessage());
           
        return "redirect:/clientes";
        }
    }
    @GetMapping("/clientes/eliminar")
    public String eliminarCliente(@RequestParam("id") int id, Model model) {
        
        try {
            clienteService.eliminarCliente(id);
            return "redirect:/clientes"; 
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al eliminar el cliente: " + e.getMessage());
            
            try {
                model.addAttribute("clientes", clienteService.obtenerTodosLosClientes());
            } catch (SQLException ex) {
                ex.printStackTrace();
                model.addAttribute("error", "Error al recargar clientes después de eliminación fallida: " + ex.getMessage());
            }
            return "clientes-list";
        }
    }
    @PostMapping("/clientes/actualizar")
    public String actualizarAlumno(@ModelAttribute("cliente") Cliente cliente, Model model) {
        try {
            // Delega la actualización al servicio
            clienteService.guardarCliente(cliente); // El servicio maneja si es create o update
            return "redirect:/clientes";
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al actualizar el cliente: " + e.getMessage());
            return "cliente-editar";
        } catch (IllegalArgumentException e) { // Captura excepciones de validación de negocio del servicio
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "cliente-editar";
        }
    }
    @GetMapping("/clientes/editar")
    public String editarclienteForm(@RequestParam("id") int id, Model model) {
        try {
            Cliente cliente = clienteService.buscarClientePorId(id);
            if (cliente != null) {
                model.addAttribute("cliente", cliente);
                return "cliente-editar"; 
            } else {
                return "redirect:/clientes"; 
            } 
        }catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al cargar el cliente para editar: " + e.getMessage());
            return "redirect:/clientes"; 
        }
    }

    @GetMapping("/clientes/alta")
    public String altaClienteForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        try {
            List<Cliente> clientes = clienteService.obtenerTodosLosClientes();
            model.addAttribute("clientes", clientes);
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("errorClientes", "Error al cargar los clientes: " + e.getMessage());
        }
        return "clientes-alta";
    }
}