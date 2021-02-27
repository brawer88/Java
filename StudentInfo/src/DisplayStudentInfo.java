import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class DisplayStudentInfo<bR>
 * Abstract: A program to display database data based on user input. There is a program loop to it. 
 * @author Brandon Wernke
 * @version 1
 * @since 02/15/2021
 */
public class DisplayStudentInfo
{

	/**
	 * method main<br>
	 * Abstract: Where the program starts
	 * @param args the program arguments, none for this application
	 */
	public static void main( String[ ] args )
	{
		// declare variables
		int intUserChoice = 0;
		boolean blnConnectionOpen = false;
		
		// open the database connection
		blnConnectionOpen = DatabaseAccess.OpenDatabaseConnectionMSAccess( "StudentInfoSys.accdb" );		
		if ( blnConnectionOpen == true )
		{
			// show menu
			do 
			{
				intUserChoice = showMenu( );
				
				processUserChoice( intUserChoice );
			} while ( intUserChoice != 4 );
		}
		

		if ( DatabaseAccess.CloseDatabaseConnection( ) == true )
		{
			System.out.println( "Thank you for using Student Info" );
		}
		else
		{
			System.out.println( "Database connection closing issue in main method. Please contact Support for Project CS" );
		}
	}


	
	/**
	 * method showMenu<br>
	 * Abstract: Shows the user menu options
	 * @return intUserChoice - the user's validated choice
	 */
	private static int showMenu()
	{
		// declare variables
		int intUserChoice = 0;
		
		do
		{
			System.out.println( "Enter option 1 – see Student table " );
			System.out.println( "Enter option 2 – view Major table " );
			System.out.println( "Enter option 3 – view both " );
			System.out.println( "Enter option 4 – quit application" );
			try
			{
				intUserChoice = readIntegerFromUser( );
			}
			catch ( NumberFormatException e )
			{
				System.out.println( "Enter numbers only please.\n" );
				intUserChoice = 0;
			}
		} while ( validateUserChoice( intUserChoice ) == false );
		
		return intUserChoice;
	}

	
	
	/**
	 * method readIntegerFromUser<br>
	 * Abstract: reads an integer value from the user
	 * @return intValue - Integer value from user input
	 * @throws NumberFormatException - thrown when a non-integer value is entered by user
	 */
	private static int readIntegerFromUser( )
	throws NumberFormatException
	{			  

		int intValue = -1;

		try
		{
			String strBuffer = "";	

			// Input stream
			BufferedReader burInput = new BufferedReader( new InputStreamReader( System.in ) ) ;

			// Read a line from the user
			strBuffer = burInput.readLine( );
			
			// Convert from string to integer
			intValue = Integer.parseInt( strBuffer );
		}
		catch( NumberFormatException excError )
		{
			throw new NumberFormatException();
		} catch ( IOException e )
		{
			e.printStackTrace();
		}
		
		// Return integer value
		return intValue;
	}
	
	
	
	/**
	 * method processUserChoice<br>
	 * Abstract: Displays requested database information based on input
	 * @param intUserChoice the choice to process
	 */
	private static void processUserChoice( int intUserChoice )
	{
		try {
			switch ( intUserChoice ) 
			{
				// show student table if 1
				case 1:
					DatabaseAccess.LoadStudentsFromDatabase( "TContacts", "intContactID" );
					break;
					
				// show major table if 2
				case 2:
					DatabaseAccess.LoadMajorsFromDatabase( "TMajors", "intMajorID" );
					break;
			
				// show both if 3
				case 3:
					DatabaseAccess.LoadStudentsFromDatabase( "TContacts", "intContactID" );
					DatabaseAccess.LoadMajorsFromDatabase( "TMajors", "intMajorID" );
					break;			
				
				// display message ending the program
				case 4:
					System.out.println( "Quitting..." );
					break;			
			}
		}
		catch( IndexOutOfBoundsException e )
		{
			System.out.println( "You have an issue with your index. Please contact level 1 Support for Project CS." );
		}
	}


	
	/**
	 * method validateUserChoice<br>
	 * Abstract: Validates the user choice is 1-4
	 * @param intUserChoice the choice to validate
	 * @return blnValidChoice - if the choice is valid, 1-4
	 */
	private static boolean validateUserChoice( int intUserChoice )
	{
		// declare variables
		boolean blnValidChoice = false;
		
		if( intUserChoice > 0 && intUserChoice < 5 )
		{
			blnValidChoice = true;
		}
		else
		{
			System.out.println( "Please enter a valid choice (1-4)." );
		}
		return blnValidChoice;
	}

}
