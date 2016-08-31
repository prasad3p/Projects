<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>
function validate()
{
	var x = document.forms["indexpage"]["primaryFailureModel"].value;
	var y = document.forms["indexpage"]["method"].value;
	
	
    if (x == "None") {
        alert("Please select Failure Model");
        return false;
    }
    if (y == "None") {
        alert("Please select Method");
        return false;
    }
   
    	return true;   	
   
}
</script>
<title>Failure Estimation</title>
</head>
<body>
<center>
		<h1>Welcome</h1>
		<%
		out.println("Please select");
		%>
		<br><br>

		<form name="indexpage" method="post" action="SelectFailureModel" >
			<%
			out.println("Select Failure Model: ");
			%>
			<select name="primaryFailureModel" id="primaryFailureModel">
				
					  <option>None</option>
					  <option>Hot Carrier Injection</option>
					  <option>NBTI</option>
					  <option>Surface Inversion</option>
					  <option>Aluminum and Copper Corrosion</option>
					  <option>Aluminum Stress Migration</option>
					  <option>Copper Stress Migration</option>
					  <option>Fatigue Failure Temp Cycle Shock</option>
					  <option>Intermetallic Oxidation</option>
					  <option>Ionic Mobility Kinetics</option>
			</select>
			<br><br>
			<%
			out.println("Method: ");
			%>
			<select name="method" id="method">
					<option>None</option>
					<option>Enter data</option>
				 	<option>By Parts</option>
				  
				  
			</select>
			<br><br>
			<input type="submit" name="selectFailureModel" value="Next" onclick="return validate()">
		</form>

</center>
</body>
</html>