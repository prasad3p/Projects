package com.estimate.pkg;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SelectFailureModel
 */
@WebServlet("/SelectFailureModel")
public class SelectFailureModel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectFailureModel() {
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
		String Fmodel=request.getParameter("primaryFailureModel");
		System.out.println("f model value"+Fmodel);
		String type=request.getParameter("method");
		

		if(type.equals("Enter Data")){
			//JSPs for each failure model.
			if(Fmodel.equals("Hot Carrier Injection")){
				
				RequestDispatcher dispatcher=request.getRequestDispatcher("enterData1.jsp");
				dispatcher.forward(request, response);	
			}
			else if(Fmodel.equals(" ")){
				RequestDispatcher dispatcher=request.getRequestDispatcher("enterData2.jsp");
				dispatcher.forward(request, response);	
			}
			else if(Fmodel.equals(" ")){
				RequestDispatcher dispatcher=request.getRequestDispatcher("enterData3.jsp");
				dispatcher.forward(request, response);	
			}
			else if(Fmodel.equals(" ")){
				RequestDispatcher dispatcher=request.getRequestDispatcher("enterData4.jsp");
				dispatcher.forward(request, response);	
			}
			else if(Fmodel.equals(" ")){
				RequestDispatcher dispatcher=request.getRequestDispatcher("enterData5.jsp");
				dispatcher.forward(request, response);	
			}
			else if(Fmodel.equals(" ")){
				RequestDispatcher dispatcher=request.getRequestDispatcher("enterData6.jsp");
				dispatcher.forward(request, response);	
			}
			else if(Fmodel.equals(" ")){
				RequestDispatcher dispatcher=request.getRequestDispatcher("enterData7.jsp");
				dispatcher.forward(request, response);	
			}
			else if(Fmodel.equals(" ")){
				RequestDispatcher dispatcher=request.getRequestDispatcher("enterData8.jsp");
				dispatcher.forward(request, response);	
			}
			else{
				RequestDispatcher dispatcher=request.getRequestDispatcher("enterData1.jsp");
				dispatcher.forward(request, response);	
			}
			
		}
		
		else{
			//Add code here if type selected is By Parts
			if(Fmodel.equals("F1")){
				RequestDispatcher dispatcher=request.getRequestDispatcher("enterData1.jsp");
				dispatcher.forward(request, response);	
			}
			else if(Fmodel.equals(" ")){
				RequestDispatcher dispatcher=request.getRequestDispatcher("enterData2.jsp");
				dispatcher.forward(request, response);	
			}
			else if(Fmodel.equals(" ")){
				RequestDispatcher dispatcher=request.getRequestDispatcher("enterData3.jsp");
				dispatcher.forward(request, response);	
			}
			else if(Fmodel.equals(" ")){
				RequestDispatcher dispatcher=request.getRequestDispatcher("enterData4.jsp");
				dispatcher.forward(request, response);	
			}
			else if(Fmodel.equals(" ")){
				RequestDispatcher dispatcher=request.getRequestDispatcher("enterData5.jsp");
				dispatcher.forward(request, response);	
			}
			else if(Fmodel.equals(" ")){
				RequestDispatcher dispatcher=request.getRequestDispatcher("enterData6.jsp");
				dispatcher.forward(request, response);	
			}
			else if(Fmodel.equals(" ")){
				RequestDispatcher dispatcher=request.getRequestDispatcher("enterData7.jsp");
				dispatcher.forward(request, response);	
			}
			else if(Fmodel.equals(" ")){
				RequestDispatcher dispatcher=request.getRequestDispatcher("enterData8.jsp");
				dispatcher.forward(request, response);	
			}
			else{
				RequestDispatcher dispatcher=request.getRequestDispatcher("enterData1.jsp");
				dispatcher.forward(request, response);	
			}
		}
		
		
		
	}

}
