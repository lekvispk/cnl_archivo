<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
	<jsp:include page="../includes/head.jsp"/>
    	<script>
			function nuevo(){
				document.forms[0].action = "nuevoUsrPermisos.htm";
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
                                    <h3> <span>PERMISOS PARA USUARIO</span> </h3>    
                                    
                                     	<div align="center" style="color: red"> <c:out value="${msgError}"/> </div>
										<div align="center" style="color: blue"> <c:out value="${mensaje}"/> </div>
            
                                    <form:form action="." name="frm1" method="post" modelAttribute="usuario">
                                    <table class="bloqueTablaLineal" cellspacing="6">
                                         <tr>
                                            <th>Nombre:</th>
                                            <td>${usuario.nombreCompleto}</td>
                                        </tr>
                                        <tr>
                                            <th>Tipo de  Usuario:</th>
                                            <td>
                                            
                                            <form:hidden path="username"/>
                                            
                                            <select name="tipoUsuario">
                                            	<option value="ROLE_ADMIN">Administrador</option>
                                            	<Option value="ROLE_USER">Consulta</option>
                                            </select>
                                            
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
                                                        	<input type="button" onclick="javascript:nuevo();" class="boton" value="Grabar">
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