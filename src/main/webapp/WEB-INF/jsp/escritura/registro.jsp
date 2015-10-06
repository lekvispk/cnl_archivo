<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>

	<jsp:include page="../includes/head.jsp"/> 


 <script>

   	function cargar(){
   	    document.forms[1].action = "cargarEscritura.htm";
   	    document.forms[1].submit();
   	}
   	
   	function eliminar(cod,docum){
   	    if(confirm('Está segur de eliminar el Oficio')){
   	    	window.location='eliminarDoc.htm?id='+cod+"&cod="+docum;
   	    }
   	}
   	
	function nuevo(){
        document.forms[0].action = "nuevo.htm";
    	document.forms[0].submit();
	}
	
	function cancelar(){
	    document.forms[0].action = "lista.htm";
	    document.forms[0].submit();
	}
	
	function implicados(){
	    document.forms[0].action = "preparticipantes.htm";
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
                                    <h2>REGISTRO DE ESCRITURA</h2> 
                           
                                   	<div align="center" style="color: red"> <c:out value="${msgError}"/> </div>
									<div align="center" style="color: blue"> <c:out value="${mensaje}"/> </div>
                                           
                                   <h3> <span>Datos de Escritura</span> </h3>
                                   
                                   <form:form name="frm2" action="nuevo.htm" method="post" modelAttribute="escritura">
                                     
                                     <form:hidden path="idEscritura"/>
                                   <table class="bloqueTablaLineal" cellspacing="6">
                                        <tr>
                                            <th>Notaria:</th>
                                            <td><form:input path="tramNotario" size="50"/></td>
                                        </tr>
                                        <tr>
                                            <th>Acto:</th>
                                            <td>
                                            	<form:select path="tipoActo.idActo" cssStyle="height: 20px; width: 180px; font-family: Arial; font-size: 9pt">
                                            		<form:option value="-1">-TODOS-</form:option>
	                                            	<form:options items="${ltipoacto}" itemLabel="nombreActo" itemValue="idActo"/>
                                            	</form:select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>Nro. Kardex:</th>
                                            <td><form:input path="tramKardex" size="15"/></td>
                                        </tr>
                                        <tr>
                                            <th>Nro. Escritura:</th>
                                            <td><form:input path="numeroDoc" size="15"/></td>
                                        </tr>
                                         <tr>
                                            <th>Nro. Instrumento:</th>
                                            <td><form:input path="tramNuIntr" size="15"/></td>
                                        </tr>
                                         <tr>
                                            <th>Nro. Folios:</th>
                                            <td><form:input path="tramFolios" size="15"/></td>
                                        </tr>
                                        <tr>
                                            <th>Fecha de Documento:</th>
                                            <td>
                                            	<fmt:formatDate value="${escritura.tramFechaRegistro}" pattern="dd/MM/yyyy" var="f_tramFechaRegistro"/>
                                        	<input type="text" name="tramFechaRegistro" id="tramFechaRegistro" size="15" value="${f_tramFechaRegistro}"/>
                                          		<img src="${pageContext.request.contextPath}/images/cal.gif" alt="D&iacute;a/Mes/A&ntilde;o" width="16" height="16" border="0" id="triggerCalr" />
												<script type="text/javascript">
														Calendar.setup({
															inputField     :    "tramFechaRegistro",  // id del campo de texto
															ifFormat       :    "%d/%m/%Y",  // Formato de la Fecha
															showsTime      :    false,       // Flag para mostrar la Fecha
															button         :    "triggerCalr",// ID del elemento que llamara al calendario
															singleClick    :    true         // Flag Modo doble-click 
														});
												</script>
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
                                                       		<input type="submit" class="boton" value="Aceptar">
                                                        	<input type="button" onclick="javascript:cancelar();" class="boton" value="Cancelar">
                                                            <c:if test="${ escritura.idEscritura != 0}">
                                                            	<input type="button" onclick="javascript:implicados();" class="boton" value="Agregar Participantes">
                                                            </c:if>
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
                                    
                                     <c:if test="${ escritura.idEscritura != 0}">
                                    
                                     <c:if test="${ empty escritura.archivos }">
                                     
                                    <h3><span>Cargar Archivo</span> </h3>
									<form name="frmFile" action="#" method="post" enctype="multipart/form-data">
										<input type="hidden" name="idDocumento" value="${escritura.idEscritura}"/>
		                                 
										<table class="bloqueTablaLineal" cellspacing="6">
											<tr>
	                                            <th>Seleccionar Archivo:</th>
	                                            <td><input type="file" name="txtfile"></td>
	                                        </tr>
	                                   </table>
	                                    <center>
	                                        <table summary="Contenedor Botones" class="tablaAnchoAjustable">
	                                            <tr>
	                                                <td>
	                                                    <ul class="enlacesCentroAjustables">
	                                                        <li><input type="button" onclick="javascript:cargar();" class="boton" value="Cargar"></li>
	                                                    </ul>
	                                                </td>
	                                            </tr>
	                                        </table>
	                                    </center>
                                    </form>
                                   </c:if>
                                    
                                     <c:if test="${ not empty  escritura.archivos }">
		                                    <table class="bloqueTablaLineal" cellspacing="6">
													<c:forEach items="${escritura.archivos}" var="doc">
													<tr>
			                                            <th>Descargar Archivo:</th>
			                                            <td>
			                                            <a href="descargar.htm?id=${doc.idArchivo}">${doc.nombre}</a>&nbsp;&nbsp;&nbsp;&nbsp;			                                          
			                                            <a href="javascript:eliminar('${doc.idArchivo}','${documento.idDocumento}');" class="btnEnlaceCentro"> 
			                                           		<img title="Eliminar archivo" src="${pageContext.request.contextPath}/images/error.png" width="18" height="18" border="0">
			                                           	</a>
			                                            </td>
			                                        </tr>
			                                        </c:forEach>
			                                   </table>
	                                    </c:if>
                              </c:if>
                               
                                </div>
                               
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <jsp:include page="../includes/pie.jsp" flush="true"/>