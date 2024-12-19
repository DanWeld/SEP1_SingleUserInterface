package Model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents a sale of a pet.
 * The sale has a pet, a discount, a sale date, and a price.
 * The price of the pet is set to -1 when the pet is sold.
 * @author Group6
 * @version 1.0
 */
public class Sale implements Serializable
{
  /**
   * Instance variables.
   */
  private Pet pet;
  private double discount;
  private MyDate saleDate;
  private double price;

  /**
   * Constructor with 2 parameters.
   *
   * @param pet,customer parameters.
   * sets the customer of the pet to the customer parameter, and sets the price of the pet to -1.
   * @throws IllegalArgumentException if the pet belongs to a customer.
   */
  public Sale (Pet pet, Customer customer)
  {
    if (pet.getPrice() == -1)
    {
      throw new IllegalArgumentException("pet belongs to someone else, and cannot be sold");
    }
    else
    {
      this.saleDate = new MyDate(LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(), LocalDate.now().getYear());
      this.pet = pet;
      this.discount = 0;
      this.price = pet.getPrice();
      this.pet.setCustomer(customer);
    }
  }

  /**
   * get the sale date.
   * @return copy of the sale date.
   */
  public MyDate getSaleDate()
  {
    return saleDate.copy();
  }

  /**
   * get the pet.
   * @return the pet.
   */
  public Pet getPet()
  {
    return pet;
  }

  /**
   * get the name of the pet.
   * @return the name of the pet.
   */
  public String getPetName()
  {
    return pet.getName();
  }

  /**
   * set the pet.
   * @param pet to set.
   */
  public void setPet(Pet pet)
  {
    this.pet = pet;
  }

  /**
   * get the customer.
   * @return the customer.
   */
  public Customer getCustomer()
  {
    return pet.getCustomer();
  }

  /**
   * get the discount as a double.
   * @return the discount.
   */
  public double getDiscount()
  {
    return discount;
  }

  /**
   * set the discount as a double.
   * multiplies the price of the pet by the discount.
   * @param discount as double.
   */
  public void setDiscount(double discount)
  {
    this.discount = discount;
  }

  /**
   * get the price of the pet.
   * @return the price.
   */
  public double getPrice()
  {
    return price - (price * discount / 100);
  }


  /**
   * Provides a string representation of the Sale.
   * @return a formatted string of the sale.
   */
  public String toString()
  {
    return "Pet"+pet+ "\nCustomer"+pet.getCustomer()+"\nPrice after discount: "+getPrice()+"\nDate: "+saleDate;
  }

  /**
   * Compares the sale to another object.
   * @param obj the object to compare with.
   * @return true if the object is a Sale with the same pet, discount, and sale date, false otherwise.
   */
  public boolean equals(Object obj)
  {
    if (obj == null || obj.getClass() != this.getClass())
    {
      return false;
    }
    Sale other = (Sale) obj;
    return pet.equals(other.pet) && discount == other.discount && saleDate.equals(other.saleDate) && price == other.price;
  }
}
