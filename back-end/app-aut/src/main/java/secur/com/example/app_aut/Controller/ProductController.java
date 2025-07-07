package secur.com.example.app_aut.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import secur.com.example.app_aut.Model.Producto;
import secur.com.example.app_aut.Service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductoService service;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/products")
    public List<Producto> list(){
        return service.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/products")
    public Producto save(@RequestBody Producto producto){
        return service.save(producto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/products/{id}")
    public void delete (@PathVariable Long id){
        service.delete(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/products/{id}")
    public Producto update(@PathVariable Long id, @RequestBody Producto producto) {
        producto.setId(id);
        return service.save(producto);
    }
}