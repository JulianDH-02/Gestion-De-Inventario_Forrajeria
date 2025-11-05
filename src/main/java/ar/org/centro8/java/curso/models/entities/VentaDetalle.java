package ar.org.centro8.java.curso.models.entities;

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
public class VentaDetalle {
    private int idVenta;
    private int idProducto;
    private int cantidad;
    private int precioTotal;
}
