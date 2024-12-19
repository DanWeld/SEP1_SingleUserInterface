package Model;

/**
 * this is a subclass Various that extends the superclass Pet
 * @author Group6
 * @version 1.0
 */
public class Various extends Pet
{
  /**
   * this constructor is to create an object of various in the pet shop showcased by having a price
   * @param name,gender,comment,birthdate,color,price,species from the super class
   */
  public Various(String name, char gender, String comment, MyDate birthDate, String color, int price, String species)
  {
    super(name, gender ,comment, birthDate, color, price);
    super.setSpecies(species);
  }

  /**
   * this creates a toString method for Various
   *@return it returns String for Various with added species field
   */
  public String toString()
  {
    return super.toString();
  }

  /**
   * equals method for various pet
   * @return boolean if the various pet is equal to another various pet
   * @param obj to compare with the various pet
   */
  public boolean equals(Object obj)
  {
    if (obj==null||obj.getClass()!=this.getClass())
    {
      return false;
    }
    Various other = (Various) obj;
    return super.equals(other);
  }
}