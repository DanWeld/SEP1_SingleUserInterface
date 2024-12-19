package Model;
/**
 * A subclass representing a fish
 * @author Group6
 * @version 1.0
 */
public class Fish extends Pet
{
  /**

   * Two extra fields more than the super class as needed for the Cat class.
   */
  private boolean isSaltyWater;
  private boolean isPredator;


  /**
   * @param name,gender,comment,birthDate,color,price form super class
   * @param isSaltyWater,isPredator specific for this class
   * This constructor is to create an object of a cat in the pet shop "Has a price"
   */
  public Fish(String name, char gender, String comment, MyDate birthDate, String color, int price, boolean isSaltyWater, boolean isPredator)
  {
    super(name, gender, comment, birthDate, color, price);
    super.setSpecies("Fish");
    this.isSaltyWater = isSaltyWater;
    this.isPredator = isPredator;
  }

  /**
   * Get isSaltyWater to check if this fish lives in salty water or fresh water
   * @return boolean: true if lives in salty water or false if lives in fresh water
   */
  public boolean getIsSaltyWater()
  {
    return isSaltyWater;
  }

  /**
   * Get isPredator
   * @return boolean: true if the fish is predator or false if the fish is not predator.
   */
  public boolean getIsPredator()
  {
    return isPredator;
  }

  /**
   * toString method for fish
   * @return String with fish species
   */
  public String toString()
  {
    String str = super.toString()+"\n";

    if (isSaltyWater)
    {
      str += "lives in salty water\n";
    }
    else
    {
      str += "lives in fresh water\n";
    }
    if (isPredator)
    {
      str += "Predator";
    }
    else
    {
      str += "Prey";
    }
    return str;
  }

  /**
   * equals method for fish
   * @return boolean if the fish is equal to another fish
   * @param obj to compare
   *
   */
  public boolean equals(Object obj)
  {
    if (obj==null||obj.getClass()!=this.getClass())
    {
      return false;
    }
    Fish other = (Fish) obj;
    return super.equals(other) && isSaltyWater==other.isSaltyWater && isPredator==other.isPredator;
  }
}
