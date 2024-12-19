package Model;

/**
 * this class is a subclass from a super class pet
 * @author Group6
 * @version 1.0
*/
public class Cat extends Pet

{

  /**
   * Two extra fields more than the super class as needed for the Cat class.
   */
  private String breed;
  private String breeder;

  /**
   *
   * @param name,gender,comment,birthDate,color,price,species form super class
   * @param breed,breeder specific for this class
   * This constructor is to create an object of a cat in the pet shop "Has a price"
   */
  public Cat(String name, char gender, String comment, MyDate birthDate, String color, int price, String breed, String breeder)
  {
    super(name, gender, comment, birthDate, color, price);
    super.setSpecies("Cat");
    this.breed = breed;
    this.breeder = breeder;
  }

  /**
   *
   * @param name,gender,comment,birthDate,color,customer form super class
   * @param breed,breeder specific for this class
   *  This constructor is to create an object of a cat in the pet shop "Has a customer, and no price"
   */
  public Cat(String name, char gender, String comment, MyDate birthDate, String color, Customer customer,  String breed, String breeder)
  {
    super(name, gender, comment, birthDate, color, customer);
    super.setSpecies("Cat");
    this.breed = breed;
    this.breeder = breeder;
  }

  /**
   * get breed method
   * @return the breed
   */
  public String getBreed()
  {
    return breed;
  }

  /**
   * get breeder method
   * @return the breeder
   */
  public String getBreeder()
  {
    return breeder;
  }

  /**
   * toString method for cat
   * @return String with cat species, breed and breeder
   */
  public String toString()
  {
    return super.toString() + "\nBreed: " + breed + "\nBreeder: " + breeder;
  }

  /**
   * equals method for cat
   * @param obj to compare with the cat class in case if it is identical or not
   * @return true if the object is equal to this object or false if it is not
   */
  public boolean equals(Object obj)
  {
    if (obj == null || obj.getClass() != this.getClass())
    {
      return false;
    }
    Cat other = (Cat) obj;
    return super.equals(other) && breed.equals(other.breed) && breeder.equals(other.breeder);
  }
}
