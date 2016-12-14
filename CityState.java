/**
 * Tuple used to store city name and state abbreviation.
 *
 * Created by reed2 on 9/3/16 for UIC CS 342, Program 1
 */


// Private class to store each city,state pair
public class CityState {

    private String city;     // City name
    private String state;    // 2 chars for state initials

    // default constructor
    public CityState()
    {
        this("FakeName","ZZ");   // chain to fully qualified constructor
    }

    // fully qualified constructor
    public CityState( String theCity, String theState)
    {
        // Validate user input for state
        if( theState.length() != 2) {
            System.out.println("State is not the two initials expected, but instead is " + theState);
            System.out.println("   and has length " + theState.length() + ". Exiting program...");
            System.exit( -1);
        }
        else {
            // State is valid length, so store it
            this.state = theState;
        }

        this.city = theCity;
    }

    public String getCity() {
        return city;
    }

    public String toString()
    {
        return(city + ", " + state);
    }
}