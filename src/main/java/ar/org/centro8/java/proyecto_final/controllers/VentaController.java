package ar.org.centro8.java.proyecto_final.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.org.centro8.java.proyecto_final.models.entities.Cliente;
import ar.org.centro8.java.proyecto_final.models.entities.Producto;
import ar.org.centro8.java.proyecto_final.models.entities.Venta;
import ar.org.centro8.java.proyecto_final.models.entities.VentaDetalle;
import ar.org.centro8.java.proyecto_final.services.ClienteService;
import ar.org.centro8.java.proyecto_final.services.ProductoService;
import ar.org.centro8.java.proyecto_final.services.VentaService;

@Controller

public class VentaController {

    private final VentaService ventaService;
    private final ClienteService clienteService;
    private final ProductoService productoService;
    public VentaController(VentaService ventaService, ClienteService clienteService, ProductoService productoService){
        this.ventaService = ventaService;
        this.clienteService = clienteService;
        this.productoService = productoService;
    }

    @GetMapping("/ventas")
    public String listar(Model model) throws SQLException {
        List<Venta> ventas = ventaService.obtenerTodasLasVentas();
        model.addAttribute("ventas", ventas);
        List<Cliente> clientes = clienteService.obtenerTodosLosClientes();
        model.addAttribute("clientes", clientes);
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        model.addAttribute("productos", productos);
        Map<Integer, String> clientesMap = clientes.stream()
            .collect(Collectors.toMap(
                    Cliente::getId,
                    c -> c.getNombre() + " " + c.getApellido()
            ));
        model.addAttribute("clientesMap", clientesMap);
        return "ventas-list";
    }

    @GetMapping("ventas/alta")
    public String alta(Model model) throws SQLException {
        model.addAttribute("venta", new Venta());
        model.addAttribute("clientes", clienteService.obtenerTodosLosClientes());
        model.addAttribute("productos", productoService.obtenerTodosLosProductos());
        return "ventas-alta";
    }

    @PostMapping("ventas/guardar")
    public String guardar(@ModelAttribute Venta venta, @RequestParam("idProducto") List<Integer> productos,@RequestParam("cantidad") List<Integer> cantidades) {
        try {
            List<VentaDetalle> detalles = new ArrayList<>();

            for (int i = 0; i < productos.size(); i++) {
                VentaDetalle vd = new VentaDetalle();
                vd.setIdProducto(productos.get(i));
                vd.setCantidad(cantidades.get(i));
                detalles.add(vd);
            }
            int total = 0;
            for (VentaDetalle d : detalles) {
                Producto p = productoService.buscarProductoPorId(d.getIdProducto());
                int precio = p.getPrecioVenta() * d.getCantidad();
                d.setPrecioTotal(precio);

                total += precio;
            }   

            venta.setPrecioTotalVenta(total);
            
            ventaService.crearVenta(venta, detalles);
            return "redirect:/ventas";

        } catch (Exception e) {
            
            return "redirect:/ventas/alta";
        }
    }

    @GetMapping("/ventasDetalle")
    public String detalle(@RequestParam int id, Model model) throws SQLException {

    Venta venta = ventaService.buscarVentaPorId(id);  // ejemplo
    List<VentaDetalle> detalles = ventaService.getDetallesByIdVenta(venta.getId());
    List<Producto> productos = productoService.obtenerTodosLosProductos();
    Map<Integer, String> productosMap = productos.stream()
            .collect(Collectors.toMap(
                    Producto::getId,
                    p -> p.getNombre()
            ));
        model.addAttribute("productosMap", productosMap);    
    model.addAttribute("venta", venta); 
    model.addAttribute("detalles", detalles);

    return "ventasDetalle-list";
    }
    @GetMapping("/ventas/eliminar")
    public String eliminar(@RequestParam int id) throws SQLException {
        ventaService.eliminarVenta(id);
        return "redirect:/ventas";
    }
}