package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository extends GenericRepository<Product> {
    @Override
    protected String getId(Product product) {
        return product.getProductId();
    }

    @Override
    protected void setId(Product product, String id) {
        product.setProductId(id);
    }
}
