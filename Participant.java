/**
 * (Name, city) for each person.
 *
 */
public class Participant {

    String name;
    int cityNumber;

    // Constructors
    public Participant()
    {
        this("Noname", 0);   // default participant name and city location
    }

    public Participant(String theName, int theCityNumber)
    {
        name = theName;
        cityNumber = theCityNumber;
    }


    public String getName() {
        return name;
    }

    public int getCityNumber() {
        return cityNumber;
    }


    public String toString()
    {
        return ( name + " at " + cityNumber);
    }
}
