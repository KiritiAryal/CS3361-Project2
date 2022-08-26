/**
 * @author  Bibek Dhungana, Kiriti Aryal, Arogya Bhatta
 * @version 1.0
 * @since April 10, 2022
 * This is Main class of the project(starting point)
 */


public class Main
{
	public static void main(String[] args)
	{	
		//creating object of Scan class. So, the input file must be passed as 
		Scan scan = new Scan(args[0]);
		
		//invoking the parser Constructor
		new Parser(scan.getTokens(), scan.getValTokens(), scan.getHowManyTokens());
	}
}