package Model;

/**
 * A class representing a bird
 * @author Group6
 * @version 1.0
 */
public class Bird extends Pet
{
  /**
   * instance variables of the class Bird that has preferredFood,species.
   */
  private String type;
  private String preferredFood;

  /**
   * constructor with price-pet for sale
   */
  public Bird(String name, char gender , String comment, MyDate birthDate, String color, int price, String preferredFood, String type)
  {
    super(name, gender, comment, birthDate, color, price);
    super.setSpecies("Bird");
    this.preferredFood = preferredFood;
    this.type = type;
  }

  /**
   * constructor with customer-kenneled pet
   */
  public Bird(String name, char gender , String comment, MyDate birthDate, String color, Customer customer , String preferredFood, String type)
  {
    super(name, gender, comment, birthDate, color, customer);
    super.setSpecies("Bird");
    this.preferredFood = preferredFood;
    this.type = type;
  }

  /**
   * Get preferred food
   * @return preferred food
   */
  public String getPreferredFood()
  {
    return preferredFood;
  }

  /**
   * Set preferred food
   * @param preferredFood the food of the bird that prefer
   */
  public void setPreferredFood(String preferredFood)
  {
    this.preferredFood = preferredFood;
  }

  /**
   * Get type
   * @return type
   */
  public String getType()
  {
    return type;
  }

  /**
   * toString method for bird
   * @return String with bird species and preferred food
   */
  public String toString()
  {
    return super.toString() + "\nPreferred food: "+ preferredFood + "\nType: " + type;
  }

  /**
   * equals method
   * @param obj to compare with the bird class in case if it is identical or not
   * @return true if the object is equal to this object or false if it is not
   *
   */
  public boolean equals(Object obj)
  {
    if(obj==null||this.getClass()!=obj.getClass())
    {
      return false;
    }
    Bird other = (Bird) obj;
    return super.equals(other) && preferredFood.equals(other.preferredFood) && type.equals(other.type);
  }
}