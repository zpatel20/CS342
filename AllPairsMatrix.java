/**
 * Calculate the minimum distance between all sets of points, creating an n x n matrix.
 * Do this by applying Dijkstra's one-point-all-pairs algorithm n times, once from the perspective of each point.
 *
 * Created by Dale Reed 9/5/2016 for UIC CS 342, program 1
 */
public class AllPairsMatrix {

    int[][] minCityDistances;
    int numberOfCities;

    /**
     *
     * @param theNumberOfCities    How many cities there are
     * @param citiesAdjacencyList  Adjacency list for each city.  This is an ArrayList of ArrayLists.
     */
    public AllPairsMatrix(
                int theNumberOfCities,
                CityDistancesAdjacencyList citiesAdjacencyList)
    {
        DijkstraGraph singlePointAllPairsMinCost;
        numberOfCities = theNumberOfCities;

        // Create all pairs shortest graph.  Add one to each dimension, since we are using 1 based indexing and are
        // ignoring the 0th entry, to match the numbering in the data files.
        minCityDistances = new int[ numberOfCities+1][ numberOfCities+1];

        // Repeatedly use Dijkstras single point all pairs algorithm to fill in the all pairs shortest distance matrix
        for( int startCity=1; startCity <= numberOfCities; startCity++) {
            // Get single-point all pairs shortest distance from startCity
            singlePointAllPairsMinCost = new DijkstraGraph( citiesAdjacencyList.getCityAdjacencyList(), startCity);

            // Retrieve the resulting distances array
            int[] distancesFromStartCity = singlePointAllPairsMinCost.getDistance();

            // Store results into adjacency matrix for the row corresponding to startCity
            for( int destinationCity=1; destinationCity <= numberOfCities; destinationCity++) {
                minCityDistances[ startCity][ destinationCity] = distancesFromStartCity[ destinationCity];
            }
        }//end for( int startCity...


    }//end AllPairsMatrix() constructor


    /**
     *
     * @param sourceCity       The starting city for calculating distance
     * @param destinationCity  The ending city for calculating distance
     * @return                 The distance between the two cities
     */
    public int getDistance( int sourceCity, int destinationCity)
    {
        return minCityDistances[ sourceCity][ destinationCity];
    }

    /**
     *
     * @return  A String containing the n x n table
     */
    public String toString()
    {
        String result = "Resulting matrix is:";

        // Display the column headers
        result += "     ";     // leave space for row labels
        for( int col=1; col <= numberOfCities; col++) {
            result += String.format("%4d:", col);
        }
        result += "\n";

        // For each row
        for( int row=1; row <= numberOfCities; row++) {
            // Display the row labels
            result += String.format( "%4d: ", row);

            // for each column within that row
            for( int col=1; col <= numberOfCities; col++) {
                result += String.format( "%4d ", minCityDistances[ row][ col]) ;
            }
            result += "\n";
        }

        return result;
    }//end toString()

}//end class AllPairsMatrix
