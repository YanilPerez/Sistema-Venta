package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.modelo.Customer;
import pe.edu.upeu.sysalmacenfx.modelo.Product;
import pe.edu.upeu.sysalmacenfx.repositorio.CustomerRepository;
import pe.edu.upeu.sysalmacenfx.repositorio.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    // Obtener todos los productos
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Obtener un producto por su ID
    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    // Añadir un nuevo producto
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // Actualizar un producto existente
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    // Eliminar un producto por su ID
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
}