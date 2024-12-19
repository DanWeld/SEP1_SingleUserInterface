 package Gui;
import Model.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
 public class ReservationController extends Application{

   @FXML  private DatePicker startDate;
   @FXML private DatePicker endDate;
   @FXML private Button checkAvailability;
   @FXML  private TextArea available;

  @FXML private TextField searchByPhoneNumber;
  @FXML  private Button searchButton;

  @FXML private TableView<Pet> petTable;
  @FXML private TableColumn<Pet, String> petName;

  @FXML private Button AddReservation;

//List of Reservations
  @FXML private TextField listOfSearchByPhoneNumber;
  @FXML private Button listOfPetsSearchButton;

  @FXML private DatePicker listOfStartDate;
  @FXML private DatePicker listOfEndDate;
  @FXML private Button listOfCheckAvailability;
  @FXML private TextArea listOfAvailable;

  @FXML private TableView<Reservation> listOfReservations;
  @FXML private TableColumn<Reservation, String> listOfPetCustomer;
  @FXML private TableColumn<Reservation, String> listOfPetName;
  @FXML private TableColumn<Reservation, MyDate> listoflistOfStartDate;
  @FXML private TableColumn<Reservation, MyDate> listoflistOfEndDate;
  @FXML private Button listOfUpdate;
  @FXML private Button listOfDelete;
   private MyDate addreservationStartDate = null;
   private MyDate addreservationEndDate = null;
   private MyDate newStartDate = null;
   private MyDate newEndDate = null;
   private ReservationModelManager reservationModelManager;

   public void initialize()
    {
    listOfPetName.setCellValueFactory(new PropertyValueFactory<>("petName"));
    listOfPetCustomer.setCellValueFactory(new PropertyValueFactory<>("customer"));
    listoflistOfStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
    listoflistOfEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
    petName.setCellValueFactory(new PropertyValueFactory<>("name"));
    PetModelManager petModelManager = new PetModelManager("pets.bin");
    PetList allPets = petModelManager.getAllPets();
    petTable.getItems().clear();
    for (int i = 0; i < allPets.size(); i++)
    {
      if (allPets.getByIndex(i).getCustomer() != null && allPets.getByIndex(i).getPrice() == -1)
      {
        petTable.getItems().add(allPets.getByIndex(i));
      }
    }
    reservationModelManager = new ReservationModelManager("reservations.bin");
    updateReservations();
  }

   private void updateReservations()
   {
     ReservationList allReservations = reservationModelManager.getAllReservations();
     listOfReservations.getItems().clear();

     for (int i = 0; i < allReservations.size(); i++)
     {
       listOfReservations.getItems().add(allReservations.getByIndex(i));
     }

     listOfSearchByPhoneNumber.clear();
     listOfStartDate.setValue(null);
     listOfEndDate.setValue(null);
     listOfAvailable.clear();
   }

   @FXML public void start(Stage window) throws IOException
  {
    window.setTitle("Reservation");
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/Reservation.fxml"));
    Scene scene = new Scene(loader.load());
    window.setScene(scene);
    window.show();
  }

   @FXML
   private void handel(ActionEvent event) {
     if (event.getSource() == checkAvailability) {
        addreservationStartDate = new MyDate(startDate.getValue().getDayOfMonth(), startDate.getValue().getMonthValue(), startDate.getValue().getYear());
        addreservationEndDate = new MyDate(endDate.getValue().getDayOfMonth(), endDate.getValue().getMonthValue(), endDate.getValue().getYear());

       ReservationModelManager reservationModelManager = new ReservationModelManager("reservations.bin");
       ReservationList allReservations = reservationModelManager.getAllReservations();
       SaleModelManager saleModelManager = new SaleModelManager("sales.bin");
       SalesLog allSales = saleModelManager.getAllSales();
       Pet tempPet = allSales.getByIndex(0).getPet();
       Reservation tempReservation = new Reservation(tempPet, addreservationStartDate, addreservationEndDate);
       if (allReservations.getOverlappingReservationsCount(tempReservation) > 10) {
         available.setText("No");
       } else {
         available.setText("Yes");
       }
       if(addreservationEndDate.isBefore(addreservationStartDate))
       {
         showAlert(Alert.AlertType.ERROR, "Error", "End date is before start date", "End date is before start date");
       }
     }
     if (event.getSource() == AddReservation) {
       try {
         Alert alert = new Alert(Alert.AlertType.INFORMATION, "Do you really want to add a reservation?", ButtonType.YES, ButtonType.NO);
         alert.setTitle("Exit");
         alert.setHeaderText(null);
         alert.showAndWait();
         if (alert.getResult() == ButtonType.YES) {
           Pet selectedPet = petTable.getSelectionModel().getSelectedItem();
           ReservationModelManager reservationModelManager = new ReservationModelManager("reservations.bin");
           ReservationList allReservations = reservationModelManager.getAllReservations();
           Reservation newReservation = new Reservation(selectedPet, addreservationStartDate, addreservationEndDate);
           allReservations.addReservation(newReservation);
           reservationModelManager.saveReservations(allReservations);
           searchByPhoneNumber.clear();
           listOfSearchByPhoneNumber.clear();
           showAlert(Alert.AlertType.INFORMATION, "Add", null, "Reservation added successfully");
         }
         updateReservations();
         clear();
       } catch (IllegalArgumentException e) {
         showAlert(Alert.AlertType.ERROR, "Error", "Cannot add reservation", e.getMessage());
       }
     }
     if (event.getSource() == searchButton) {
       try {
         CustomerModelManager customerModelManager = new CustomerModelManager("customers.bin");
         CustomerList allCustomers = customerModelManager.getAllCustomers();
         Customer petsByCustomer = allCustomers.getCustomerByPhoneNumber(searchByPhoneNumber.getText());

         PetModelManager petModelManager = new PetModelManager("pets.bin");
         PetList allPets = petModelManager.getAllPets();
         PetList petCustomerx = allPets.getPetsByCustomer(petsByCustomer);
         petTable.getItems().clear();
         System.out.println(petCustomerx);
         for (int i = 0; i < petCustomerx.size(); i++) {
           petTable.getItems().add(petCustomerx.getByIndex(i));
           System.out.println(petCustomerx.getByIndex(i));
           clear();
         }
       } catch (IllegalArgumentException e) {
         showAlert(Alert.AlertType.ERROR, "Error", "Cannot find customer", e.getMessage());
       }
     }
     if (event.getSource() == listOfPetsSearchButton) {
        try {
          CustomerModelManager customerModelManager = new CustomerModelManager("customers.bin");
          CustomerList allCustomers = customerModelManager.getAllCustomers();
          Customer petsByCustomer = allCustomers.getCustomerByPhoneNumber(listOfSearchByPhoneNumber.getText());

          ReservationModelManager reservationModelManager = new ReservationModelManager("reservations.bin");
          ReservationList allReservations = reservationModelManager.getAllReservations();
          ReservationList reservationListByPhoneNumber = allReservations.getReservationsByCustomersPhoneNumber(petsByCustomer.getPhoneNumber());
          listOfReservations.getItems().clear();
          System.out.println("Reservations by phone number:" + listOfSearchByPhoneNumber.getText());
          System.out.println(reservationListByPhoneNumber);
          for (int i = 0; i < reservationListByPhoneNumber.size(); i++) {
            listOfReservations.getItems().add(reservationListByPhoneNumber.getByIndex(i));
            System.out.println(reservationListByPhoneNumber.getByIndex(i));
          }
          clear();
        } catch (IllegalArgumentException e) {
          showAlert(Alert.AlertType.ERROR, "Error", "Cannot find customer", e.getMessage());
        }
      }
     if (event.getSource() == listOfCheckAvailability) {

       newStartDate = new MyDate(listOfStartDate.getValue().getDayOfMonth(), listOfStartDate.getValue().getMonthValue(), listOfStartDate.getValue().getYear());
       newEndDate = new MyDate(listOfEndDate.getValue().getDayOfMonth(), listOfEndDate.getValue().getMonthValue(), listOfEndDate.getValue().getYear());
       ReservationModelManager reservationModelManager = new ReservationModelManager("reservations.bin");
       ReservationList allReservations = reservationModelManager.getAllReservations();
       SaleModelManager saleModelManager = new SaleModelManager("sales.bin");
       SalesLog allSales = saleModelManager.getAllSales();
       Pet tempPet = allSales.getByIndex(0).getPet();
       Reservation tempReservation = new Reservation(tempPet, newStartDate, newEndDate);
       if (allReservations.getOverlappingReservationsCount(tempReservation) > 10) {
         listOfAvailable.setText("No");
       } else {
         listOfAvailable.setText("Yes");
       }
       //if end date is before than start date show in text area
        if(newEndDate.isBefore(newStartDate))
        {
          listOfAvailable.setText("End date is before start date");
        }

     }
     if (event.getSource() == listOfUpdate) {
       Alert alert = new Alert(Alert.AlertType.INFORMATION, "Do you really want to update the reservation?", ButtonType.YES, ButtonType.NO);
       alert.setTitle("Exit");
       alert.setHeaderText(null);
       alert.showAndWait();
       if (alert.getResult() == ButtonType.YES) {
         Reservation selectedReservation = listOfReservations.getSelectionModel().getSelectedItem();
         ReservationModelManager reservationModelManager = new ReservationModelManager("reservations.bin");
         ReservationList allReservations = reservationModelManager.getAllReservations();

         for (int i = 0; i < allReservations.size(); i++)
         {
           if (allReservations.getByIndex(i).equals(selectedReservation))
           {
             selectedReservation = allReservations.getByIndex(i);
           }
         }
         selectedReservation.setStartDate(newStartDate, allReservations);
         selectedReservation.setEndDate(newEndDate, allReservations);
         reservationModelManager.saveReservations(allReservations);
         showAlert(Alert.AlertType.INFORMATION, "Update", null, "Reservation updated successfully");

         updateReservations();
         clear();
       }
     }
     if (event.getSource() == listOfDelete) {
       Alert alert = new Alert(Alert.AlertType.WARNING, "Do you really want to delete?", ButtonType.YES, ButtonType.NO);
       alert.setTitle("Exit");
       alert.setHeaderText(null);
       alert.showAndWait();
       if (alert.getResult() == ButtonType.YES) {
         Reservation selectedReservation = listOfReservations.getSelectionModel().getSelectedItem();
         ReservationModelManager reservationModelManager = new ReservationModelManager("reservations.bin");
         ReservationList allReservations = reservationModelManager.getAllReservations();
         allReservations.removeReservation(selectedReservation);
         reservationModelManager.saveReservations(allReservations);

         showAlert(Alert.AlertType.INFORMATION, "Delete", null, "Reservation deleted successfully");
         updateReservations();
         clear();
       }
     }
   }

   public void tabChanged()
   {
     updateReservations();
   }

   public void clear()
   {
     startDate.setValue(null);
     endDate.setValue(null);
     available.setText("");

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


