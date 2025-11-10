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
 * Representa detalles de una venta
 * 
 * Esta clase almacena la informacion relacionada con una venta, es decir, detalles de una venta
 * como el id de la venta, el id del producto, la cantidad vendida del producto y el precio total
 * por la cantidad de ese producto
 */
public class VentaDetalle {
    private int idVenta;
    private int idProducto;
    private int cantidad;
    private int precioTotal;
}
