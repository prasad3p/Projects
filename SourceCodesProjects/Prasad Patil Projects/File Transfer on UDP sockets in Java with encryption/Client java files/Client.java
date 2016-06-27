package clientApplications;

import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.regex.Pattern;
 
public class Client
{
	private final static String PROTOCOL="ENTS/1.0 Request";
	//[CR+LF] is denoted here by SEPERATOR.
	private final static String SEPERATOR="\r\n"; 
	private int timeoutCounter;
	private int timeOut;
	private String reqMsg;
	private String fileName;
	private String checkSum;
	private String serResCheckSum;
	
	
    public void goToFirstStep() throws Exception 
    {
    	//Call the function selectFileName() to accept the filename until it is in correct format 
    	while(true)
    	{
    		if(this.selectFileName()==true)
    			break;
    	}
    	
    	//Set request message to "ENTS/1.0 Request[CR+LF]Filename.extension[CR+LF]". Here [CR+LF] (\r\n) is accessed by getter method called getSeperater().
    	this.setReqMsg(this.getProtocol()+this.getSeperater()+this.getFileName()+this.getSeperater());
    	
    	//CharacterFitting() method fits two consecutive characters of a string into a single 16 bit word. And returns an array of such 16 bit words.
    	short[] J=this.characterFitting(this.getReqMsg());
    	
    	//Array of such 16 bit words is then used to generated checksum. Checksum is generated by the method call generateChecksum()
    	short CS=this.generateChecksum(J);
    	this.setCheckSum(String.valueOf(CS));
    	
    	//Request msg (reqMsg) is now appended with the checksum and [CR+LF]
    	this.setReqMsg(this.getReqMsg()+this.getCheckSum()+this.getSeperater());
    	
    	//Timer is set with initial value 1000 milliseconds.
    	this.setTimeOut(1000);
    	
    	//Timer counter is initiated with 0 to make sure that client resends the data only 4 times.
    	this.setTimeoutCounter(0);
    	
    	//Send the request message to the server 
    	this.sendReqMsg(this.getTimeOut());
	}

	private void sendReqMsg(int timeOut) throws Exception
	{
		// TODO Auto-generated method stub
    	
    	DatagramSocket DS = null;
    	InetAddress IA = InetAddress.getByName("localhost");
    	DS = new DatagramSocket();
    	byte[] b = this.getReqMsg().getBytes();
    	DatagramPacket  SND = new DatagramPacket(b , b.length , IA , 7006);
    	DS.send(SND);
    	System.out.println("\nAttempt "+ (this.timeoutCounter+1));
    	System.out.println("Message sent to the server is:\n"+this.reqMsg);
    	try
        {
    			
    			
                DS.setSoTimeout(timeOut);
                System.out.println("Waiting for server's response..\n");
                //Creating buffer to receive incoming data	
                byte[] B = new byte[8000];
                DatagramPacket reply = new DatagramPacket(B, B.length);
                
                // Receive server's reply
                DS.receive(reply);
                byte[] data=reply.getData();
                System.out.println("Response received from the server.\n");
                
                //Convert the byte received into a single string
                String serRes=new String (data,0, reply.getLength());
                
                //Reset the timer counter to 0 once the reply has been received by the client
                this.timeoutCounter=0;
               
                /*Split the response received at "[CR+LF]" i.e "\r\n". This helps to separate the received checksum from the packet received and 
                also to calculate checksum of response which is used further to verify the integrity of the message received*/
                String H[]=serRes.split("\r\n");
                
                //Combine Protocol (index 0),Response Code (index 1) and content length (index 2)
        		String D=H[0]+"\r\n"+H[1]+"\r\n"+H[2]+"\r\n";
        		
        		//Initialize the variable 'disp' that can be used further to display content of the file if response code is zero.
        		String disp="";
        		
        		//Combine all the splits but not the checksum received. 'H.length-1' specifies last but one split that excludes checksum
        		//Index 3 to last but one.
        		for(int i=3;i<H.length-1;i++)
            	{
            		D+=H[i]+"\r\n";
            		disp+=H[i]+"\r\n";

            	}
        		/*Send Protocol, Response Code, Content Length, Content to the function characterFitting() as a single string 
        		that fits two consecutive characters into a single 16 bit word. Return an array of such 16 bit words*/
        		short[] J1=this.characterFitting(D);
        		
        		//Generate checksum based on array of 16 bit words generated in the previous step
        		short CS1=this.generateChecksum(J1);
        		
        		//Save the checksum as string and not short
        		this.setSerResCheckSum((String.valueOf(CS1)));
            	
        		//Check if received checksum is equal to locally generated checksum.
                if(H[H.length-1].equals(this.getSerResCheckSum()))
                {
                	//Check if  response code is 0.
                	if(H[1].equals("0"))
                	{
                		System.out.println("COMMUNICATION SUCCESSFUL!\n");
                		System.out.println("File content is:\n"+disp);
                		//Close the socket
                		DS.close();
                		//Ask user if he wants to continue
                		this.continueQuery();
                		//Ask user to enter one more file
                		this.goToFirstStep();
                		
                	}
                	//Check if  response code is 1.
                	else if(H[1].equals("1"))
                	{
                		System.out.println("ERROR: integrity check failure. The request has one or more bit errors.");
                		//Ask user if he wants to continue
                		this.continueQuery();
                		//Close the socket
                		DS.close();
                		//Send the same request (same file name) with timer reset to 1 second (1000 ms).
                		this.sendReqMsg(1000);
                	}
                	//Check if  response code is 2.
                	else if(H[1].equals("2"))
                	{
                		System.out.println("ERROR: malformed request. The syntax of the request message is not correct.");
                		//Ask user if he wants to continue
                		this.continueQuery();
                		//Close the socket
                		DS.close();
                		//Ask user to enter another filename as the filename entered earlier is incorrect
                		this.goToFirstStep();
                	}
                	//Check if  response code is 3.
                	else if(H[1].equals("3"))
                	{
                		System.out.println("ERROR: non-existent file. The file with the requested name does not exist.");
                		//Ask user if he wants to continue
                		this.continueQuery();
                		//Close the socket
                		DS.close();
                		//Ask user to enter another filename as the filename entered does not exist in the server's database
                		this.goToFirstStep();
                	}
                	//Check if  response code is 4.
                	else
                	{
                		System.out.println("ERROR: wrong protocol version. The version number in the request is different from 1.0.");
                		//Ask user if he wants to continue
	                	this.continueQuery();
	                	//Close the socket
	                	DS.close();
	                	//Ask user to select the file name again
	                	this.goToFirstStep();
                	}
                }
                else
                {
                	System.out.println("ERROR!! Integrity check failure at client end.");
                	//Ask user if he wants to continue
                	this.continueQuery();   
                	//Close the socket
                	DS.close();
                	//Send the same request (same file name) with timer reset to 1000 seconds.
                	this.sendReqMsg(1000);
                }    
        }
        catch (InterruptedIOException e) 
    	{
        	
        	//If attempt to send the request fails then increment timer counter. 
        	this.timeoutCounter++;
        	System.out.println("No response from the server");
        	//Check if maximum number of attempts have failed. If yes then terminate the program. 
			if(this.timeoutCounter==4)
			{
				System.out.println("\nReached maximum time outs. COMMUNICATION FAILURE!!");
				this.continueQuery();
				this.goToFirstStep();
			}
			//Close the socket
			DS.close();
			
			//Multiply the timer each time by 2.
        	this.sendReqMsg(timeOut*2);
        }
	}
	
	//Definition of the function to ask user if he wants to continue after each time failure has occured.
	private void continueQuery()
	{
		Scanner sc=new Scanner(System.in);
		String choice;
		while(true)
    	{
    		System.out.println("\nDo you want to continue sending request ? (y/n):");
        	choice=sc.next();
        	if(choice.equalsIgnoreCase("y"))
        		break;
        	else if(choice.equalsIgnoreCase("n"))
        	{
        		System.out.println("\nProgram terminated!!\nThank you. Have a nice day.");
        		System.exit(1);
        	}
        	else
        		System.out.println("\nInvalid Choice! Enter either y or n");
    	}
	}
	
	//Function to generate checksum
	private short generateChecksum(short[] div) 
	{
		// TODO Auto-generated method stub
    	short S=0,index=0;
    	int C=7919;
    	int D=65536;
    	
    	for(int i=0;i<div.length;i++)
    	{
    		index=(short) (S ^ div[i]);
    		S=(short) (C*index);
    		//Modulo adjustment
    		if(S>D)
    			S-=(D+1);
    	}
    	return S;
    }

	//Function to fit two consecutive characters of a string into a single 16 bit word. Do it for the whole string.
	private short[] characterFitting(String msg) throws Exception {
		// TODO Auto-generated method stub
		//Save message length in N
    	int N=msg.length();
    	int j=0;
    	
    	//Check if message length is even
    	if(N%2==0)
    	{
    		/*if N length is even, then length of array is set to N/2. As two characters are fit into single word, 
    		 length of array will be half of the length string*/
    		N=N/2;
    		short[] div= new short [N];
    		
    		for(int i=0;i<N;i++)
        	{
    			/*To fit two consecutive characters in a single 16 bit word, left shift first character by 8 and 'or' that with the next character
    			  continue this for the length of the string*/
        		div[i]=(short) ((msg.charAt(j)<<8) | msg.charAt(j+1));	
        		
        		//Use indexing that will shift the position to access string in alternate ways (0,2,4 and so on)
        		j+=2;
        	}
    		return div;
    	}
    	else
    	{
    		//if length is odd then array length is set to N/2 + 1. As the last character has to be 'or'ed with 0.
    		N=(N/2)+1;
    		short[] div= new short [N];
    		for(int i=0;i<N;i++)
        	{
        		//Check if index has reached the last character. If yes then 'or' the last character with 0.
        		if(j==msg.length()-1)
        		{
        			div[i]=(short) ((msg.charAt(j)<<8) | 0x0000);
        			break;
        		}
        		/*To fit two consecutive characters in a single 16 bit word, left shift first character by 8 and 'or' that with the next character
  			  	continue this for the length of the string*/
        		div[i]=(short) ((msg.charAt(j)<<8) | msg.charAt(j+1));	
        		
        		//Use indexing that will shift the position to access string in alternate ways (0,2,4 and so on)
        		j+=2;
        	}
    		return div;
    	}
	}
	
	//Function to ask user to enter a file name and verify its syntax.
	private boolean selectFileName()
    {
		System.out.println("\nAvailable files are:\n1:directors_message.txt\n2:program_overview.txt\n3:scholarly_paper.txt");
    	Scanner s=new Scanner(System.in);
    	System.out.println("\nEnter the file name:");
    	String F=s.nextLine();
    	String X[]=F.split("\\.");
    	//Check the correct format of user input i.e <filename.extention>
    	if(X.length!=2)
    	{
    		System.out.println("Invalid file name.\nPlease enter the filename in <filename.extention> format");
    		return false;
    	}
    	
    	Pattern p = Pattern.compile("[^a-zA-Z0-9_]");
    	//Check if filename contains special characters, or it starts with a number or it starts with an underscore
    	if(p.matcher(X[0]).find()==true || Character.isDigit(X[0].charAt(0)) || X[0].charAt(0)=='_')
    	{
    		System.out.println("Invalid file name.\nFile name can not contain special characters or it can not start with a number.\nFilename can only be alphanumeric");
    		return false;
    	}
    	
    	Pattern p1 = Pattern.compile("[^a-zA-Z0-9]");
    	//Check if file extension contains anythings other than alphabets and numbers
    	if(p1.matcher(X[1]).find()==true)
    	{
    		System.out.println("Invalid file extension.\nFile extention can only be alphanumeric");
    		return false;
    	}
    	
    	this.setFileName(F);
    	return true;
    	
    }
	
	//Setters and getters of all the fields declared for the class.
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
    public String getProtocol() {
		return PROTOCOL;
	}

	public String getSeperater() {
		return SEPERATOR;
	}
	public String getCheckSum() {
		return checkSum;
	}

	public void setCheckSum(String checkSum) {
		this.checkSum = checkSum;
	}
	public int getTimeoutCounter() {
		return timeoutCounter;
	}

	public void setTimeoutCounter(int timeoutCounter) {
		this.timeoutCounter = timeoutCounter;
	}

	public int getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	public String getSerResCheckSum() {
		return serResCheckSum;
	}

	public void setSerResCheckSum(String serResCheckSum) {
		this.serResCheckSum = serResCheckSum;
	}

	public String getReqMsg() {
		return reqMsg;
	}

	public void setReqMsg(String reqMsg) {
		this.reqMsg = reqMsg;
	}
}
