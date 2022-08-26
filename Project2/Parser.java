/**
 * @author Bibek Dhungana, Kiriti Aryal, Arogya Bhatta
 * @version 1.0
 * @since April 10, 2022
 * This class is implementation of the parser.
 * 
 */

public class Parser{
	
	//instance variables
	private String allTokensGiven[] = new String[500];
	private String valueTokensResult[] = new String[500];
	private int inposition = 0;
	
	
	//constructor
	public Parser(String[] tokens, String[] values, int theAmount)
	{
		for(int x = 0; x < theAmount; x++)
		{
			allTokensGiven[x] = tokens[x];
			valueTokensResult[x] = values[x];
		}
			
		allTokensGiven[theAmount] = "$$";
		valueTokensResult[theAmount] = "$$";
		
		String input = "";
		program(input);   
	}
	
	
	/**
	 * @param input String
	 * @return void 
	 */
	public void program(String input)
	{
		switch(allTokensGiven[inposition])
		{
		case "id":
		case "read":
		case "write":
			System.out.println(input + "<Program>");
			stmt_list(input + "  ");
		case "$$":
			match(allTokensGiven[inposition], input, inposition); 
			System.out.println(input + "</Program>");
		}
	}
	
	
	/**
	 * @param input String
	 * @return void
	 */
	public void stmt_list(String input)
	{	
		switch(allTokensGiven[inposition])
		{
		case "id":
		case "read":
		case "write":
			System.out.println(input + "<Stmt_List>");
			stmt(input + "  ");
			stmt_list(input + "  ");
			System.out.println(input + "</Stmt_List>");
			break;
		case "$$":
			break;
		default:
            System.out.println("Error is encountered");
            System.exit(0);
		}
	}
	
	
	/**
	 * 
	 * @param input
	 * @return void
	 */
	public void stmt(String input)
	{
		switch(allTokensGiven[inposition])
		{
		case "id":
			System.out.println(input + "<Stmt>");
			match(allTokensGiven[inposition], input + "  ", inposition);
			if(!allTokensGiven[inposition].equals("assign"))
			{
				System.out.println("ERROR");
				System.exit(0);
			}
			match(allTokensGiven[inposition], input + "  ", inposition);
			expr(input + "  ");
			System.out.println(input + "</Stmt>");
			break;
		case "read":
			System.out.println(input + "<Stmt>");
			match(allTokensGiven[inposition], input + "  ", inposition);
			if(!allTokensGiven[inposition].equals("id"))
			{
				System.out.println("ERROR");
				System.exit(0);
			}
			match(allTokensGiven[inposition], input + "  ", inposition);
			System.out.println(input + "</Stmt>");
			break;
		case "write":
			System.out.println(input + "<Stmt>");
			match(allTokensGiven[inposition], input + "  ", inposition);
			expr(input + "  ");
			System.out.println(input + "</Stmt>");
			break;
		default:
            System.out.println("Error");
            System.exit(0);
		}
	}
	
	
	/**
	 * 
	 * @param input
	 * @return void
	 */
	public void expr(String input)
	{
		switch(allTokensGiven[inposition])
		{
		case "id":
		case "number":
		case "lparen":
			System.out.println(input + "<Expr>");
			term(input + "  ");
			term_tail(input + "  ");
			System.out.println(input + "</Expr>");
			break;
		default:
            System.out.println("Error is encountered");
            System.exit(0);
		}
		
	}
	
	/**
	 * 
	 * @param input
	 * @return void
	 */
	public void term(String input)
	{
		switch(allTokensGiven[inposition])
		{
		case "id":
		case "number":
		case "lparen":
			System.out.println(input + "<Term>");
			factor(input + "  ");
			fact_tail(input + "  ");
			System.out.println(input + "</Term>");
			break;
		default:
            System.out.println("Error");
            System.exit(0);
		}
	}
	
	
	/**
	 * 
	 * @param input
	 * @return void
	 */
	public void factor(String input)
	{
		
		switch(allTokensGiven[inposition])
		{
		case "id":
			System.out.println(input + "<Factor>");
			match(allTokensGiven[inposition], input + "  ", inposition);
			System.out.println(input + "</Factor>");
			break;
		case "number":
			System.out.println(input + "<Factor>");
			match(allTokensGiven[inposition], input + "  ", inposition);
			System.out.println(input + "</Factor>");
			break;
		case "lparen":
			System.out.println(input + "<Factor>");
			match(allTokensGiven[inposition], input + "  ", inposition);
			expr(input + "  ");
			match(allTokensGiven[inposition], input + "  ", inposition);
			System.out.println(input + "</Factor>");
			break;
		default:
            System.out.println("Error");
            System.exit(0);
		}
	}
	
	
	/**
	 * 
	 * @param input
	 * @return void
	 */
	public void fact_tail(String input)
	{
		
		switch(allTokensGiven[inposition])
		{
		case "plus":
		case "minus":
		case "rparen":
		case "id":
		case "read":
		case "write":
		case "$$":
			break;
		case "times":
		case "div":
			System.out.println(input + "<Fact_Tail>");
			mult_op(input + "  ");
			factor(input + "  ");
			fact_tail(input + "  ");
			System.out.println(input + "</Fact_Tail>");
			break;
		default:
            System.out.println("Error");
            System.exit(0);
		}
		
	}
	
	
	/**
	 * 
	 * @param input
	 * @void
	 */
	
	public void term_tail(String input)
	{
		
		switch(allTokensGiven[inposition])
		{
		case "rparen":
		case "id":
		case "read":
		case "write":
		case "$$":
			break;
		case "plus":
		case "minus":
			System.out.println(input + "<Term_Tail>");
			add_op(input + "  ");
			term(input + "  ");
			term_tail(input + "  ");
			System.out.println(input +"</Term_Tail>");
			break;
		default:
            System.out.println("Error");
            System.exit(0);
		}
		
	}
	
	
	/**
	 * 
	 * @param input
	 * @return void
	 */
	public void add_op(String input)
	{
		switch(allTokensGiven[inposition])
		{
		case "plus":
		case "minus":
			System.out.println(input + "<Add_Op>");
			match(allTokensGiven[inposition], input + "  ", inposition);
			System.out.println(input + "</Add_Op>");
			break;
		default:
            System.out.println("Error");
            System.exit(0);
		}
	}
	
	
	/**
	 * 
	 * @param input
	 * @return void
	 */
	public void mult_op(String input)
	{
		switch(allTokensGiven[inposition])
		{
		case "times":
		case "div":
			System.out.println(input + "<Mult_Op>");
			match(allTokensGiven[inposition], input + "  ", inposition);
			System.out.println(input + "</Mult_Op>");
			break;
		default:
            System.out.println("Error");
            System.exit(0);
		}
	}
	
	
	
	/**
	 * 
	 * @param matcher
	 * @param input
	 * @param index
	 * @return void 
	 * This method use switch case to match the operation or id.
	 */
	public void match(String matcher, String input, int index)
	{
		switch (matcher) 
		{
			case "id":
				System.out.println(input + "<id>");
				System.out.println(input + "  " + valueTokensResult[index]);
				System.out.println(input + "</id>");
				inposition++;
				break;
			case "assign":
				System.out.println(input + "<assign>");
				System.out.println(input + "  " + valueTokensResult[index]);
				System.out.println(input + "</assign>");
				inposition++;
				break;
			case "read":
				System.out.println(input + "<read>");
				System.out.println(input + "  " + valueTokensResult[index]);
				System.out.println(input + "</read>");
				inposition++;
				break;
			case "write":
				System.out.println(input + "<write>");
				System.out.println(input + "  " + valueTokensResult[index]);
				System.out.println(input + "</write>");
				inposition++;
				break;
			case "lparen":
				System.out.println(input + "<lparen>");
				System.out.println(input + "  " + valueTokensResult[index]);
				System.out.println(input + "</lparen>");
				inposition++;
				break;
			case "rparen":
				System.out.println(input + "<rparen>");
				System.out.println(input + "  " + valueTokensResult[index]);
				System.out.println(input + "</rparen>");
				inposition++;
				break;
			case "number":
				System.out.println(input + "<number>");
				System.out.println(input + "  " + valueTokensResult[index]);
				System.out.println(input + "</number>");
				inposition++;
				break;
			case "plus":
				System.out.println(input + "<plus>");
				System.out.println(input + "  " + valueTokensResult[index]);
				System.out.println(input + "</plus>");
				inposition++;
				break;
			case "minus":
				System.out.println(input + "<minus>");
				System.out.println(input + "  " + valueTokensResult[index]);
				System.out.println(input + "</minus>");
				inposition++;
				break;
			case "times":
				System.out.println(input + "<times>");
				System.out.println(input + "  " + valueTokensResult[index]);
				System.out.println(input + "</times>");
				inposition++;
				break;
			case "div":
				System.out.println(input + "<div>");
				System.out.println(input + "  " + valueTokensResult[index]);
				System.out.println(input + "</div>");
				inposition++;
				break;
			case "$$":
				break;
		}
		
		
	}
}