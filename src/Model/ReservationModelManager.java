package Model;
import javafx.geometry.Pos;
import parser.ParserException;
import parser.XmlJsonParser;
import utils.MyFileHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

/**
 * A class that manages a list of reservations for a kennel.
 * @author uthor Group6
 * @version 1.0
 */
public class ReservationModelManager
{
  private String fileName;
  private ReservationModelManager reservationModelManager;

  /**
   * Constructs a ReservationModelManager object.
   * @param fileName the name of the file to read/write reservations from/to.
   */
  public ReservationModelManager(String fileName)
  {
    this.fileName = fileName;
  }

  /**
   * Retrieves the number of reservations in the list.
   * @return the number of reservations.
   */
  public ReservationList getAllReservations()
  {
    ReservationList allReservations = new ReservationList();
    try
    {
      allReservations=(ReservationList)MyFileHandler.readFromBinaryFile(fileName);
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
    return allReservations;
  }

  /**
   * Saves a list of reservations to the file.
   * @param reservations the reservations to save.
   */
  public void saveReservations(ReservationList reservations)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(fileName, reservations);
      saveToXML(reservations, "reservations.xml");
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
   * Saves a list of reservations to an XML file.
   * @param reservations the reservations to save.
   * @param fileNameXML the name of the XML file to save to.
   */
  public void saveToXML(ReservationList reservations, String fileNameXML)
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
      parser.toXml(reservations, file.getAbsolutePath());
      System.out.println("File saved as: " + file.getAbsolutePath());
    } catch (ParserException e) {
      throw new RuntimeException(e);
    }
  }
}