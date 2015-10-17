<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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