package exer;
import java.text.ParseException;
import java.util.Scanner;
import java.util.TreeMap;

public class LogQueryTool 
{
	
	public static void main(String[] args) throws ParseException 
	{
		// TODO Auto-generated method stub
		//Declare object of type LogSearch which has all the methods implemented for whole log search functionality.
		LogSearch LS= new LogSearch();
	
		TreeMap <String, String> hlog = new TreeMap <String, String>();
		Scanner in;
		
		//Load all the data from the file into a tree map.
		hlog=LS.loadData(hlog,args[0]);
		

		while(true)
		{
			//Get input from the user.
			String userInput=LS.getUserInput();
			
			//Proceed if user input is valid else get the user input again (go back to previous step).
			if(LS.validateUserInput(userInput))
			{
				//Split the user input from space character.
				String[] userInputSplit=userInput.split(" ");
				
				//Print the cpu usage if user inputs are valid.
				LS.printLogInfo(hlog,userInputSplit);
				
				//While loop to continue asking user if he wants to continue or quit.
			    while(true)
			    {
				    System.out.println("Do you wish to continue (Y/N)?");
				    in = new Scanner(System.in);
				    String choice=in.nextLine();
				    
				    //Check user's response.
				    //Continue if choice is yes.
				    if(choice.equalsIgnoreCase("Y"))break;
				    //Terminate the program if choice is no.
				    else if (choice.equalsIgnoreCase("N"))
				    {
				    	System.out.println("Program terminated.");
				    	in.close();
				    	System.exit(0);
				    }
				    //If input in invalid
				    else System.out.println("Enter a valid choice Y/N.");
			    }
			}
		}
	}	
}
