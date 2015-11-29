<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security'%>

<!-- BEGIN tramiteForm.jsp-->
	<spring:url value="/" var="root_url" />
	
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
         Atencion de Solicitud N. ${tramite.solicitud.idsolicitud}
      </div>
      
      <div class="panel-body">
      
	<c:if test="${ tramite.estado le 2 }">
	<div class="row">
      	<div class="col-lg-12">
      	
		<div class="form-group col-lg-6">
		  <label class="col-md-4 control-label">Tipo de Solicitud</label>
		  <p class="form-control-static">${tramite.solicitud.tipoSolicitud.nombreTipoSolicitud}</p>
		</div>
		
		<div class="form-group col-lg-6">
		  <label class="col-md-4 control-label">Nombre del Solicitante</label>  
		  <p class="form-control-static">${tramite.solicitud.persona.nombreCompleto}</p>
		</div>
		
		</div>
	</div>
	<div class="row">
      	<div class="col-lg-12">
      	
		<div class="form-group  col-lg-6">
		  <label class="col-md-4 control-label">Nro de Telefono</label>  
		  <p class="form-control-static">${tramite.solicitud.persona.telefono}</p>
		</div>
		
		<div class="form-group col-lg-6">
		  <label class="col-md-4 control-label">Correo Electronico</label>  
		  <p class="form-control-static">${tramite.solicitud.persona.email}</p>
		</div>
		</div>
	</div>

	<div class="form-group">
	  <label class="control-label">Datos de los Contratantes:</label>
	</div>
		
	<div class="row">
      	<div class="col-lg-12">
      	
		<div class="form-group col-lg-6">
		  <label class="col-md-4 control-label">Nombre Vendedor</label>  
		  <p class="form-control-static">${tramite.solicitud.tramVendedor}</p>
		</div>
		
		<div class="form-group col-lg-6">
		  <label class="col-md-4 control-label">Nombre Comprador</label>  
		  <p class="form-control-static">${tramite.solicitud.tramComprador}</p>
		</div>
		
		</div>
	</div>

	<div class="form-group">
	  <label class="control-label">Datos de la Escritura</label>
	</div>

	<div class="row">
      	<div class="col-lg-12">
      	
		<div class="form-group col-lg-6">
		  <label class="col-md-4 control-label">Tramitado en el oficio notarial</label>  
		  <p class="form-control-static">${tramite.escritura.notaria.nombre}</p>
		</div>
		
		<div class="form-group col-lg-6">
		  <label class="col-md-4 control-label">Fecha de la Escritura p&uacute;blica</label>  
		  <p class="form-control-static">${tramite.escritura.fechaEscritura}</p>
		</div>
	
		</div>
	</div>
	<div class="row">
      	<div class="col-lg-12">
      		
		<div class="form-group col-lg-6">
		  <label class="col-md-4 control-label">Nro. Kardex</label>  
		  <p class="form-control-static">${tramite.escritura.kardex}</p>
		</div>
		
		<div class="form-group col-lg-6">
		  <label class="col-md-4 control-label">Nro. Folio</label>  
		  <p class="form-control-static">${tramite.escritura.numeroFolios}</p>
		</div>
		
		</div>
	</div>
	<div class="row">
      	<div class="col-lg-12">
      	
		<div class="form-group col-lg-6">
		  <label class="col-md-4 control-label">Nro. Instrumento</label>  
		  <p class="form-control-static">${tramite.escritura.numeroInstrumento}</p>
		</div>
		
		<div class="form-group col-lg-6">
		  <label class="col-md-4 control-label">Nro. Minuta</label>  
		  <p class="form-control-static">${tramite.escritura.numeroMinuta}</p>
		</div>
		
		</div>
	</div>
	<div class="row">
      	<div class="col-lg-12">
      	
		<div class="form-group col-lg-6">
		  <label class="col-md-4 control-label">Nro. Hojas</label>  
		  <p class="form-control-static">${tramite.escritura.numeroMinuta}</p>
		</div>
		
		<div class="form-group col-lg-6">
		  <label class="col-md-4 control-label">Costo Servicio</label>  
		  <p class="form-control-static">${tramite.escritura.numeroMinuta}</p>
		</div>

		</div>
	</div>
	<div class="row">
      	<div class="col-lg-12">
      	
		<div class="form-group col-lg-6">
		  <label class="col-md-4 control-label">Nro. Firmas Faltantes</label>  
		  <p class="form-control-static">${tramite.escritura.numeroMinuta}</p>
		</div>
		
		<div class="form-group col-lg-6">
		  <label class="col-md-4 control-label">Factura Nro.</label>  
		  <p class="form-control-static">${tramite.escritura.numeroFactura}&nbsp;</p>
		</div>
	 	
	 	</div>
     </div>
     </c:if>
     
     
     <c:if test="${ tramite.idTramite != 0 && tramite.estado le 2}">
	
   	<div class="row">
     
      <div class="col-lg-6">
      	<div class="panel panel-default">
			<div class="panel-heading">
				<i class="fa fa-bell fa-fw"></i>Archivos Adjuntos
			</div>
			<div class="panel-body">
				
				<div class="list-group">
					<c:forEach items="${tramite.tramiteAdjuntos}" var="doc">
					<div class="list-group-item" >
						<i class="fa fa-comment fa-fw"></i> ${doc.nombre}
						<security:authorize ifAnyGranted="ROLE_ARCHIVO,ROLE_ADMIN">
						<span class="pull-right text-muted small"><em><a href="${root_url}tramites/eliminarAdjunto.htm?id=${doc.idAdjunto}&idTr=${tramite.idTramite}" title="Eliminar">Eliminar</a></em></span>
						</security:authorize>
						<security:authorize ifAnyGranted="ROLE_NOTARIO">
						<span class="pull-right text-muted small"><em><a href="${root_url}tramites/eliminarAdjunto.htm?id=${doc.idAdjunto}&idTr=${tramite.idTramite}" title="Descargar">Ver</a></em></span>
						</security:authorize>
					</div>
					</c:forEach>
				</div>
				
				<form:form id="frmAdjuntar" name="frmAdjuntar" action="${root_url}tramites/cargarAdjunto.htm" method="POST" modelAttribute="uploadForm" enctype="multipart/form-data">
					<input type="hidden" name="idDocumento" value="${tramite.idTramite}"/>
					<input id="file" name="file" class="btn" type="file">
					<input type="button" class="btn btn-default btn-block" value="Cargar" id="btnUploadTramiteAdjunto">
			 	</form:form> 
			 	
			</div>
		</div>
      </div>
    </div>

      <div class="row">
      	<div class="col-lg-6">
		 <form:form name="frm2" action="atender.htm" method="post" modelAttribute="tramite">	
		  	<form:hidden path="idTramite"/>
	    	
	    	<!-- Textarea -->
			<div class="form-group">
			  <label class="control-label" for="detalleNotificacion">Informe de Escritura</label>
			  <form:textarea class="form-control" path="informeSolicitud" rows="6"/>
			</div>
			
			<input type="button" id="button2id" name="button2id" class="btn btn-success" onclick="cancelar();" value="Cancelar"/>
		    <button id="button1id" name="button1id" class="btn btn-success">Guardar</button>
		   
		 </form:form>
     	</div>
     </div>
         
     </c:if>
     
     <c:if test="${ tramite.estado ge 3 }">
     
     <div class="row">
      	<div class="col-lg-12">
      		<div class="form-group col-lg-6">
			  <label class="col-md-4 control-label">Nombre del Solicitante</label>  
			  <p class="form-control-static">${tramite.solicitud.persona.nombreCompleto}</p>
			</div>
			<div class="form-group col-lg-6">
			  <label class="col-md-4 control-label">Fecha de Solicitud</label>
			  <p class="form-control-static"><fmt:formatDate value="${tramite.solicitud.fechaIngreso}" pattern="dd/MM/yyyy"/></p>
			</div>
		</div>
	</div>
	<div class="row">
      	<div class="col-lg-12">      		
			<div class="form-group col-lg-6">
			  <label class="col-md-4 control-label">Nro. Kardex</label>  
			  <p class="form-control-static">${tramite.escritura.kardex}</p>
			</div>
		</div>
	</div>
	
	</c:if>
	
	<c:if test="${ tramite.estado eq 3 }">
    <div class="row">
      	<div class="col-lg-12">      		
			<div class="form-group col-lg-6">
			  <label class="control-label">Informe de Solicitud</label>  
			  <p class="form-control-static">${tramite.informeSolicitud}</p>
			</div>
		</div>
	</div>
	 <div class="row">
      	<div class="col-lg-6">
		 <form:form name="frm2" action="responder.htm" method="post" modelAttribute="tramite">	
		  	<form:hidden path="idTramite"/>
	    	
			<div class="form-group">
			  <label class="col-md-4 control-label" for="observacionesNotario">Observaciones del Notario</label>
			  <form:textarea class="form-control" path="observacionesNotario"/>
			</div>
			
			<div class="form-group">
			  <label class="col-md-4 control-label" for="fechaConclusion">Fecha de Conclusion</label>
			  <fmt:formatDate value="${tramite.fechaConclusion}" pattern="dd/MM/yyyy" var="f_fechaConclusion"/>
              <input type="text" name="fechaConclusion" id="fechaConclusion" size="15" value="${f_fechaConclusion}"/>
              <img src="${pageContext.request.contextPath}/images/cal.gif" alt="D&iacute;a/Mes/A&ntilde;o" width="16" height="16" border="0" id="triggerCalr" />
				<script type="text/javascript">
				Calendar.setup({
					inputField     :    "fechaConclusion",  // id del campo de texto
					ifFormat       :    "%d/%m/%Y",  // Formato de la Fecha
					showsTime      :    false,       // Flag para mostrar la Fecha
					button         :    "triggerCalr",// ID del elemento que llamara al calendario
					singleClick    :    true         // Flag Modo doble-click 
				});
				</script>
			</div>
						
			<input type="button" id="button2id" name="button2id" class="btn btn-success" onclick="cancelar();" value="Cancelar"/>
		    <button id="button1id" name="button1id" class="btn btn-success">Guardar</button>
		   
		 </form:form>
     	</div>
     </div>
	
     </c:if>
     <c:if test="${ tramite.estado eq 4 }">
    
    <div class="row">
      	<div class="col-lg-12">      		
			<div class="form-group col-lg-6">
			  <label class="col-md-4 control-label">Observaciones del Notario</label>  
			  <p class="form-control-static">${tramite.observacionesNotario}</p>
			</div>
		</div>
	</div>
	
	<div class="row">
      	<div class="col-lg-6">
			<div class="form-group">
			  <label class="col-md-4 control-label" for="fechaConclusion">Fecha de Conclusion</label>
			  <fmt:formatDate value="${tramite.fechaConclusion}" pattern="dd/MM/yyyy"/>
	            <img src="${pageContext.request.contextPath}/images/cal.gif" alt="D&iacute;a/Mes/A&ntilde;o" width="16" height="16" border="0" id="triggerCalr" />
			
			</div>
		</div>
	</div>
	
	 <div class="row">
      	<div class="col-lg-6">
		 <form:form name="frm2" action="notificar.htm" method="post" modelAttribute="tramite">	
		  	<form:hidden path="idTramite"/>
				
			<div class="form-group">
			  <label class="control-label" for="detalleNotificacion">Detalle de la Notificacion</label>
			  <form:textarea class="form-control" path="detalleNotificacion"/>
			</div>
			
			<input type="button" id="button2id" name="button2id" class="btn btn-success" onclick="cancelar();" value="Cancelar"/>
		    <button id="button1id" name="button1id" class="btn btn-success">Notificar</button>
		   
		 </form:form>
     	</div>
     </div>
	
     </c:if>
     
     </div>
     </div>
          
     </div>
     </div>
     
    </div>
                            
	<jsp:include page="../includes/pie.jsp" flush="true"/>
	
	<script>
	
		function nuevo(){
	      /*
	        if (document.forms[0].obs9.value.length > 500 )  {
	          alert("La longitud de la observacion del remitente no debe ser mayor a 500");
	          return false;
	        }  */
	        document.forms[0].action = "nuevo.htm";
	    	document.forms[0].submit();
		}
		
		function cancelar(){
		    document.forms[0].action = "lista.htm?estado=1";
		    document.forms[0].submit();
		}
		
    $(function() {
    	
		$( "#tagsPersona" ).autocomplete({
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
	                    /* console.log(textStatus);*/
	                }
	            });
	        },
			minLength: 2,
			select: function( event, ui ) {
				  $( "#idPersona" ).val( ui.item.id );
			}
		});
		
    });	
    
    </script>
    
   </body>
<!-- END tramiteForm.jsp-->
</html>