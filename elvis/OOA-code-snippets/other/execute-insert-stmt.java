//EXECUTE AN INSERT
private static final String SQL_ADD_PRODUCT = "insert into product(name, price) values (?, ?)";

private void addProduct(){
    try(
    Connection connection = DriveManager.getConnection(URL, USERNAME, PASSWORD);
    PreparedStatement prep = connection.prepareStatement(SQL_ADD_PRODUCT)){
        prep.setString(1, "tablet");
        prep.setDouble(2, 499);
        
        prep.executeUpdate();    
    } catch (SQLException ex){
        LOGGER.log(Level.SEVERE, "A database error occured adding a product.", ex);
        throw new RunTimeException("Unable to add product.");
    }
}