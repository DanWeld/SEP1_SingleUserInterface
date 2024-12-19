package Model;
/**
 * A class representing a dog
 * @author Group6
 * @version 1.0
 */
public class Dog extends Pet
{
  /**
   * instance variables
   */
  private String breed;
  private String breeder;
  /**
   * constructor with price-pet for sale
   */
  public Dog(String name, char gender, String comment, MyDate birthDate, String color, int price, String breed, String breeder)
  {
    super(name, gender, comment, birthDate, color, price);
    super.setSpecies("Dog");
    this.breed = breed;
    this.breeder = breeder;
  }

  /**
   * constructor with customer-kenneled pet
   * @param name,gender,comment,birthDate,color,customer form super class
   * @param breed specific for this class
   * @param breeder specific for this class
   */
  public Dog(String name, char gender, String comment, MyDate birthDate, String color, Customer customer, String breed, String breeder)
  {
    super(name, gender, comment, birthDate, color, customer);
    super.setSpecies("Dog");
    this.breed = breed;
    this.breeder = breeder;
  }

  /**
   * constructor with customer-kenneled pet
   * @return breed
   */
  public String getBreed()
  {
    return breed;
  }

  /**
   * Get breeder
   * @return breeder
   */
  public String getBreeder()
  {
    return breeder;
  }

  /**
   * toString method for dog
   * @return String with dog breed and breeder
   */
  public String toString()
  {
    return super.toString() + "\nBreed: " + breed + "\nBreeder: " + breeder;
  }

  /**
   * equals method for dog
   * @return boolean if the dog is equal to another dog
   * @param obj to compare
   */
  public boolean equals(Object obj)
  {
    if (obj==null||obj.getClass()!=this.getClass())
    {
      return false;
    }
    Dog other = (Dog) obj;
    return super.equals(other) && breed.equals(other.breed) && breeder.equals(other.breeder);
  }
}