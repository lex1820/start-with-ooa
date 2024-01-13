/* This is the interface of all the repositories:
- here you need to define the methods that the repositories need to implement so that they can be used by the service layer.
- the service layer will use the interface to access the repositories, and it will not know anything about the implementation of the repositories.
- this is a good practice because it allows you to change the implementation of the repositories without affecting the service layer.
*/

public interface ProductsRepository {
    void addProduct(Product product);
    List<Product> getProducts();
}