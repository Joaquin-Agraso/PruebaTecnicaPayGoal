package com.PruebaTecnica.AppProductos.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PruebaTecnica.AppProductos.Models.Producto;
import com.PruebaTecnica.AppProductos.Repository.ProductoRepositorio;
/*SE CREA LA CLASE CONTROLADORA ENCARGADA
 * DE MANEJAR LAS RUTAS DE ACCESO A LOS ENDPOINTS
 * Y DE MANEJAR EL CRUD
 */

@RestController
@RequestMapping("/api")
public class ProductoControlador {
    @Autowired
    private ProductoRepositorio productoRepositorio;

    @PostMapping("/productos/crear")
    public Producto crearProducto(@RequestBody Producto producto){
        return productoRepositorio.save(producto);
    }

    @GetMapping("/productos/{id}") // Cambio a GetMapping
    public Producto obtenerProducto(@PathVariable Long id){
    return productoRepositorio.findById(id).orElse(null);
    }


    @GetMapping("/productos/nombre/{nombre}")
    public List<Producto> obtenerProductosPorNombre(@PathVariable String nombre) {
    return productoRepositorio.findByNombreIgnoreCase(nombre);
    }

    @GetMapping("/productos/listado")
    public List<Producto> listarProductos(){
        return (List<Producto>) productoRepositorio.findAll();
    }

    @DeleteMapping("/productos/eliminar/{id}")
    public void eliminarProducto(@PathVariable Long id){
        productoRepositorio.deleteById(id);
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable Long id,
            @RequestBody Producto productoActualizado) {

        Optional<Producto> productoOptional = productoRepositorio.findById(id);

        if (productoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Producto productoExistente = productoOptional.get();

        // Actualizar los campos del producto existente con los valores del producto actualizado
        productoExistente.setNombre(productoActualizado.getNombre());
        productoExistente.setDescripcion(productoActualizado.getDescripcion());
        productoExistente.setPrecio(productoActualizado.getPrecio());
        productoExistente.setCantidad(productoActualizado.getCantidad());

        // Guardar el producto actualizado en la base de datos
        Producto productoActualizadoEnDB = productoRepositorio.save(productoExistente);

        return ResponseEntity.ok(productoActualizadoEnDB);
    }
}