<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!-- BEGIN tramiteForm.jsp-->
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
          Registro de Tramites
      </div>
      
      <div class="panel-body">
      <div class="row">
      
      <div class="col-lg-6">
      
      	<div class="form-group">
		  <label class="control-label">Atencion de Solicitud N. ${tramite.solicitud.idsolicitud}</label>  
		</div>
		
		<div class="form-group">
		  <label class="col-md-4 control-label">Tipo de Solicitud</label>  
		  <label class="control-label">${tramite.solicitud.tipoSolicitud.nombreTipoSolicitud}</label>
		</div>
		
		<div class="form-group">
		  <label class="col-md-4 control-label">Nombre del Solicitante</label>  
		  <label class="control-label">${tramite.solicitud.persona.nombreCompleto}</label>
		</div>
		
		<div class="form-group">
		  <label class="col-md-4 control-label">Nro de Telefono</label>  
		  <label class="control-label">${tramite.solicitud.persona.telefono}</label>
		</div>
		
		<div class="form-group">
		  <label class="col-md-4 control-label">Correo Electronico</label>  
		  <label class="control-label">${tramite.solicitud.persona.email}</label>
		</div>
		
		<div class="form-group">
		  <label class="control-label">Datos de los Contratantes:</label>
		</div>
		
		<div class="form-group">
		  <label class="col-md-4 control-label">Nombre Vendedor</label>  
		  <label class="control-label">${tramite.solicitud.tramVendedor}</label>
		</div>
		
		<div class="form-group">
		  <label class="col-md-4 control-label">Nombre Comprador</label>  
		  <label class="control-label">${tramite.solicitud.tramComprador}</label>
		</div>
		
		<div class="form-group">
		  <label class="control-label">Datos de la Escritura</label>
		</div>
		
		<div class="form-group">
		  <label class="col-md-4 control-label">Tramitado en el oficio notarial</label>  
		  <label class="control-label">${tramite.escritura.notaria.nombre}</label>
		</div>
		
		<div class="form-group">
		  <label class="col-md-4 control-label">Fecha de la Escritura p&uacute;blica</label>  
		  <label class="control-label">${tramite.escritura.fechaEscritura}</label>
		</div>
		
		<div class="form-group">
		  <label class="col-md-4 control-label">Nro. Kardex</label>  
		  <label class="control-label">${tramite.escritura.kardex}</label>
		</div>
		
		<div class="form-group">
		  <label class="col-md-4 control-label">Nro. Folio</label>  
		  <label class="control-label">${tramite.escritura.numeroFolios}</label>
		</div>
		
		<div class="form-group">
		  <label class="col-md-4 control-label">Nro. Instrumento</label>  
		  <label class="control-label">${tramite.escritura.numeroInstrumento}</label>
		</div>
		
		<div class="form-group">
		  <label class="col-md-4 control-label">Nro. Minuta</label>  
		  <label class="control-label">${tramite.escritura.numeroMinuta}</label>
		</div>
		
		<div class="form-group">
		  <label class="col-md-4 control-label">Nro. Hojas</label>  
		  <label class="control-label">${tramite.escritura.numeroMinuta}</label>
		</div>
		
		<div class="form-group">
		  <label class="col-md-4 control-label">Costo Servicio</label>  
		  <label class="control-label">${tramite.escritura.numeroMinuta}</label>
		</div>
		
		<div class="form-group">
		  <label class="col-md-4 control-label">Nro. Firmas Faltantes</label>  
		  <label class="control-label">${tramite.escritura.numeroMinuta}</label>
		</div>
		
		<div class="form-group">
		  <label class="col-md-4 control-label">Factura Nro.</label>  
		  <label class="control-label">${tramite.escritura.numeroFactura}</label>
		</div>
		
		<div class="form-group">
		  <label class="control-label">Adjuntos</label>
		</div>
		
		<!-- File Button --> 
		<div class="form-group">
		  <label class="col-md-4 control-label" for="filebutton">Adjuntar</label>
		  <input id="filebutton" name="filebutton" class="input-file" type="file">
		</div>
	 <%--
	 	<c:if test="${ not empty  escritura.archivos }">
           <c:forEach items="${escritura.archivos}" var="doc">
			<a href="${root_url}archivo/descargar.htm?id=${doc.idArchivo}">${doc.nombre}</a>&nbsp;
           </c:forEach>
        </c:if>
       --%>    
	 <form:form name="frm2" action="nuevo.htm" method="post" modelAttribute="tramite">	
	
      	<input type="hidden" name="idTramite">
     
		<!-- Textarea -->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="detalleNotificacion">Detalle de Notificacion</label>
		  <textarea class="form-control" id="detalleNotificacion" name="detalleNotificacion">Ingrese el detalle</textarea>
		</div>
		
	    <button id="button1id" name="button1id" class="btn btn-success">Grabar</button>
	    <button id="button2id" name="button2id" class="btn btn-danger">Cancelar</button>
		  
     </form:form>
     
     </div>
     
     <div class="col-lg-6">
     </div>
     
     </div>
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
		    document.forms[0].action = "lista.htm";
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