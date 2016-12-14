/**
 * (Name, city) for each person.
 *
 * Created by reed2 on 9/5/16 for UIC CS 342, Program 1
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
