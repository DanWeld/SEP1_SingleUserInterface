package Gui;

import Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class PetController
{

  // Add Pet - Dog Tab
  @FXML private TextField dogNameField;
  @FXML private RadioButton dogMaleRadio;
  @FXML private RadioButton dogFemaleRadio;
  @FXML private DatePicker dogBirthDatePicker;
  @FXML private TextField dogColorField;
  @FXML private TextField dogPriceField;
  @FXML private TextField dogBreedField;
  @FXML private TextField dogBreederField;
  @FXML private TextArea dogCommentArea;
  @FXML private RadioButton dogKennel;
  @FXML private RadioButton dogPetShop;


  // Add Pet - Cat Tab
  @FXML private TextField catNameField;
  @FXML private RadioButton catMaleRadio;
  @FXML private RadioButton catFemaleRadio;
  @FXML private DatePicker catBirthDatePicker;
  @FXML private TextField catColorField;
  @FXML private TextField catPriceField;
  @FXML private TextField catBreedField;
  @FXML private TextField catBreederField;
  @FXML private TextArea catCommentArea;
  @FXML private RadioButton catKennel;
  @FXML private RadioButton catPetShop;

  // Add Pet - Rodent Tab
  @FXML private TextField rodentNameField;
  @FXML private RadioButton rodentMaleRadio;
  @FXML private RadioButton rodentFemaleRadio;
  @FXML private DatePicker rodentBirthDatePicker;
  @FXML private TextField rodentColorField;
  @FXML private TextField rodentPriceField;
  @FXML private TextField rodentSpeciesField;
  @FXML private TextArea rodentCommentArea;
  @FXML private Button addRodentButton;
  @FXML private RadioButton rodentIsBite;
  @FXML private RadioButton rodentIsNotBite;

  // Add Pet - Bird Tab
  @FXML private TextField birdNameField;
  @FXML private RadioButton birdMaleRadio;
  @FXML private RadioButton birdFemaleRadio;
  @FXML private DatePicker birdBirthDatePicker;
  @FXML private TextField birdColorField;
  @FXML private TextField birdPriceField;
  @FXML private TextField birdSpeciesField;
  @FXML private TextField birdFoodField;
  @FXML private TextArea birdCommentArea;
  @FXML private RadioButton birdKennel;
  @FXML private RadioButton birdPetShop;

  @FXML private TextField fishNameField;
  @FXML private RadioButton fishMaleRadio;
  @FXML private RadioButton fishFemaleRadio;
  @FXML private DatePicker fishBirthDatePicker;
  @FXML private TextField fishColorField;
  @FXML private TextField fishPriceField;
  @FXML private TextField fishSpeciesField;
  @FXML private RadioButton fishFreshWaterRadio;
  @FXML private RadioButton fishSaltWaterRadio;
  @FXML private RadioButton fishPredetor;
  @FXML private RadioButton fishNotPredetor;
  @FXML private TextArea fishCommentArea;

  @FXML private TextField variousNameField;
  @FXML private RadioButton variousMaleRadio;
  @FXML private RadioButton variousFemaleRadio;
  @FXML private DatePicker variousBirthDatePicker;
  @FXML private TextField variousColorField;
  @FXML private TextField variousPriceField;
  @FXML private TextField variousSpeciesField;
  @FXML private TextArea variousCommentArea;

  @FXML private TableView<Pet> petListTable;
  @FXML private TableColumn<Pet, String> petNameColumn;
  @FXML private TableColumn<Pet, MyDate> petBirthDateColumn;
  @FXML private TableColumn<Pet, String> petSpeciesColumn;
  @FXML private TableColumn<Pet, String> petColorColumn;
  @FXML private TextField modifyPetNameField;
  @FXML private TextField searchPetField;
  @FXML private TextField modifyPetPriceField;
  @FXML private TextArea modifyPetCommentArea;

  @FXML private TableView<Customer> dogCustomerListTable;
  @FXML private TableColumn<Customer, String> customerNameColumn;
  @FXML private TableColumn<Customer, String> customerPhoneColumn;
  @FXML private TableColumn<Customer, String> customerEmailColumn;
  @FXML private TextField dogSelectedCustomer;
  @FXML private TextField dogSearchCustomerField;
  @FXML private TableView<Customer> catCustomerListTable;
  @FXML private TableColumn<Customer, String> catCustomerNameColumn;
  @FXML private TableColumn<Customer, String> catCustomerPhoneColumn;
  @FXML private TableColumn<Customer, String> catCustomerEmailColumn;
  @FXML private TextField catSelectedCustomer;
  @FXML private TextField catSearchCustomerField;
  @FXML private TableView<Customer> birdCustomerListTable;
  @FXML private TableColumn<Customer, String> birdCustomerNameColumn;
  @FXML private TableColumn<Customer, String> birdCustomerPhoneColumn;
  @FXML private TableColumn<Customer, String> birdCustomerEmailColumn;
  @FXML private TextField birdSelectedCustomer;
  @FXML private TextField birdSearchCustomerField;

  PetList pets =new PetList();
  Customer customer = null;
  MyDate birthDate;


  @FXML private void initialize()
  {
    petNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    petBirthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
    petSpeciesColumn.setCellValueFactory(new PropertyValueFactory<>("species"));
    petColorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));

    PetModelManager manager = new PetModelManager("pets.bin");
    PetList allPets = manager.getAllPets();

    for (int i = 0; i < allPets.size(); i++)
    {
      petListTable.getItems().add(allPets.getByIndex(i));
    }

    customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    customerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    customerEmailColumn.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
    catCustomerEmailColumn.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
    catCustomerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    catCustomerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    birdCustomerEmailColumn.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
    birdCustomerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    birdCustomerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));


    CustomerModelManager customerManager = new CustomerModelManager("customers.bin");
    CustomerList allCustomers = customerManager.getAllCustomers();
    for (int i = 0; i < allCustomers.size(); i++)
    {
      dogCustomerListTable.getItems().add(allCustomers.getByIndex(i));
      birdCustomerListTable.getItems().add(allCustomers.getByIndex(i));
      catCustomerListTable.getItems().add(allCustomers.getByIndex(i));
    }


  }

  @FXML private void handleAddDog()
  {
    // Retrieve field values
    String name = dogNameField.getText();
    char gender = 'U';
    String comment = dogCommentArea.getText();
    String color = dogColorField.getText();
    String breed = dogBreedField.getText();
    String breeder = dogBreederField.getText();
    boolean isKennel;
    int price = 0;
    if (dogKennel.isSelected())
    {
      isKennel=true;
      dogPriceField.editableProperty().setValue(false);
      dogPriceField.clear();
    }
    else if (dogPetShop.isSelected())
    {
      isKennel=false;
      dogPriceField.editableProperty().setValue(true);
    }
    else
    {
      showAlert(Alert.AlertType.ERROR, "Error", "Invalid",
          "Please select a kennel or pet shop.");
      return;
    }

    if(gender == 'U')
    {
      showAlert(Alert.AlertType.ERROR, "Error" , "Invalid Gender", "Please select a gender");
      return;
    }
    if (dogMaleRadio.isSelected())
    {   gender='m';}
    else
    {  gender='F';}


    if (name.isEmpty()|| color.isEmpty() || breed.isEmpty() || breeder.isEmpty())
    {
      showAlert(Alert.AlertType.ERROR, "Error", "Invalid Details",
          "Please enter all the details for the dog.");
      return;
    }

    // Parse price, handling invalid input
    if (dogBirthDatePicker.getValue() == null) {
      showAlert(Alert.AlertType.ERROR, "Error", "Invalid Date",
          "Please select a valid birthdate for the dog.");
      return;
    }

    birthDate = new MyDate(
        dogBirthDatePicker.getValue().getDayOfMonth(),
        dogBirthDatePicker.getValue().getMonthValue(),
        dogBirthDatePicker.getValue().getYear()
    );

    if (this.birthDate == null) {
      throw new IllegalArgumentException("Birthdate cannot be null.");
    }

    PetModelManager manager = new PetModelManager("pets.bin");
    PetList allPets = manager.getAllPets();

    // Create a new Dog object
    Dog newDog;
    if (isKennel) {
      if (customer == null) {
        showAlert(Alert.AlertType.ERROR, "Error", "Invalid Customer",
            "Please select a customer for the dog.");
        return;
      }
      newDog = new Dog(name, gender, comment, birthDate, color, customer, breed, breeder);
      newDog.setPrice(-1);
    } else {
      try
      {
        price = Integer.parseInt(dogPriceField.getText());
      }
      catch (NumberFormatException e)
      {
        showAlert(Alert.AlertType.ERROR, "Error", "Invalid Price",
            "Please enter a valid price for the dog.");
        return;
      }
      newDog = new Dog(name, gender, comment, birthDate, color, price, breed, breeder);
      dogSelectedCustomer.setText("No Customer Selected");
      newDog.setPrice(price);
    }

    // Add the new dog to the list
    allPets.addPet(newDog);

    // Save the updated list back to the file
    manager.savePets(allPets);

    // Log or store the dog (for now, just output to console)
    showAlert(Alert.AlertType.INFORMATION, "Success", "Dog Added",
        "Dog added successfully: ");
    clearDogForm();
    updatePetTable();
  }

  private void clearDogForm()
  {
    dogNameField.clear();
    dogMaleRadio.setSelected(false);
    dogFemaleRadio.setSelected(false);
    dogKennel.setSelected(false);
    dogPetShop.setSelected(false);
    dogBirthDatePicker.setValue(null);
    dogColorField.clear();
    dogPriceField.clear();
    dogBreedField.clear();
    dogBreederField.clear();
    dogCommentArea.clear();
    dogPriceField.editableProperty().setValue(true);
  }

  @FXML private void handleAddCat()
  {
    // Retrieve field values
    String name = catNameField.getText();
    char gender = 'U';
    String comment = catCommentArea.getText();
    String color = catColorField.getText();
    int price = 0;
    String breed = catBreedField.getText();
    String breeder = catBreederField.getText();
    boolean isKennel;

    if (catKennel.isSelected())
    {
      isKennel=true;
      catPriceField.editableProperty().setValue(false);
      catPriceField.clear();
    }
    else if (catPetShop.isSelected())
    {
      isKennel=false;
      catPriceField.editableProperty().setValue(true);
    }
    else
    {
      showAlert(Alert.AlertType.ERROR, "Error", "Invalid",
          "Please select a kennel or pet shop.");
      return;
    }

    if(gender == 'U')
    {
      showAlert(Alert.AlertType.ERROR, "Error" , "Invalid Gender", "Please select a gender");
      return;
    }

    if (catMaleRadio.isSelected())
    {
      gender = 'm';
    }
    else
    {
      gender = 'F';
    }

    if (name.isEmpty() || color.isEmpty() || breed.isEmpty() || breeder.isEmpty())
    {
      showAlert(Alert.AlertType.ERROR, "Error", "Invalid Data",
          "Please enter all the details for the cat.");
    }

    if (catBirthDatePicker.getValue() == null)
    {
      showAlert(Alert.AlertType.ERROR, "Error", "Invalid Date",
          "Please select a valid birthdate for the cat.");
    }

    birthDate = new MyDate(
        catBirthDatePicker.getValue().getDayOfMonth(),
        catBirthDatePicker.getValue().getMonthValue(),
        catBirthDatePicker.getValue().getYear()
    );

    if (this.birthDate == null)
    {
      throw new IllegalArgumentException("Birthdate cannot be null.");
    }

    PetModelManager manager = new PetModelManager("pets.bin");
    PetList allPets = manager.getAllPets();

    Cat newCat;
    if (isKennel) {
      if (customer == null) {
        showAlert(Alert.AlertType.ERROR, "Error", "Invalid Customer",
            "Please select a customer for the dog.");
        return;
      }
      newCat = new Cat(name, gender, comment, birthDate, color, customer, breed, breeder);
      newCat.setPrice(-1);
    } else {
      try
      {
        price = Integer.parseInt(dogPriceField.getText());
      }
      catch (NumberFormatException e)
      {
        showAlert(Alert.AlertType.ERROR, "Error", "Invalid Price",
            "Please enter a valid price for the dog.");
        return;
      }
      newCat = new Cat(name, gender, comment, birthDate, color, price, breed, breeder);
      catSelectedCustomer.setText("No Customer Selected");
      newCat.setPrice(price);
    }

    allPets.addPet(newCat);
    manager.savePets(allPets);
    showAlert(Alert.AlertType.INFORMATION, "Success", "Cat Added",
        "Cat added successfully: ");
    clearCatForm();
    updatePetTable();
  }

  private void clearCatForm()
  {
    catNameField.clear();
    catMaleRadio.setSelected(false);
    catFemaleRadio.setSelected(false);
    catBirthDatePicker.setValue(null);
    catColorField.clear();
    catPriceField.clear();
    catBreedField.clear();
    catBreederField.clear();
    catCommentArea.clear();
    catKennel.setSelected(false);
    catPetShop.setSelected(false);
    catPriceField.editableProperty().setValue(true);
  }

  @FXML
  private void handleAddBird()
  {
    String name = birdNameField.getText();
    char gender = 'U';

    if (birdMaleRadio.isSelected())
    {
      gender = 'm';
    }
    else
    {
      gender = 'F';
    }


    String comment = birdCommentArea.getText();
    String color = birdColorField.getText();
    int price;
    String food = birdFoodField.getText();
    String type = birdSpeciesField.getText();
    boolean isKennel;
    if (birdKennel.isSelected())
    {
      isKennel=true;
      birdPriceField.editableProperty().setValue(false);
      birdPriceField.clear();
    }
    else if (birdPetShop.isSelected())
    {
      isKennel=false;
      birdPriceField.editableProperty().setValue(true);
    }
    else
    {
      showAlert(Alert.AlertType.ERROR, "Error", "Invalid",
          "Please select a kennel or pet shop.");
      return;
    }

    if (name.isEmpty() || color.isEmpty() || food.isEmpty() || type.isEmpty())
    {
      showAlert(Alert.AlertType.ERROR, "Error", "Invalid Data",
          "Please enter all the details for the bird.");
    }

    if(gender == 'U')
    {
      showAlert(Alert.AlertType.ERROR, "Error" , "Invalid Gender", "Please select a gender");
      return;
    }

    MyDate birthDate = null;
    if (birdBirthDatePicker.getValue() != null) {
      birthDate = new MyDate(birdBirthDatePicker.getValue().getDayOfMonth(),
          birdBirthDatePicker.getValue().getMonthValue(),
          birdBirthDatePicker.getValue().getYear());
    }

    if (birthDate == null) {
      showAlert(Alert.AlertType.ERROR, "Error", "Invalid Date",
          "Please select a valid birthdate for the bird.");
      return;
    }

    PetModelManager manager = new PetModelManager("pets.bin");
    PetList allPets = manager.getAllPets();

    Bird bird;
    if (isKennel) {
      if (customer == null) {
        showAlert(Alert.AlertType.ERROR, "Error", "Invalid Customer",
            "Please select a customer for the dog.");
        return;
      }
      bird = new Bird(name, gender, comment, birthDate, color, customer,food, type);
      bird.setPrice(-1);
    }
    else
    {
      try
      {
        price = Integer.parseInt(dogPriceField.getText());
      }
      catch (NumberFormatException e)
      {
        showAlert(Alert.AlertType.ERROR, "Error", "Invalid Price",
            "Please enter a valid price for the dog.");
        return;
      }
      bird = new Bird(name, gender, comment, birthDate, color, price, food, type);
      birdSelectedCustomer.setText("No Customer Selected");
      bird.setPrice(price);
    }

    allPets.addPet(bird);
    manager.savePets(allPets);
    showAlert(Alert.AlertType.INFORMATION, "Success", "Bird Added",
        "Bird added successfully: ");
    clearBirdForm();
    updatePetTable();
  }

  private void clearBirdForm()
  {
    birdNameField.clear();
    birdMaleRadio.setSelected(false);
    birdFemaleRadio.setSelected(false);
    birdBirthDatePicker.setValue(null);
    birdColorField.clear();
    birdPriceField.clear();
    birdSpeciesField.clear();
    birdCommentArea.clear();
    birdFoodField.clear();
    birdKennel.setSelected(false);
    birdPetShop.setSelected(false);
    birdPriceField.editableProperty().setValue(true);
  }

  @FXML
  private void handleAddFish()
  {
    String name = fishNameField.getText();
    char gender = 'U';
    String comment = fishCommentArea.getText();
    String color = fishColorField.getText();
    int price;

    if (fishMaleRadio.isSelected())
    {
      gender = 'm';
    }
    else
    {
      gender = 'F';
    }

    if(gender == 'U')
    {
      showAlert(Alert.AlertType.ERROR, "Error" , "Invalid Gender", "Please select a gender");
      return;
    }

    try {
      price = Integer.parseInt(fishPriceField.getText());
    } catch (NumberFormatException e) {
      showAlert(Alert.AlertType.ERROR, "Error", "Invalid Price",
          "Please enter a valid price for the fish.");
      return;
    }

    MyDate birthDate = null;
    if (fishBirthDatePicker.getValue() != null) {
      birthDate = new MyDate(fishBirthDatePicker.getValue().getDayOfMonth(),
          fishBirthDatePicker.getValue().getMonthValue(),
          fishBirthDatePicker.getValue().getYear());
    }

    boolean isFreshWater = fishFreshWaterRadio.isSelected();
    boolean isPredetor= fishPredetor.isSelected();



    Fish newFish = new Fish(name, gender, comment, birthDate, color, price, isFreshWater,isPredetor);
    pets.addPet(newFish);


    clearFishForm();
    updatePetTable();
  }

  private void clearFishForm()
  {
    fishNameField.clear();
    fishMaleRadio.setSelected(false);
    fishFemaleRadio.setSelected(false);
    fishBirthDatePicker.setValue(null);
    fishColorField.clear();
    fishPriceField.clear();
    fishSpeciesField.clear();
    fishFreshWaterRadio.setSelected(false);
    fishSaltWaterRadio.setSelected(false);
    fishCommentArea.clear();
    fishPredetor.setSelected(false);
    fishNotPredetor.setSelected(false);
  }

  @FXML
  private void handleAddRodent()
  {
    // Validate mandatory fields
    if (rodentNameField.getText().isEmpty() || rodentSpeciesField.getText().isEmpty()) {
      System.out.println("Error: Rodent name and species are required.");
      return;
    }

    // Retrieve field values
    String name = rodentNameField.getText();
    char gender = rodentMaleRadio.isSelected() ? 'm' : rodentFemaleRadio.isSelected() ? 'F' : 'U';
    if (gender == 'U')
    {
      showAlert(Alert.AlertType.ERROR, "Error", "Invalid gender", "Please select a gender.");
    }
    boolean doesItBite= rodentIsBite.isSelected();

    String comment = rodentCommentArea.getText();
    String color = rodentColorField.getText();
    String type = rodentSpeciesField.getText();
    int price;

    try {
      price = Integer.parseInt(rodentPriceField.getText());
    } catch (NumberFormatException e) {
      showAlert(Alert.AlertType.ERROR, "Error", "Invalid Price",
          "Please enter a valid price for the rodent.");
      return;
    }

    MyDate birthDate = null;
    if (rodentBirthDatePicker.getValue() != null)
    {
      birthDate = new MyDate(rodentBirthDatePicker.getValue().getDayOfMonth(),
          rodentBirthDatePicker.getValue().getMonthValue(),
          rodentBirthDatePicker.getValue().getYear());
    }
    else
    {
      showAlert(Alert.AlertType.ERROR, "Error", "Invalid Date",
          "Please select a valid birthdate for the rodent.");
      return;
    }

    if (name.isEmpty() || color.isEmpty() || type.isEmpty())
    {
      showAlert(Alert.AlertType.ERROR, "Error", "Invalid Data",
          "Please enter all the details for the rodent.");
    }
    Rodents newRodent = new Rodents(name, gender, comment, birthDate, color, price, type,doesItBite);
    pets.addPet(newRodent);
    showAlert(Alert.AlertType.INFORMATION, "Success", "Rodent Added",
        "Rodent added successfully: ");

    clearRodentForm();
    updatePetTable();
  }

  private void clearRodentForm()
  {
    rodentNameField.clear();
    rodentMaleRadio.setSelected(false);
    rodentFemaleRadio.setSelected(false);
    rodentBirthDatePicker.setValue(null);
    rodentColorField.clear();
    rodentPriceField.clear();
    rodentSpeciesField.clear();
    rodentCommentArea.clear();
    rodentIsBite.setSelected(false);
    rodentIsNotBite.setSelected(false);
  }

  @FXML
  private void handleAddVarious()
  {
    // Retrieve field values
    String name = variousNameField.getText();
    char gender = variousMaleRadio.isSelected() ? 'm' : variousFemaleRadio.isSelected() ? 'F' : 'U';
    if (gender == 'U')
    {
      showAlert(Alert.AlertType.ERROR, "Error", "Invalid Gender",
          "Please select a gender.");
    }
    String comment = variousCommentArea.getText();
    String color = variousColorField.getText();
    int price;

    try {
      price = Integer.parseInt(variousPriceField.getText());
    } catch (NumberFormatException e) {
      System.out.println("Error: Invalid price entered.");
      return;
    }

    MyDate birthDate = null;
    if (variousBirthDatePicker.getValue() != null) {
      birthDate = new MyDate(variousBirthDatePicker.getValue().getDayOfMonth(),
          variousBirthDatePicker.getValue().getMonthValue(),
          variousBirthDatePicker.getValue().getYear());
    }
    String species = variousSpeciesField.getText();

    if (name.isEmpty() || color.isEmpty() || species.isEmpty())
    {
      showAlert(Alert.AlertType.ERROR, "Error", "Invalid Data",
          "Please enter all the details for the various pet.");
    }
    Various newVarious = new Various(name, gender, comment, birthDate, color, price, species);
    pets.addPet(newVarious);
    showAlert(Alert.AlertType.INFORMATION, "Success", "Various Pet Added",
        "Various pet added successfully: ");

    clearVariousForm();
    updatePetTable();
  }

  private void clearVariousForm()
  {
    variousNameField.clear();
    variousMaleRadio.setSelected(false);
    variousFemaleRadio.setSelected(false);
    variousBirthDatePicker.setValue(null);
    variousColorField.clear();
    variousPriceField.clear();
    variousSpeciesField.clear();
    variousCommentArea.clear();
  }

  public void dogCustomerSelected()
  {
    Customer customer = dogCustomerListTable.getSelectionModel().getSelectedItem();
    if (customer != null)
    {
      this.customer = customer;
      dogSelectedCustomer.setText(customer.getName());
    }
    else
    {
      showAlert(Alert.AlertType.ERROR, "Error", "Invalid Selection",
          "Please select a customer from the list.");
    }
  }

  public void catCustomerSelected()
  {
    Customer customer = catCustomerListTable.getSelectionModel().getSelectedItem();
    if (customer != null)
    {
      this.customer = customer;
      catSelectedCustomer.setText(customer.getName());
    }
    else
    {
      showAlert(Alert.AlertType.ERROR, "Error", "Invalid Selection",
          "Please select a customer from the list.");
    }
  }

  public void birdCustomerSelected()
  {
    Customer customer = birdCustomerListTable.getSelectionModel().getSelectedItem();
    if (customer != null)
    {
      this.customer = customer;
      birdSelectedCustomer.setText(customer.getName());
    }
    else
    {
      showAlert(Alert.AlertType.ERROR, "Error", "Invalid Selection",
          "Please select a customer from the list.");
    }
  }

  public void selectedPet()
  {
    Pet pet = petListTable.getSelectionModel().getSelectedItem();
    if (pet != null)
    {
      modifyPetNameField.setText(pet.getName());
      modifyPetPriceField.setText(String.valueOf((int)pet.getPrice()));
      modifyPetCommentArea.setText(pet.getComment());
    }
    else
    {
      showAlert(Alert.AlertType.ERROR, "Error", "Invalid Selection",
          "Please select a pet from the list.");
    }
  }

  public void customerSearchByPhoneDog()
  {
    String phone = dogSearchCustomerField.getText();
    if (phone.isEmpty())
    {
      showAlert(Alert.AlertType.ERROR, "Error", "Invalid Search",
          "Please enter a phone number to search for.");
      return;
    }

    CustomerModelManager manager = new CustomerModelManager("customers.bin");
    CustomerList customers = manager.getAllCustomers();
    Customer customer = customers.getCustomerByPhoneNumber(phone);

    if (customer != null)
    {
      dogCustomerListTable.getItems().clear();
      dogCustomerListTable.getItems().add(customer);
    }
    else
    {
      showAlert(Alert.AlertType.INFORMATION, "Not Found", "No Results",
          "No customer found with phone number: " + phone);
    }
  }

  public void customerSearchByPhoneCat()
  {
    String phone = catSearchCustomerField.getText();
    if (phone.isEmpty())
    {
      showAlert(Alert.AlertType.ERROR, "Error", "Invalid Search",
          "Please enter a phone number to search for.");
      return;
    }

    CustomerModelManager manager = new CustomerModelManager("customers.bin");
    CustomerList customers = manager.getAllCustomers();
    Customer customer = customers.getCustomerByPhoneNumber(phone);

    if (customer != null)
    {
      catCustomerListTable.getItems().clear();
      catCustomerListTable.getItems().add(customer);
    }
    else
    {
      showAlert(Alert.AlertType.INFORMATION, "Not Found", "No Results",
          "No customer found with phone number: " + phone);
    }
  }

  public void customerSearchByPhoneBird()
  {
    String phone = birdSearchCustomerField.getText();
    if (phone.isEmpty())
    {
      showAlert(Alert.AlertType.ERROR, "Error", "Invalid Search",
          "Please enter a phone number to search for.");
      return;
    }

    CustomerModelManager manager = new CustomerModelManager("customers.bin");
    CustomerList customers = manager.getAllCustomers();
    Customer customer = customers.getCustomerByPhoneNumber(phone);

    if (customer != null)
    {
      birdCustomerListTable.getItems().clear();
      birdCustomerListTable.getItems().add(customer);
    }
    else
    {
      showAlert(Alert.AlertType.INFORMATION, "Not Found", "No Results",
          "No customer found with phone number: " + phone);
    }
  }

  public void searchPetByName()
  {
    String name = searchPetField.getText();
    if (name.isEmpty())
    {
      showAlert(Alert.AlertType.ERROR, "Error", "Invalid Search",
          "Please enter a name to search for.");
      return;
    }

    PetModelManager manager = new PetModelManager("pets.bin");
    PetList allPets = manager.getAllPets();
    PetList searchResults = allPets.getPetsByName(name);

    if (searchResults.size() > 0)
    {
      petListTable.getItems().clear();
      for (int i = 0; i < searchResults.size(); i++)
      {
        petListTable.getItems().add(searchResults.getByIndex(i));
      }
    }
    else
    {
      showAlert(Alert.AlertType.INFORMATION, "Not Found", "No Results",
          "No pets found with name: " + name);
    }
  }

  public void modifyPet()
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Modify Pet");
    alert.setHeaderText("Are you sure you want to modify this pet?");
    alert.setContentText("This action cannot be undone.");

    PetModelManager manager = new PetModelManager("pets.bin");
    int index = petListTable.getSelectionModel().getSelectedIndex();
   if (alert.showAndWait().get() == ButtonType.OK)
    {
      manager.changePetName(index, modifyPetNameField.getText());
      manager.changePetComments(index, modifyPetCommentArea.getText());
      try
      {
        int price = Integer.parseInt(modifyPetPriceField.getText());
        manager.changePetPrice(index, price);
      }
      catch (NumberFormatException e)
      {
        showAlert(Alert.AlertType.ERROR, "Error", "Invalid Price",
            "Please enter a valid price for the pet.");
      }
    }
    if (modifyPetNameField.getText().isEmpty() || modifyPetCommentArea.getText().isEmpty() || modifyPetPriceField.getText().isEmpty())
    {
      showAlert(Alert.AlertType.ERROR, "Error", "Invalid Data",
          "Please enter all the details for the pet.");
      return;
    }
    updatePetTable();
  }

  public void removePet()
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Remove Pet");
    alert.setHeaderText("Are you sure you want to remove this pet?");
    alert.setContentText("This action cannot be undone.");

    if (alert.showAndWait().get() != ButtonType.OK)
    {
      PetModelManager manager = new PetModelManager("pets.bin");
      PetList allPets = manager.getAllPets();
      allPets.removePet(petListTable.getSelectionModel().getSelectedItem());
      manager.savePets(allPets);
      updatePetTable();
    }
  }

  public void updatePetTable()
  {
    petListTable.getItems().clear();
    PetModelManager manager = new PetModelManager("pets.bin");
    PetList allPets = manager.getAllPets();

    for (int i = 0; i < allPets.size(); i++)
    {
      petListTable.getItems().add(allPets.getByIndex(i));
    }
  }

  public void onCancel()
  {
    modifyPetNameField.clear();
    modifyPetPriceField.clear();
    modifyPetCommentArea.clear();
    searchPetField.clear();
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
