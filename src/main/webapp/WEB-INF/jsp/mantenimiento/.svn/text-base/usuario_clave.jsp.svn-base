<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
	<jsp:include page="../includes/head.jsp"/>
    	<script>
			function nuevo(){
				document.forms[0].action = "cpass.htm";
		    	document.forms[0].submit();
			}
			
			function cancelar(){
			    document.forms[0].action = "${pageContext.request.contextPath}/inicio.htm";
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
                                    <h2>CAMBIO DE CONTRASE&Ntilde;A</h2>                                     
                                    <h3> <span>DATOS DE USUARIO</span> </h3>    
                                    
                                     	<div align="center" style="color: red"> <c:out value="${msgError}"/> </div>
										<div align="center" style="color: blue"> <c:out value="${mensaje}"/> </div>
            
                                    <form action="." name="frm1" method="post">
                                    <table class="bloqueTablaLineal" cellspacing="6">
                                         <tr>
                                            <th>Clave actual:</th>
                                            <td><input type="password" name="clave" size="30"/></td>
                                        </tr>
                                         <tr>
                                            <th>Nueva clave:</th>
                                            <td><input type="password" name="nueva" size="30"/></td>
                                        </tr>
                                        <tr>
                                            <th>Confirmar clave:</th>
                                            <td><input type="password" name="confirmar" size="30"/></td>
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
                                    </form>
                                 </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <jsp:include page="../includes/pie.jsp" flush="true"/>