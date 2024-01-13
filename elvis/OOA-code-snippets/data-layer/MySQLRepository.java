import be.howest.ti.products.data.util.MySqlConnection;
import be.howest.ti.products.domain.Product;
import be.howest.ti.products.util.ProductsException;

public class MySqlProductsRepository implements ProductsRepository {

    private static final Logger LOGGER = Logger.getLogger(MySqlProductsRepository.class.getName());
    private static final String SQL_SELECT_ALL_PRODUCTS = Config.getInstance().readSetting(db.selectAllProducts)// or "select * from products";
    private static final String SQL_INSERT_PRODUCT = Config.getInstance().readSetting(db.insertProduct) // or "insert into products(name, price) values(?, ?)";

    @Override
    public void addProduct(Product product) {
        try (Connection con = MySqlConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(SQL_INSERT_PRODUCT, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());

            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                keys.next();
                int newProductId = keys.getInt(1);

                product.setId(newProductId);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Unable to add product to DB.", ex);
            throw new ProductsException("Unable to add product.");
        }
    }

    @Override
    public List<Product> getProducts() {
        try (Connection con = MySqlConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(SQL_SELECT_ALL_PRODUCTS);
             ResultSet rs = stmt.executeQuery()) {

            List<Product> products = new ArrayList<>();

            while (rs.next()) {
                Product product = rs2product(rs);
                products.add(product);
            }

            return products;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Unable to retrieve products from DB.", ex);
            throw new ProductsException("Unable to retrieve products.");
        }
    }

    private Product rs2product(ResultSet rs) throws SQLException {
        return new Product(rs.getInt("id"),
                rs.getString("name"),
                rs.getDouble("price"));
    }
}
