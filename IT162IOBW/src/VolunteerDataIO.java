import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Class VolunteerDataIO<br>
 * Abstract: Program to write data to a file and read from the file
 * 
 * @author Brandon Wernke
 * @version 1
 * @since 02/21/2021
 */
public class VolunteerDataIO
{

	/**
	 * method main<br>
	 * Abstract: where the program starts
	 * 
	 * @param args command line arguments, not used
	 */
	public static void main( String[ ] args )
	{
		// create four new Volunteers
		VolunteerData clsVolunteer1 = new VolunteerData( "Jack", "Black", "Cincinnati", "OH", "Flying Pig" );
		VolunteerData clsVolunteer2 = new VolunteerData( "Jason", "Gather", "Newport", "KY", "Jazz Festival" );
		VolunteerData clsVolunteer3 = new VolunteerData( "Christy", "Jackson", "Hamilton", "OH", "5k" );
		VolunteerData clsVolunteer4 = new VolunteerData( "Brandon", "Wernke", "Hamilton", "OH", "10k" );

		try
		{
			// open file and output stream
			FileOutputStream fFile = new FileOutputStream( new File( "Volunteers.txt" ) );
			ObjectOutputStream oOutputStream = new ObjectOutputStream( fFile );

			// write volunteer data to Volunteers.txt
			oOutputStream.writeObject( clsVolunteer1 );
			oOutputStream.writeObject( clsVolunteer2 );
			oOutputStream.writeObject( clsVolunteer3 );
			oOutputStream.writeObject( clsVolunteer4 );
			
			// close writer and file
			oOutputStream.close( );
			fFile.close( );
	
			// open file and reader
			FileInputStream fiFileIn = new FileInputStream( new File( "Volunteers.txt" ) );
			ObjectInputStream oiObjectIn = new ObjectInputStream( fiFileIn );
	
			// read and print data
			VolunteerData clsReadVolunteer1 = (VolunteerData) oiObjectIn.readObject( );
			VolunteerData clsReadVolunteer2 = (VolunteerData) oiObjectIn.readObject( );
			VolunteerData clsReadVolunteer3 = (VolunteerData) oiObjectIn.readObject( );
			VolunteerData clsReadVolunteer4 = (VolunteerData) oiObjectIn.readObject( );
			
			System.out.printf( "%-15s %-15s %-12s %-7s %-10s\n", "First Name", "Last Name", "City", "State", "Event" );
			System.out.println( clsReadVolunteer1.toString( ) );
			System.out.println( clsReadVolunteer2.toString( ) );
			System.out.println( clsReadVolunteer3.toString( ) );
			System.out.println( clsReadVolunteer4.toString( ) );
			
			// close reader and file
			oiObjectIn.close( );
			fiFileIn.close( );

		} catch ( FileNotFoundException e )
		{
			System.out.println( "File Volunteers.txt not found. Contact the support desk and reference this error in class VolunteerDataIO" );
		} catch ( IOException e )
		{
			System.out.println( "Error initializing stream" );
		} catch ( ClassNotFoundException e )
		{
			e.printStackTrace( );
		}
	}

}
