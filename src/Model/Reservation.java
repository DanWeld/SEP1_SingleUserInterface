package Model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents a reservation for a pet in the kennel.
 * @author  Group6
 * @version 1.0
 */
public class Reservation implements Serializable
{

  /**
   * instance variables
   */
  private MyDate startDate;
  private MyDate endDate;
  private Pet pet;
  private int pricePerDay = 20;

  /**
   * Constructs a Reservation object.
   *
   * @param pet       the pet being reserved.
   * @param startDate the start date of the reservation.
   * @param endDate   the end date of the reservation.
   * @throws IllegalArgumentException if the start date is in the past, the end
   *                                  date is before the start date, or the pet
   *                                  has no customer.
   */
  public Reservation(Pet pet, MyDate startDate, MyDate endDate) {
    if (startDate.isBefore(new MyDate(LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(), LocalDate.now().getYear()))) {
      throw new IllegalArgumentException("Start date is before today: " + startDate);
    }
    this.startDate = startDate.copy();

    if (endDate.isBefore(startDate)) {
      throw new IllegalArgumentException("End date is before start date");
    }
    this.endDate = endDate.copy();
    this.pet = pet;
    if (pet.getPrice() != -1) {
      throw new IllegalArgumentException("Pet belongs in the pet shop, and has no customer");
    }
  }

  /**
   * Sets a new start date for the reservation, ensuring no overlap with existing
   * reservations.
   *
   * @param newStartDate    the new start date.
   * @param reservationList the list of existing reservations for validation.
   * @throws IllegalArgumentException if the new start date causes overlap or is
   *                                  in the past.
   */
  public void setStartDate(MyDate newStartDate, ReservationList reservationList) {
    Reservation tempReservation = new Reservation(pet, newStartDate.copy(), endDate.copy());

    if (reservationList.getOverlappingReservationsCount(tempReservation) > 10) {
      throw new IllegalArgumentException("Changing the start date will exceed kennel capacity.");
    }
    if (newStartDate.isBefore(new MyDate(LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(), LocalDate.now().getYear()))) {
      throw new IllegalArgumentException("Start date is before today");
    }
    this.startDate = newStartDate.copy();
  }

  /**
   * Gets the reservation's start date.
   *
   * @return the start date.
   */
  public MyDate getStartDate() {
    return startDate.copy();
  }

  /**
   * Sets a new end date for the reservation, ensuring no overlap with existing
   * reservations.
   *
   * @param newEndDate      the new end date.
   * @param reservationList the list of existing reservations for validation.
   * @throws IllegalArgumentException if the new end date causes overlap or is
   *                                  invalid.
   */
  public void setEndDate(MyDate newEndDate, ReservationList reservationList) {
    Reservation tempReservation = new Reservation(pet, startDate.copy(), newEndDate.copy());

    if (reservationList.getOverlappingReservationsCount(tempReservation) > 10) {
      throw new IllegalArgumentException("Changing the end date will exceed kennel capacity.");
    }
    if (newEndDate.isBefore(startDate)) {
      throw new IllegalArgumentException("End date is before start date");
    }
    this.endDate = newEndDate.copy();
  }

  /**
   * Gets the reservation's end date.
   *
   * @return the end date.
   */
  public MyDate getEndDate() {
    return endDate.copy();
  }

  /**
   * Calculates the total price for the reservation.
   *
   * @return the total price.
   */
  public int getPrice() {
    return pricePerDay * numberOfDays();
  }

  /**
   * Sets the price per day for the reservation.
   * @param pricePerDay the price per day
   */
  public void setPricePerDay(int pricePerDay)
  {
    this.pricePerDay = pricePerDay;
  }

  /**
   * Calculates the number of days for the reservation.
   *
   * @return the number of days.
   */
  public int numberOfDays() {
    return startDate.until(endDate);
  }

  /**
   * Gets the reserved pet.
   *
   * @return the pet.
   */
  public Pet getPet() {
    return pet;
  }

  public String getPetName() {
    return pet.getName();
  }


  /**
   * Gets the customer who made the reservation.
   *
   * @return the customer through the pet class.
   */
  public Customer getCustomer() {
    return pet.getCustomer();
  }

  /**
   * Checks if this reservation overlaps with another reservation.
   *
   * @param other the other reservation.
   * @return true if the reservations overlap, false otherwise.
   */
  public boolean overlaps(Reservation other)
  {
    return !(endDate.isBefore(other.startDate) || startDate.isAfter(other.endDate));
  }

  /**
   * Provides a string representation of the Reservation.
   *
   * @return a formatted string of the reservation details.
   */
  public String toString()
  {
    return "Start: " + startDate + " End: " + endDate + "\nPet: " + pet + "\nPrice: " + getPrice();
  }

  /**
   * Checks if this reservation is equal to another object.
   *
   * @param obj the object to compare with.
   * @return true if the reservations are equal, false otherwise.
   */
  public boolean equals(Object obj)
  {
    if (obj == null || this.getClass() != obj.getClass()) {
      return false;
    }
    Reservation other = (Reservation) obj;
    return other.startDate.equals(startDate) && other.endDate.equals(endDate) && other.pet.equals(pet)
        && other.getPrice() == getPrice();
  }
}
