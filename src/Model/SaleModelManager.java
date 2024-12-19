package Model;
import utils.MyFileHandler;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A class that manages the sales of pets.
 * The class has a method that returns all sales, and a method that saves sales.
 * @author Group6
 * @version 1.0
 */
public class SaleModelManager
{
    private String fileName;

    /**
     * Constructor with 1 parameter.
     * @param fileName the name of the file.
     */
    public SaleModelManager(String fileName)
    {
        this.fileName = fileName;
    }

    /**
     * Method that returns all sales from a file.
     * @return all sales.
     */
    public SalesLog getAllSales()
    {
        SalesLog allSales = new SalesLog();

        try
        {
          allSales = (SalesLog) MyFileHandler.readFromBinaryFile(fileName);
        }
        catch (FileNotFoundException e)
        {
          System.out.println("File not found");
        }
        catch (IOException e)
        {
          System.out.println("IO Error reading file");
        }
        catch (ClassNotFoundException e)
        {
          System.out.println("Class Not Found");
        }
        return allSales;
    }

    /**
     * Method that saves sales to a file.
     * @param sales the sales to be saved.
     */
    public void saveSales(SalesLog sales)
    {
        try
        {
          MyFileHandler.writeToBinaryFile(fileName, sales);
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