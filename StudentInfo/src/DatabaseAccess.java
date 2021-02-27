//import for sql Connection, DriverManager, ResultSet, Statement
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Class DatabaseAccess<bR>
 * Abstract: This will access databases located in the "Database" folder of the application.
 * 			This particular class has public static methods to be used throughout the program.
 * 			Namely, to load the Student and Major Data from the "StudentInfoSys" MS Access database.
 * @author Brandon Wernke
 * @version 1
 * @since 02/15/2021
 */
public class DatabaseAccess
{
	// define the Connection
	private static Connection m_conAdministrator;

	/**
	 * method LoadStudentsFromDatabase<br>
	 * Abstract: This will load the list from the table.
	 * @param strTable the table to load
	 * @param strOrderBy how to oder the result set
	 * @throws NullPointerException thrown when values passed are null
	 * @throws IndexOutOfBoundsException thrown when resultset cannot find a column
	 * @return blnResult - was loading the table successful?
	 */
	public static boolean LoadStudentsFromDatabase( String strTable, String strOrderBy )
			throws NullPointerException, IndexOutOfBoundsException
	{	// set flag to false
		boolean blnResult = false;
		try{
			if( strTable.isEmpty( ) || strOrderBy.isEmpty( ) ) throw new NullPointerException();
			String strSelect = "";
			Statement sqlCommand = null;
			ResultSet rstTSource = null;
			int intID = 0;
			String strFirstName, strLastName, strPhoneNumber, strEmail, strMajor = "";
			// Build the SQL string
			strSelect = "SELECT * FROM " + strTable + " ORDER BY " + strOrderBy;
			// Retrieve the all the records
			sqlCommand = m_conAdministrator.createStatement( );
			rstTSource = sqlCommand.executeQuery( strSelect );
			System.out.println( );
			System.out.println( "The Students table contains:" );
			System.out.printf( "--------------------------------------------------------------------------------------------------------------------------------------------------\n" );
			// Loop through all the records
			while ( rstTSource.next( ) == true ){
				// Get ID and Name from current row
				intID = rstTSource.getInt( 1 );
				strFirstName = rstTSource.getString( 2 );
				strLastName = rstTSource.getString( 3 );
				strPhoneNumber = rstTSource.getString( 4 );
				strEmail = rstTSource.getString( 5 );
				strMajor = rstTSource.getString( 6 );
				// Print the table
				System.out.printf( "Student Contact ID: %2d | First name: %-8s | Last Name: %-8s | "
						+ "Phone: %-13s | Email: %-30s | Major: %3s   \n", intID, strFirstName, strLastName, strPhoneNumber, strEmail, strMajor);
				System.out.printf( "--------------------------------------------------------------------------------------------------------------------------------------------------\n" );
			}
			// Clean up
			rstTSource.close( );
			sqlCommand.close( );
			// Success
			blnResult = true;
		} catch ( NullPointerException e ){
			System.out.println( "The value passed is NULL – data needs to be fixed. Please contact level 1 Support for Project CS." );
		} catch ( SQLException e ){
			if( e.getMessage( ).contains( "Column not found" ) == true ) throw new IndexOutOfBoundsException(); // throw out of bounds
		} finally {
			System.out.println( "Processing Complete by Student Info" );
		}
		return blnResult;
	}

	
	
	/**
	 * method LoadMajorsFromDatabase<br>
	 * Abstract: This will load the Majors from the table.
	 * @param strTable the table to load
	 * @param strOrderBy how to oder the result set
	 * @throws NullPointerException thrown when values passed are null
	 * @throws IndexOutOfBoundsException thrown when resultset cannot find a column
	 * @return blnResult - was loading the table successful?
	 */
	public static boolean LoadMajorsFromDatabase( String strTable, String strOrderBy )
			throws NullPointerException, IndexOutOfBoundsException 
	{
		// set flag to false
		boolean blnResult = false;
		try{
			if( strTable.isEmpty( ) || strOrderBy.isEmpty( ) ) throw new NullPointerException();
			String strSelect = "";
			Statement sqlCommand = null;
			ResultSet rstTSource = null;
			int intID = 0;
			String strMajorCode = "";
			String strDesc = "";
			String strExtendedDesc = "";
			// Build the SQL string
			strSelect = "SELECT * FROM " + strTable + " ORDER BY " + strOrderBy;
			// Retrieve the all the records
			sqlCommand = m_conAdministrator.createStatement( );
			rstTSource = sqlCommand.executeQuery( strSelect );
			System.out.println( );
			System.out.println( "The Majors table contains:" );
			System.out.printf( "--------------------------------------------------------------------------------------------------------------------------------------------------\n" );
			// Loop through all the records
			while ( rstTSource.next( ) == true ){
				// Get ID and Name from current row
				intID = rstTSource.getInt( 1 );
				strMajorCode = rstTSource.getString( 2 );
				strDesc = rstTSource.getString( 3 );
				strExtendedDesc = rstTSource.getString( 4 );
				
				// Print the table
				System.out.printf( "Major ID: %2d | Major Code: %3s | Short Description: %-20s | "
						+ "Extended Description: %-40s    \n", intID, strMajorCode, strDesc, strExtendedDesc );
				System.out.printf( "--------------------------------------------------------------------------------------------------------------------------------------------------\n" );
			}
			// Clean up
			rstTSource.close( );
			sqlCommand.close( );
			// Success
			blnResult = true;
		} catch ( NullPointerException e ) {
			System.out.println( "The value passed is NULL – data needs to be fixed. Please contact level 1 Support for Project CS." );
		} catch ( SQLException e ) {
			if( e.getMessage( ).contains( "Column not found" ) == true ) throw new IndexOutOfBoundsException(); // throw out of bounds
		} finally {
			System.out.println( "Processing Complete by Student Info" );
		}
		return blnResult;
	}
	
	
	
	/**
	 * method name: OpenDatabaseConnectionMSAccess <br>
	 * Abstract: The opens the database connection 
	 * @param strDbName the database to open in the "Database" directory
	 * @return blnResult - did we open the connection?
	 */
	public static boolean OpenDatabaseConnectionMSAccess( String strDbName )
	{
		boolean blnResult = false;

		try
		{
			String strConnectionString = "";

			// Server name/port, IP address/port or path for file based DB like MS Access
			// System.getProperty( "user.dir" ) => Current working directory from where
			// application was started
			strConnectionString = "jdbc:ucanaccess://" + System.getProperty( "user.dir" ) + "\\Database\\" + strDbName;
			// Open a connection to the database
			m_conAdministrator = DriverManager.getConnection( strConnectionString );
			// Success
			blnResult = true;
			System.out.println( "Database Connection Established\n" );
		} 
		catch ( Exception FileNotFoundException )
		{
			System.out.println(	"Database connection issue in method 'OpenDatabaseConnectionMSAccess'. Please contact Support for Project CS." );
		}
		return blnResult;
	}
	
	

	/**
	 * Name: CloseDatabaseConnection <br>
	 * Abstract: Close the connection to the database
	 * @return blnResult - did we close the connection?
	 */
	public static boolean CloseDatabaseConnection()
	{
		boolean blnResult = false;

		try
		{
			// Is there a connection object?
			if ( m_conAdministrator != null )
			{
				// Yes, close the connection if not closed already
				if ( m_conAdministrator.isClosed( ) == false )
				{
					m_conAdministrator.close( );

					// Prevent JVM from crashing
					m_conAdministrator = null;
				}
			}
			// Success
			blnResult = true;
		} catch ( Exception excError )
		{
			// Display Error Message
			System.out.println( excError );
		}

		return blnResult;
	}

}