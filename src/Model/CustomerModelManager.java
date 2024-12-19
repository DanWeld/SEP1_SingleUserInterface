
package Model;
import utils.MyFileHandler;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A class that manages the customer list
 * It can get all customers, save customers, change email address, change phone number, and change name
 * @version 1.0
 * @author Group6
 */
public class CustomerModelManager
{
  private String fileName;

  /**
   * A constructor that initializes the file name
   * @param fileName the name of the file
   */
  public CustomerModelManager(String fileName)
  {
    this.fileName = fileName;
  }

  /**
   * A method that gets all customers
   * @return all customers
   */
  public CustomerList getAllCustomers()
  {
    CustomerList allCustomers = new CustomerList();
    try
    {
      allCustomers=(CustomerList)MyFileHandler.readFromBinaryFile(fileName);

    }
    catch (FileNotFoundException e)
    {
      System.out.println("File was not found, or could not be opened");
    }
    catch (IOException e)
    {
      System.out.println("Error reading from file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Error reading from file");
    }
    return allCustomers;
  }


  /**
   * A method that saves customers
   * @param customers the list of customers
   */
  public void saveCustomers(CustomerList customers)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(fileName, customers);
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
}
