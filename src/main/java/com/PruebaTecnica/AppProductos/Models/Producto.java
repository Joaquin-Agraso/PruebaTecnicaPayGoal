package com.PruebaTecnica.AppProductos.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
/*Se crea el modelo del producto
 * con los campos solicitados
 * y a demas el campo id para darle un identificador numerico auto-incremental,
 * aunque el id podria ser el nombre del producto si es muy especifico.
 * Se lo anota como entidad y se genera un tabla llamada productos donde se
 * almacenaran todos los productos cargados
 */
@Entity
@Table(name = "productos")
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    private double precio;

    @Column(name = "cantidad")
    private int cantidad;

}