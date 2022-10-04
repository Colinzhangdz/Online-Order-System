import com.group7.asd.dao.Database;
import com.group7.asd.model.Product;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

// Test add staff
public class MenuTest {
    @Test
    public void testAddProduct() {
                ArrayList<Product> products = Database.instance().getProducts();
                Product product = new Product();
                product.setId(999);
                product.setBrand_name("egg");
                product.setCompany_name("china");
                product.setOrdered(100);
                product.setDescription("1234");
                product.setStatus(1);
                Database.instance().addProductTest(product);
                ArrayList<Product> products1 = Database.instance().getProducts();
                Assertions.assertEquals(1, products1.size() - products.size());


    }
    @Test
    public void testUpdateProduct() {
        ArrayList<Product> products = Database.instance().getProducts();
        Product product = new Product();

        product.setBrand_name("joey1");
        product.setCompany_name("china");
        product.setOrdered(1009);
        product.setDescription("1234");
        product.setStatus(1);
        product.setId(999);
        Database.instance().editProduct(product);
        //ArrayList<Product> products1 = Database.instance().getProducts();
        Product product1 = Database.instance().getProductsById(999);
        Assertions.assertEquals(1009, product1.getOrdered());


    }

    @Test
    public void DeleteProduct() {
        ArrayList<Product> products = Database.instance().getProducts();
        Product product = new Product();

        Database.instance().deleteProduct(999);
        ArrayList<Product> products1 = Database.instance().getProducts();
        Assertions.assertEquals(1, products.size() - products1.size());


    }

}
