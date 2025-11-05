package ar.org.centro8.java.curso.tests;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ar.org.centro8.java.curso.models.entities.Cliente;
import ar.org.centro8.java.curso.models.entities.Producto;
import ar.org.centro8.java.curso.models.repositories.ClienteRepository;
import ar.org.centro8.java.curso.models.repositories.ProductoRepository;
import ar.org.centro8.java.curso.models.repositories.ProveedorRepository;
import ar.org.centro8.java.curso.models.repositories.VentaRepository;
import ar.org.centro8.java.curso.models.repositories.interfaces.IClienteRepository;
import ar.org.centro8.java.curso.models.repositories.interfaces.IProductoRepository;
import ar.org.centro8.java.curso.models.repositories.interfaces.IProveedorRepository;
import ar.org.centro8.java.curso.models.repositories.interfaces.IVentaRepository;

@SpringBootApplication(scanBasePackages = "ar.org.centro8.java.curso")
public class TestRepositories {
    public static void main(String[] args) {
    try (ConfigurableApplicationContext context = SpringApplication.run(TestRepositories.class, args)){
            IClienteRepository clienteRepository = context.getBean(ClienteRepository.class);
            IProductoRepository productoRepository = context.getBean(ProductoRepository.class);
            IVentaRepository ventaRepository = context.getBean(VentaRepository.class);
            IProveedorRepository proveedorRepository = context.getBean(ProveedorRepository.class);

            //------------------- CLIENTE ------------------------

            System.out.println("\n>>> Nuevo Cliente ");

            Cliente cliente1 = new Cliente(0,"Jorge","Gonzalez","44902123",null, null);
            clienteRepository.create(cliente1);
            if (cliente1.getId()>0) {
                 System.out.println("Cliente creado con ID: " + cliente1.getId());
                 System.out.println(cliente1);
            } else {
                 System.err.println("ERROR: no se pudo crear el cliente...");
            }

            System.out.println("\n>>> Buscando cliente por id ");

            Cliente clienteEncontrado = clienteRepository.findById(cliente1.getId());
            if(clienteEncontrado != null){
                System.out.println("Cliente encontrado: " + clienteEncontrado);
            } else {
                System.err.println("ERROR: no se encontro ningun cliente");
            }

            System.out.println("\n>>> Actualizando cliente");

            clienteEncontrado.setNombre("Pedro");
            clienteEncontrado.setApellido("Sanchez");
            int filasAfectadas = clienteRepository.update(clienteEncontrado);
            if(filasAfectadas == 1){
                System.out.println("Cliente con id" + clienteEncontrado.getId() + "actualizado con exito! ");
                System.out.println("\n" + clienteEncontrado);
            } else {
                System.err.println("ERROR: no se pudo actualizar el cliente");
            }

            System.out.println("\n>>> Eliminando cliente");

            filasAfectadas = clienteRepository.delete(clienteEncontrado.getId());
            if(filasAfectadas == 1){
                System.out.println("Cliente con id" + clienteEncontrado.getId() + "eliminado con exito!");
            } else {
                System.out.println("ERROR: no se pudo eliminar el cliente ");
            }

            //------------------------------- PRODUCTO --------------------------------

            System.out.println("\n>>> Nuevo producto ");

            Producto producto1 = new Producto(0,"Calcio para Aves 250g",4000,2000,20,"Suplemento mineral para aves ponedoras",5);
            if (producto1.getId()>0) {
                 System.out.println("Producto creado con ID: " + producto1.getId());
                 System.out.println(cliente1);
            } else {
                 System.err.println("ERROR: no se pudo crear el producto...");
            }

            System.out.println("\n>>> Buscando producto por id ");

            Producto productoEncontrado = productoRepository.findById(producto1.getId());
            if(productoEncontrado != null){
                System.out.println("Producto encontrado: " + productoEncontrado);
            } else {
                System.err.println("ERROR: no se encontro ningun producto");
            }

            System.out.println("\n>>> Actualizando producto");

            productoEncontrado.setPrecioCompra(2500);
            productoEncontrado.setPrecioVenta(4500);
            filasAfectadas = productoRepository.update(productoEncontrado);
            if(filasAfectadas == 1){
                System.out.println("Producto con id" + productoEncontrado.getId() + "actualizado con exito! ");
                System.out.println("\n" + productoEncontrado);
            } else {
                System.err.println("ERROR: no se pudo actualizar el producto");
            }

            System.out.println("\n>>> Eliminando producto");

            filasAfectadas = productoRepository.delete(productoEncontrado.getId());
            if(filasAfectadas == 1){
                System.out.println("Producto con id" + productoEncontrado.getId() + "eliminado con exito!");
            } else {
                System.out.println("ERROR: no se pudo eliminar el producto ");
            }

            //-------------------------------- VENTA ---------------------------------

            System.out.println("\n>>> Nueva venta");
            


        } catch(Exception e){
            System.out.println("ERROR en la base de datos durante las pruebas!!");
            e.printStackTrace();
        }finally {
            System.out.println("-- FINALIZANDO PRUEBAS --");
        }
    }
}
