
public class ProductsController {

    // Initialize the service here
    private final ProductsService service = new ProductsService();

    // Add all the FXML elements here that have an fx:id
    @FXML
    private ResourceBundle resources;

    @FXML
    private LblComboBoxTitle title;
    
    @FXML
    private URL location;

    @FXML
    private ListView<Product> lstProducts;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    void onAddProduct(ActionEvent event) {
        try {
            String name = txtName.getText();
            double price = Double.parseDouble(txtPrice.getText());

            Product product = new Product(name, price);
            service.addProduct(product);

            fillInProducts();
        } catch (ProductsException ex) {
            showAlert(ex);
        }
    }

    @FXML
    void initialize() {
        fillInProducts();
        title.setText("Products");
        // add more initializations here
    }

    private void fillInProducts() {
        lstProducts.setItems(
            FXCollections.observableList(service.getProducts())
        );
    }

    private void showAlert(ProductsException ex) {
        Alert al = new Alert(Alert.AlertType.ERROR, ex.getMessage());
        al.showAndWait();
    }

}
