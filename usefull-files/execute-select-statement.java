private static final String SQL_SELECT_ALL_PRODUCTS = "select * from product";

//EXECUTE SELECT STATEMENT
private List<Product> getProducts() {
    try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    PreparedStatement prep = connection.prepareStatement(SQL_SELECT_ALL_PRODUCTS);
    ResultSet rs = prep.executeQuery()) {
        List<Product> products = new ArrayList<>();
        while (rs.next()) {
            products.add(resultSetToProduct(rs));
        }
        return products;
    } catch (SQLException ex) {
        LOGGER.log(Level.SEVERE, "A database error occured retrieving all products.", ex);
        throw new ShopException("Unable to retrieve products.");
    }
}

private Product resultSetToProduct(ResultSet rs) throws SQLException {
    return new Product(rs.getInt("id"), rs.getString("name"),
    rs.getDouble("price"));
}

//EXECUTE SELECT STATEMENT WITH PARAMETERS
private static final String SQL_SELECT_PRODUCTS = "select * from product where price > ?";

private List<Product> getProducts() {
    try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    PreparedStatement prep = connection.prepareStatement(SQL_SELECT_PRODUCTS)) {
        prep.setDouble(1, 300);
        try (ResultSet rs = prep.executeQuery()) {
            //...
            return products;
        }
    } catch (SQLException ex) {
        // handle the exceptions
    }
}