<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security'%>

<jsp:include page="../includes/head.jsp"/>
    <script type="text/javascript">
    
    	function buscar(){
			document.forms[0].action="buscar.htm";
			document.forms[0].submit();
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
    		
    		$( "#tags2" ).autocomplete({
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
    				  $( "#idPersona2" ).val( ui.item.id );
    			}
    		});
    		
    	});
    	
    	 $( function(){
    	   $("#displayTagDiv").displayTagAjax();
    	})
	    	
	    	
    </script>
       <form:form name="frmlista" action="buscar.htm" method="post" modelAttribute="solicitud">
       
	<div id="contenedor">
		<jsp:include page="../includes/cabecera.jsp" flush="true"/>
    <div id="cuerpo">
        <table class="anchoTotal">
        <tr>
        <td id="anchoMenu">
			<jsp:include page="../includes/menu2.jsp" flush="true"/>
		</td>
        <td id="anchoCuerpo">
          
          
      <!--    <input type="hidden" name="solicitud.tipoTram1.idTipotram" value="1" />-->
          
            <div id="contenido">
            
            	<div align="center" style="color: red"> <c:out value="${msgError}"/> </div>
				<div align="center" style="color: blue"> <c:out value="${mensaje}"/> </div>
            
            	<h2>Busqueda de Escrituras Publicas</h2>
                <h4></h4>
                
                <div class="capaEnlace">
                    <center>
                        <table summary="Contenedor Botones" class="tablaAnchoAjustable"><tr><td>
                            <ul class="enlacesCentroAjustables">
                                <li>
                                
                                <form:hidden path="solicitud.idsolicitud"/>
                                
                                    <table class="bloqueTablaLineal" summary="Datos del Calendario">
                                         <tr>
                                            <th>Acto:</th>
                                            <td colspan="3">
                                            	<form:select path="escritura.tipoActo.idActo" cssStyle="height: 20px; width: 180px; font-family: Arial; font-size: 9pt">
                                            		<form:option value="-1">-TODOS-</form:option>
	                                            	<form:options items="${ltipoacto}" itemLabel="nombreActo" itemValue="idActo"/>
                                            	</form:select>
                                            </td>
                                      </tr>
                                      
                                      <tr>
                                        <th>Otorgado por:</th>
                                        <td colspan="3">
                                       		<form:hidden path="escritura.idPersona" id="idPersona"/>
                                           	<div class="demo">
												<div class="ui-widget">
													<form:input path="escritura.comprador" id="tags" size="40"/>
												</div>
											</div>
                                        </td>        
                                      </tr>
                            	           
                                       <tr>
                                        <th>A favor de:</th>
                                        <td colspan="3">
                                        	<form:hidden path="escritura.idPersona2" id="idPersona2"/>
                                            	<div class="demo">
													<div class="ui-widget">
														<form:input path="escritura.vendedor" id="tags2" size="40"/>
													</div>
												</div>
                                        </td>        
                                      </tr>
                                   
                                      <tr>
                                        <th>Notar&iacute;a:</th>
                                        <td colspan="3"> <form:input path="escritura.tramNotario" size="10"/>  </td>        
                                      </tr>
                                      
                                       <tr>
                                        <th>Kardex:</th>
                                        <td colspan="3"> <form:input path="escritura.tramKardex" size="10"/>  </td>        
                                      </tr>
                                      
                                       <tr>
                                        <th>Num. Escritura:</th>
                                        <td colspan="3"> <form:input path="escritura.numeroDoc" size="10"/>  </td>        
                                      </tr>
                                       
                                       <tr>
                                        <th>Folio Inicial:</th>
                                        <td colspan="3"> <form:input path="escritura.tramFolios" size="10"/>  </td>        
                                      </tr>
                                      
                                      <tr>
                                        <th>Escrituras Registradas Desde:</th>
                                        <td>
                                        	<fmt:formatDate value="${solicitud.escritura.tramFechaRegistro}" pattern="dd/MM/yyyy" var="f_tramFechaRegistro"/>
                                        	<input type="text" name="tramFechaRegistro" id="tramFechaRegistro" size="15" value="${f_tramFechaRegistro}"/>
		                             		<img src="${pageContext.request.contextPath}/images/cal.gif" alt="D&iacute;a/Mes/A&ntilde;o" width="16" height="16" border="0" id="triggerCald" />
											<script type="text/javascript">
													Calendar.setup({
														inputField     :    "tramFechaRegistro",  // id del campo de texto
														ifFormat       :    "%d/%m/%Y",  // Formato de la Fecha
														showsTime      :    false,       // Flag para mostrar la Fecha
														button         :    "triggerCald",// ID del elemento que llamara al calendario
														singleClick    :    true         // Flag Modo doble-click 
													});
											</script>
										</td>  
                                        <th>Hasta:</th>
                                        <td>
                                        	<fmt:formatDate value="${solicitud.escritura.tramFechaRegistro2}" pattern="dd/MM/yyyy" var="f_tramFechaRegistro2"/>
                                        	<input type="text" name="tramFechaRegistro2" id="tramFechaRegistro2" size="15" value="${f_tramFechaRegistro2}"/>
                                        	<img src="${pageContext.request.contextPath}/images/cal.gif" alt="D&iacute;a/Mes/A&ntilde;o" width="16" height="16" border="0" id="triggerCalh" />
											<script type="text/javascript">
													Calendar.setup({
														inputField     :    "tramFechaRegistro2",  // id del campo de texto
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
                            </ul>
                        </td></tr>
                        </table>
                    </center>
                </div>
                
                <h4>RESULTADOS</h4>
                
               <div class="bloqueListadoDatos">
               
                      <div id="displayTagDiv">
                             <display:table name="requestScope.lEscrituras" requestURI="buscar.htm" class="displaytag" pagesize="25" defaultsort="1"
								defaultorder="ascending" export="false" id="row" excludedParams="ajax">
									<display:column title="F. Escritura" sortable="true" headerClass="sortable">
										<fmt:formatDate value="${row.tramFechaRegistro}" pattern="dd/MM/yyyy"/>
									</display:column>
									<display:column title="Numero" property="numeroDoc" sortable="true" headerClass="sortable" />
									<display:column title="Kardex" property="tramKardex" sortable="true" headerClass="sortable" />
									<display:column title="Notario" property="tramNotario" sortable="true" headerClass="sortable" />
									<display:column title="archivo" sortable="true" headerClass="sortable">
										<c:forEach items="${row.archivos}" var="doc">
											<a href="descargar.htm?id=${doc.idArchivo}">${doc.nombre}</a>
										</c:forEach>
									</display:column>
						            <display:column title="Acciones" sortable="true" headerClass="sortable" style=" width: 80px;">
						            	<a href="seleccionar.htm?id1=${row.idEscritura}&id2=${solicitud.solicitud.idsolicitud}" style="border: 0px;" title="Modificar"><img src="${pageContext.request.contextPath}/images/edit.png" width="18" height="18" border="0"></a>                                    	
                                    </display:column>
							 </display:table>
		 				</div>
		 			</div>
		 			
         	   </div>
         
         
        </td></tr></table>
        
        
		 				
    </div>
   
</div>

  
                     
</form:form>
 <jsp:include page="../includes/pie.jsp" flush="true"/>
