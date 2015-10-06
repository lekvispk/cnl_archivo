<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>

<jsp:include page="../includes/head.jsp"/> 
<script>

	cerrar( <%=request.getAttribute("r") %>);

	function cerrar(accion){
		if( accion == "1" ){
			window.close();
		}
	}
	
	function nuevo(){
        document.forms[0].action = "npersona.htm";
    	document.forms[0].submit();
	}
	
	function cancelar(){
	    document.forms[0].action = "lista.htm";
	    document.forms[0].submit();
	    window.close();
	}
	
</script>

            <div id="contenedor">
            
                <!--Capa que contiene toda la plantilla-->
                 
                <div id="cuerpo" align="center">
                    <table class="anchoTotal" align="center" width="90%" border="1">
                        <tr>
                            <td id="anchoMenu" >
                              
                            </td>
                            <td id="anchoCuerpo">
                            
                                <div id="contenido">
                                    <!--Capa que contiene el cuerpo de la plantilla-->                                     
                                    <h2>REGISTRO DE PERSONA</h2>
                                    
                                  	<div align="center" style="color: red"> <c:out value="${msgError}"/> </div>
									<div align="center" style="color: blue"> <c:out value="${mensaje}"/> </div>
         
                                  <!--  <h2>Datos de Alerta</h2> -->
                                   <h3> <span>Datos de Alerta</span> </h3>
                                   <form:form name="frm2" action="#" method="post" modelAttribute="persona">
                                      
                                   <form:hidden path="idPersona"/>
                                 	
                                   <table class="bloqueTablaLineal" cellspacing="6">                                       
                                         <tr>
                                            <th>Grado(*):</th>
                                            <td><form:input path="grado" size="50"/></td>
                                        </tr>
                                         <tr>
                                            <th>Nombres:</th>
                                            <td><form:input path="nombre"  size="50"/></td>
                                        </tr>
                                         <tr>
                                            <th>Ap. Paterno:</th>
                                            <td><form:input path="apePaterno" size="50"/></td>
                                        </tr>
                                         <tr>
                                            <th>AP. Materno:</th>
                                            <td><form:input path="apeMaterno" size="50"/></td>
                                        </tr>
                                        <tr>
                                            <th>Cargo(*):</th>
                                            <td><form:input path="cargo" size="50"/></td>
                                        </tr>
                                        <tr>
                                            <th>Tipo Documento:</th>
                                            <td>
                                            	<form:select path="tipoDocumento"  cssStyle="height: 20px; width: 180px; font-family: Arial; font-size: 9pt">
                                            		<form:option value="0">DNI</form:option>
                                            		<form:option value="1">Pasaporte</form:option>
                                            		<form:option value="2">Carnet de Extranjeria</form:option>
                                            	</form:select>
                                            </td>
                                        </tr>
                                         <tr>
                                            <th>Num. Documento:</th>
                                            <td><form:input path="numDocumento" maxlength="12" size="15"/></td>
                                        </tr>
                                    </table>
                                     
                                    <center>
                                        <table summary="Contenedor Botones" class="tablaAnchoAjustable">
                                            <tr>
                                                <td>
                                                    <ul class="enlacesCentroAjustables">
                                                        <li>
                                                            <a onclick="javascript:nuevo();" class="btnEnlaceCentro"> <span>Aceptar</span> </a>
                                                            <a onclick="javascript:cancelar();" class="btnEnlaceCentro"> <span>Cancelar</span> </a>
                                                        </li>
                                                    </ul>
                                                    <ul class="enlacesCentroAjustables">
                                                        <li> &nbsp; </li>
                                                    </ul>
                                                </td>
                                            </tr>
                                        </table>
                                    </center>
                                    </form:form>
                                    
                               
                                </div>
                               
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <script>
            document.forms[0].grado.focus();
            </script> 
            <jsp:include page="../includes/pie.jsp" flush="true"/>