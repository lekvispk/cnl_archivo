<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security'%>
<!-- BEGIN lista.jsp-->
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
    <form:form name="frmlista" action="buscar.htm" method="post" modelAttribute="solicitud">
    <input type="hidden" name="tipoTram1.idTipotram" value="1" />
  
  	<div class="row">
  		<div class="col-lg-3"><label class="control-label">Tipo Solicitud:</label></div>
  		<div class="col-lg-3">
  			<form:select path="tipoSolicitud.idTipoSolicitud" class="form-control">
           		<form:option value="-1">-TODOS-</form:option>
               <form:options items="${ltiposolicitid}" itemLabel="nombreTipoSolicitud" itemValue="idTipoSolicitud"/>
           </form:select>
  		</div>
  		<div class="col-lg-3"><label class="control-label">Tipo Acto:</label></div>
  		<div class="col-lg-3">
  			<form:select path="tipoActo.idActo" class="form-control">
            	<form:option value="-1">-TODOS-</form:option>
                <form:options items="${ltipoacto}" itemLabel="nombreActo" itemValue="idActo"/>
            </form:select>
  		</div>
  	</div>
  	<div class="row">
  		<div class="col-lg-3"><label class="control-label">Tipo Doc:</label></div>
  		<div class="col-lg-3"><input type="text" name="idPersona" id="idPersona" class="form-control" size="15"/></div>
  		<div class="col-lg-3"><label class="control-label">Nro. Doc:</label></div>
  		<div class="col-lg-3"><input type="text" name="idPersona" id="idPersona" class="form-control" size="15"/></div>
  	</div>
  	<div class="row">
  		<div class="col-lg-3"><label class="control-label">Nombre:</label></div>
  		<div class="col-lg-9"><input type="text" name="idPersona" id="idPersona" class="form-control" size="15"/></div>
  	</div>
  	<div class="row">
  		<div class="col-lg-3"><label class="control-label">Ap. Paterno:</label></div>
  		<div class="col-lg-3"><input type="text" name="idPersona" id="idPersona" class="form-control" size="15"/></div>
  		<div class="col-lg-3"><label class="control-label">Ap. Materno:</label></div>
  		<div class="col-lg-3"><input type="text" name="idPersona" id="idPersona" class="form-control" size="15"/></div>
  	</div>
  	<div class="row">
  		<div class="col-lg-3"><label class="control-label">Solicitudes Registradas Desde:</label></div>
  		<div class="col-lg-3">
  			<div class="form-group input-group">
  			<fmt:formatDate value="${solicitud.fechaIngreso}" pattern="dd/MM/yyyy" var="f_fechaIngreso"/>
                <input type="text" name="fechaIngreso" id="fechaIngreso" size="15" value="${f_fechaIngreso}" class="form-control  input-md"/>
              	<span class="input-group-addon"><img src="${pageContext.request.contextPath}/images/cal.gif" alt="D&iacute;a/Mes/A&ntilde;o" width="16" height="16" border="0" id="triggerCald" /></span>
				<script type="text/javascript">
				Calendar.setup({
					inputField     :    "fechaIngreso",  // id del campo de texto
					ifFormat       :    "%d/%m/%Y",  // Formato de la Fecha
					showsTime      :    false,       // Flag para mostrar la Fecha
					button         :    "triggerCald",// ID del elemento que llamara al calendario
					singleClick    :    true         // Flag Modo doble-click 
				});
				</script>
  			</div>
  		</div>
  		<div class="col-lg-3"><label class="control-label">Hasta:</label></div>
  		<div class="col-lg-3">
  			<div class="form-group input-group">
  			<fmt:formatDate value="${solicitud.fechaIngreso2}" pattern="dd/MM/yyyy" var="f_fechaIngreso2"/>
                <input type="text" name="fechaIngreso2" id="fechaIngreso2" size="15" value="${f_fechaIngreso2}" class="form-control  input-md"/>
                <span class="input-group-addon"><img src="${pageContext.request.contextPath}/images/cal.gif" alt="D&iacute;a/Mes/A&ntilde;o" width="16" height="16" border="0" id="triggerCalh" /></span>
				<script type="text/javascript">
				Calendar.setup({
					inputField     :    "fechaIngreso2",  // id del campo de texto
					ifFormat       :    "%d/%m/%Y",  // Formato de la Fecha
					showsTime      :    false,       // Flag para mostrar la Fecha
					button         :    "triggerCalh",// ID del elemento que llamara al calendario
					singleClick    :    true         // Flag Modo doble-click 
				});
				</script>
  			</div>
  		</div>
  	</div>
	<%--
	<div class="row">
      	<div class="col-lg-12">
			<div class="form-group col-lg-6">
			  <label class="col-md-4 control-label"></label>
			  <p class="form-control-static">
			  	
			  </p>
			</div>
			<div class="form-group col-lg-6">
			  <label class="col-md-4 control-label"></label>  
			  <p class="form-control-static">
			  	
			  </p>
			</div>
		</div>
	</div>
	 --%>
	</form:form>
	<div class="row">
		<div class="col-lg-12">
			<input type="button" onclick="javascript:buscar();" class="btn btn-success" value="Buscar">
	        <input type="button" onclick="javascript:nuevo();" class="btn btn-success" value="Nuevo">
		</div>
	</div>
	
	<div class="row">
		<div class="col-lg-12">
		    	<div id="tablaDinamica">
			 	<div id="resultado">
		   		<div id="displayTagDiv">
		   			
		          <display:table name="requestScope.lSolicitudes" requestURI="buscar.htm" class="displaytag" pagesize="25" defaultsort="1"
					defaultorder="ascending" export="false" id="row" excludedParams="ajax">
						<display:column title="F. Ingreso" property="fechaIngreso" format="{0,date,dd/MM/yyyy}" sortable="true" headerClass="sortable"/>
						<display:column title="Solicitante" property="persona.nombreCompleto" sortable="true" headerClass="sortable" />
						<display:column title="Tipo" property="tipoSolicitud.nombreTipoSolicitud" sortable="true" headerClass="sortable" />
						<display:column title="Acto" property="tipoActo.nombreActo" sortable="true" headerClass="sortable" />
						<display:column title="Acciones" sortable="true" headerClass="sortable" style=" width: 100px;">
			            	<a id="ver_${row.idsolicitud}" href="#" data-link="ver.htm?cod=${row.idsolicitud}" style="border: 0px;" title="Ver"><img src="${pageContext.request.contextPath}/images/view.jpg" width="18" height="18" border="0"></a>
			            	<a href="editar.htm?cod=${row.idsolicitud}" style="border: 0px;" title="Modificar"><img src="${pageContext.request.contextPath}/images/edit.png" width="18" height="18" border="0"></a>
		                   	<a href="${pageContext.request.contextPath}/pendientes/preCargaEscritura.htm?cod=${row.idsolicitud}" style="border: 0px;" title="Seleccionar Escrituras"><img src="${pageContext.request.contextPath}/images/view.jpg" width="18" height="18" border="0"></a>                                    	
		                   	<a href="javascript:eliminar(${row.idsolicitud});" style="border: 0px;" title="Eliminar"><img src="${pageContext.request.contextPath}/images/error.png" width="18" height="18" border="0"></a>
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
<!-- END lista.jsp-->