<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security'%>
<!-- BEGIN listaInicialSolicitud.jsp-->
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
          Lista Solicitudes
      </div>
      
    <div class="panel-body">
    <div class="row">
          
	</div>	        
	<div class="row">
		<div class="col-lg-12">
		    	<div id="tablaDinamica">
			 	<div id="resultado">
		   		<div id="displayTagDiv">
		   			
		          <display:table name="requestScope.lSolicitudes" requestURI="buscar.htm" class="displaytag" pagesize="25" defaultsort="1"
					defaultorder="ascending" export="false" id="row" excludedParams="ajax">
						<display:column title="Codigo" property="codSolicitud" sortable="true" headerClass="sortable" />
						<display:column title="Solicitante" property="persona.nombreCompleto" sortable="true" headerClass="sortable" />
						<display:column title="F. Ingreso" property="fechaIngreso" format="{0,date,dd/MM/yyyy}" sortable="true" headerClass="sortable"/>
						<display:column title="Tipo" property="tipoSolicitud.nombreTipoSolicitud" sortable="true" headerClass="sortable" />
						<display:column title="Acto" property="tipoActo.nombreActo" sortable="true" headerClass="sortable" />
						<display:column title="Estado" sortable="true" headerClass="sortable" style=" width: 100px;">
			            	
			            	<c:if test="${row.estado=='1'}">
			            		<security:authorize ifAnyGranted="ROLE_MP,ROLE_ADMIN">
			            			<a href="${pageContext.request.contextPath}/pendientes/preCargaEscritura.htm?cod=${row.idsolicitud}" style="border: 0px;" title="Seleccionar Escrituras">Por buscar</a>
			            		</security:authorize>
			            		<security:authorize ifNotGranted="ROLE_MP,ROLE_ADMIN">
			            			Por buscar
			            		</security:authorize>
			            	</c:if>
			            	<c:if test="${row.estado=='2'}">
			            		Atendido
			            	</c:if>
			            	<c:if test="${row.estado=='3'}">
			            		Derivado
			            	</c:if>
			            	
		                </display:column>
	                   	<display:column title="Detalle" sortable="true" headerClass="sortable" style=" width: 100px;">
			            	<a id="ver_${row.idsolicitud}" href="#" data-link="${pageContext.request.contextPath}/solicitud/ver.htm?cod=${row.idsolicitud}" style="border: 0px;" title="Ver">Ver</a>
			           	</display:column>
				 </display:table>
				</div>
				</div>
				</div>	
		</div>
    </div>
   </div>
	</div>
    <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
   </div>
    </div>
    <!-- /#page-wrapper -->
    
  	<%@include file="../includes/modal.jspf" %>
    	
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
    		
    		$(document).undelegate('[id^=ver_]', 'click').delegate('[id^=ver_]', 'click', function(){ 
   			 console.info('click en ver detalle de solicitud');
   			 $("#modalView .modal-content").load( $(this).attr('data-link') , function() { 
     	       		  $( "#modalView" ).modal("show"); 
     	  		  });
   			}); 
   		 
    		$("#displayTagDiv").displayTagAjax();
    		
    	});
	    	
	    	
    </script>
    
    </body>
</html>
<!-- END listaInicialSolicitud.jsp-->