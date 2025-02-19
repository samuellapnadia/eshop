package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
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
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9646e-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }
    // Update an existing product
    @Test
    void testUpdateProduct_Success() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63777");
        product.setProductName("Old Name");
        product.setProductQuantity(50);

        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductName("Imud Product");
        updatedProduct.setProductQuantity(75);

        productRepository.update("eb558e9f-1c39-460e-8860-71af6af63777", updatedProduct);
        Product modifiedProduct = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63777");

        assertEquals("Imud Product", modifiedProduct.getProductName());
        assertEquals(75, modifiedProduct.getProductQuantity());
    }
    // Update a non-existent product
    @Test
    void testUpdateProduct_Fail_ProductNotFound() {
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Ghoib Product");
        updatedProduct.setProductQuantity(20);

        productRepository.update("a0f9646e-9022-437d-a0bf-d0821dde9096", updatedProduct);
        assertNull(productRepository.findById("a0f9646e-9022-437d-a0bf-d0821dde9096"));
    }
    // Delete an existing product
    @Test
    void testDeleteProduct_Success() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63777");
        product.setProductName("Un-Imud Product");
        product.setProductQuantity(50);

        productRepository.create(product);
        assertNotNull(productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63777"));

        productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63777");
        assertNull(productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63777"));
    }

    // Delete a non-existent product
    @Test
    void testDeleteProduct_Fail_ProductNotFound() {
        productRepository.delete("a0f9646e-9022-437d-a0bf-d0821dde9096");
        assertNull(productRepository.findById("a0f9646e-9022-437d-a0bf-d0821dde9096"));
    }

    // Create product with null ID (should generate UUID)
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

    // Create product with empty ID (should generate UUID)
    @Test
    void testCreateProduct_WithEmptyId() {
        Product product = new Product();
        product.setProductId("");
        product.setProductName("Empty ID Product");
        product.setProductQuantity(20);

        Product createdProduct = productRepository.create(product);

        assertNotNull(createdProduct.getProductId());
        assertEquals("Empty ID Product", createdProduct.getProductName());
        assertEquals(20, createdProduct.getProductQuantity());
    }

    // findById with null input
    @Test
    void testFindById_WithNullId() {
        assertNull(productRepository.findById(null));
    }

    // Delete product with null ID
    @Test
    void testDeleteProduct_WithNullId() {
        productRepository.delete(null);
        assertNull(productRepository.findById(null));
    }

}
