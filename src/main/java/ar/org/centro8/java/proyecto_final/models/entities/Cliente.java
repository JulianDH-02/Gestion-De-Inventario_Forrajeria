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
 * Representa un cliente del sistema.
 * 
 * Esta clase almacena la información básica de un cliente, como su id, 
 * nombre, apellido, dni, domicilio y teléfono.
 */
public class Cliente {
    private int id;
    private String nombre;
    private String apellido;
    private String dni;
    private String domicilio;
    private String telefono;
}
