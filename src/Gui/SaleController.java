package Gui;

import Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class SaleController
{
  @FXML private TextField searchPetField;
  @FXML private TableView<Pet> petTable;
  @FXML private TableColumn<Pet, String> petNameColumn;
  @FXML private TableColumn<Pet, MyDate> petBirthDateColumn;
  @FXML private TableColumn<Pet, String> petGenderColumn;
  @FXML private TableColumn<Pet, String> petColorColumn;
  @FXML private TableColumn<Pet, Double> petPriceColumn;

  @FXML private TextField searchCustomerByPhoneField;
  @FXML private TextField searchCustomerByNameField;
  @FXML private TableView<Customer> customerTable;
  @FXML private TableColumn<Customer, String> customerNameColumn;
  @FXML private TableColumn<Customer, String> customerPhoneColumn;
  @FXML private TableColumn<Customer, String> customerEmailColumn;

  @FXML private TextField petNameField;
  @FXML private TextField customersNameField;

  @FXML private TextField discountField;
  @FXML private TextField finalPrice;
  @FXML private Button discountButton;

  @FXML private TextField searchSaleField;
  @FXML private TableView<Sale> saleTable;
  @FXML private TableColumn<Sale, String> salePetsNameColumn;
  @FXML private TableColumn<Sale, MyDate> saleDateColumn;
  @FXML private TableColumn<Sale, String> saleCustomerNameColumn;
  @FXML private TableColumn<Sale, Double> salePriceColumn;
  @FXML private TextArea saleDetailsField;

  private Pet selectedPet;
  private Customer selectedCustomer;
  private SalesLog salesLog;
  private SaleModelManager saleManager;
  private Sale sale;

  public void initialize()
  {
    // Load all pets from PetModelManager
    PetModelManager petModelManager = new PetModelManager("pets.bin");
    PetList allPets = petModelManager.getAllPets();
    petTable.getItems().clear();

    // Add all pets to the TableView using getByIndex and size
    for (int i = 0; i < allPets.size(); i++) {
      if (allPets.getByIndex(i).getCustomer() == null)
      {
        petTable.getItems().add(allPets.getByIndex(i));
      }
    }

    // Set up the columns to display Pet attributes
    petNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    petBirthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
    petGenderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
    petColorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
    petPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    CustomerList allCustomers = new CustomerModelManager("customers.bin").getAllCustomers();
    customerTable.getItems().clear();

    // Add all customers to the TableView using getByIndex and size
    for (int i = 0; i < allCustomers.size(); i++) {
      customerTable.getItems().add(allCustomers.getByIndex(i));
    }

    // Set up the columns to display Customer attributes
    customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    customerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    customerEmailColumn.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));

    // Initialize SaleModelManager and SalesLog
    saleManager = new SaleModelManager("sales.bin");
    salesLog = saleManager.getAllSales();
    saleTable.getItems().clear();

    // Add all sales to the TableView using getByIndex and size
    for (int i = 0; i < salesLog.size(); i++)
    {
      saleTable.getItems().add(salesLog.getByIndex(i));
    }

    if (petTable == null || saleTable == null || customerTable == null)
    {
      throw new IllegalStateException("Table views are not set");
    }
    // Set up the columns to display Sale attributes
    salePetsNameColumn.setCellValueFactory(new PropertyValueFactory<>("petName"));
    saleDateColumn.setCellValueFactory(new PropertyValueFactory<>("saleDate"));
    saleCustomerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customer"));
    salePriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
  }

  public void onSearchPetByPetName()
  {
  String searchText = searchPetField.getText().trim();
  if (searchText.isEmpty()) {
    showAlert(Alert.AlertType.WARNING, "Warning", "Search Field is Empty", "Please enter a name to search for pets.");
    return;
  }

  PetList allPets = new PetModelManager("pets.bin").getAllPets();
  PetList filteredPets = allPets.getPetsByName(searchText);

  petTable.getItems().clear();
  for (int i = 0; i < filteredPets.size(); i++)
  {
    if (filteredPets.getByIndex(i).getCustomer() == null)
    {
      petTable.getItems().add(filteredPets.getByIndex(i));
    }
  }

  if (filteredPets.size() == 0) {
    showAlert(Alert.AlertType.INFORMATION, "No Results", "No Pets Found", "No pets match the search term '" + searchText + "'.");
  }
}

  public void onSearchCustomerByPhoneNumber()
  {
    String searchText = searchCustomerByPhoneField.getText();
    if (searchText.isEmpty()) {
      showAlert(Alert.AlertType.WARNING, "Warning", "Search Field is Empty", "Please enter a phone number to search for customers.");
      return;
    }

    CustomerList allCustomers = new CustomerModelManager("customers.bin").getAllCustomers();

    // Get filtered customers by phone
    Customer customer = allCustomers.getCustomerByPhoneNumber(searchText);

    // Clear the table and display filtered customers
    customerTable.getItems().clear();
    customerTable.getItems().add(customer);

    searchCustomerByNameField.clear();
    // If no customers found, show an alert
    if (customer == null) {
      showAlert(Alert.AlertType.INFORMATION, "No Results", "No Customers Found", "No customers match the search term '" + searchText + "'.");
    }
  }

  public void onSearchCustomerByName()
  {
    String searchText = searchCustomerByNameField.getText();
    if (searchText.isEmpty())
    {
      showAlert(Alert.AlertType.WARNING, "Warning", "Search Field is Empty", "Please enter a name to search for customers.");
      return;
    }
    CustomerList allCustomers = new CustomerModelManager("customers.bin").getAllCustomers();
    // Get filtered customers by name
    CustomerList filteredCustomers = allCustomers.getCustomerByName(searchText);

    // Clear the table and display filtered customers
    customerTable.getItems().clear();

    for (int i = 0; i < filteredCustomers.size(); i++) {
      customerTable.getItems().add(filteredCustomers.getByIndex(i));
    }

    searchCustomerByPhoneField.clear();
    // If no customers found, show an alert
    if (filteredCustomers.size() == 0)
    {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("No Results");
      alert.setHeaderText("No Customers Found");
      alert.setContentText("No customers match the search term '" + searchText + "'.");
      alert.showAndWait();
    }
  }

  public void onPetSelected()
  {
    selectedPet = petTable.getSelectionModel().getSelectedItem();
    if (selectedPet != null) {
      petNameField.setText(selectedPet.getName());
      discountButton.setDisable(false);
    } else {
      petNameField.clear();
    }
  }

  public void onCustomerSelected()
  {
    selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
    if (selectedCustomer != null) {
      customersNameField.setText(selectedCustomer.getName());
    } else {
      customersNameField.clear();
    }
  }

  public void onAddDiscount()
  {
    if (selectedPet == null || selectedCustomer == null)
    {
      showAlert(Alert.AlertType.WARNING, "Warning", "No Sale Created", "Please select a pet and a customer to create a sale.");
      return;
    }

    // Trim the input to remove any leading/trailing whitespace
    String discountText = discountField.getText().trim();

    // Check if the field is empty
    if (discountText.isEmpty())
    {
      showAlert(Alert.AlertType.ERROR, "Error", "Discount Field is Empty", "Please enter a discount value between 0 and 100.");
    }
    else
    {
      // Remove any non-numeric characters (except potentially a decimal point)
      discountText = discountText.replaceAll("[^0-9.]", "");

      try
      {
        // Parse the cleaned input
        double discountValue = Double.parseDouble(discountText);

        // Validate the discount range
        if (discountValue < 0 || discountValue > 100)
        {
          showAlert(Alert.AlertType.ERROR, "Error", "Invalid Discount Value", "Please enter a valid discount value between 0 and 100.");
        }

        // If all checks pass, create the sale and set the discount
        sale  = new Sale(selectedPet, selectedCustomer);

        sale.setDiscount(discountValue);
        finalPrice.clear();
        finalPrice.setText(String.valueOf(sale.getPrice()));
        discountButton.setDisable(true);
      }
      catch (NumberFormatException e)
      {
        showAlert(Alert.AlertType.ERROR, "Error", "Invalid Discount Value", "Please enter a valid discount value between 0 and 100.");
      }
    }
  }

  public void onSell()
  {
    if (selectedPet == null || selectedCustomer == null)
    {
      showAlert(Alert.AlertType.WARNING, "Warning", "No Pet or Customer Selected", "Please select a pet and a customer to sell.");
      return;
    }

    if (sale == null)
    {
      sale = new Sale(selectedPet, selectedCustomer);
    }

    // Add the sale to the SalesLog and save it
    salesLog.addSale(sale);
    saleManager.saveSales(salesLog);

    // Save the pets in the file with the updated customer
    PetModelManager petModelManager = new PetModelManager("pets.bin");
    PetList allPets = petModelManager.getAllPets();
    petModelManager.savePets(allPets);

    // Update the pet table
    petTable.getItems().clear();
    onUpdatePetTable();

    // Update the sale table
    saleTable.getItems().clear();
    SalesLog salesLog = saleManager.getAllSales();
    for (int i = 0; i < salesLog.size(); i++)
    {
      saleTable.getItems().add(salesLog.getByIndex(i));
    }
    // Clear all fields
    searchCustomerByPhoneField.clear();
    searchCustomerByNameField.clear();
    searchPetField.clear();
    petNameField.clear();
    customersNameField.clear();
    discountField.clear();
    finalPrice.clear();
    discountButton.setDisable(false);
    showAlert(Alert.AlertType.INFORMATION, "Sale Completed", "Sale Completed Successfully", "The sale has been completed successfully.");
  }

  public void onCancel()
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Cancel Sale");
    alert.setHeaderText("Are you sure you want to cancel the sale?");
    alert.setContentText("This will clear all fields and reset the sale.");
    alert.showAndWait();

    if (alert.getResult() == ButtonType.OK)
    {
      double originalPrice = sale.getPrice();
      selectedPet.setPrice(originalPrice);
      sale = null;
      searchCustomerByPhoneField.clear();
      searchCustomerByNameField.clear();
      searchPetField.clear();
      petNameField.clear();
      customersNameField.clear();
      discountField.clear();
      finalPrice.clear();
      discountButton.setDisable(false);
    }
  }

  public void onSearchSale()
  {
    String searchText = searchSaleField.getText();
    if (searchText.isEmpty())
    {
      showAlert(Alert.AlertType.WARNING, "Warning", "Search Field is Empty", "Please enter a name to search for sales.");
      return;
    }

    // Get filtered sales by name
    SalesLog filteredSales = salesLog.getSalesByCustomersPhoneNumber(searchText);

    // Clear the table and display filtered sales
    saleTable.getItems().clear();
    for (int i = 0; i < filteredSales.size(); i++)
    {
      saleTable.getItems().add(filteredSales.getByIndex(i));
    }

    // If no sales found, show an alert
    if (filteredSales.size() == 0)
    {
      showAlert(Alert.AlertType.INFORMATION, "No Results", "No Sales Found", "No sales match the search term '" + searchText + "'.");
    }
  }

  public void onSaleSelected()
  {
    Sale selectedSale = saleTable.getSelectionModel().getSelectedItem();
    if (selectedSale != null)
    {
      saleDetailsField.setText(selectedSale.toString());
    }
    else
    {
      saleDetailsField.clear();
    }
  }

  public void onUpdatePetTable()
  {
    petTable.getItems().clear();
    PetModelManager petModelManager = new PetModelManager("pets.bin");
    PetList allPets = petModelManager.getAllPets();

    for (int i =0; i< allPets.size(); i++)
    {
      if (allPets.getByIndex(i).getCustomer() == null)
      {
        petTable.getItems().add(allPets.getByIndex(i));
      }
    }
  }

  public void onUpdateCustomer()
  {
    customerTable.getItems().clear();
    CustomerModelManager customerModelManager = new CustomerModelManager("customers.bin");
    CustomerList allCustomers = customerModelManager.getAllCustomers();

    for (int i =0; i< allCustomers.size(); i++)
    {
      customerTable.getItems().add(allCustomers.getByIndex(i));
    }
  }

  public void onUpdateSale()
  {
    saleTable.getItems().clear();
    SaleModelManager saleModelManager = new SaleModelManager("sales.bin");
    SalesLog allSales = saleModelManager.getAllSales();

    for (int i = 0; i < allSales.size(); i++)
    {
      saleTable.getItems().add(allSales.getByIndex(i));
    }
  }

  private void showAlert(Alert.AlertType type, String title, String header, String content)
  {
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.showAndWait();
  }
}
