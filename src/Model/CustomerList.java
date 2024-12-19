package Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;

/**
 * @author Group6
 * @version 1.0
 * A class representing a list of customers
 */

public class CustomerList implements Serializable
{
  /**
   * instance variables
   */
  private ArrayList<Customer> customers;

  /**
   * constructor
   */
  public CustomerList()
  {
    customers = new ArrayList<Customer>();
  }

  /**
   * Get number of customers
   * @return number of customers
   */
  public int size()
  {
    return customers.size();
  }

  /**
   * add a customer
   * @param customer as the new customer to be added.
   */
  public void addCustomer(Customer customer)
  {
    customers.add(customer);
  }

  /**
   * Get a customer
   * @param customer the index of the customer
   * @return customer that matches the parameter if exist otherwise return null
   */
  public Customer getCustomer(Customer customer)
  {
    for (int i = 0; i < customers.size(); i++)
    {
      if (customers.get(i).equals(customer))
      {
        return customers.get(i);
      }
    }
    return null;
  }

  /**
   * Get a customer by index
   * @param index the index of the customer
   * @return the customer that matches the parameter if exist otherwise return null
   */
  public Customer getByIndex(int index)
  {
    if (index < customers.size())
    {
      return customers.get(index);
    }
    else
    {
      return null;
    }
  }

  /**
   * remove a customer
   * @param customer the customer to be removed
   */
  public void removeCustomer(Customer customer)
  {
    for (int i = 0; i < customers.size(); i++)
    {
      if (customers.get(i).equals(customer))
      {
        customers.remove(customers.get(i));
      }
    }
  }

  /**
   * Get a customer by name
   * @param name the name of the customer
   * @return the customer that matches the parameter if exist otherwise throw an exception
   */
  public CustomerList getCustomerByName(String name)
  {
    CustomerList customerList = new CustomerList();
    for (int i = 0; i < customers.size(); i++)
    {
      if (customers.get(i).getName().toLowerCase().contains(name.toLowerCase()))
      {
        customerList.addCustomer(customers.get(i));
      }
    }
    return customerList;
  }

  /**
   * Get a customer by email
   * @param email the email of the customer
   * @return the customer that matches the parameter if exist otherwise throw an exception
   */
  public CustomerList getCustomerByEmail(String email)
  {
    CustomerList customerList = new CustomerList();
    for (int i = 0; i < customers.size(); i++)
    {
      if (customers.get(i).getEmailAddress().equals(email))
      {
        customerList.addCustomer(customers.get(i));
      }
    }
    throw new IllegalArgumentException("Customer not found");
  }

  /**
   * Get a customer by phone number
   * @param phoneNumber the phone number of the customer
   * @return the customer that matches the parameter if exist otherwise throw an exception
   */
  public Customer getCustomerByPhoneNumber(String phoneNumber)
  {
    for (int i = 0; i < customers.size(); i++)
    {
      if (customers.get(i).getPhoneNumber().equals(phoneNumber))
      {
        return customers.get(i);
      }
    }
    return null;
  }

  /**
   * toString method
   * @return a string representation of the object
   */
  public String toString()
  {
    String str = "";
    for (int i = 0; i < customers.size(); i++)
    {
      str += customers.get(i).toString() + "\n";
    }
    return str;
  }

  /**
   * Check if two customer lists are equal.
   * @param obj  the object to compare with.
   * @return true if the customer lists are equal otherwise return false.
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    CustomerList other = (CustomerList) obj;
    if (customers.size() != other.customers.size())
    {
      return false;
    }
    for (int i = 0; i < customers.size(); i++)
    {
      if (!customers.get(i).equals(other.customers.get(i)))
      {
        return false;
      }
    }
    return true;
  }
}