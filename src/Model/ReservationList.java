package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

/**
 * A class that manages a list of reservations for a kennel.
 * @author  Group6
 * @version 1.0
 */
public class ReservationList implements Serializable
{

  /**
   * The list of reservations.
   */
  private ArrayList<Reservation> reservations;

  /**
   * Constructs an empty ReservationList.
   */
  public ReservationList()
  {
    reservations = new ArrayList<>();
  }

  /**
   * Retrieves the number of reservations in the list.
   *
   * @return the number of reservations.
   */
  public int size()
  {
    return reservations.size();
  }

  /**
   * Adds a new reservation if it does not exceed the kennel's capacity.
   *
   * @param newReservation the reservation to add.
   * @throws IllegalArgumentException if the kennel is at maximum capacity.
   */
  public void addReservation(Reservation newReservation)
  {
    int maxCapacity = 10; // Maximum number of pets allowed in the kennel
    if (getOverlappingReservationsCount(newReservation) >= maxCapacity)
    {
      throw new IllegalArgumentException(
          "Cannot add reservation. Kennel is at maximum capacity.");
    }
    for (int i = 0; i < reservations.size(); i++)
    {
      if (reservations.get(i).getPet().equals(newReservation.getPet())
          && newReservation.overlaps(reservations.get(i)))
      {
        throw new IllegalArgumentException(
            "Cannot add reservation. Pet already exists in a different reservation.");
      }
    }
    reservations.add(newReservation);
  }

  /**
   * Removes a reservation from the list if it exists.
   *
   * @param reservation the reservation cannot be removed if the reservation had started.
   */
  public void removeReservation(Reservation reservation)
  {
    if (reservations.isEmpty())
    {
      throw new IllegalArgumentException("No reservations found.");
    }
    if (reservation.getStartDate().isBefore(
        new MyDate(LocalDate.now().getDayOfMonth(),
            LocalDate.now().getMonthValue(), LocalDate.now().getYear())))
    {
      throw new IllegalArgumentException(
          "Cannot remove reservation. Start date is in the past.");
    }
    else
    {
      reservations.remove(reservation);
    }
  }

  /**
   * Retrieves a reservation by its index in the list.
   *
   * @param index the index of the reservation.
   * @return the reservation at the specified index.
   */
  public Reservation getByIndex(int index)
  {
    return reservations.get(index);
  }

  /**
   * Counts the number of reservations that overlap with a given reservation.
   *
   * @param newReservation the reservation to check.
   * @return the count of overlapping reservations.
   */
  public int getOverlappingReservationsCount(Reservation newReservation)
  {
    int count = 0;
    for (Reservation reservation : reservations)
    {
      if (!reservation.equals(newReservation) && reservation.overlaps(
          newReservation))
      {
        count++;
      }
    }
    return count;
  }

  /**
   * Finds all reservations that match a customer's phone number.
   *
   * @param phoneNumber the phone number to search for.
   * @return a ReservationList of matching reservations.
   * @throws IllegalArgumentException if no reservations match the phone number.
   */
  public ReservationList getReservationsByCustomersPhoneNumber(String phoneNumber)
  {
    ReservationList matchingReservations = new ReservationList();
    for (Reservation reservation : reservations)
    {
      if (reservation.getPet().getCustomer().getPhoneNumber()
          .equals(phoneNumber))
      {
        matchingReservations.addReservation(reservation);
      }
    }
    if (matchingReservations.reservations.isEmpty())
    {
      throw new IllegalArgumentException("No reservation found by that phone number");
    }
    return matchingReservations;
  }

  /**
   * Checks if a pet is available for reservation on a given date.
   *
   * @param phoneNumber the phone number of the pet's owner.
   * @param startDate the start date of the reservation.
   * @param endDate the end date of the reservation.
   * @throws IllegalArgumentException if the pet is already reserved for that date.
   */
  public void checkAvailability(String phoneNumber, MyDate startDate,
      MyDate endDate)
  {
    for (Reservation reservation : reservations)
    {
      if (reservation.getPet().getCustomer().getPhoneNumber()
          .equals(phoneNumber))
      {
        if (reservation.overlaps(new Reservation(reservation.getPet(), startDate,
            endDate)))
        {
          throw new IllegalArgumentException("Pet is already reserved for that date.");
        }
      }
    }
  }

  /**
   * Provides a string representation of the ReservationList.
   *
   * @return a formatted string of all reservations in the list.
   */
  public String toString()
  {
    StringBuilder result = new StringBuilder("Reservation List:\n");
    if (reservations.isEmpty())
    {
      result.append("No reservations found.");
    }
    else
    {
      for (int i = 0; i < reservations.size(); i++)
      {
        result.append(i + 1).append(". ").append(reservations.get(i))
            .append("\n\n");
      }
    }
    return result.toString();
  }

  /**
   * Checks if this ReservationList is equal to another object.
   *
   * @param obj the object to compare with.
   * @return true if the object is equal to this ReservationList.
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }

    ReservationList other = (ReservationList) obj;
    if (reservations.size() != other.reservations.size())
    {
      return false;
    }
    for (int i = 0; i < reservations.size(); i++)
    {
      if (!reservations.get(i).equals(other.reservations.get(i)))
      {
        return false;
      }
    }
    return true;
  }

}
