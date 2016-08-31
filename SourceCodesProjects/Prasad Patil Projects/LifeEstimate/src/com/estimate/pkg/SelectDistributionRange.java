package com.estimate.pkg;

import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Servlet implementation class SelectDistributionRange
 */
@WebServlet("/SelectDistributionRange")
public class SelectDistributionRange extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static double k=8.6173324*Math.pow(10, -5);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectDistributionRange() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String DistributionRangeIsub = new String();
		String DistributionRangeN = new String();
		String DistributionRangeEaa = new String();
		String DistributionRangeT = new String();
		
		DistributionRangeIsub = request.getParameter("DistributionRangeIsub");
		DistributionRangeN = request.getParameter("DistributionRangeN");
		DistributionRangeEaa = request.getParameter("DistributionRangeEaa");
		DistributionRangeT = request.getParameter("DistributionRangeT");
		
		System.out.println("1:"+DistributionRangeIsub);
		System.out.println("2:"+DistributionRangeN);
		System.out.println("3:"+DistributionRangeEaa);
		System.out.println("4:"+DistributionRangeT);
		
		double IsubMax=0,IsubMin=0,NMin=0,NMax=0,EaaMin=0,EaaMax=0,TMin=0,TMax=0;
		double IsubMean=0,IsubSD=0,NMean=0,NSD=0,EaaMean=0,EaaSD=0,TMean=0,TSD=0;
		double Isubb=0,Isubc=0,Isuba=0,Nb=0,Nc=0,Na=0,Eaab=0,Eaac=0,Eaaa=0,Tb=0,Tc=0,Ta=0;
		
		if(DistributionRangeIsub.equals("Uniform")){
				IsubMin = Double.parseDouble(request.getParameter("IsubMin"));
				IsubMax = Double.parseDouble(request.getParameter("IsubMax"));
				System.out.println("Isub min:"+IsubMin+"\nIsub Max:"+IsubMax);
		}
		else if(DistributionRangeIsub.equals("Normal")){
				IsubMean=Double.parseDouble(request.getParameter("IsubMean"));
				IsubSD=Double.parseDouble(request.getParameter("IsubSD"));
		}
		else {
				Isubb=Double.parseDouble(request.getParameter("Isubb"));
				Isubc=Double.parseDouble(request.getParameter("Isubc"));
				Isuba=Double.parseDouble(request.getParameter("Isuba"));
		}

		if(DistributionRangeN.equals("Uniform")){
			 NMin = Double.parseDouble(request.getParameter("NMin"));
			 NMax = Double.parseDouble(request.getParameter("NMax"));	
		}
		else if(DistributionRangeN.equals("Normal")){
			NMean=Double.parseDouble(request.getParameter("NMean"));
			NSD=Double.parseDouble(request.getParameter("NSD"));
		}
		else {
			Nb=Double.parseDouble(request.getParameter("Nb"));
			Nc=Double.parseDouble(request.getParameter("Nc"));
			Na=Double.parseDouble(request.getParameter("Na"));
		}
	
		
		if(DistributionRangeEaa.equals("Uniform")){
			 EaaMin = Double.parseDouble(request.getParameter("EaaMin"));
			 EaaMax = Double.parseDouble(request.getParameter("EaaMax"));		
		}
		else if(DistributionRangeEaa.equals("Normal")){
			EaaMean=Double.parseDouble(request.getParameter("EaaMean"));
			EaaSD=Double.parseDouble(request.getParameter("EaaSD"));
		}
		else {
			Eaab=Double.parseDouble(request.getParameter("Eaab"));
			Eaac=Double.parseDouble(request.getParameter("Eaac"));
			Eaaa=Double.parseDouble(request.getParameter("Eaaa"));
		}

		
		if(DistributionRangeT.equals("Uniform")){
			 TMin = Double.parseDouble(request.getParameter("TMin"));
			 TMax = Double.parseDouble(request.getParameter("TMax"));
			
		}
		else if(DistributionRangeT.equals("Normal")){
			TMean=Double.parseDouble(request.getParameter("TMean"));
			TSD=Double.parseDouble(request.getParameter("TSD"));
		}
		else {
			Tb=Double.parseDouble(request.getParameter("Tb"));
			Tc=Double.parseDouble(request.getParameter("Tc"));
			Ta=Double.parseDouble(request.getParameter("Ta"));
		}
	
		try {
		//public void Hot_Carrier_Injection(double IsubMin, double IsubMax, double NMin, double NMax, double  EaaMin, double EaaMax, double TMin, double TMax) throws Exception{
		
			  XSSFWorkbook workbook = new XSSFWorkbook(); 
		      //Create a blank sheet
		      XSSFSheet spreadsheet = workbook.createSheet( 
		      " Hot Carrier Injection");
		      //Create row object
		      XSSFRow row=spreadsheet.createRow(0);
		      Cell cell=row.createCell(0);
		      cell.setCellValue("TTF");
		      Cell cell1=row.createCell(1);
		      cell1.setCellValue("Isub");
		      Cell cell2=row.createCell(2);
		      cell2.setCellValue("N");
		      Cell cell3=row.createCell(3);
		      cell3.setCellValue("Eaa");
		      Cell cell4=row.createCell(4);
		      cell4.setCellValue("T");
	     
		      double i=0;
		      int rowid=1;
		      double B=1.0;
		      double numberofSim=Double.parseDouble(request.getParameter("numberOfSim"));
		      System.out.println("number of sims:"+numberofSim);
		      for(i=0;i<numberofSim;i++){
		    	  
			    	double Isub=0,N=0,Eaa=0,T=0;
			    	if(DistributionRangeIsub.equals("Uniform")){
			    		Isub=uniformDistribution(IsubMin,IsubMax);
					}
					else if(DistributionRangeIsub.equals("Normal")){
						Isub=normalDistribution(IsubMean,IsubSD);
					}
					else {
						Isub=triangularDistribution(Isubb,Isubc,Isuba);
					}
	
					if(DistributionRangeN.equals("Uniform")){
						N = uniformDistribution(NMin,NMax);
					}
					else if(DistributionRangeN.equals("Normal")){
						N=normalDistribution(NMean,NSD);
					}
					else {
						N=triangularDistribution(Nb,Nc,Na);
					}
				
					
					if(DistributionRangeEaa.equals("Uniform")){
						Eaa= uniformDistribution(EaaMin, EaaMax);
						 		
					}
					else if(DistributionRangeEaa.equals("Normal")){
						Eaa=normalDistribution(EaaMean,EaaSD);
					}
					else {
						Eaa=triangularDistribution(Eaab,Eaac,Eaaa);
					}
	
					
					if(DistributionRangeT.equals("Uniform")){
						T= uniformDistribution(TMin, TMax);
						
					}
					else if(DistributionRangeT.equals("Normal")){
						T=normalDistribution(TMean,TSD);
					}
					else {
						T=triangularDistribution(Tb,Tc,Ta);
					}
			    	  
		    	
			    	  row=spreadsheet.createRow(rowid++);
			    	  double ttf= B*Math.pow(Isub, -N)*Math.exp(Eaa/(k*T));
			    	  
			    	  
			    	  Cell cell5=row.createCell(0);
			    	  cell5.setCellValue(ttf);
			    	  Cell cell6=row.createCell(1);
			    	  cell6.setCellValue(Isub);
				      Cell cell7=row.createCell(2);
				      cell7.setCellValue(N);
				      Cell cell8=row.createCell(3);
				      cell8.setCellValue(Eaa);
				      Cell cell9=row.createCell(4);
				      cell9.setCellValue(T);
		      
		      }
		      String home = System.getProperty("user.home")+"\\Downloads\\MechanismResults.xlsx";
			     
		      
				 /* FileOutputStream out = new FileOutputStream(new File("C:/Users/prpatil/workspaceCALCEFAST/LifeEstimate/Mechanism Results.xlsx"));*/
			      
			      FileOutputStream out = new FileOutputStream(new File(home));
			      
			      
			      workbook.write(out);
			      out.close();
			      
			      System.out.println("Mechanism Results.xlsx written successfully" );
			      
			      request.setAttribute("FilePath",home);
		      RequestDispatcher dispatcher=request.getRequestDispatcher("download.jsp");
		      dispatcher.forward(request, response);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public double uniformDistribution(double min, double max){
		return min+(Math.random()*(max-min));
	}
	
	public double normalDistribution(double mean, double stdDev){
		Random rng=new Random();
		return mean+(rng.nextGaussian()*stdDev);
	}
	
	public double triangularDistribution(double a, double b, double c) {
	    double F = (c - a) / (b - a);
	    double rand = Math.random();
	    if (rand < F) {
	        return a + Math.sqrt(rand * (b - a) * (c - a));
	    } else {
	        return b - Math.sqrt((1 - rand) * (b - a) * (b - c));
	    }
	}

}
