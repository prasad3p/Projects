<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
function ShowHideDiv() 
{
		
        var DistributionRangeIsub = document.getElementById("DistributionRangeIsub");
        var Isub1 = document.getElementById("Isub1");
        var Isub2 = document.getElementById("Isub2");
        var Isub3 = document.getElementById("Isub3");
        Isub1.style.display = DistributionRangeIsub.value == "Uniform" ? "block" : "none";
        Isub2.style.display = DistributionRangeIsub.value == "Normal" ? "block" : "none";
        Isub3.style.display = DistributionRangeIsub.value == "Triangular" ? "block" : "none";
        
        var DistributionRangeN = document.getElementById("DistributionRangeN");
        var N1 = document.getElementById("N1");
        var N2 = document.getElementById("N2");
        var N3 = document.getElementById("N3");
        N1.style.display = DistributionRangeN.value == "Uniform" ? "block" : "none";
        N2.style.display = DistributionRangeN.value == "Normal" ? "block" : "none";
        N3.style.display = DistributionRangeN.value == "Triangular" ? "block" : "none";
        
        var DistributionRangeEaa = document.getElementById("DistributionRangeEaa");
        var Eaa1 = document.getElementById("Eaa1");
        var Eaa2 = document.getElementById("Eaa2");
        var Eaa3 = document.getElementById("Eaa3");
        Eaa1.style.display = DistributionRangeEaa.value == "Uniform" ? "block" : "none";
        Eaa2.style.display = DistributionRangeEaa.value == "Normal" ? "block" : "none";
        Eaa3.style.display = DistributionRangeEaa.value == "Triangular" ? "block" : "none";
        
        var DistributionRangeT = document.getElementById("DistributionRangeT");
        var T1 = document.getElementById("T1");
        var T2 = document.getElementById("T2");
        var T3 = document.getElementById("T3");
        T1.style.display = DistributionRangeT.value == "Uniform" ? "block" : "none";
        T2.style.display = DistributionRangeT.value == "Normal" ? "block" : "none";
        T3.style.display = DistributionRangeT.value == "Triangular" ? "block" : "none";
}
function validate()
{
	var w = document.forms["myForm"]["DistributionRangeIsub"].value;
	var x = document.forms["myForm"]["DistributionRangeN"].value;
	var y = document.forms["myForm"]["DistributionRangeEaa"].value;
	var z = document.forms["myForm"]["DistributionRangeT"].value;
	
	if (x == "None" && w == "None" && y == "None" && z == "None"){
		alert("Please select destribution");
		return false;
	}
    if (x == "None") {
    	alert("Please select destribution for N");
        return false;
    }
    if (w == "None") {
    	alert("Please select destribution for Isub");
        return false;
    }
    if (y == "None") {
    	alert("Please select destribution for Eaa");
        return false;
    }
    if (z == "None") {
    	alert("Please select destribution for T");
        return false;
    }
	
	/* var a1 = document.forms["myForm"]["IsubMin"].value;
	var a2 = document.forms["myForm"]["IsubMax"].value;
	var b1 = document.forms["myForm"]["NMin"].value;
	var b2 = document.forms["myForm"]["NMax"].value;
	var c1 = document.forms["myForm"]["EaaMin"].value;
	var c2 = document.forms["myForm"]["EaaMax"].value;
	var d1 = document.forms["myForm"]["TMin"].value;
	var d2 = document.forms["myForm"]["TMax"].value;
		
	var a3 = document.forms["myForm"]["IsubMean"].value;
	var a4 = document.forms["myForm"]["IsubSD"].value;
	var a5 = document.forms["myForm"]["Isubb"].value;
	var a6 = document.forms["myForm"]["Isubc"].value;
	var a7 = document.forms["myForm"]["Isuba"].value;
	
	var b3 = document.forms["myForm"]["NMean"].value;
	var b4 = document.forms["myForm"]["NSD"].value;
	var b5 = document.forms["myForm"]["Nb"].value;
	var b6 = document.forms["myForm"]["Nc"].value;
	var b7 = document.forms["myForm"]["Na"].value;

	var c3 = document.forms["myForm"]["EaaMean"].value;
	var c4 = document.forms["myForm"]["EaaSD"].value;
	var c5 = document.forms["myForm"]["Eaab"].value;
	var c6 = document.forms["myForm"]["Eaac"].value;
	var c7 = document.forms["myForm"]["Eaaa"].value;
	 
	var d3 = document.forms["myForm"]["TMean"].value;
	var d4 = document.forms["myForm"]["TSD"].value;
	var d5 = document.forms["myForm"]["Tb"].value;
	var d6 = document.forms["myForm"]["Tc"].value;
	var d7 = document.forms["myForm"]["Ta"].value;
	
	if(isNaN(a1)||isNaN(a2)||isNaN(a3)||isNaN(a4)||isNaN(a5)||isNaN(a6)||isNaN(a7)||
			isNaN(b1)||isNaN(b2)||isNaN(b3)||isNaN(b4)||isNaN(b5)||isNaN(b6)||isNaN(b7)||
			isNaN(c1)||isNaN(c2)||isNaN(c3)||isNaN(c4)||isNaN(c5)||isNaN(c6)||isNaN(c7)||
			isNaN(d1)||isNaN(d2)||isNaN(d3)||isNaN(d4)||isNaN(d5)||isNaN(d6)||isNaN(d7))
	{
		alert("Must input numbers only!");
	    return false;	
	}
	
	if(a1>a2 || b1>b2 || c1>c2 || d1>d2 ){
		alert("Min. must be less than Max.!");
	    return false;		
	}  */

    	return true;   	
   
}
</script>
</head>
<body>
<center>
	<br><br>
	Hot Carrier Injection<br><br>
	<form name="myForm" method="post" action="SelectDistributionRange" >
		<table border="1">
			<tr>
				<td>Isub</td>
				
				<td>
					<select name="DistributionRangeIsub" id="DistributionRangeIsub" onchange="ShowHideDiv()">
						  <option value="None">None</option>											  																						
						  <option value="Uniform">Uniform</option>
						  <option value="Normal">Normal</option>
						  <option value="Triangular">Triangular</option>
					</select>
				</td>
				
				<td>
					<div></div>
					<div id="Isub1" style="display: none">
					    Min: <input type="text" value="1" name="IsubMin" id="IsubMin"/>
					    Max: <input type="text" value="3" name="IsubMax" id="IsubMax"/>
					</div>
					<div id="Isub2" style="display: none">
					    Mean: <input type="text" name="IsubMean" id="IsubMean"/>
					    Std. Div: <input type="text" name="IsubSD" id="IsubSD"/>
					</div>
					<div id="Isub3" style="display: none">
					    b: <input type="text" name="Isubb" id="Isubb"/>
					    c: <input type="text" name="Isubc" id="Isubc"/>
					    a: <input type="text" name="Isua" id="Isua"/>
					</div>
				</td>
				
			</tr>
			<tr>
				<td>N</td>
				<td>
					<select name="DistributionRangeN" id="DistributionRangeN" onchange="ShowHideDiv()">
						  <option value="None">None</option>											  																						
						  <option value="Uniform">Uniform</option>
						  <option value="Normal">Normal</option>
						  <option value="Triangular">Triangular</option>
					</select>
				</td>
				
				<td>
					<div></div>
					<div id="N1" style="display: none">
					    Min: <input type="text" value="2" name="NMin" id="NMin"/>
					    Max: <input type="text" value="4" name="NMax" id="NMax"/>
					</div>
					<div id="N2" style="display: none">
					    Mean: <input type="text" name="NMean" id="NMean"/>
					    Std. Div: <input type="text" name="NSD" id="NSD"/>
					</div>
					<div id="N3" style="display: none">
					    b: <input type="text" name="Nb" id="Nb"/>
					    c: <input type="text" name="Nc" id="Nc"/>
					    a: <input type="text" name="Na" id="Na"/>
					</div>
				</td>
			</tr>
			<tr>
				<td>Eaa</td>
				<td>
					<select name="DistributionRangeEaa" id="DistributionRangeEaa" onchange="ShowHideDiv()">
						  <option value="None">None</option>											  																						
						  <option value="Uniform">Uniform</option>
						  <option value="Normal">Normal</option>
						  <option value="Triangular">Triangular</option>
					</select>
				</td>
				
				<td>
					<div></div>
					<div id="Eaa1" style="display: none">
					    Min: <input type="text" value="-0.2" name="EaaMin" id="EaaMin"/>
					    Max: <input type="text" value="0.4" name="EaaMax" id="EaaMax"/>
					</div>
					<div id="Eaa2" style="display: none">
					    Mean: <input type="text" name="EaaMean" id="EaaMean"/>
					    Std. Div: <input type="text" name="EaaSD" id="EaaSD"/>
					</div>
					<div id="Eaa3" style="display: none">
					    b: <input type="text" name="Eaab" id="Eaab"/>
					    c: <input type="text" name="Eaac" id="Eaac"/>
					    a: <input type="text" name="Eaaa" id="Eaaa"/>
					</div>
				</td>
			</tr>
			<tr>
				<td>T</td>
				<td>
					<select name="DistributionRangeT" id="DistributionRangeT" onchange="ShowHideDiv()">
						  <option value="None">None</option>											  																						
						  <option value="Uniform">Uniform</option>
						  <option value="Normal">Normal</option>
						  <option value="Triangular">Triangular</option>
					</select>
				</td>
				
				<td>
					<div></div>
					<div id="T1" style="display: none">
					    Min: <input type="text" value="223.15" name="TMin" id="TMin"/>
					    Max: <input type="text" value="423.15" name="TMax" id="TMax"/>
					</div>
					<div id="T2" style="display: none">
					    Mean: <input type="text" name="TMean" id="TMean"/>
					    Std. Div: <input type="text" name="TSD" id="TSD"/>
					</div>
					<div id="T3" style="display: none">
					    b: <input type="text" name="Tb" id="Tb"/>
					    c: <input type="text" name="Tc" id="Tc"/>
					    a: <input type="text" name="Ta" id="Ta"/>
					</div>
				</td>
			</tr>
			
		</table><br><br>
		<table border="1">
			<tr>
				<td>Number of simulations</td>
				<td><input type="text" value="1000" name="numberOfSim" id="numberOfSim"/></td>		
			</tr>
			<tr>
				<td>Scaling factor</td>
				<td><input type="text" value="10" name="saclingFactor" id="saclingFactor"/></td>		
			</tr>
		</table>
		<br><br>
		<input type="submit" name="SelectDistributionRange" value="Next" onclick="return validate()">
	</form>



</center>



</body>
</html>