package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        productRepository.create(product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    // Finds the product based on ID
    @Override
    public Product findById(String id) {
        return productRepository.findById(id);
    }

    // Updates the product based on the intended ID
    @Override
    public void update(String id, Product product) {
        productRepository.update(id, product);
    }

    // Deletes the product based on the intended ID
    @Override
    public void delete(String id) {
        productRepository.delete(id);
    }
}
