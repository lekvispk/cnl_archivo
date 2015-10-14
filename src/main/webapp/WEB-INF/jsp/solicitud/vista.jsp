<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!-- vista.jsp BEGIN -->

<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <h4 class="modal-title" id="modalViewLabel">Detalle de Solicitud</h4>
</div>

<div class="modal-body">

      <div id="contenido"  align="center">
          <!--Capa que contiene el cuerpo de la plantilla-->                                     
                
                <jsp:include page="../includes/error.jsp"/>
                
                <form:form name="frm2" action="#" method="post" modelAttribute="solicitud">
                 
                 <table class="bloqueTablaLineal" >
                     <tr>
                         <th>Fecha de Solicitud:</th>
                         <td><fmt:formatDate value="${solicitud.fechaIngreso}" pattern="dd/MM/yyyy"/></td>
                     </tr>
                     <tr>
                         <th>Tipo Solicitud:</th>
                         <td>${solicitud.tipoSolicitud.nombreTipoSolicitud}</td>
                     </tr>
                      <tr>
                         <th>Acto:</th>
                         <td>${solicitud.tipoActo.nombreActo}</td>
                     </tr>
                     <tr>
                         <th>Comprador:</th>
                         <td>${solicitud.tramComprador}</td>
                     </tr>
                       <tr>
                         <th>Vendedor:</th>
                         <td>${solicitud.tramVendedor}</td>
                     </tr>
                     <tr>
                         <th>Solicitante:</th>
                         <td>${solicitud.persona.nombreCompleto}</td>
                     </tr>
                     <tr>
                         <th>Nro. Kardex:</th>
                         <td>${solicitud.tramKardex}</td>
                     </tr>
                     <tr>
                         <th>Nro. Escritura:</th>
                         <td>${solicitud.tramEscritura}</td>
                     </tr>
                     <c:if test="${not empty solicitud.tramApoderado}">
                    <tr>
                         <th>Nombre Apoderado:</th>
                         <td>${solicitud.tramApoderado}</td>
                     </tr>
                     <tr>
                         <th>Doc. Apoderado:</th>
                         <td>${solicitud.tramApodDocumento}</td>
                     </tr>
                     <tr>
                         <th>Telefono:</th>
                         <td>${solicitud.tramApodTelefono}</td>
                     </tr>
                      <tr>
                         <th>Direccion:</th>
                         <td>${solicitud.tramApodDireccion}</td>
                     </tr>
                     </c:if>
                     <tr>
                         <th>Observaciones:</th>
                         <td>${solicitud.tramSolicitado}</td>
                     </tr>
                 </table> 
                 
          </form:form>
          
      </div>
                        
</div>
<div class="modal-footer">
  <button type="button" class="btn btn-primary">Cerrar</button>
</div>	
<!-- vista.jsp END -->