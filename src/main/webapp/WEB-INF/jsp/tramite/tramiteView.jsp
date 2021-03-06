<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security'%>

<!-- BEGIN tramiteView.jsp-->
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <h4 class="modal-title" id="modalViewLabel">Detalle de Tr&aacute;mite</h4>
</div>
<div class="modal-body">

Datos de la Solicitud<br>
Codigo de Solicitud: ${tramite.solicitud.codSolicitud }<br>
Fecha de Solicitud: <fmt:formatDate value="${tramite.fechaConclusion }" pattern="dd/MM/yyyy"/> <br>  
Solicitante: ${tramite.solicitud.persona.nombreCompleto }<br>
<br>
Datos del Tramite<br>
Cantidad de hojas: ${tramite.cantHojas }<br>
Costo por hoja: ${tramite.costoHoja }<br>
Precio Total: ${tramite.costoTotal }<br>
<br>

<div class="list-group">
<c:forEach items="${tramite.tramiteAdjuntos}" var="doc">
<div class="list-group-item" >
	<i class="fa fa-comment fa-fw"></i> ${doc.nombre}
	<span class="pull-right text-muted small"><em><a href="${pageContext.request.contextPath}/tramites/descargar.htm?id=${doc.idAdjunto}&idTr=${tramite.idTramite}" title="Descargar">Ver</a></em></span>
</div>
</c:forEach>
</div>

<c:if test="${not empty tramite.informeSolicitud }">
Informe: ${tramite.informeSolicitud }<br>
<br>
</c:if>

<c:if test="${not empty tramite.observacionesNotario }">
Observaciones del notario: ${tramite.observacionesNotario }<br>
Fecha de conclusion: <fmt:formatDate value="${tramite.fechaConclusion }" pattern="dd/MM/yyyy"/> <br>
<br>
</c:if>
</div>
<div class="modal-footer">
  <button type="button" class="btn btn-primary" data-dismiss="modal">Cerrar</button>
</div>	      
<!-- END tramiteView.jsp-->