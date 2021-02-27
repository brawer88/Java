/**
 * Create BankAccountInClass thread using Runnable
 * 
 * @author Tomie Gartland
 *
 */
public class BankAccountInClass implements Runnable
{
	// create a new Account
	private Account acct = new Account( );

	// create a main method that will do the following:
	public static void main( String[ ] args )
	{

		BankAccountInClass r = new BankAccountInClass( );

		Thread one = new Thread( r );

		Thread two = new Thread( r );

		one.setName( "Jade" );

		two.setName( "Rocket" );

		one.start( );

		two.start( );

	}

	/**
	 * run method to test the withdrawal process. It will execute makeWithdrawal 5x
	 * for 100 each. If the acccount balance is less than zero, it notify of the
	 * account being overdrawn.
	 */
	@Override
	public void run()
	{		
		for ( int x = 0; x < 5; x++ )
		{
			
			makeWithdrawal( 100 );
			if ( acct.getBalance( ) < 0 )
			{
				System.out.println( "account is overdrawn!" );
			}
			try
			{
				Thread.sleep( 1000 );
			} catch ( InterruptedException e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		}

	}

	/**
	 * makeWithdrawal method to take money from the account
	 */
	//private synchronized void makeWithdrawal( int amt ) {
	private synchronized void makeWithdrawal( int amt )
	{
		if ( acct.getBalance( ) >= amt )
		{
			System.out.println( Thread.currentThread( ).getName( ) + " is going to withdraw" );
			try
			{
				Thread.sleep( 100 );
			} catch ( InterruptedException ex )
			{
			}
			acct.withdraw( amt );
			System.out.println( Thread.currentThread( ).getName( ) + " completes the withdrawal" );
		} else
		{
			System.out.println( "Not enough in account for " + Thread.currentThread( ).getName( ) + " to withdraw "
					+ acct.getBalance( ) );
		}


	}
}

/**
 * Class Account to create the bank account and methods in the bank account
 * 
 */
class Account
{
	private int balance = 500;

	public int getBalance()
	{
		return balance;
	}

	public void withdraw( int amount )
	{
		balance = balance - amount;
	}
	
}
