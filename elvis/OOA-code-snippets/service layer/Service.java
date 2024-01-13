
public class ProductsService {

    // You need to initialize a repository here, you can choose between a flat file repository or a MySQL repository
    private final ProductsRepository repo = new FlatFileProductsRepository();

    public void addProduct(Product product) {
        if (product.getPrice() > 99.99) {
            throw new ProductsException("We don't do expensive products here!");
        }

        repo.addProduct(product);
    }

    public List<Product> getProducts() {
        return repo.getProducts();
    }

    // Add other methods here

}
