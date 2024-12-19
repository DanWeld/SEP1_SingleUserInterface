package main;

import Model.*;
import Model.PetModelManager;

public class LoadInitialData
{
  public static void main(String[] args)
  {
    Cat cat = new Cat("Misty", 'F', "Does not Bite, and she is okay with kids", new MyDate(1, 1, 2019), "White", 1000, "Siamese", "John");
    Dog dog = new Dog("Rex", 'M', "Does not bark", new MyDate(2, 1, 2019), "Brown", 2000, "Golden Retriever", "John");
    Bird bird = new Bird("Tweety", 'F', "Can talk", new MyDate(3, 1, 2019), "Yellow", 500, "Seeds", "Parrot");
    Fish fish = new Fish("Goldie", 'M', "Swims fast", new MyDate(4, 1, 2019), "Gold", 50, true, false);
    Various various = new Various("Fluffy", 'F', "Does not bite", new MyDate(5, 1, 2019), "Black", 200, "Rabbit");
    Rodents rodents = new Rodents("Jerry", 'M', "Eats cheese", new MyDate(6, 1, 2019), "Gray", 100, "Mouse", false);
    Cat cat1 = new Cat("Milo", 'm', "Loves to play", new MyDate(1, 1, 2018), "Black", 1000, "Persian", "John's family");
    Cat cat2 = new Cat("Bella", 'f', "Very affectionate", new MyDate(2, 2, 2019), "White", 1000, "Siamese", "Jane's family");
    Dog dog1 = new Dog("Rexion", 'M', "Does not bark", new MyDate(3, 3, 2020), "Brown", 2000, "Golden Retriever", "John's family");
    Bird bird1 = new Bird("Tweetah", 'F', "Can talk", new MyDate(4, 4, 2021), "Yellow", 500, "Seeds", "Parrot");
    Fish  fish1 = new Fish("Golden", 'M', "Swims fast", new MyDate(5, 5, 2022), "Gold", 50, true, false);
    Various various1 = new Various("Fluffy", 'F', "Does not bite", new MyDate(6, 6, 2023), "Black", 200, "Rabbit");
    Rodents rodents1 = new Rodents("Jerry", 'M', "Eats cheese", new MyDate(7, 7, 2024), "Gray", 100, "Mouse", false);
    Dog dog2 = new Dog("Rex", 'M', "Does not bark", new MyDate(8, 8, 2023), "Brown", 2000, "Golden Retriever", "John's family");
    Bird bird2 = new Bird("Tweetah", 'F', "Can talk", new MyDate(9, 9, 2023), "Yellow", 500, "Seeds", "Parrot");


    PetList petList = new PetList();
    petList.addPet(cat);
    petList.addPet(dog);
    petList.addPet(bird);
    petList.addPet(fish);
    petList.addPet(various);
    petList.addPet(rodents);
    petList.addPet(cat1);
    petList.addPet(cat2);
    petList.addPet(dog1);
    petList.addPet(bird1);
    petList.addPet(fish1);
    petList.addPet(various1);
    petList.addPet(rodents1);
    petList.addPet(dog2);
    petList.addPet(bird2);


    Customer customer1 = new Customer ("John", "71846557", "john@gmail.com");
    Customer customer2 = new Customer ("Jane", "71146258", "jane@gmail.com");
    Customer customer3 = new Customer ("Sam", "26843257", "sam@gmao.com");
    Customer customer4 = new Customer ("Sara", "92036557", "sara@gmail.com");
    Customer customer5 = new Customer ("Sally", "71231457", "sally@gmail.com");
    Customer customer6 = new Customer ("Sana", "92036224", "sana@gmail.com");
    Customer customer7 = new Customer ("Karim", "92036123", "karim@gmail.com");


    CustomerList customerList = new CustomerList();
    customerList.addCustomer(customer1);
    customerList.addCustomer(customer2);
    customerList.addCustomer(customer3);
    customerList.addCustomer(customer4);
    customerList.addCustomer(customer5);
    customerList.addCustomer(customer6);
    customerList.addCustomer(customer7);


    Sale sale = new Sale(cat, customer1);
    Sale sale1 = new Sale(dog, customer2);
    Sale sale2 = new Sale(bird, customer3);
    Sale sale3 = new Sale(fish, customer4);
    Sale sale4 = new Sale(various, customer5);

    SalesLog salesLog = new SalesLog();
    salesLog.addSale(sale);
    salesLog.addSale(sale1);
    salesLog.addSale(sale2);
    salesLog.addSale(sale3);
    salesLog.addSale(sale4);

    PetModelManager petModelManager = new PetModelManager("pets.bin");
    petModelManager.savePets(petList);

    CustomerModelManager customerModelManager = new CustomerModelManager("customers.bin");
    customerModelManager.saveCustomers(customerList);

    SaleModelManager saleModelManager = new SaleModelManager("sales.bin");
    saleModelManager.saveSales(salesLog);



//    Reservation reservation0 = new Reservation(cat1, new MyDate(12,12,2024), new MyDate(12,12,2026));
//    Reservation reservation1 = new Reservation(dog1, new MyDate(12,12,2024), new MyDate(12,12,2026));
//    Reservation reservation2 = new Reservation(dog2, new MyDate(12,12,2024), new MyDate(12,12,2026));
//
    ReservationList reservationList = new ReservationList();
//    reservationList.addReservation(reservation0);
//    reservationList.addReservation(reservation1);
//    reservationList.addReservation(reservation2);

    ReservationModelManager reservationModelManager = new ReservationModelManager("reservations.bin");
    reservationModelManager.saveReservations(reservationList);


  }
}
