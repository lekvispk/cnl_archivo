<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
	<jsp:include page="../includes/head.jsp"/>
    	<script>
			function nuevo(){
				document.forms[0].action = "nuevoUsr.htm";
		    	document.forms[0].submit();
			}
			
			function cancelar(){
			    document.forms[0].action = "listaUsr.htm";
			    document.forms[0].submit();
			}
		</script>
		
            <div id="contenedor">
                <!--Capa que contiene toda la plantilla-->
                <jsp:include page="../includes/cabecera.jsp" flush="true"/>
                 
                <div id="cuerpo" align="center">
                    <table class="anchoTotal" align="center" width="90%" border="1">
                        <tr>
                            <td id="anchoMenu" >
                                <jsp:include page="../includes/menu2.jsp" flush="true"/>
                            </td>
                            <td id="anchoCuerpo">
                                <div id="contenido">
                                    <!--Capa que contiene el cuerpo de la plantilla-->                                     
                                    <h2>REGISTRO DE USUARIOS</h2>                                     
                                    <h3> <span>DATOS DE USUARIO</span> </h3>    
                                    
                                     	<div align="center" style="color: red"> <c:out value="${msgError}"/> </div>
										<div align="center" style="color: blue"> <c:out value="${mensaje}"/> </div>
            
                                    <form:form action="." name="frm1" method="post" modelAttribute="usuario">
                                    
                                    <form:hidden path="idusuario"/>
                                    
                                    <table class="bloqueTablaLineal" cellspacing="6">
                                         <tr>
                                            <th>Nombre:</th>
                                            <td><form:input path="nombre" size="30"/></td>
                                        </tr>
                                         <tr>
                                            <th>Ape. Paterno:</th>
                                            <td><form:input path="apePaterno" size="30"/></td>
                                        </tr>
                                        <tr>
                                            <th>Ape. Materno:</th>
                                            <td><form:input path="apeMaterno" size="30"/></td>
                                        </tr>
                                        <tr>
                                            <th>Entidad:</th>
                                            <td>
                                            	<form:select path="entidad.idEntidad" cssStyle="height: 20px; width: 180px; font-family: Arial; font-size: 9pt">
	                                            	<form:options items="${lentidad}" itemLabel="nomEntidad" itemValue="idEntidad"/>
                                            	</form:select>
                                            </td>
                                        </tr>
                                         <tr>
                                            <th>Usuario:</th>
                                            <td><form:input path="username" size="30"/></td>
                                        </tr>
                                         <tr>
                                            <th>Password:</th>
                                            <td><form:password path="clave" size="30"/></td>
                                        </tr>
                                         <tr>
                                            <th>Estado:</th>
                                            <td>
                                            	<form:select path="estado" cssStyle="height: 20px; width: 180px; font-family: Arial; font-size: 9pt">
                                            		<form:option label="Activo" value="1"/>
                                            		<form:option label="Inactivo" value="0"/>
                                            	</form:select>
                                            </td>
                                        </tr>
                                    </table>
                                     
                                    <center>
                                        <table summary="Contenedor Botones"
                                               class="tablaAnchoAjustable">
                                            <tr>
                                                <td>
                                                    <ul class="enlacesCentroAjustables">
                                                        <li>
                                                        	<input type="button" onclick="javascript:nuevo();" class="boton" value="Aceptar">
                                                            <input type="button" onclick="javascript:cancelar();" class="boton" value="Cancelar">
                                                        </li>
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
            <jsp:include page="../includes/pie.jsp" flush="true"/>