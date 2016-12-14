/**
 * City number and distance, used as entries on the adjacency list for some source city.  cityName is only used when a
 * temporary list is created to be sorted. The CityDistance class implements Comparable, which means it has to
 * implement the compareTo() function, which is subsequently used in sorting an ArrayList of CityDistances
 *
 * Created by reed2 on 9/4/16 for UIC CS 342, Program 1
 */
public class CityDistance implements Comparable<CityDistance>
{
    private int cityNumber;
    private int cityDistance;
    private String cityName;   // used only when displaying list of min city distances in alphabetical order

    // Constructors
    // Default constructor
    public CityDistance()
    {
        this(0,0);  // chain to fully qualified constructor
    }

    // Constructor with default for city name
    public CityDistance( int aCityNumber, int aCityDistance)
    {
        this( aCityNumber, aCityDistance, "");
    }


    // Fully qualified constructor
    public CityDistance( int theCityNumber, int theCityDistance, String theCityName)
    {
        cityNumber = theCityNumber;
        cityDistance = theCityDistance;
        cityName = theCityName;
    }

    // Copy constructor
    public CityDistance( CityDistance other)
    {
        if( other != null) {
            cityNumber = other.cityNumber;
            cityName = other.cityName;
            cityDistance = other.cityDistance;
        }
        else {
            // Error condition
            System.out.println("Fatal error, tried to create a CityDistance from a null instance.");
            System.exit( -1);
        }
    }


    // get methods
    public int getCityNumber() {
        return cityNumber;
    }
    public int getCityDistance() { return cityDistance; }
    public String getCityName() {
        return cityName;
    }

    // set methods
    public void setCityNumber(int cityNumber) {
        this.cityNumber = cityNumber;
    }
    public void setCityDistance(int cityDistance) {
        this.cityDistance = cityDistance;
    }

    // Comparison method is needed to implement Collections class sorting
    public int compareTo( CityDistance other) {
        // comparison is alphabetic by last name
        return cityName.compareTo( other.cityName);

    }


    public String toString()
    {
        return (cityNumber + ":" + cityDistance);
    }

}
