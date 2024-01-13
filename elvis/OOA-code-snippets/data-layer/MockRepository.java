// Mock implementation of the Repository interface

public class MockProductsRepository implements Repository {
    private Map<Integer, Product> storage = new HashMap<>();
    private int currentId = 1;

    @Override
    public void addProduct(Product product) {
        product.setId(currentId);
        storage.put(currentId, product);
        currentId++;
    }

    @Override
    public List<Product> getProducts() {
        return new ArrayList<>(storage.values());
    }
}