package secur.com.example.app_aut.Repository;

import secur.com.example.app_aut.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {}