import java.util.ArrayList;
import java.util.Collections;       // Class for Collection-related algorithms.  Used only to sort output city names

/**
 * Finding the single-point all-pairs shortest paths using Dijkstra's algorithm.
 * This assumes you already have a graph represented by adjacency lists, and a starting vertex.
 *
 */
public class DijkstraGraph {

    private int startingCity;      // Number of the starting city
    private int numberOfVertices;  // Total number of cities
    private int[] parent;          // Parent pointer for each city, in the constructed min cost spanning tree
    private int[] distance;        // Distance to each city, from the starting city

    /**
     *
     * @param theCityAdjacencyList  Symmetrical adjacency list, implemented as an ArrayList of ArrayLists
     * @param start  Index of starting city
     */
    public DijkstraGraph(
            ArrayList< ArrayList> theCityAdjacencyList,   // Existing symmetric adjacency list
            int start)                                    // Index of starting city
    {
        numberOfVertices = theCityAdjacencyList.size()-1; // Total number of verticies (cities).  Because the 0th
                                                          //    node is a placeholder, we actually have 1 less.
        boolean[] isInTree = new boolean[ numberOfVertices + 1];  // Whether or not each vertex is currently in the tree.
        distance = new int[ numberOfVertices + 1];                // Minimum distance so far to each vertex.
        parent = new int[ numberOfVertices + 1];                  // Allocate space for parent pointers for each vertex.
        startingCity = start;                                     // Set the starting city

        // Initialize all vertices as not being in the tree, having max distance and no parent.
        // Since we will be using 1 based indexing connected to city numbers, add the 0th city element placeholder
        for (int i=1; i<=numberOfVertices; i++) {
            isInTree[i] = false;
            distance[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }

        // Set values for starting node
        distance[start] = 0;
        int currentVertex = start;              // Current vertex being processed

        // main loop, continued until all vertices are handled
        while ( ! isInTree[ currentVertex]) {
            isInTree[ currentVertex] = true;	// Include current vertex into tree

            // Get a reference to the current adjacency list
            ArrayList< CityDistance> adjacencyListFromSourceCity = theCityAdjacencyList.get(currentVertex);

            // Examine in turn each edge incident to the current vertex
            for( int i=0; i<adjacencyListFromSourceCity.size(); i++) {
                // Create a graph node reference to aid in traversal
                CityDistance tempNode = adjacencyListFromSourceCity.get( i);

                int adjacentVertex = tempNode.getCityNumber();  // Index of destination city
                int weight = tempNode.getCityDistance();        // Distance of incident edge to destination city

                // If this is a new lower distance, store the new lower-cost distance and spanning tree connection point
                if (distance[ adjacentVertex] > (distance[currentVertex] + weight) ) {
                    distance[ adjacentVertex] = distance[currentVertex] + weight;
                    parent[ adjacentVertex] = currentVertex;
                }
            }//end for( int i....

            // Find next vertex to be processed.  It should be the closest one not already in tree.
            currentVertex = 1;
            int shortestNewNodeDistance = Integer.MAX_VALUE;		// Initialize to some large number
            // Examine each vertex in graph
            for (int i=1; i<=numberOfVertices; i++) {
                // if it is not yet in the tree and it has the shortest distance to it of any node
                if ((! isInTree[i]) && (distance[i] < shortestNewNodeDistance)) {
                    // This ith vertex is not yet in tree and is closest so far
                    shortestNewNodeDistance = distance[i];  // set new shortest distance
                    currentVertex = i;                      // set this as the new closest vertex
                }
            }//end for( int i...

        }//end while( ! isInTree...

    }//end DijkstraGraph() constructor


    public int[] getDistance() {
        return distance;
    }

    /**
     * Given a city number and the array of city names, display all cities and min distances for that city,
     * where the results are displayed in alphabetic order by city name.  This is essentially a parameterized
     * version of toString()
     *
     * @param cityNames  All city names
     * @return  String with all cities in alphabetical order, along with their min distance form the source city.
     */
    public String displayResultsWithCityNames(
            ArrayList<CityState> cityNames     // ArrayList of CityState, where each has city name and state
    )
    {
        // Make a copy of city names, to use in sorting them into alphabetic order.
        ArrayList< CityDistance> cityDistancesCopy = new ArrayList();

        // Iterate through and make the copy, using the computing distances from the designated source city
        for( int i=1; i<cityNames.size(); i++) {
            String theCityName = cityNames.get( i).getCity();
            CityDistance newInstance = new CityDistance(i, distance[i], theCityName);
            cityDistancesCopy.add( newInstance);
        }

        // Sort this copy of city distances.  The Collections class requires class CityDistance to implement the
        // Comparable interface, which in turns requires class CityDistance to have the compareTo() function, so
        // it knows how to compare two CityDistance ArrayList entries.
        Collections.sort( cityDistancesCopy);

        String startingCityName = cityNames.get( startingCity).getCity();
        String resultString = "Distances from " +  startingCityName + " are: \n";

        // Step through each city on the list, concatenating the city names and distances to use in printing
        for( int i=0; i<cityDistancesCopy.size(); i++) {
            String theCityName = cityDistancesCopy.get( i).getCityName();
            int cityDistance = cityDistancesCopy.get( i).getCityDistance();
            resultString = resultString + String.format("%19s", theCityName) + ":" + String.format("%5d",cityDistance) + " ";
            // Add a line break after every 10th element to help with the display
            if( (i+1)%8 == 0) {
                resultString = resultString + "\n";
            }
        }
        resultString = resultString + "\n";

        return resultString;
    }


    public String toString()
    {
        String resultString = "Distances from " + startingCity+ " are: ";

        for( int i=1; i<=numberOfVertices; i++) {
            resultString = resultString + i + ":" + distance[ i] + " ";
        }
        resultString = resultString + "\n";

        return resultString;
    }
}//end class DijkstraGraph
