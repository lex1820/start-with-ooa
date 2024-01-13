// This is a simple implementation of a repository that uses a flat file (plain text) to store data.

public class FlatFileProductsRepository implements ProductsRepository {

    private static final Logger LOGGER = Logger.getLogger(FlatFileProductsRepository.class.getName());

    @Override
    public void addProduct(Product product) {
        List<Product> products = getProducts();
        products.add(product);

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("data.ser")
        )) {
            oos.writeObject(products);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Unable to persist products to file.", ex);
            throw new ProductsException("Unable to add product.");
        }
    }

    @Override
    public List<Product> getProducts() {
        File f = new File("data.ser");
        if (f.exists()) {

            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream("data.ser")
            )) {
                return (List<Product>) ois.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                LOGGER.log(Level.SEVERE, "Unable to retrieve products from file.", ex);
                throw new ProductsException("Unable to get products.");
            }
        } else {
            return new ArrayList<Product>();
        }
    }
}
