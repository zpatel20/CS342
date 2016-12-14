import java.util.ArrayList;
/**
 * Read from files, create adjacency lists, create min distance matrix, and find min average city.
 *
 */
public class Driver {

    public static void main(String[] args) {

        Driver driverInstance = new Driver();
        driverInstance.doIt();
    }

    /**
     * Code for testing was left in, but commented out, to help illustrated development process.
     */
    public void doIt()
    {
        int numberOfCities;
        CityStateList theCityStateList;
        CityDistancesAdjacencyList citiesAdjacencyList;
        DijkstraGraph singlePointAllPairsMinCost;
        int[][] minCityDistances;

        // Test CityState class
        /*
        CityState cs1 = new CityState();
        System.out.println(cs1);
        CityState cs2 = new CityState("Evanston", "IL");
        System.out.println(cs2);
        */

        // Read city names
        theCityStateList = new CityStateList("CityNames.txt");
        //System.out.println(rcn);

        numberOfCities = theCityStateList.getNumberOfCities();
        ArrayList<CityState> cities = theCityStateList.getCityStateArrayList();
        /*
        System.out.println("There are " + numberOfCities + " cities, which are: ");
        System.out.println(cities);
        */

        // Create CityDistancesAdjacencyList
        citiesAdjacencyList = new CityDistancesAdjacencyList();
        System.out.println("All the adjacency lists are: ");
        System.out.println( citiesAdjacencyList);

        // Create Dijkstras single point all-pairs min cost solution, from perspective of Chicago (city 58)
        singlePointAllPairsMinCost = new DijkstraGraph( citiesAdjacencyList.getCityAdjacencyList(), 58);
        System.out.println( singlePointAllPairsMinCost.displayResultsWithCityNames( cities));

        // Now repeatedly apply Dijkstra's algorithm to create the all points min-cost distance matrix
        AllPairsMatrix allPairs = new AllPairsMatrix( numberOfCities, citiesAdjacencyList);
        // test the allPairs matrix distance retrieval
        // System.out.println("Distance from 58 (Chicago) to 10 (Tucson) is: " + allPairs.getDistance( 58, 10));
        // System.out.println("Distance from 58 (Chicago) to 26 (Los Angeles) is: " + allPairs.getDistance( 58, 26));
        // System.out.println();

        // Read Participant list
        ParticipantList allPeople = new ParticipantList( "Participants.txt");
        // System.out.println( allPeople);

        // Find a city giving the minimum average travel distance for all people
        int bestCity = findShortestAverageDistanceCity( allPeople, allPairs, numberOfCities);
        System.out.println("City with shortest average distance travelled is: " + cities.get( bestCity));
    }//end doIt()


    /**
     * Go through every city and find the average travel distance across all people.  Find the minimum of these average
     * travel distances.
     *
     * @param allPeople         ArrayList of people and which city they are in
     * @param allPairs          All pairs distance matrix
     * @param numberOfCities    How many cities there are
     * @return                  The index of the best city, giving the minimum average travel distance
     */
    private int findShortestAverageDistanceCity(
                    ParticipantList allPeople,
                    AllPairsMatrix allPairs,
                    int numberOfCities)
    {
        int shortestAverageDistance = Integer.MAX_VALUE;
        int bestCity = -1;
        int numberOfPeople = allPeople.getNumberOfParticipants();
        int[] distances = new int[ numberOfPeople];
        int[] bestDistances = new int[ numberOfPeople];

        // For each city, find the average distance travelled
        for( int cityCandidate=1; cityCandidate <= numberOfCities; cityCandidate++) {

            // first find the sum of distances travelled across all people
            int totalDistance = 0;
            for( int person=0; person < numberOfPeople; person++) {
                int thisPersonsCity = allPeople.getParticipant( person).getCityNumber();
                distances[ person] = allPairs.getDistance( cityCandidate, thisPersonsCity);
                totalDistance += allPairs.getDistance( cityCandidate, thisPersonsCity);
            }

            // Now remember the total distance and the city if this is the best found so far
            int averageDistance = totalDistance / numberOfPeople;
            if( averageDistance < shortestAverageDistance) {
                shortestAverageDistance = averageDistance;
                bestCity = cityCandidate;
                // Store distances travelled for each person
                for( int person=0; person < numberOfPeople; person++) {
                    bestDistances[ person] = distances[ person];
                }
            }

        }//end for( int cityCandidate...

        System.out.println("Distances travelled are: ");
        for( int person=0; person < numberOfPeople; person++) {
            String personName = allPeople.getParticipant( person).getName();
            System.out.printf( "%10s: %4d\n", personName, bestDistances[ person]);
        }
        System.out.printf("   Total: %6d    with average: %d\n", shortestAverageDistance * numberOfPeople, shortestAverageDistance);
        System.out.println();

        return bestCity;
    }//end findShortestAverageDistanceCity(...

}//end class Driver()
