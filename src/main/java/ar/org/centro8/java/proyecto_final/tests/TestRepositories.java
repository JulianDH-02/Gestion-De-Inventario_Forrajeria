package ar.org.centro8.java.proyecto_final.tests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ar.org.centro8.java.proyecto_final.models.entities.Cliente;
import ar.org.centro8.java.proyecto_final.models.entities.Producto;
import ar.org.centro8.java.proyecto_final.models.entities.Proveedor;
import ar.org.centro8.java.proyecto_final.models.entities.Venta;
import ar.org.centro8.java.proyecto_final.models.entities.VentaDetalle;
import ar.org.centro8.java.proyecto_final.models.repositories.ClienteRepository;
import ar.org.centro8.java.proyecto_final.models.repositories.ProductoRepository;
import ar.org.centro8.java.proyecto_final.models.repositories.ProveedorRepository;
import ar.org.centro8.java.proyecto_final.models.repositories.VentaDetalleRepository;
import ar.org.centro8.java.proyecto_final.models.repositories.VentaRepository;
import ar.org.centro8.java.proyecto_final.models.repositories.interfaces.IClienteRepository;
import ar.org.centro8.java.proyecto_final.models.repositories.interfaces.IProductoRepository;
import ar.org.centro8.java.proyecto_final.models.repositories.interfaces.IProveedorRepository;
import ar.org.centro8.java.proyecto_final.models.repositories.interfaces.IVentaDetalleRepository;
import ar.org.centro8.java.proyecto_final.models.repositories.interfaces.IVentaRepository;

@SpringBootApplication(scanBasePackages = "ar.org.centro8.java.proyecto_final")
public class TestRepositories {
    public static void main(String[] args) {
    try (ConfigurableApplicationContext context = SpringApplication.run(TestRepositories.class, args)){
            IClienteRepository clienteRepository = context.getBean(ClienteRepository.class);
            IProductoRepository productoRepository = context.getBean(ProductoRepository.class);
            IProveedorRepository proveedorRepository = context.getBean(ProveedorRepository.class);
            IVentaRepository ventaRepository = context.getBean(VentaRepository.class);
            IVentaDetalleRepository ventaDetalleRepository = context.getBean(VentaDetalleRepository.class);
            
            

            //------------------------------- CLIENTE ---------------------------------

            System.out.println("\n>>> Nuevo Cliente ");

            Cliente cliente1 = new Cliente(0,"Jorge","Gonzalez","44902123",null, null);
            clienteRepository.create(cliente1);
            if (cliente1.getId()>0) {
                 System.out.println("\nCliente creado con ID: " + cliente1.getId());
                 System.out.println(cliente1);
            } else System.err.println("ERROR: no se pudo crear el cliente...");

            System.out.println("\n>>> Buscando cliente por id ");

            Cliente clienteEncontrado = clienteRepository.findById(cliente1.getId());
            if(clienteEncontrado != null) System.out.println("Cliente encontrado: " + clienteEncontrado);
            else System.err.println("ERROR: no se encontro ningun cliente");
            

            System.out.println("\n>>> Actualizando cliente");

            clienteEncontrado.setNombre("Pedro");
            clienteEncontrado.setApellido("Sanchez");
            int filasAfectadas = clienteRepository.update(clienteEncontrado);
            if(filasAfectadas == 1){
                System.out.println("\nCliente con id " + clienteEncontrado.getId() + " actualizado con exito! ");
                System.out.println("\n" + clienteEncontrado);
            } else System.err.println("ERROR: no se pudo actualizar el cliente");

            System.out.println("\n>>> Listando clientes");
            List<Cliente> clientes = new ArrayList<>();
            clientes = clienteRepository.findAll();
            if(!clientes.isEmpty()){
                System.out.println("\nClientes encontrados: " + clientes.size());
                clientes.forEach(System.out::println);
            } else System.err.println("ERROR: no se encontraron clientes");

            //------------------------------- PROVEEDOR -------------------------------

            System.out.println("\n>>> Nuevo proveedor");
           
            Proveedor proveedor1 = new Proveedor(0,"AgroAndes","1188990011","contacto@agroandes.com",null);
            proveedorRepository.create(proveedor1);
            if(proveedor1.getId()>0){
                System.out.println("\nProveedor creado con ID " + proveedor1.getId());
                System.out.println(proveedor1);
            } else System.err.println("ERROR: no se pudo crear un registro de proveedor...");

            System.out.println("\n>>> Buscando proveedor por id");
            
            Proveedor proveedorEncontrado = proveedorRepository.findById(proveedor1.getId());
            if(proveedorEncontrado != null) System.out.println("\nProveedor encontrado: " + proveedorEncontrado);
            else System.err.println("ERROR: no se encontro ningun proveedor");

            System.out.println("\n>>> Actualizando proveedor");

            proveedorEncontrado.setNombre("AniLine");
            proveedorEncontrado.setEmail("contacto@aniline.com");
            filasAfectadas = proveedorRepository.update(proveedorEncontrado);
            if(filasAfectadas == 1){
                System.out.println("\nProveedor con id " + proveedorEncontrado.getId() + "actualizado correctamente");
                System.out.println(proveedorEncontrado);
            } else System.err.println("ERROR: no se pudo actulizar el proveedor");

            System.out.println("\n>>> Listando proveedores");

            List<Proveedor> proveedores = new ArrayList<>();
            proveedores = proveedorRepository.findAll();
            if(!proveedores.isEmpty()){
                System.out.println("\nproveedores encontrados: " + proveedores.size());
                proveedores.forEach(System.out::println);
            } else System.err.println("ERROR: no se encontraron proveedores");
            
            //------------------------------ PRODUCTO ---------------------------------

            System.out.println("\n>>> Nuevo producto ");

            Producto producto1 = new Producto(0,"Calcio para Aves 250g",4000,2000,20,"Suplemento mineral para aves ponedoras","Suplementos",proveedorEncontrado.getId());
            productoRepository.create(producto1);
            if (producto1.getId()>0) {
                 System.out.println("\nProducto creado con ID: " + producto1.getId());
                 System.out.println(producto1);
            } else System.err.println("ERROR: no se pudo crear el producto...");
            

            System.out.println("\n>>> Buscando producto por id ");

            Producto productoEncontrado = productoRepository.findById(producto1.getId());
            if(productoEncontrado != null) System.out.println("\nProducto encontrado: " + productoEncontrado);
            else System.err.println("ERROR: no se encontro ningun producto");

            System.out.println("\n>>> Buscando productos por idProveedor");
            List<Producto> productosEncontrados = new ArrayList<>();
            productosEncontrados = productoRepository.findByIdProveedor(producto1.getIdProveedor());

            if(!productosEncontrados.isEmpty()){
                System.out.println("productos encontrados: " + productosEncontrados.size());
                productosEncontrados.forEach(System.out::println);
            } else  System.err.println("ERROR: no se encontraron productos");

            System.out.println("\n>>> Buscando productos por categoria");

            productosEncontrados = productoRepository.findByCategoria(producto1.getCategoria());

            if(!productosEncontrados.isEmpty()){
                System.out.println("productos encontrados: " + productosEncontrados.size());
                productosEncontrados.forEach(System.out::println);
            } else  System.err.println("ERROR: no se encontraron productos");

            System.out.println("\n>>> Actualizando producto");

            productoEncontrado.setPrecioCompra(2500);
            productoEncontrado.setPrecioVenta(4500);
            filasAfectadas = productoRepository.update(productoEncontrado);
            if(filasAfectadas == 1){
                System.out.println("\nProducto con id: " + productoEncontrado.getId() + " actualizado con exito! ");
                System.out.println(productoEncontrado);
            } else System.err.println("ERROR: no se pudo actualizar el producto");
            

            System.out.println("\n>>> Listando productos");

            List<Producto> productos = new ArrayList<>();
            productos = productoRepository.findAll();
            if (!productos.isEmpty()) {
                System.out.println("\nproductos encontrados: " + productos.size());
                productos.forEach(System.out::println);
            } else System.err.println("ERROR: no se encontraron productos");

            
            //------------------------------- VENTA -----------------------------------

            System.out.println("\n>>> Nueva venta");

            Venta venta1 = new Venta(0,clienteEncontrado.getId(),21*productoEncontrado.getPrecioVenta(), LocalDate.now());
            ventaRepository.create(venta1);
            if(venta1.getId() > 0){
                System.out.println("\nVenta realizada. Id: " + venta1.getId());
                System.out.println(venta1);
            } else System.err.println("ERROR: no se pudo crear la venta");

            System.out.println("\n>>> Buscando venta por id");

            Venta ventaEncontrada = ventaRepository.findById(venta1.getId());
            if(ventaEncontrada != null) System.out.println("\nVenta encontrada: " + ventaEncontrada);
            else System.err.println("ERROR: no se encontro ninguna venta");

            System.out.println("\n>>> Buscando ventas por idCliente");

            List<Venta> ventasEncontradas = new ArrayList<>();
            ventasEncontradas = ventaRepository.findByIdCliente(venta1.getIdCliente());

            if(!ventasEncontradas.isEmpty()){
                System.out.println("ventas encontradas: " + ventasEncontradas.size());
            } else System.err.println("ERROR: no se encontraron ventas");

            System.out.println("\n>>> Actualizando Venta");

            ventaEncontrada.setIdCliente(2);
            ventaEncontrada.setPrecioTotalVenta(20*productoEncontrado.getPrecioVenta());
            filasAfectadas = ventaRepository.update(ventaEncontrada);
            if (filasAfectadas == 1) {
                System.out.println("\nVenta con id: " + ventaEncontrada.getId() + " actualizada con exito!");
                System.out.println(ventaEncontrada);
            } else System.out.println("ERROR: no se pudo actualizar la venta");

            System.out.println("\n>>> Listando ventas");

            List<Venta> ventas = new ArrayList<>();
            ventas = ventaRepository.findAll();
            if(!ventas.isEmpty()){
                System.out.println("\nventas encontradas: " + ventas.size());
                ventas.forEach(System.out::println);
            } else System.out.println("\nERROR: no se encontraron ventas");

            //--------------------------- VENTA DETALLE -------------------------------

            System.out.println("\n>>> Nueva venta detallada");

            VentaDetalle ventaDetalle1 = new VentaDetalle(venta1.getId(),productoEncontrado.getId(),20, 20*productoEncontrado.getPrecioVenta());
            ventaDetalleRepository.create(ventaDetalle1);

            boolean existeIdVenta = ventas.stream()
            .anyMatch(v -> v.getId() == ventaDetalle1.getIdVenta());
            boolean existeIdProducto = productos.stream()
            .anyMatch(p -> p.getId() == ventaDetalle1.getIdProducto());

            if(existeIdVenta && existeIdProducto){
                System.out.println("\nVenta detallada creada con idVenta : " + ventaDetalle1.getIdVenta() + " y idProducto: " + ventaDetalle1.getIdProducto());
                System.out.println(ventaDetalle1);
            } else System.err.println("\nERROR: no se pudo crear la venta");

            System.out.println("\n>>> Buscando por idVenta y idProducto");

            VentaDetalle ventaDetalleEncontrada = ventaDetalleRepository.findByIdVentaAndIdProducto(ventaDetalle1.getIdVenta(), ventaDetalle1.getIdProducto());
            if(ventaDetalleEncontrada != null){
                System.out.println("Venta detallada encontrada con idVenta: " + ventaDetalleEncontrada.getIdVenta() + " y idProducto: " + ventaDetalleEncontrada.getIdProducto());
            } else System.err.println("ERROR: no se encontro ninguna venta detallada");

            System.out.println("\n>>> Buscando por idVenta");

            List<VentaDetalle> ventaDetallesEncontradas = new ArrayList<>();
            ventaDetallesEncontradas = ventaDetalleRepository.findByIdVenta(ventaDetalle1.getIdVenta());
            if(!ventaDetallesEncontradas.isEmpty()){
                System.out.println("Ventas detalladas encontradas: " + ventaDetallesEncontradas.size());
                ventaDetallesEncontradas.forEach(System.out::println);
            } else System.err.println("ERROR: no se encontraron ventas detalladas");
            
            System.out.println("\n>>> Buscando por idProducto");

            ventaDetallesEncontradas = ventaDetalleRepository.findByIdVenta(ventaDetalle1.getIdProducto());
            if(!ventaDetallesEncontradas.isEmpty()){
                System.out.println("Ventas detalladas encontradas: " + ventaDetallesEncontradas.size());
                ventaDetallesEncontradas.forEach(System.out::println);
            } else System.err.println("ERROR: no se encontraron ventas detalladas");

            System.out.println("\n>>> Actualizando venta detallada");

            ventaDetalleEncontrada.setCantidad(21);
            ventaEncontrada.setPrecioTotalVenta(21*productoEncontrado.getPrecioVenta());
            ventaDetalleEncontrada.setPrecioTotal(21*productoEncontrado.getPrecioVenta());
            filasAfectadas = ventaDetalleRepository.update(ventaDetalleEncontrada);
            if(filasAfectadas == 1){
                System.out.println("Venta detallada con id_venta: " + ventaDetalleEncontrada.getIdVenta() + " y id_producto: " + ventaDetalleEncontrada.getIdProducto() + " actualizada con exito!");
                System.out.println(ventaDetalleEncontrada);
            } else System.err.println("ERROR: no se pudo actualizar la venta detallada");

            System.out.println("\n>>> Listando ventas detalladas");

            List<VentaDetalle> ventasDetalles = new ArrayList<>();
            ventasDetalles = ventaDetalleRepository.findAll();
            if(!ventasDetalles.isEmpty()){
                System.out.println("ventas detalladas encontradas: " + ventasDetalles.size());
                ventasDetalles.forEach(System.out::println);
            } else System.err.println("ERROR: no se encontraron ventas detalladas");

            
            
            //----------------------- ELIMINANDO REGISTROS ----------------------------
            System.out.println("\n>>> Eliminando venta detallada");

            filasAfectadas = ventaDetalleRepository.delete(ventaDetalleEncontrada.getIdVenta(), ventaDetalleEncontrada.getIdProducto());
            if(filasAfectadas == 1) System.out.println("Venta detallada con id_venta " + ventaDetalleEncontrada.getIdVenta() + " y id_producto: " + ventaDetalleEncontrada.getIdProducto() + " eliminada con exito!");
            else System.err.println("ERROR: no se pudo eliminar la venta detallada");

            System.out.println("\n>>> Eliminando venta");

            filasAfectadas = ventaRepository.delete(ventaEncontrada.getId());
            if(filasAfectadas == 1) System.out.println("Venta con id: " + ventaEncontrada.getId() + " eliminada con exito!");

            System.out.println("\n>>> Eliminando cliente");

            filasAfectadas = clienteRepository.delete(clienteEncontrado.getId());
            if(filasAfectadas == 1) System.out.println("\nCliente con id " + clienteEncontrado.getId() + " eliminado con exito!");
            else System.out.println("ERROR: no se pudo eliminar el cliente ");
            System.out.println("\n>>> Eliminando producto");

            filasAfectadas = productoRepository.delete(productoEncontrado.getId());
            if(filasAfectadas == 1) System.out.println("\nProducto con id " + productoEncontrado.getId() + " eliminado con exito!");
            else System.out.println("ERROR: no se pudo eliminar el producto ");

            System.out.println("\n>>> Eliminando proveedor");

            filasAfectadas = proveedorRepository.delete(proveedorEncontrado.getId());
            if(filasAfectadas == 1) System.out.println("\nProveedor con id " + proveedorEncontrado.getId() + " eliminado con exito!");
            else System.err.println("ERROR: no se pudo eliminar el proveedor");            

        } catch(Exception e){
            System.out.println("ERROR en la base de datos durante las pruebas!!");
            e.printStackTrace();
        }finally {
            System.out.println("-- FINALIZANDO PRUEBAS --");
        }
    }
}
