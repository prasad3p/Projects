package exer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class LogGenerator {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		String content = "timestamp\t\tIP\t\tcpu_id \t\tusage";
		int t=1468036800;//Unix time stamp for 2016-July-09, 00:00 EST.
		int tq=0,fq=0,cpuid=0,min=0,max=100;	
		
		try 
		{				
			//args[0] is the file name passed as an input argument.
			File file = new File(args[0]);

			// if file does not exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			Random r= new Random();

			bw.write(content);
			bw.newLine();

			//Each iteration of the loop is assumed as one minute of the day.
			//So for 24 hours number of minutes will be 24*60=1440.
			//1441 is taken instead of 1440 to accommodate 00:00 of 2016-July-10.
			for(int i=1;i<=1441;i++)
			{	
				//Two consecutive rows in the log file contain log of two CPUs of each server.
				//For 1000 servers with 2 CPUs each the total number of iterations per minute will be 2000.
				for(int j=0;j<2000;j++)
				{
					//First, write unix time to the file.
					bw.write(Integer.toString(t));
					bw.write("\t\t");
					
					if(cpuid==2)
					{
						cpuid=0;
						fq++;
						if(fq==256){fq=0;tq++;}
					}
					
					//First and the second quadrant of IP address are same as we have only 1000 servers.
					//Last two quadrants are varied consecutively to accommodate 1000 servers.
					StringBuffer ip= new StringBuffer("198.162.");
					ip.append(Integer.toString(tq)+"."+Integer.toString(fq));
					
					//Second, write IP address to the file.
					bw.write(ip.toString());
					bw.write("\t\t");
					
					//Third, write CPU ID.
					bw.write(Integer.toString(cpuid));
					cpuid++;
					bw.write("\t\t");
					
					//Last, write CPU usage which is random number respective to each CPU on the servers.
					bw.write(Integer.toString(r.nextInt((max - min) + 1) + min));
					bw.newLine();
				}
				t=t+60;
				fq=0;
				tq=0;
				cpuid=0;

			}
			//Flush and close the buffer writer.
			bw.flush();
			bw.close();

			System.out.println("Done");

		} catch (IOException e) 
		{
			e.printStackTrace();
		}

	}
	

}
