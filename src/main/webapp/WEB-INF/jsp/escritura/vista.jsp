<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>

	<jsp:include page="../includes/head.jsp"/>
		<script>
			function regresar(){
			    document.forms[0].action = "lista.htm";
			    document.forms[0].submit();
			}
			function firmar(id){
				
				window.open("http://localhost:8080/FirmaDigitalCnl/firmatramite.do?method=firmarTramite&idp=" + id, "firmaPT", "toolbar=0,location=0,directories=0,status=no,menubar=0,scrollbars=yes,resizable=yes,width=780,height=580,titlebar=no");
				
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
                      
                                   
                                   <form:form name="frm2" action="#" method="post" modelAttribute="escritura">
                                   
                                  <form:hidden path="idEscritura"/>
                                   <table class="bloqueTablaLineal" cellspacing="6">
                                        <tr>
                                            <th>Notaria:</th>
                                            <td>${escritura.tramNotario}</td>
                                        </tr>
                                        <tr>
                                            <th>Acto:</th>
                                            <td>${escritura.tipoActo.nombreActo} </td>
                                        </tr>
                                        <tr>
                                            <th>Nro. Kardex:</th>
                                            <td>${escritura.tramKardex}</td>
                                        </tr>
                                        <tr>
                                            <th>Nro. Escritura:</th>
                                            <td>${escritura.numeroDoc}</td>
                                        </tr>
                                         <tr>
                                            <th>Nro. Instrumento:</th>
                                            <td>${escritura.tramNuIntr}</td>
                                        </tr>
                                         <tr>
                                            <th>Nro. Folios:</th>
                                            <td>${escritura.tramFolios}</td>
                                        </tr>
                                        <tr>
                                            <th>Fecha de Documento:</th>
                                            <td><fmt:formatDate value="${escritura.tramFechaRegistro}" pattern="dd/MM/yyyy"/></td>
                                        </tr>                                    
                                    </table>
                                     
                                         <h3> <span>PARTICIPANTES</span> </h3>
                                      <table id="myTable" width="90%" align="center">
						                            <thead>
						                            	<tr>
						                            		<th class="tituloTabla" axis="string">Tipo</th>
						                            		<th class="tituloTabla" axis="string">Persona</th>
						                            		<th class="tituloTabla" axis="string">Documento</th>
						                                  </tr>
						                            </thead>
						                            <tbody>
						                            
						                             <c:if test="${not empty limplicados}">
						                            	<c:forEach items="${limplicados}" var="row">
						                            	<tr>                                    
						                                    <td>&nbsp;<c:out value="${row.tipoRelacion.relDescripcion}"/></td>
						                                    <td>&nbsp;<c:out value="${row.persona.nombreCompleto}"/></td>
						                                    <td>&nbsp;<c:out value="${row.persona.numDocumento}"/></td>
						                                </tr>
						                               </c:forEach>
						                           </c:if>
						                           </tbody>
						                       </table>
						                       
						                       
                                    <c:if test="${ not empty escritura.archivos }">
                                     <h3> <span>ARCHIVOS</span> </h3>
                                     
	                                    <table class="bloqueTablaLineal" cellspacing="6">
												<c:forEach items="${escritura.archivos}" var="doc">
												<tr>
		                                            <th>Descargar Archivo:</th>
		                                            <td><a href="descargar.htm?id=${doc.idArchivo}">${doc.nombre}</a>&nbsp;</td>
		                                        </tr>
		                                        <tr>
		                                            <th>Firmar Archivo:</th>
		                                            <td><a href="javascript:firmar(${doc.idArchivo});">${doc.nombre}</a>&nbsp;</td>
		                                            
		                                        </tr>
		                                        </c:forEach>
		                                   </table>
                                    </c:if>
                                   
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