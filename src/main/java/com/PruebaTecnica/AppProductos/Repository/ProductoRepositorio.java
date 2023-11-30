package com.PruebaTecnica.AppProductos.Repository;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.PruebaTecnica.AppProductos.Models.Producto;

public interface ProductoRepositorio extends CrudRepository<Producto,Long>{
    
    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombre) = LOWER(:nombre)")
    List<Producto> findByNombreIgnoreCase(String nombre);
    
}