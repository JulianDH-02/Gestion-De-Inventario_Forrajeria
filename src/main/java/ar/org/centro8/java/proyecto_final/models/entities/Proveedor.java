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
 * Representa un proveedor del sistema.
 * 
 * Esta clase almacena la información básica de un proveedor, como su id, 
 * nombre, teléfono, correo electrónico y categoría
 */
public class Proveedor {
    private int id;
    private String nombre;
    private String telefono;
    private String email;
    private String observaciones;
}
