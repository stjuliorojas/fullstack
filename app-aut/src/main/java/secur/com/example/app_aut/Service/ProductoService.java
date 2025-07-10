package secur.com.example.app_aut.Service;

import org.springframework.beans.factory.annotation.Autowired;
import secur.com.example.app_aut.Model.Producto;
import secur.com.example.app_aut.Repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductoService {

    @Autowired
    private  ProductoRepository productoRepository;

    public List<Producto> findAll(){
        return productoRepository.findAll();
    }

    public Producto save(Producto p){
        return productoRepository.save(p);
    }

    public void delete(Long id){
        productoRepository.deleteById(id);
    }

}
