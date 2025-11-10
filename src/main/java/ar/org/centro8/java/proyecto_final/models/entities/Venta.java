package ar.org.centro8.java.proyecto_final.models.entities;

import java.time.LocalDate;

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
 */
public class Venta {
    private int id;
    private int idCliente;
    private int precioTotalVenta;
    private LocalDate fechaVenta;
}
