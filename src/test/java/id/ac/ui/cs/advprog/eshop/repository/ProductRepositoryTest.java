package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId(UUID.randomUUID().toString());
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testUpdateProduct_Success() {
        Product product = new Product();
        product.setProductId("test-id-123");
        product.setProductName("Old Name");
        product.setProductQuantity(50);

        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("test-id-123");
        updatedProduct.setProductName("Imud Product");
        updatedProduct.setProductQuantity(75);

        productRepository.update("test-id-123", updatedProduct);
        Product modifiedProduct = productRepository.findById("test-id-123");

        assertEquals("Imud Product", modifiedProduct.getProductName());
        assertEquals(75, modifiedProduct.getProductQuantity());
    }

    @Test
    void testUpdateProduct_Fail_ProductNotFound() {
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Ghoib Product");
        updatedProduct.setProductQuantity(20);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productRepository.update("invalid-id-999", updatedProduct);
        });

        assertEquals("Entity with ID invalid-id-999 not found", exception.getMessage());
    }

    @Test
    void testDeleteProduct_Success() {
        Product product = new Product();
        product.setProductId("test-id-456");
        product.setProductName("Un-Imud Product");
        product.setProductQuantity(50);

        productRepository.create(product);
        assertNotNull(productRepository.findById("test-id-456"));

        productRepository.delete("test-id-456");
        assertNull(productRepository.findById("test-id-456"));
    }

    @Test
    void testCreateProduct_WithNullId() {
        Product product = new Product();
        product.setProductName("Unnamed Product");
        product.setProductQuantity(10);

        Product createdProduct = productRepository.create(product);

        assertNotNull(createdProduct.getProductId());
        assertEquals("Unnamed Product", createdProduct.getProductName());
        assertEquals(10, createdProduct.getProductQuantity());
    }

    @Test
    void testFindById_WithNullId() {
        assertNull(productRepository.findById(null));
    }

    @Test
    void testDeleteProduct_WithNullId() {
        productRepository.delete(null);
        assertNull(productRepository.findById(null));
    }
}
