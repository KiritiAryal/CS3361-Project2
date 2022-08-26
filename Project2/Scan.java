/**

 * @author Bibek Dhungana, Kiriti Aryal, Arogya Bhatta
 * @version 1.0
 * @since April 10, 2022
 * This class is implementation of the scanner.This class was used from previous project
 */



//importing all the required library
import java.io.*;

public class Scan{
	
	//instance variable
    private String[] tokens = new String[100]; 
    private String[] valOFTOKENS = new String[100]; 
    private int amountTokens = 0; 

    
    //constructor
    public Scan(String fileName)
    {
        readFromFile(fileName);
    }

    /**
	 * Name: readFile
	 * @param String
	 * @return void
	 * This method read the file content
	 */
    public void readFromFile(String file)
    {
        try
        {
            BufferedReader inputStream = new BufferedReader(new FileReader(file));
            int charNum = 0;
            charNum = inputStream.read();

            while(charNum != -1) 
            {
                if (charNum == 32 || charNum == 10 || charNum == 9){ 
                    while (charNum == 32 || charNum == 10 || charNum == 9){
                        charNum = inputStream.read();
                    }
                }
                checkChar(charNum, inputStream);
                charNum = inputStream.read();
            }
            inputStream.close();
        }
        catch(FileNotFoundException e)
        {
            e.getMessage();
        }
        catch(IOException e)
        {
            e.getMessage();
        }
    }
    
    /**
	 * Name: checkChar
	 * @param String
	 * @return void
	 * This method is used to check character
	 */
    public void checkChar(int charNum, BufferedReader stream) throws IOException
    {
        char currentChar = (char)charNum;

        switch(currentChar)
        {
            case '(':
            	tokens[amountTokens]= "lparen";
            	valOFTOKENS[amountTokens]= "(";
            	amountTokens++;
            	break;
            case ')':
            	tokens[amountTokens]= "rparen";
            	valOFTOKENS[amountTokens]= ")";
            	amountTokens++;
            	break;
            case '+':
            	tokens[amountTokens]= "plus";
            	valOFTOKENS[amountTokens]= "+";
            	amountTokens++;
            	break;
            case '-':
            	tokens[amountTokens]= "minus";
            	valOFTOKENS[amountTokens]= "-";
            	amountTokens++;
            	break;
            case '*':
            	tokens[amountTokens]= "times";
            	valOFTOKENS[amountTokens]= "*";
            	amountTokens++;
            	break;
            case ':':
                currentChar = (char)stream.read();
                if(currentChar == '=')
                {
                	tokens[amountTokens] = "assign";
                	valOFTOKENS[amountTokens]= ":=";
                	amountTokens++;
                	break;
                }
                	
                else
                {
                	System.out.println("error in :");
                    System.exit(0);
                }
                    
            case '/':
                currentChar = (char)stream.read();
                if(currentChar == '/')
                {
                    stream.readLine();
                    checkChar(stream.read(), stream);
                    break;
                }
                else if(currentChar == '*')
                {
                    char previous = currentChar;
                    currentChar = (char)stream.read();

                    String prevChar = new String(Character.toString(previous));
                    String curChar = new String(Character.toString(currentChar));

                    while (!prevChar.concat(curChar).equals("*/")){
                        previous = currentChar;
                        currentChar = (char)stream.read();

                        prevChar = new String(Character.toString(previous));
                        curChar = new String(Character.toString(currentChar));
                    }
                    checkChar(stream.read(), stream);
                    break;
                }
                else
                {
                    tokens[amountTokens]= "div";
                    valOFTOKENS[amountTokens]= "/";
                    amountTokens++;
                    break;
                }
            case '.':
                char quickRead = (char)stream.read();
                if(Character.isDigit(quickRead))
                {
                	String s = ".";
                    while(Character.isDigit(quickRead)){
                        s = s + Character.toString(quickRead);
                        quickRead = (char)stream.read();
                    }
                    tokens[amountTokens]= "number";
                    valOFTOKENS[amountTokens] = s;
                    
                    amountTokens++;
                    checkChar(quickRead, stream);
                    break;
                }
                else
                    System.out.println("error in . digit");
                    System.exit(0);
            default:
                String temp = new String ();
                String f;
                if (Character.isDigit(currentChar)){
                	f = Character.toString(currentChar);
                    char next = (char)stream.read();
                    while(Character.isDigit(next) || next == '.') {
                    	f = f + Character.toString(next);
                        next = (char)stream.read();
                    }
                    tokens[amountTokens]= "number";
                    valOFTOKENS[amountTokens] = f;
                    amountTokens++;
                    checkChar(next, stream);
                    break;
                }
                else if(Character.isAlphabetic(currentChar)){
                    temp = temp.concat(Character.toString(currentChar));
                    currentChar = (char)stream.read();
                    while (Character.isAlphabetic(currentChar) || Character.isDigit(currentChar)){
                        temp = temp.concat(Character.toString(currentChar));
                        currentChar = (char)stream.read();
                    }
                    if (temp.equals("write"))
                    {
                    	tokens[amountTokens]= "write";
                    	valOFTOKENS[amountTokens] = temp;
                    	amountTokens++;
                    	checkChar(currentChar, stream);
                    	break;
                    }
                        
                    else if(temp.equals("read"))
                    {
                    	tokens[amountTokens] = "read";
                    	valOFTOKENS[amountTokens] = temp;
                    	amountTokens++;
                    	checkChar(currentChar, stream);
                    	break;
                    }
                        
                    else
                    {
                    	tokens[amountTokens] = "id";
                    	valOFTOKENS[amountTokens] = temp;
                    	amountTokens++;
                    	checkChar(currentChar, stream);
                    	break;
                    }                   
                }
        }
    }
    
    /**
   	 * Name: getTokens
   	 * @param String
   	 * @return void
   	 * This method is used to get String[] of the tokens
   	 */
    public String[] getTokens()
    {
    	String[] arrayTokens = new String[100];
    	
    	for(int x = 0; x < tokens.length; x++)
    		arrayTokens[x] = tokens[x];
    	
    	
    	return arrayTokens;
    }
    
    
    /**
   	 * Name: getValTokens
   	 * @param String
   	 * @return void
   	 * This method is used to check character
   	 */
    public String[] getValTokens()
    {
    	String[] aryTokens2 = new String[100];
    	
    	for(int x = 0; x < valOFTOKENS.length; x++)
    		aryTokens2[x] = valOFTOKENS[x];
    	
    	
    	return aryTokens2;	
    }
    
    /**
   	 * Name: getHowManyTokens
   	 * @param String
   	 * @return void
   	 * This method is used to many tokens
   	 */
    public int getHowManyTokens()
    {
    	return amountTokens;	
    }
    
    /**
   	 * Name: toString
   	 * @param String
   	 * @return void
   	 * This method is used to return the string
   	 */
    public String toString()
    {
        String finalTokensResult = "(";
        for(int i = 0; i < amountTokens; i++) 
        {
        	if(i == amountTokens - 1)
        	{
        		finalTokensResult = finalTokensResult + tokens[i] + ")";
        	}
        	else
        		finalTokensResult = finalTokensResult + tokens[i] + ", ";
        }
        return finalTokensResult;
    }
}




