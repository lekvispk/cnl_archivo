<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security'%>
<!-- BEGIN busqueda.jsp-->
	
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
         Busqueda de Escrituras Publicas
      </div>
      
    <div class="panel-body">
    <div class="row">
    

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

    <div class="capaEnlace">
	    Criterios de busqueda: 
	    
	    <c:if test="${not empty solicitud.escritura.tipoActo.idActo }">Acto</c:if>
	    <c:if test="${not empty solicitud.escritura.comprador }">Comprador</c:if>
	    <c:if test="${not empty solicitud.escritura.vendedor }">Vendedor</c:if>
	    <c:if test="${not empty solicitud.escritura.notaria.nombre }">Notario</c:if>
	    <c:if test="${not empty solicitud.escritura.kardex }">Kardex</c:if>
	    <c:if test="${not empty solicitud.escritura.numeroDoc }">Escritura</c:if>
	    <c:if test="${not empty solicitud.escritura.numeroFolios }">Folio</c:if>
	    <c:if test="${not empty solicitud.escritura.tramFechaRegistro }">Fecha de registro</c:if>
	    <c:if test="${not empty solicitud.escritura.numeroDoc }">Escritura</c:if>
	    <c:if test="${not empty solicitud.escritura.numeroDoc }">Escritura</c:if>
      
	</div>
	
	<div class="capaEnlace">
		<input type="button" onclick="javascript:buscar();" class="boton" value="Buscar">
	</div>
	
	</form:form>
	</div>
 
 
                <div class="row">
	    		<div class="col-lg-12">
	    		<div id="tablaDinamica">
		 		<div id="resultado">
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
			    </div>
		    	</div>
			     

 
    </div>
   
   </div>
   </div>
   </div>
   
    </div>
    <!-- /#page-wrapper -->
    
    
	<div id='somediv' style="display: none">
		<div id="cuerpoDiv"></div>
	</div>
	
	<jsp:include page="../includes/pie.jsp" flush="true"/>
	
	
 </body>
</html>
<!-- END listaTramite.jsp-->