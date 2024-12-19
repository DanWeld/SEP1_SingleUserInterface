package Model;

import java.io.Serializable;

/**
 * A class representing a customer
 * @author Group6
 * @version 1.0
 */
public class Customer implements Serializable
{
  /**
   * instance variables
   */
  private String name;
  private String phoneNumber;
  private String emailAddress;

  /**
   * constructor
   */
  public Customer(String name, String phoneNumber, String emailAddress)
  {
    this.name = name;
    this.phoneNumber = phoneNumber;
    setEmailAddress(emailAddress);
  }

  /**
   * Get a name
   * @return name of the customer
   */
  public String getName()
  {
    return name;
  }

  /**
   * Get a phone number
   * @return phone number
   */
  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  /**
   * Get an email address
   * @return email address
   */
  public String getEmailAddress()
  {
    return emailAddress;
  }

  /**
   * Set a name
   * @param name to set
   */
  public void setName(String name)
  {
    this.name = name;
  }

  /**
   * Set a phone number
   * @param phoneNumber to set
   */
  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }

  /**
   * Set an email address
   * @param emailAddress to set
   * @throws RuntimeException if the email address is not formatted correctly
   */
  public void setEmailAddress(String emailAddress)
  {
    if (formattedEmail(emailAddress))
    {
      this.emailAddress = emailAddress;
    }
    else
    {
      throw new IllegalArgumentException("Wrong email format user@host.domain");
    }
  }

  /**
   * Check if the email address is formatted correctly
   * @param emailAddress to check
   * @return true if the email address is formatted correctly
   */
  public boolean formattedEmail(String emailAddress)
  {
    if(emailAddress.contains("@") && emailAddress.contains("."))
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  /**
   * toString method
   * @return name, phone number, email address
   */
  public String toString()
  {
    return "Name: " + name + "\nPhone Number: " + phoneNumber + "\nEmail Address: " + emailAddress;
  }

  /**
   * Check if two customers are equal
   * @param obj the object to compare with
   * @return true if the customers are equal
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    Customer other = (Customer)obj;
    return name.equals(other.name) && phoneNumber.equals(other.phoneNumber) && emailAddress.equals(other.emailAddress);
  }
}