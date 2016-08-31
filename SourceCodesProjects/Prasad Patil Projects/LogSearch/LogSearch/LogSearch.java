package exer;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.TreeMap;

public class LogSearch {
	
	//User input is split from space character and stored into a string array.
	private String[] userInputSplits;
	//Start time/end time is split from ":" character to separate minute and hours.
	private String[] startTime;
	private String[] endTime;
	//IP address is split from "." character to access each quadrant separately.
	private String[] ipSplits;
	//Date is split by "-" character to access day month and year separately.
	private String[] dateSplit;
	private String[] dateSplit2;
	//Time variable stored as integers for comparison and count.
	private int startTimeHrs;
	private int startTimeMin;
	private int endTimeHrs;
	private int endTimeMin;
	private DateFormat dateFormat;
	//Scanners declared to accept user input.
	private Scanner in;
	private Scanner in2;
	//Unix date is stored in long.
	private long unixDate;
	
	//Function to print the results based on the user's query.
	public void printLogInfo(TreeMap<String, String> hlog, String[] userInputSplit) throws ParseException 
	{
		// TODO Auto-generated method stub
		startTime=userInputSplit[3].split(":");
	    endTime=userInputSplit[5].split(":");
	    
	    startTimeHrs=Integer.parseInt(startTime[0]);
	    startTimeMin=Integer.parseInt(startTime[1]);
	    endTimeHrs=Integer.parseInt(endTime[0]);
	    endTimeMin=Integer.parseInt(endTime[1]);
	    
	    int i=0,j=0,llimit=0,hlimit=0;
	    for(i=startTimeHrs;i<=endTimeHrs;i++)
	    {
	    	//Takes care of boundary conditions.
	    	if(i==startTimeHrs)llimit=startTimeMin;
	    	else llimit=0;
	    	if(i==endTimeHrs)hlimit=endTimeMin;
	    	else hlimit=60;
	    	
	    	for(j=llimit;j<=hlimit;j++)
	    	{
	    		//In tree map: key=unixtime + ip + cpuid || value=cpu usage.
	    		unixDate=getUnixDate(userInputSplit[2]+" "+String.valueOf(i)+":"+String.valueOf(j));
	    		String keyStr=String.valueOf(unixDate)+userInputSplit[0]+userInputSplit[1];
	    		System.out.println("On "+userInputSplit[2]+" at "+i+":"+j+" CPU usage: "+hlog.get(keyStr)+"%");
	    	}
	    }
		
	}
	
	//Function to validate user input
	public boolean validateUserInput(String userInput) 
	{
		// TODO Auto-generated method stub
		try{
			userInputSplits=userInput.split(" ");
		
			//Check for valid number of arguments.
			if(userInputSplits.length!=6)
			{
				System.out.println("Enter valid number of arguments");
				return false;
			}
			ipSplits=userInputSplits[0].split("\\.");
			
			//Check if IP is entered in correct format.
			if(ipSplits.length!=4 || ipSplits.equals(null))
			{
				System.out.println("Invalid IP format. Please enter in x.x.x.x format");
				return false;
			}
				
			
			//Check for IP address range. Check if it falls between 198.162.0.0 to 198.162.3.231 i.e 1000 IPs
			if(Integer.valueOf(ipSplits[0])!=198 || Integer.valueOf(ipSplits[1])!=162 || Integer.valueOf(ipSplits[2])>3 || Integer.valueOf(ipSplits[2])<0)
				{System.out.println("Enter valid range for IPs (198.162.0.0 to 198.162.3.231)");return false;}
			if(Integer.valueOf(ipSplits[2])==3 && (Integer.valueOf(ipSplits[3])>231 ||Integer.valueOf(ipSplits[3])<0))
				{System.out.println("Enter valid range for IPs (198.162.0.0 to 198.162.3.231)");return false;}
			if(Integer.valueOf(ipSplits[3])>255 || Integer.valueOf(ipSplits[3])<0)
				{System.out.println("Enter valid range for IPs (198.162.0.0 to 198.162.3.231)");return false;}
			
			//CPU ID Validations. Check if its either 0 or 1.
			if(Integer.valueOf(userInputSplits[1])!=1 && Integer.valueOf(userInputSplits[1])!=0)
				{System.out.println("CPU ID should be either 0 or 1");return false;}
			
			//Time Validations
			String[] timeSplit=userInputSplits[3].split(":");
			if(timeSplit.length!=2 || timeSplit.equals(null))
				{System.out.println("Enter time in <hh:mm> format");return false;}
			if(Integer.valueOf(timeSplit[0])==24 && Integer.valueOf(timeSplit[1])>0)
				{System.out.println("Last logged time is 24:00");return false;}
			if(Integer.valueOf(timeSplit[0])>24 || Integer.valueOf(timeSplit[1])>60 ||Integer.valueOf(timeSplit[0])<0 || Integer.valueOf(timeSplit[1])<0 )
				{System.out.println("Enter time within valid range (hh:mm)(0-24 : 0-60)");return false;}
			
			String[] timeSplit2=userInputSplits[5].split(":");
			if(timeSplit2.length!=2 || timeSplit.equals(null))
				{System.out.println("Enter time in <hh:mm> format");return false;}
			if(Integer.valueOf(timeSplit2[0])==24 && Integer.valueOf(timeSplit2[1])>0)
				{System.out.println("Last logged time is 24:00");return false;}
			if(Integer.valueOf(timeSplit2[0])>24 || Integer.valueOf(timeSplit2[1])>60 ||Integer.valueOf(timeSplit2[0])<0 || Integer.valueOf(timeSplit2[1])<0 )
				{System.out.println("Enter time within valid range (hh:mm)(0-24 : 0-60)");return false;}
			
			if(Integer.valueOf(timeSplit[0])>Integer.valueOf(timeSplit2[0]))
				{System.out.println("<hh1:mm1> should be less than <hh2:mm2> for a 24 hours clock.");return false;}
			
			//Date validations. Check if date is not equal to 2016-07-09.
			dateSplit=userInputSplits[2].split("-");
			dateSplit2=userInputSplits[4].split("-");
			if(dateSplit.length!=3 || dateSplit.equals(null) || dateSplit2.length!=3 || dateSplit2.equals(null))
				{System.out.println("Enter date in <yyyy-mm-dd> format"); return false;}
			if(Integer.valueOf(dateSplit[0])!=2016 || Integer.valueOf(dateSplit[1])!=07 || Integer.valueOf(dateSplit[2])!=9
				||Integer.valueOf(dateSplit2[0])!=2016 || Integer.valueOf(dateSplit2[1])!=07 || Integer.valueOf(dateSplit2[2])!=9)
				{System.out.println("Log is valid only for date 2016-07-09"); return false;}
		}
		catch (NumberFormatException n)
		{
			System.out.println("Invalid input format.");
			return false;
		}
		return true;
	}
	
	//Function to get inputs from the user
	public String getUserInput()
	{
		in = new Scanner(System.in);
	    System.out.println("Enter IP address, CPU ID and time in the format: <ip> <cpuid> <yyyy-mm-dd> <hh:mm> <yyyy-mm-dd> <hh:mm>");
		return in.nextLine();
	}
	
	//Function to convert time entered by the user to Unix time.
	public long getUnixDate(String d) throws ParseException
	{
	    dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		java.util.Date date = dateFormat.parse(d);
		return (long)date.getTime()/1000;
		
	}
	
	//Function to load log data from the file into the program.
	public TreeMap<String, String> loadData(TreeMap<String, String> hlog, String filename)
	{
		Scanner sc2 = null;
		int count=0;
	    try 
	    {
	        sc2 = new Scanner(new File(filename));
	    } 
	    catch (FileNotFoundException e) 
	    {
	        e.printStackTrace();  
	    }
	    sc2.nextLine();
	    System.out.println("Initializing..Please Wait! May take 30 sec. to 2 min. 30 sec.");
	    //Read the log file until end of the file.
	    while (sc2.hasNextLine()) 
	    {
	        in2 = new Scanner(sc2.nextLine());
	        StringBuffer key = new StringBuffer();
	        while (in2.hasNext()) 
	        {
	        	//"key" declared here is key stored in TreeMao which is read from the file.
	        	key=key.append(in2.next());
	        	count++;
	        	if(count==3)
	        	{
	        		//"value" declared here is value stored in TreeMap which is CPU usage read from the file.
	        		String value=in2.next();
	        		//key value pair is written into TreeMap.
	        		hlog.put(key.toString(),value);
	        		break;
	        	}
	        }
	        //Count here keeps the track of how many column entries are to be stored as a single key
	        //Resets to zero when count = 3 i.e three column entries (Time stamp, CPU id and IP address) as one key.
	        count=0;
	    }
		return hlog;
		
	}

}
