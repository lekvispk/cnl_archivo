<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- BEGIN concluir.jsp -->

<style>

.progress { position:relative; width:400px; border: 1px solid #ddd; padding: 1px; border-radius: 3px; }
.bar { background-color: #B4F5B4; width:0%; height:20px; border-radius: 3px; }
.percent { position:absolute; display:inline-block; top:3px; left:48%; }
</style>


<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <h4 class="modal-title" id="modalViewLabel">Concluir Escritura</h4>
</div>

<div class="modal-body">

	<div id="contenido">
	   
	  	<jsp:include page="../includes/error.jsp"/>
	  	
	  	 <div class="row">
		    <div class="col-lg-12 has-warning">
		    	<p class="text-center">Atencion de solicitud Nro <input size="10" type="text" value="${tramite.solicitud.codSolicitud}"/></p>
		    </div>
	    </div>
		
		<div class="row">
		    <div class="col-lg-6">
		    	<div class="form-group">
		    		<label>Nombre del solicitante: </label>
		    		<p>${tramite.solicitud.persona.nombreCompleto} </p>
		    	</div>
		    </div>
		    <div class="col-lg-6">
		    	<div class="form-group input-group">
		    		<fmt:formatDate value="${tramite.solicitud.fechaIngreso}" pattern="dd/MM/yyyy" var="fecha"/>
		    		<input type="text" value="${fecha}" class="form-control"/>
		    		<span class="input-group-addon"><img src="${pageContext.request.contextPath}/images/cal.gif" alt="D&iacute;a/Mes/A&ntilde;o" width="16" height="16" border="0" id="triggerCalr" /></span>
		    	</div>
		    </div>
	    </div>
	    
	    <c:if test="${ empty archivoPendiente }">
    	<form:form id="frmAdjuntar" name="frmAdjuntar" action="${pageContext.request.contextPath}/tramites/cargarNuevaEscritura.htm" method="POST" modelAttribute="uploadForm" enctype="multipart/form-data">
		
		<div id="preUpload">
		<div class="row">
		    <div class="col-lg-12">
		    	<div class="form-group">
					<input type="hidden" name="idDocumento" value="${tramite.idTramite}"/>
					<div class="col-lg-4"><label class="control-label" for="filebutton">Actualizar escritura</label></div>
				 	<div class="col-lg-4"><input id="file" name="file" class="input-file" type="file"></div>
		    	</div>
		    </div>
	    </div>
	    <div class="row">
	    	 <div class="col-lg-12">
	    	 	<input type="submit" id="btnuploadPendienteXXXXX"  class="btn btn-success btn-block" value="Cargar">
	    	 </div>
	    </div>
	    </div>
	    </form:form>
		</c:if>
		
		<div id="uploadOutput">
		</div>
		
		<div class="progress" style="display: none;">
			<div class="bar" style="width: 100%;"></div>
			<div class="percent"></div>
		</div>
		
		<div id="status">
		</div>

		<div id="postUpload" class="row" style="display: none;">
		    <div class="col-lg-12">
						
				<c:set var="nomArchivo" value=""/>
				<c:if test="${not empty tramite.escritura.archivos}">
					<c:set var="nomArchivo" value="${tramite.escritura.archivos[0].nombre}"/>
				</c:if>
				Se reemplazara el archivo <strong>${nomArchivo}</strong> con Kardex Nro.  ${tramite.escritura.kardex}<br>
						    	
		    </div>
	    </div>
	    
	</div>

</div>
<div class="modal-footer">
	<button type="button" class="btn btn-primary" data-dismiss="modal">Cancelar</button>
	<a class="btn btn-primary" data-target="#firmaModal" data-toggle="modal" href="${pageContext.request.contextPath}/firmar.htm?id1=${tramite.escritura.idEscritura}&id2=${tramite.solicitud.idsolicitud}">Actualizar</a>
</div>

<script type="text/javascript">
<!--
$(function() {
	/*http://malsup.com/jquery/form/#file-upload*/
	
		    
	var bar = $('.bar');
	var percent = $('.percent');
	var status = $('#status');
	

	 $('#frmAdjuntar').ajaxForm({
	        beforeSubmit: function(formData ,form ,options) {
	        	console.info('antes del submit');
	        	//options.dataType = "html";
	            $('#uploadOutput').html('Submitting...');
	        },
	        beforeSend: function() {
	        	 $('.progress').show();
	            status.empty();
	            var percentVal = '0%';
	            bar.width(percentVal);
	            percent.html(percentVal);
	        },
	        uploadProgress: function(event, position, total, percentComplete) {
	            var percentVal = percentComplete + '%';
	            bar.width(percentVal);
	            percent.html(percentVal);
	        },
	        success: function(data) {
	        	console.info('upload!!!!!!');
	            var $out = $('#uploadOutput');
	            $out.empty();
	            if (typeof data == 'object' && data.nodeType){
	            	// data = elementToString(data.documentElement, true);
	            }else if (typeof data == 'object'){
	            	//data = objToString(data);
	            }   
	            //$out.append('<div><pre>'+ data +'</pre></div>');
	            $("#preUpload").hide();
	            $("#postUpload").show();
	            
	            var percentVal = '100%';
	            bar.width(percentVal);
	            percent.html(percentVal);
	            
	        },
	        error: function(data){
	        	var $out = $('#uploadOutput');
		        $out.empty();
	        	status.html('ERROR->'+data);
	        },
	    	complete: function(xhr) {
	    	//	status.html(xhr.responseText);
	    		$('.progress').hide();
	    	}
	        
	    });
	 
});
//-->
</script>


<!-- END concluir.jsp-->