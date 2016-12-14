import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Read city distance information and create the symmetric adjacency lists.
 *
 *
 * Input file looks like:  <br>
 *    711         <br>
 *    1 2 100     <br>
 *    1 102 119   <br>
 *    1 194 131   <br>
 * where the first line indicates how many subsequent entries there are, and each following line has: <br>
 *   1. Source city  <br>
 *   2. Destination city <br>
 *   3. Distance from source to destination cities
 */
public class CityDistancesAdjacencyList {

    // Instance variables for class ReadCityNames
    private int numberOfInputFileRows;
    private ArrayList< ArrayList> cityAdjacencyList;   // Make an ArrayList of ArrayLists to represent City adjacency lists

    // Default constructor
    public CityDistancesAdjacencyList()
    {
        this("CityDistances.txt");
    }

    // Fully qualified constructor
    public CityDistancesAdjacencyList(String fileName)
    {
        String inputLine;              // stores one line at a time from input file
        Scanner reader = null;         // create a reader
        try {
            reader = new Scanner( new FileInputStream( fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Create an ArrayList, where each entry will itself also be an ArrayList
        cityAdjacencyList = new ArrayList();
        // Add a 0th entry on the list, so that index values match with city numbers
        cityAdjacencyList.add( new ArrayList());

        inputLine = reader.nextLine();
        numberOfInputFileRows = Integer.parseInt( inputLine);

        int sourceCity;         // City number we're coming from
        int destinationCity;    // City number we're going to
        int distance;           // Distance between the two cities

        for( int i=0; i<numberOfInputFileRows; i++) {
            sourceCity = reader.nextInt();
            destinationCity = reader.nextInt();
            distance = reader.nextInt();

            // Make a new ArrayList entry if this is a new source City.
            // Add new ArrayList entries for all city numbers up to the currently read city number, since the
            // input could skip some city numbers.  City numbers can be skipped because  the CityDistances file
            // isn't symmetric.  E.g. it has distances from A to B, but not B to A
            while( sourceCity >= cityAdjacencyList.size()) {
                // Create an adjacency list for the new source city.
                cityAdjacencyList.add(new ArrayList());
            }
            // Get a reference to the current adjacency list
            ArrayList< CityDistance> adjacencyListFromSourceCity = cityAdjacencyList.get(sourceCity);

            // Add to the list for the source city.
            adjacencyListFromSourceCity.add( new CityDistance( destinationCity, distance) );

        }//end for( int i...

        // Add symmetric information to adjacency lists
        makeAdjacencyListsSymmetric();

    }//end constructor


    // The input file does not have symmetric distances.  I.e. it has distance A to B, but not B to A.
    // Go through each distance and add these symmetric entries.
    private void makeAdjacencyListsSymmetric( )
    {
        // For each source city.  Since the 0th entry is just a placeholder to make matching cities easier,
        // skip the 0th entry and start at 1.
        for( int sourceCity=1; sourceCity<cityAdjacencyList.size(); sourceCity++) {

            // Make a reference to the ArrayList for this source city
            ArrayList< CityDistance> adjacencyListFromCity = cityAdjacencyList.get( sourceCity);

            // for each destination city node on the ArrayList for this source city
            for( int adjacencyListNode=0; adjacencyListNode < adjacencyListFromCity.size(); adjacencyListNode++) {
                // Create a reference to the ith position CityDistance entry on the ArrayList for fromCity
                CityDistance theCityDistanceInstance = adjacencyListFromCity.get( adjacencyListNode);

                // Retrieve the destination city number and distance for this entry
                int destinationCity = theCityDistanceInstance.getCityNumber();
                int distance = theCityDistanceInstance.getCityDistance();

                // First ensure the destination city is not a number past the end of the list.  If it is, create the
                // adjacency lists up to and including that city number.
                while( destinationCity >= cityAdjacencyList.size()) {
                    // Create an adjacency list for this new source city.
                    cityAdjacencyList.add(new ArrayList());
                }

                // If the sourceCity to destinationCity entry is not already stored the other way around, then
                // add that entry.
                if( getCityDistance( destinationCity, sourceCity) == Integer.MAX_VALUE) {
                    // Entry is not there, so add it.
                    // First get the reference to the current adjacency list
                    ArrayList< CityDistance> adjacencyListForDestinationCity = cityAdjacencyList.get( destinationCity);
                    // Add the entry
                    adjacencyListForDestinationCity.add( new CityDistance( sourceCity, distance));
                }
            }//end for( int adjacencyListNode...

        }//end for( int sourceCity=1...
    }//end makeAdjacencyListsSymmetric()


    // "Get" functions
    public int getnumberOfInputFileRows() {
        return numberOfInputFileRows;
    }

    // The cityAdjacencyList is an ArrayList of ArrayLists.  Given a source and destination city, return
    // the distance, or maxInt if the destination city is not on that adjacency list.
    public int getCityDistance( int sourceCity, int toCity)
    {
        int returnValue = Integer.MAX_VALUE;

        // Ensure source city has an adjacency list
        if( sourceCity >= cityAdjacencyList.size()) {
            System.out.println("*** Error: City " + sourceCity + " does not have an adjacency list entry.");
        }
        else {
            // Source city does have an associated adjacency list. Create a reference to it
            ArrayList< CityDistance> adjacencyListFromSourceCity = cityAdjacencyList.get(sourceCity);

            // Step through that ArrayList looking for destination city
            for (int destinationCity = 0; destinationCity < adjacencyListFromSourceCity.size(); destinationCity++) {
                // Create a reference to the ith position CityDistance entry on the ArrayList for fromCity
                CityDistance theCityDistance = adjacencyListFromSourceCity.get( destinationCity);

                if (theCityDistance.getCityNumber() == toCity) {
                    returnValue = theCityDistance.getCityDistance();
                    break;    // No need to keep traversing this list
                }
            }//end for( int destinationCity...
        }//end else

        // return the city distance, which will be either the max value, or the value found
        return returnValue;

    }//end getCityDistance(...


    public ArrayList<ArrayList> getCityAdjacencyList() {
        return cityAdjacencyList;
    }


    // Return a string containing all the array list entries
    public String toString()
    {
        String allCityDistances = "";

        // For each source city.  Since the 0th entry is just a placeholder to make matching cities easier,
        // skip the 0th entry and start at 1.
        for( int sourceCity=1; sourceCity<cityAdjacencyList.size(); sourceCity++) {
            allCityDistances = allCityDistances + "From " + sourceCity + " to ";
            // Make a reference to the ArrayList for this source city
            ArrayList< CityDistance> adjacencyListFromCity = cityAdjacencyList.get( sourceCity);

            // for each destination city on the ArrayList for this source city
            for( int destinationCity=0; destinationCity < adjacencyListFromCity.size(); destinationCity++) {
                // Create a reference to the ith position CityDistance entry on the ArrayList for fromCity
                CityDistance theCityDistance = adjacencyListFromCity.get( destinationCity);

                // Append the destination city and distance to the output string
                allCityDistances = allCityDistances + theCityDistance + " ";
            }
            // append a return character at the end of the list for each source city
            allCityDistances = allCityDistances + "\n";
        }

        return allCityDistances;
    }//end toString()

}
