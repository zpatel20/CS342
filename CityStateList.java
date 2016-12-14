import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * List of (cityName, state initial) pairs, created by reading from input file that has the format: <br>
 *   248             <br>
 *   Huntsville, AL  <br>
 *   Birmingham, AL  <br>
 *   ...    <br>
 * where the first line indicates how many cities there are, followed by that many city, state pairs.
 *
 */
public class CityStateList {

    // Instance variables for class ReadCityNames
    private int numberOfCities;
    private ArrayList< CityState> cities;   // Make an ArrayList of CityState objects

    // Default constructor
    public CityStateList()
    {
        this("CityStateList.txt");
    }


    /**
     * Create ArrayList of (City, State) entries.
     * @param fileName  File with (City, State) entries
     */
    // Fully qualified constructor
    public CityStateList(String fileName)
    {
        String inputLine;              // stores one line at a time from input file
        Scanner reader = null;         // create a reader
        try {
            reader = new Scanner( new FileInputStream( fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // ArrayList to store city,state entries.
        // Add a 0th entry so that index values match city names
        cities = new ArrayList();
        cities.add( new CityState());

        inputLine = reader.nextLine();
        numberOfCities = Integer.parseInt( inputLine);

        String aCity;     // City name from the input file
        String aState;    // State name from the input file

        for( int i=0; i<numberOfCities; i++) {
            inputLine = reader.nextLine();
            String[] tokens = inputLine.split(",");
            aCity = tokens[0];
            aState = tokens[1].trim();

            cities.add( new CityState( aCity, aState));
        }
    }


    // "Get" functions
    public int getNumberOfCities() {
        return numberOfCities;
    }

    public ArrayList<CityState> getCityStateArrayList()
    {
        return cities;
    }


    public String toString()
    {
        String allCities = "";

        for( int i=0; i<cities.size(); i++) {
            allCities = allCities + (i+1) + ". " + cities.get(i).toString() + "; ";
        }

        return allCities;
    }

}//end class CityStateList
