package Model;

import parser.XmlJsonParser;
import utils.MyFileHandler;
import parser.ParserException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A class that manages the pet list
 * It can get all pets, save pets, change comments, change name, and change price
 * @version 1.0
 * @author Group6
 */
public class PetModelManager
{
  private String fileName;

  /**
   * A constructor that initializes the file name
   * @param fileName the name of the file
   */
  public PetModelManager(String fileName)
  {
    this.fileName = fileName;
  }

  /**
   * A method that gets all pets
   * @return all pets
   */
  public PetList getAllPets()
  {
    PetList allPets = new PetList();
    try
    {
      allPets=(PetList)MyFileHandler.readFromBinaryFile(fileName);

    }
    catch (FileNotFoundException e)
    {
      System.out.println("File was not found, or could not be opened");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading from file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Error reading from file");
    }
    return allPets;
  }

  /**
   * A method that saves pets
   * @param pets the list of pets
   */
  public void savePets(PetList pets)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(fileName, pets);
      System.out.println("File saved as: " + fileName +", in: "+ new File(fileName).getAbsolutePath());
      saveToXML(pets, "pets.xml");
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found or could not be opened");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file ");
    }
  }

  /**
   * A method that changes the comment content of a pet
   * @param index the index of the pet
   * @param newComments the new comment
   */
  public void changePetComments(int index ,String newComments)
  {
    PetList pets = getAllPets();
    pets.getByIndex(index).setComment(newComments);
    savePets(pets);
  }

  /**
   * A method that changes the name of a pet
   * @param index the index of the pet
   * @param newPetName the new name
   */
  public void changePetName(int index, String newPetName)
  {
    PetList pets = getAllPets();
    pets.getByIndex(index).setName(newPetName);
    savePets(pets);
  }

  /**
   * A method that changes the price of a pet
   * @param index the index of the pet
   * @param newPrice the new price
   */
  public void changePetPrice(int index , int newPrice)
  {
    PetList pets = getAllPets();
    pets.getByIndex(index).setPrice(newPrice);
    savePets(pets);
  }

  /**
   * A method that saves the pet list to an XML file
   * @param pets the list of pets
   * @param fileNameXML the name of the XML file
   */
  public void saveToXML(PetList pets, String fileNameXML)
  {
    try {
      XmlJsonParser parser = new XmlJsonParser();

      // Define the directory path
      String directoryPath = "website/xml/";
      File directory = new File(directoryPath);

      // Ensure the directory exists
      if (!directory.exists()) {
        if (directory.mkdirs()) {
          System.out.println("Directory created: " + directory.getAbsolutePath());
        } else {
          System.out.println("Failed to create directory: " + directory.getAbsolutePath());
        }
      }

      // Create the file path
      File file = new File(directory, fileNameXML);

      // Save the XML file
      parser.toXml(pets, file.getAbsolutePath());
      System.out.println("File saved as: " + file.getAbsolutePath());
    } catch (ParserException e) {
      throw new RuntimeException(e);
    }
  }

}