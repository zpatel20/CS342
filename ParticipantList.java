import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * List of all participants, read in from a datafile, stored as an ArrayList
 *
 * Created by reed2 on 9/5/16 for UIC CS 342, Program 1
 */
public class ParticipantList {

    // Instance variables
    private int numberOfParticipants;
    private ArrayList< Participant> allParticipants;   // Make an ArrayList of all Participants


    // Fully qualified constructor
    public ParticipantList(String fileName)
    {
        String inputLine;              // stores one line at a time from input file
        Scanner reader = null;         // create a reader
        try {
            reader = new Scanner( new FileInputStream( fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // ArrayList to store participantName, cityNumber pairs
        allParticipants = new ArrayList();

        inputLine = reader.nextLine();
        numberOfParticipants = Integer.parseInt( inputLine);

        // Use to read from the input file
        String theName;
        int theCityNumber;

        for( int i=0; i<numberOfParticipants; i++) {
            theName = reader.next();
            theCityNumber = reader.nextInt();

            allParticipants.add( new Participant( theName, theCityNumber));
        }
    }


    // "Get" functions
    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public Participant getParticipant( int i)
    {
        return allParticipants.get( i);
    }


    public String toString()
    {
        String allPeople = "Participants: ";

        for( int i=0; i<allParticipants.size(); i++) {
            allPeople += (i+1) + ". " + allParticipants.get(i).toString() + "; ";
        }

        return allPeople;
    }//end toString()

}//end class ParticipantList
