<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<title>.. :: FIRMA DE TRAMITE ::..</title>

</head>
<body>

	<fieldset>
		<legend>Modulo Firmador</legend>
		
		<form class="jqtransform" >
	
	 			<applet name="firmaApplet"
				 		 code="pe.org.cnl.gestiondoc.firmador.FirmadorApp" 
              			 archive="app/firmaApplet.jar"
              			 width="400" height="300">
              			  <param name="id1" value="<%=request.getParameter("id1")%>">
              			  <param name="id2" value="<%=request.getParameter("id2")%>">
       			</applet>
     
     	
		</form>
		
	</fieldset>
	
</body>
</html>