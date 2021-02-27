import java.io.Serializable;

/**
* Class VolunteerData<br>
 * Abstract: This class serves as a structure to store volunteer data
 * @author Brandon Wernke
 * @version 1
 * @since 02/21/2021
 */
public class VolunteerData implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String strFirstName;
	private String strLastName;
	private String strCity;
	private String strState;
	private String strEvent;
	
	/**
	 * constructor VolunteerData<br>
	 * Abstract: default constructor
	 */
	VolunteerData()
	{
		
	}
	
	/** 
	 * parameterized constructor VolunteerData<br>
	 * Abstract: parameterized constructor 
	 * @param strFirstName first name of volunteer
	 * @param strLastName last name of volunteer
	 * @param strCity city of volunteer
	 * @param strState state of volunteer
	 * @param strEvent event volunteer volunteers for
	 */
	VolunteerData( String strFirstName, String strLastName, String strCity, String strState, String strEvent )
	{
		this.strFirstName = strFirstName;
		this.strLastName = strLastName;
		this.strCity = strCity;
		this.strState = strState;
		this.strEvent = strEvent;
	}
	

	/**
	 * method toString<br>
	 * Abstract: overrides "toString" method to print the contents of the object
	 */
	@Override
	public String toString() {
		return String.format( "%-15s %-15s %-12s %-7s %-10s", strFirstName, strLastName, strCity, strState, strEvent );
	}
}
