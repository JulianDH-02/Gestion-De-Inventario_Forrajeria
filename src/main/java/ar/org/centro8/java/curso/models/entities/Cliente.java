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
/**
 * Representa un cliente del sistema.
 * 
 * Esta clase almacena la información básica de un cliente, como su id, 
 * nombre, apellido, dni, domicilio y teléfono. Puede utilizarse en operaciones
 * de registro, búsqueda y gestión de clientes dentro de un sistema de ventas.
 */
public class Cliente {
    private int id;
    private String nombre;
    private String apellido;
    private String dni;
    private String domicilio;
    private String telefono;
}
