<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>

	<jsp:include page="../includes/head.jsp"/>
		<script>
			function regresar(){
			    document.forms[0].action = "lista.htm";
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
                             <h2>INFORMACION DE OFICIOS</h2>                                     
                             <h3> <span>DATOS DE OFICIO</span> </h3>
                             
								<div align="center" style="color: red"> <c:out value="${msgError}"/> </div>
								<div align="center" style="color: blue"> <c:out value="${mensaje}"/> </div>
                                           
                                   <!--  <h2>Datos de Alerta</h2> -->
                                   <h3><span>Datos de Oficio</span> </h3>
                                   
                                   <form:form name="frm2" action="#" method="post" modelAttribute="solicitud">
                                    
                                    <table class="bloqueTablaLineal" cellspacing="6">
                                        <tr>
                                            <th>Fecha de Solicitud:</th>
                                            <td><fmt:formatDate value="${solicitud.fechaIngreso}" pattern="dd/MM/yyyy"/></td>
                                        </tr>
                                        <tr>
                                            <th>Tipo Solicitud:</th>
                                            <td>${solicitud.tipoSolicitud.nombreTipoSolicitud}</td>
                                        </tr>
                                         <tr>
                                            <th>Acto:</th>
                                            <td>${solicitud.tipoActo.nombreActo}</td>
                                        </tr>
                                        <tr>
                                            <th>Comprador:</th>
                                            <td>${solicitud.tramComprador}</td>
                                        </tr>
                                          <tr>
                                            <th>Vendedor:</th>
                                            <td>${solicitud.tramVendedor}</td>
                                        </tr>
                                        <tr>
                                            <th>Solicitante:</th>
                                            <td>${solicitud.tramSolicitante}</td>
                                        </tr>
                                        <tr>
                                            <th>Nro. Kardex:</th>
                                            <td>${solicitud.tramKardex}</td>
                                        </tr>
                                        <tr>
                                            <th>Nro. Escritura:</th>
                                            <td>${solicitud.tramEscritura}</td>
                                        </tr>
                                        <c:if test="${not empty solicitud.tramApoderado}">
                                       <tr>
                                            <th>Nombre Apoderado:</th>
                                            <td>${solicitud.tramApoderado}</td>
                                        </tr>
                                        <tr>
                                            <th>Doc. Apoderado:</th>
                                            <td>${solicitud.tramApodDocumento}</td>
                                        </tr>
                                        <tr>
                                            <th>Telefono:</th>
                                            <td>${solicitud.tramApodTelefono}</td>
                                        </tr>
                                         <tr>
                                            <th>Direccion:</th>
                                            <td>${solicitud.tramApodDireccion}</td>
                                        </tr>
                                        </c:if>
                                        <tr>
                                            <th>Observaciones:</th>
                                            <td>${solicitud.tramSolicitado}</td>
                                        </tr>
                                    </table> 
                                    
                             <center>
                                 <table summary="Contenedor Botones"
                                        class="tablaAnchoAjustable">
                                     <tr>
                                         <td>
                                             <ul class="enlacesCentroAjustables">
                                                 <li>
                                                     <a onclick="javascript:regresar();" class="btnEnlaceCentro"> <span>Regresar</span> </a>
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
     <jsp:include page="../includes/pie.jsp" flush="true"/>