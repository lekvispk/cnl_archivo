<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- BEGIN concluir.jsp -->
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
	    
		<div class="row">
		    <div class="col-lg-12">
		    	<div class="form-group">
		    		<label>Actualizar escritura</label>
		    		<input type="file"> 	
		    	</div>
		    </div>
	    </div>
		
		<div class="row">
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
<!-- END concluir.jsp-->