package ar.org.centro8.java.curso.models.entities;

import java.sql.Date;
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
 * Representa una venta realizada en el sistema.
 * 
 * Esta clase almacena la información de una venta a un cliente, incluyendo
 * el id, el producto vendido, la cantidad, el precio unitario, el total de la 
 * venta y la referencia al cliente correspondiente.
 * 
 * Puede utilizarse en operaciones de registro, consulta y análisis de ventas
 * dentro de un sistema comercial o de facturación.
 */
public class Venta {
    private int id;
    private int idCliente;
    private int precioTotalVenta;
    private Date fechaVenta;
}
