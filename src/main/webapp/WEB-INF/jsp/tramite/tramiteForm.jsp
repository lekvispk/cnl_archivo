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
      	<div class="col-lg-12">
      
      	<div class="form-group col-lg-12">
		  <label class="control-label">Atencion de Solicitud N. ${tramite.solicitud.idsolicitud}</label>  
		</div>
		
		</div>
	</div>
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
     
	 <div class="row">
      	<div class="col-lg-6">
      	
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
       
      </div>
     </div>
     
      <div class="row">
      	<div class="col-lg-6">
		 <form:form name="frm2" action="atender.htm" method="post" modelAttribute="tramite">	
		  	<form:hidden path="idTramite"/>
	    	<!-- Textarea -->
			<div class="form-group">
			  <label class="col-md-4 control-label" for="detalleNotificacion">Detalle de Notificacion</label>
			  <form:textarea class="form-control" path="informeSolicitud"/>
			</div>
			
		    <button id="button1id" name="button1id" class="btn btn-success">Grabar</button>
		    <button id="button2id" name="button2id" class="btn btn-success">Cancelar</button>
		 </form:form>
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