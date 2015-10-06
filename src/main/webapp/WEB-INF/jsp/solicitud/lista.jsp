<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security'%>
<!-- BEGIN lista.jsp-->
<jsp:include page="../includes/cabecera.jsp"/>

    
    <!-- Page Content -->
    <div id="page-wrapper">
    <div class="container-fluid">
    <div class="row">
    <div class="col-lg-12">
                   
	    <form:form name="frmlista" action="buscar.htm" method="post" modelAttribute="solicitud">
	       
	    <input type="hidden" name="tipoTram1.idTipotram" value="1" />
	    
		<div align="center" style="color: red"> <c:out value="${msgError}"/> </div>
		<div align="center" style="color: blue"> <c:out value="${mensaje}"/> </div>
	       
	       	<h2>Lista Solicitudes</h2>
	        <h4>Busqueda</h4>                
	           
	           <div class="capaEnlace">
	               <center>
	                   <table summary="Contenedor Botones" class="tablaAnchoAjustable"><tr><td>
	                       <ul class="enlacesCentroAjustables">
	                           <li>
	                               <table class="bloqueTablaLineal" summary="Datos del Calendario">
	                                    <tr>
	                                       <th>Acto:</th>
	                                       <td colspan="3">
	                                       
	                                       	<form:select path="tipoActo.idActo" cssStyle="height: 20px; width: 180px; font-family: Arial; font-size: 9pt">
	                                       		<form:option value="-1">-TODOS-</form:option>
	                                        	<form:options items="${ltipoacto}" itemLabel="nombreActo" itemValue="idActo"/>
	                                       	</form:select>
	                                       	
	                                       </td>
	                                   </tr>
	                                 <tr>
	                                   <th>Persona:</th>
	                                   <td colspan="3">
	                                   		<input type="hidden" name="idPersona" id="idPersona"/>
	                                       	<div class="demo">
									<div class="ui-widget">
										<input id="tags" type="text" size="40"/>
									</div>
								</div>
	                                   </td>        
	                                 </tr>
	                                    <tr>
	                                   <th>Solicitudes Registradas Desde:</th>
	                                   <td>
	                                   	<fmt:formatDate value="${solicitud.fechaIngreso}" pattern="dd/MM/yyyy" var="f_fechaIngreso"/>
	                                   	<input type="text" name="fechaIngreso" id="fechaIngreso" size="15" value="${f_fechaIngreso}"/>
	                          		<img src="${pageContext.request.contextPath}/images/cal.gif" alt="D&iacute;a/Mes/A&ntilde;o" width="16" height="16" border="0" id="triggerCald" />
							<script type="text/javascript">
									Calendar.setup({
										inputField     :    "fechaIngreso",  // id del campo de texto
										ifFormat       :    "%d/%m/%Y",  // Formato de la Fecha
										showsTime      :    false,       // Flag para mostrar la Fecha
										button         :    "triggerCald",// ID del elemento que llamara al calendario
										singleClick    :    true         // Flag Modo doble-click 
									});
							</script>
						</td>  
	                                   <th>Hasta:</th>
	                                   <td>
	                                   	<fmt:formatDate value="${solicitud.fechaIngreso2}" pattern="dd/MM/yyyy" var="f_fechaIngreso2"/>
	                                   	<input type="text" name="fechaIngreso2" id="fechaIngreso2" size="15" value="${f_fechaIngreso2}"/>
	                                   	<img src="${pageContext.request.contextPath}/images/cal.gif" alt="D&iacute;a/Mes/A&ntilde;o" width="16" height="16" border="0" id="triggerCalh" />
							<script type="text/javascript">
									Calendar.setup({
										inputField     :    "fechaIngreso2",  // id del campo de texto
										ifFormat       :    "%d/%m/%Y",  // Formato de la Fecha
										showsTime      :    false,       // Flag para mostrar la Fecha
										button         :    "triggerCalh",// ID del elemento que llamara al calendario
										singleClick    :    true         // Flag Modo doble-click 
									});
							</script>
						</td>          
	                                 </tr>
	                               </table>
	                           </li>
	                       </ul>
	                   </td></tr></table>
	               </center>
	           </div>
	           
	           <div class="capaEnlace">
	               <center>
	                   <table summary="Contenedor Botones" class="tablaAnchoAjustable"><tr><td>
	                       <ul class="enlacesCentroAjustables">
	                         <li>
	                          <input type="button" onclick="javascript:buscar();" class="boton" value="Buscar">
	                          <input type="button" onclick="javascript:nuevo();" class="boton" value="Nuevo">
	                       </ul>
	                   </td></tr>
	                   </table>
	               </center>
	           </div>
	           <h4>LISTADO DE Solicitudes</h4>
	           
	          <div class="bloqueListadoDatos">
	          
	          <div id="displayTagDiv">
	          <display:table name="requestScope.lSolicitudes" requestURI="buscar.htm" class="displaytag" pagesize="25" defaultsort="1"
				defaultorder="ascending" export="false" id="row" excludedParams="ajax">
					<display:column title="F. Ingreso" sortable="true" headerClass="sortable">
						<fmt:formatDate value="${row.fechaIngreso}" pattern="dd/MM/yyyy"/>
					</display:column>
					<display:column title="Tipo" property="tipoSolicitud.nombreTipoSolicitud" sortable="true" headerClass="sortable" />
					<display:column title="Acto" property="tipoActo.nombreActo" sortable="true" headerClass="sortable" />
					<display:column title="Acciones" sortable="true" headerClass="sortable" style=" width: 80px;">
		            	<a href="ver.htm?cod=${row.idsolicitud}" style="border: 0px;" title="Ver"><img src="${pageContext.request.contextPath}/images/view.jpg" width="18" height="18" border="0"></a>
	                   	<a href="editar.htm?cod=${row.idsolicitud}" style="border: 0px;" title="Modificar"><img src="${pageContext.request.contextPath}/images/edit.png" width="18" height="18" border="0"></a>
	                   	<a href="${pageContext.request.contextPath}/pendientes/preCargaEscritura.htm?cod=${row.idsolicitud}" style="border: 0px;" title="Seleccionar Escrituras"><img src="${pageContext.request.contextPath}/images/view.jpg" width="18" height="18" border="0"></a>                                    	
	                   	<a href="javascript:eliminar(${row.idsolicitud});" style="border: 0px;" title="Eliminar"><img src="${pageContext.request.contextPath}/images/error.png" width="18" height="18" border="0"></a>
                   	</display:column>
			 </display:table>
			</div>
		</div>
         
		</form:form>
		
	</div>
    <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    </div>
    <!-- /.container-fluid -->
    </div>
    <!-- /#page-wrapper -->
    
	<jsp:include page="../includes/pie.jsp" flush="true"/>

    <script type="text/javascript">
    	function nuevo(){
    			document.forms[0].action="prenuevo.htm";
    			document.forms[0].submit();
    	}
    	function buscar(){
			document.forms[0].action="buscar.htm";
			document.forms[0].submit();
		}
    	function eliminar(id){
    		if(confirm('seguro que desea eliminar la Solicitud?')){
    			document.forms[0].action="eliminar.htm?cod="+id;
    			document.forms[0].submit();	
    		}
		}
    	
    	$(function() {
    		$( "#tags" ).autocomplete({
    			width: 300,
    	        max: 10,
    	        delay: 100,
    	        autoFocus: true,
    	        cacheLength: 1,
    	        scroll: true,
    	        highlight: false,
    			source: function(request, response) {
    	            $.ajax({
    	                url: "${pageContext.request.contextPath}/admin/lpersonasAuto.htm",
    	                dataType: "json",
    	                data: request,
    	                success: function( data, textStatus, jqXHR) {
    	                    /*console.log( data);*/
    	                    var items = data;
    	                    response(items);
    	                },
    	                error: function(jqXHR, textStatus, errorThrown){
    	                     console.log(textStatus);
    	                }
    	            });
    	        },
    			minLength: 2,
    			select: function( event, ui ) {
    				  $( "#idPersona" ).val( ui.item.id );
    			}
    		});
    	});
    	
    	 $( function(){
    	   $("#displayTagDiv").displayTagAjax();
    	})
	    	
	    	
    </script>
    
    </body>
</html>
<!-- END lista.jsp-->