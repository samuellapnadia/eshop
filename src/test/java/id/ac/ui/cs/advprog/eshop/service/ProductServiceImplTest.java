package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setup() {
        product = new Product();
        product.setProductId("1");
        product.setProductName("Test Product");
        product.setProductQuantity(50);
    }

    @Test
    void testCreateProduct() {
        when(productRepository.create(any(Product.class))).thenReturn(product);
        Product createdProduct = productService.create(product);
        assertNotNull(createdProduct);
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAllProducts() {
        List<Product> products = new ArrayList<>();
        products.add(product);
        when(productRepository.findAll()).thenReturn(products.iterator());

        List<Product> result = productService.findAll();
        assertEquals(1, result.size());
        assertEquals("Test Product", result.get(0).getProductName());
    }

    @Test
    void testFindById_ProductExists() {
        when(productRepository.findById("b558e9f-1c39-460e-8860-71af6af63b32")).thenReturn(product);
        Product foundProduct = productService.findById("b558e9f-1c39-460e-8860-71af6af63b32");
        assertNotNull(foundProduct);
        assertEquals("Test Product", foundProduct.getProductName());
    }

    @Test
    void testFindById_ProductNotFound() {
        when(productRepository.findById("b558e9f-1c39-460e-8860-71af6af63b34")).thenReturn(null);
        Product foundProduct = productService.findById("b558e9f-1c39-460e-8860-71af6af63b34");
        assertNull(foundProduct);
    }

    @Test
    void testUpdateProduct() {
        doNothing().when(productRepository).update(eq("1"), any(Product.class));
        productService.update("1", product);
        verify(productRepository, times(1)).update(eq("1"), any(Product.class));
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).delete("1");
        productService.delete("1");
        verify(productRepository, times(1)).delete("1");
    }
}
