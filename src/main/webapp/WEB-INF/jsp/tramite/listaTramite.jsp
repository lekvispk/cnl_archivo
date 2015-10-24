<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security'%>


<!-- BEGIN listaTramite.jsp-->
<jsp:include page="../includes/cabecera.jsp"/>

    <!-- Page Content -->
    <div id="page-wrapper">
    
    <div class="row">
	    <div class="col-lg-12">
	    	&nbsp;
	    </div>
    </div>
    
    <jsp:include page="../includes/error.jsp"/>
    
    <div class="row">
    <div class="col-lg-12">
    
    <div class="panel panel-default">
      <div class="panel-heading">
          Lista de Tr&aacute;mites
      </div>
      
    <div class="panel-body">
    
    <div class="row">
	    <div class="col-lg-12">
	    		<div id="tablaDinamica">
		 		<div id="resultado">
	   			<div id="displayTagDiv">
	   			
		          <display:table name="requestScope.lTramites" requestURI="buscar.htm" class="displaytag" pagesize="25" defaultsort="1"
					defaultorder="ascending" export="false" id="row" excludedParams="ajax">
						<display:column title="Codigo de solicitud" property="solicitud.codSolicitud" sortable="true" headerClass="sortable" />
						<display:column title="Nombre (Solicitante)" property="solicitud.persona.nombreCompleto" sortable="true" headerClass="sortable" />
						<display:column title="Fecha solicitud" property="solicitud.fechaIngreso" format="{0,date,dd/MM/yyyy}" sortable="true" headerClass="sortable" />
						<display:column title="Tipo de solicitud" property="solicitud.tipoSolicitud.nombreTipoSolicitud" sortable="true" headerClass="sortable" />
						<display:column title="Estado" sortable="true" headerClass="sortable" style=" width: 120px;">
						
			            	<c:choose>
			            		<c:when test="${row.estado==1}">
					            	Por Atender
				                 </c:when>
			            		<c:when test="${row.estado==2}">
			            			Por Derivar
				                </c:when>
			            		<c:when test="${row.estado==3}">
			            			Por Responder
			            		</c:when>
			            		<c:when test="${row.estado==4}">
			            			Por Notificar
			            		</c:when>
			            		<c:when test="${row.estado==5}">
			            			Por Concluir
			            		</c:when>
			            		<c:otherwise>
			            			Concluido
			            		</c:otherwise>
			            	</c:choose>
			            	
						</display:column>
						<display:column title="Acciones" sortable="true" headerClass="sortable" style=" width: 80px;">
			            	<%--
			            	1 = recien creado
			            	2 = por derivar
			            	3 = derivado a notario
			            	4 = respondido notario
			            	5 = notificado
			            	6 = concluido
			            	 --%>
			            	
			            	<c:choose>
			            		<c:when test="${row.estado==1}">
			            		<spring:url value="/" var="rooot" />
			            			<%-- <a id="ver_${row.idTramite}" href="#" data-link="ver.htm?cod=${row.idTramite}" style="border: 0px;" title="Ver Tramite"><img src="${pageContext.request.contextPath}/images/view.jpg" width="18" height="18" border="0"></a>--%>
			            			<a id="ver_es_${row.idTramite}" href="#" data-link="${rooot }escritura/ver.htm?cod=${row.escritura.idEscritura}" style="border: 0px;" title="Ver Escritura"><img src="${pageContext.request.contextPath}/images/view.jpg" width="18" height="18" border="0"></a>
					               	<a href="editar.htm?cod=${row.idTramite}" style="border: 0px;" title="Atender"><img src="${pageContext.request.contextPath}/images/edit.png" width="18" height="18" border="0"></a>
				                 </c:when>
			            		<c:when test="${row.estado==2}">
			            			<a id="modificar_${row.idTramite}" href="#" data-link="ver.htm?cod=${row.idTramite}" style="border: 0px;" title="Ver">Modificar</a>
				                   	<a href="derivar.htm?cod=${row.idTramite}" style="border: 0px;" title="Atender">Derivar</a>
				                </c:when>
			            		<c:when test="${row.estado==3}">
			            			<a href="derivar.htm?cod=${row.idTramite}" style="border: 0px;" title="Atender">Responder</a>
			            		</c:when>
			            		<c:when test="${row.estado==4}">
			            			<a id="modificar_${row.idTramite}" href="#" data-link="ver.htm?cod=${row.idTramite}" style="border: 0px;" title="Ver">Modificar</a>
				                   	<a href="derivar.htm?cod=${row.idTramite}" style="border: 0px;" title="Atender">Notificar</a>
			            		</c:when>
			            		<c:when test="${row.estado==5}">
			            			<a href="derivar.htm?cod=${row.idTramite}" style="border: 0px;" title="Atender">Concluir</a>
			            		</c:when>
			            		<c:otherwise>
			            			Concluido
			            		</c:otherwise>
			            	</c:choose>
			            	<%-- <a href="javascript:eliminar(${row.idTramite});" style="border: 0px;" title="Eliminar"><img src="${pageContext.request.contextPath}/images/error.png" width="18" height="18" border="0"></a>--%>
	                   	</display:column>
				 </display:table>
				 
				</div>
			  	</div>
				</div>	
	    </div>
    </div>
    
   </div>
   
   </div>
   </div>
   </div>
   
    </div>
    <!-- /#page-wrapper -->

	<%@include file="../includes/modal.jspf" %>

	<div id='somediv' style="display: none">
		<div id="cuerpoDiv"></div>
	</div>
	
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
    		
    		 $("#displayTagDiv").displayTagAjax();
    		 
    		 $(document).undelegate('[id^=ver_]', 'click').delegate('[id^=ver_]', 'click', function(){ 
    			 console.info('click en ver detalle de tramite');
    			 $("#modalView .modal-content").load( $(this).attr('data-link') , function() { 
      	       		  $( "#modalView" ).modal("show"); 
      	  		  });
    		 }); 
    		 
    	});
    	
	    	
    </script>
    
    </body>
</html>
<!-- END listaTramite.jsp-->