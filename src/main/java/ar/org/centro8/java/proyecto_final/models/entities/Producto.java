package ar.org.centro8.java.proyecto_final.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
/**
 * Representa un producto disponible en el sistema.
 * 
 * Esta clase almacena la información relacionada con un producto, incluyendo
 * su id, nombre, precios de compra y venta, stock disponible, descripción y 
 * el id del proveedor asociado.
 */
public class Producto {
    private int id;
    private String nombre;
    private int precioVenta;
    private int precioCompra;
    private int stock;
    private String descripcion;
    private String categoria;
    private int idProveedor;
}
