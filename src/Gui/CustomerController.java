package Gui;

import Model.Customer;
import Model.CustomerList;
import Model.CustomerModelManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class CustomerController
{
  @FXML private TextField nameField;
  @FXML private TextField phoneNumberField;
  @FXML private TextField emailAddressField;

  @FXML private TextField searchByPhoneField;
  @FXML private TextField searchByNameField;

  @FXML private TableView<Customer> customerTable;
  @FXML private TableColumn<Customer, String> nameColumn;
  @FXML private TableColumn<Customer, String> phoneNumberColumn;
  @FXML private TableColumn<Customer, String> emailAddressColumn;

  @FXML private TextField updateNameField;
  @FXML private TextField updatePhoneNumberField;
  @FXML private TextField updateEmailAddressField;

  private CustomerModelManager customerModelManager = new CustomerModelManager("customers.bin");
  private CustomerList customerList = customerModelManager.getAllCustomers();
  private Customer selectedCustomer;

  public void initialize()
  {
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    emailAddressColumn.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));

    CustomerList customerList = customerModelManager.getAllCustomers();
    for (int i = 0; i < customerList.size(); i++)
    {
      customerTable.getItems().add(customerList.getByIndex(i));
    }
  }

  public void saveButtonPressed()
  {
    String name = nameField.getText();
    String phoneNumber = phoneNumberField.getText();
    String emailAddress = emailAddressField.getText();

    if (name.isEmpty() || phoneNumber.isEmpty() || emailAddress.isEmpty())
    {
      showAlert(Alert.AlertType.ERROR, "Error", "Please fill in all fields", "All fields must be filled in");
    }
    else if (customerList.getCustomerByPhoneNumber(phoneNumber) != null)
    {
      showAlert(Alert.AlertType.ERROR, "Error", "Phone number already exists", "Customer with this phone number already exists");
    }
    else if (!emailAddress.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"))
    {
      showAlert(Alert.AlertType.ERROR, "Error", "Invalid Email Format", "The email should follow user@host.domain.");
    }
    else if (isEmailDuplicate(emailAddress))
    {
      showAlert(Alert.AlertType.ERROR, "Error", "Email already exists", "Customer with this email already exists");
    }
    else
    {
      Customer customer = new Customer(name, phoneNumber, emailAddress);
      customerList.addCustomer(customer);
      customerModelManager.saveCustomers(customerList);
      customerTable.getItems().clear();
      for (int i = 0; i < customerList.size(); i++)
      {
        customerTable.getItems().add(customerList.getByIndex(i));
      }
      nameField.clear();
      phoneNumberField.clear();
      emailAddressField.clear();
      showAlert(Alert.AlertType.INFORMATION, "Success", "Customer added", "Customer has been added successfully");
    }
  }

  public void searchByPhoneButtonPressed()
  {
    String phoneNumber = searchByPhoneField.getText();
    Customer customer = customerList.getCustomerByPhoneNumber(phoneNumber);
    if (customer != null && !phoneNumber.isEmpty())
    {
      customerTable.getItems().clear();
      customerTable.getItems().add(customer);
      searchByNameField.clear();
    }
    else
    {
      showAlert(Alert.AlertType.ERROR, "Error", "Customer not found", "Customer with this phone number does not exist");
    }
  }

  public void searchByNameButtonPressed()
  {
    String name = searchByNameField.getText();
    CustomerList searchCustomers = customerList.getCustomerByName(name);
    if (customerList != null && !name.isEmpty())
    {
      customerTable.getItems().clear();
      for (int i = 0; i < searchCustomers.size(); i++)
      {
        customerTable.getItems().add(searchCustomers.getByIndex(i));
      }
      searchByPhoneField.clear();
    }
    else
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Customer not found");
      alert.showAndWait();
    }
  }

  public void updateTableButton()
  {
    customerTable.getItems().clear();
    for (int i = 0; i < customerList.size(); i++)
    {
      customerTable.getItems().add(customerList.getByIndex(i));
    }

    updateNameField.clear();
    updatePhoneNumberField.clear();
    updateEmailAddressField.clear();

    searchByNameField.clear();
    searchByPhoneField.clear();
  }

  public void editInfoButtonPressed()
  {
    String name = updateNameField.getText();
    String phoneNumber = updatePhoneNumberField.getText();
    String emailAddress = updateEmailAddressField.getText();

    if (selectedCustomer == null)
    {
      showAlert(Alert.AlertType.ERROR, "Error", "No Customer Selected", "Please select a customer to edit.");
      return;
    }

    if (name.isEmpty() || phoneNumber.isEmpty() || emailAddress.isEmpty())
    {
      showAlert(Alert.AlertType.ERROR, "Error", "Incomplete Fields", "All fields must be filled in.");
      return;
    }

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Are you sure you want to update this customer?");
    alert.setContentText("This action cannot be undone.");

    if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK)
    {
      selectedCustomer.setName(name);
      selectedCustomer.setPhoneNumber(phoneNumber);
      selectedCustomer.setEmailAddress(emailAddress);

      customerModelManager.saveCustomers(customerList);
      updateTableButton();
      showAlert(Alert.AlertType.INFORMATION, "Success", "Customer Updated", "The customer information has been updated successfully.");
    }
  }

  public void deleteButtonPressed()
  {
    if (selectedCustomer == null)
    {
      showAlert(Alert.AlertType.ERROR, "Error", "No Customer Selected", "Please select a customer to delete.");
      return;
    }

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Are you sure you want to delete this customer?");
    alert.setContentText("This action cannot be undone.");

    if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK)
    {
      customerList.removeCustomer(selectedCustomer);
      customerModelManager.saveCustomers(customerList);
      updateTableButton();
      showAlert(Alert.AlertType.INFORMATION, "Success", "Customer Deleted", "The customer has been deleted successfully.");
    }
  }

  public void customerSelected()
  {
    selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
    if (selectedCustomer != null)
    {
      updateNameField.setText(selectedCustomer.getName());
      updatePhoneNumberField.setText(selectedCustomer.getPhoneNumber());
      updateEmailAddressField.setText(selectedCustomer.getEmailAddress());
    }
    else
    {
      updateNameField.clear();
      updatePhoneNumberField.clear();
      updateEmailAddressField.clear();
    }
  }

  /**
   * Helper method to check if an email already exists in the customer list
   * @param email the email to check
   * @return true if the email exists, false otherwise
   */
  private boolean isEmailDuplicate(String email)
  {
    for (int i = 0; i < customerList.size(); i++)
    {
      if (customerList.getByIndex(i).getEmailAddress().equals(email))
      {
        return true;
      }
    }
    return false;
  }

  /**
   * Helper method to show an alert
   * @param type the type of the alert
   * @param title the title of the alert
   * @param header the header of the alert
   * @param content the content of the alert
   */
  private void showAlert(Alert.AlertType type, String title, String header, String content)
  {
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.showAndWait();
  }
}
