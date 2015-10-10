<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
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
    
    <form:form name="frmlista" action="buscar.htm" method="post" modelAttribute="tramite">
    	<div class="col-lg-6">
	    	<input type="hidden" name="tipoTram1.idTipotram" value="1" />
	    	
			<div class="form-group">
			  <label class="control-label" for="txtCodigo">C&oacute;digo</label>  
			  <form:input path="idTramite" id="idTramite" size="10" cssClass="form-control input-md" />
			</div>
			
			<div class="form-group">
			  <label class="control-label" for="estado">Estado</label>
			  <form:select path="estado" cssClass="form-control" >
			    	<form:option value="">-Seleccionar-</form:option>
			  		<form:option value="1">Alta</form:option>
			  		<form:option value="2">Normal</form:option>
			  		<form:option value="3">Baja</form:option>
			  </form:select>
			</div>
			
			<div class="form-group">
			  <label class=" control-label" for="txtVencimiento">Creacion</label>  
			  <fmt:formatDate value="${reclamo.fechaCreacion}" pattern="dd/MM/yyyy" var="f_fechaCreacion"/>
              <input type="text" class="form-control input-md" name="fechaCreacion" id="fechaCreacion" placeholder="dd/MM/yyyy" size="10" value="${f_fechaCreacion}"/>
			</div>
			
			<div class="form-group">
			  <label class="control-label" for="btnBuscar"></label>
			  <button id="btnBuscar" name="btnBuscar" class="btn btn-success">Buscar</button>
			  <input type="button" onclick="javascript:buscar();" class="btn btn-success" value="Buscar">
              <input type="button" onclick="javascript:nuevo();" class="btn btn-success" value="Nuevo">
			</div>
		</div>
	    <div class="col-lg-6">
	    	<div class="form-group">
			  <label class="control-label" for="txtCodigo">C&oacute;digo</label>  
			  <form:input path="idTramite" id="idTramite" size="10" cssClass="form-control input-md" />
			</div>
			
			<div class="form-group">
			  <label class="control-label" for="txtCodigo">C&oacute;digo</label>  
			  <form:input path="idTramite" id="idTramite" size="10" cssClass="form-control input-md" />
			</div>
	    </div>
    </form:form>
    </div>

    <div class="row">
	    <div class="col-lg-12">
	    		<div id="tablaDinamica">
		 		<div id="resultado">
	   			<div id="displayTagDiv">
	   			
		          <display:table name="requestScope.lTramites" requestURI="buscar.htm" class="displaytag" pagesize="25" defaultsort="1"
					defaultorder="ascending" export="false" id="row" excludedParams="ajax">
						<display:column title="Codigo de solicitud" property="solicitud.codSolicitud" sortable="true" headerClass="sortable" />
						<display:column title="Tipo de solicitud" property="solicitud.tipoSolicitud.nombreTipoSolicitud" sortable="true" headerClass="sortable" />
						<display:column title="Acciones" sortable="true" headerClass="sortable" style=" width: 80px;">
			            	<a id="ver_${row.idTramite}" href="#" data-link="ver.htm?cod=${row.idTramite}" style="border: 0px;" title="Ver"><img src="${pageContext.request.contextPath}/images/view.jpg" width="18" height="18" border="0"></a>
		                   	<a href="editar.htm?cod=${row.idTramite}" style="border: 0px;" title="Modificar"><img src="${pageContext.request.contextPath}/images/edit.png" width="18" height="18" border="0"></a>
		                   	<a href="javascript:eliminar(${row.idTramite});" style="border: 0px;" title="Eliminar"><img src="${pageContext.request.contextPath}/images/error.png" width="18" height="18" border="0"></a>
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
    
    <!-- Modal para visualizar detalle -->
	<div class="modal fade" id="modalView" tabindex="-1" role="dialog" aria-labelledby="modalViewLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	    
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="modalViewLabel">Detalle de Tr&aacute;mite</h4>
	      </div>
	      <div class="modal-body">
	        
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
	        <button type="button" class="btn btn-primary">Guardar</button>
	      </div>
	      
	    </div>
	  </div>
	</div>

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