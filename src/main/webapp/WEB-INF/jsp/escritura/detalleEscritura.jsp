<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!-- BEGIN detalleEscritura.jsp -->
	
<script>
	
	function firmar(id){
		
		window.open("http://localhost:8080/FirmaDigitalCnl/firmatramite.do?method=firmarTramite&idp=" + id, "firmaPT", "toolbar=0,location=0,directories=0,status=no,menubar=0,scrollbars=yes,resizable=yes,width=780,height=580,titlebar=no");
		
	}
</script>
		
		
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <h4 class="modal-title" id="modalViewLabel">Detalle de Escritura</h4>
</div>

<div class="modal-body">

<div id="contenido">
   
  <jsp:include page="../includes/error.jsp"/>
  
  <form:form name="frm2" action="#" method="post" modelAttribute="escritura">
  
 <form:hidden path="idEscritura"/>
  <table class="bloqueTablaLineal" cellspacing="6">
       <tr>
           <th>Notaria:</th>
           <td>${escritura.notaria.nombre}</td>
       </tr>
       <tr>
           <th>Acto:</th>
           <td>{escritura.tipoActos.nombreActo} </td>
       </tr>
       <tr>
           <th>Nro. Kardex:</th>
           <td>${escritura.kardex}</td>
       </tr>
       <tr>
           <th>Nro. Escritura:</th>
           <td>${escritura.numeroDoc}</td>
       </tr>
        <tr>
           <th>Nro. Instrumento:</th>
           <td>${escritura.numeroInstrumento}</td>
       </tr>
        <tr>
           <th>Nro. Folios:</th>
           <td>${escritura.numeroFolios}</td>
       </tr>
       <tr>
           <th>Cantidad de Hojas:</th>
           <td>${escritura.cantidadFojas}</td>
       </tr>
       <tr>
           <th>Fecha de Documento:</th>
           <td><fmt:formatDate value="${escritura.fechaEscritura}" pattern="dd/MM/yyyy"/></td>
       </tr>
        <tr>
           <th>Ubicacion Fisica:</th>
           <td>${escritura.ubicacionFisica}</td>
       </tr>
        <tr>
           <th>Ubicacion Digital:</th>
           <td>${escritura.ubicacionDigital}</td>
       </tr> 
        <tr>
           <th>Nro. de firmas faltantes:</th>
           <td><input type="text" /></td>
       </tr> 
        <tr>
           <th>Costo del Servicio:</th>
           <td><input type="text" /> <button>Calcular</button>  </td>
       </tr>                                    
   </table>
    
   <h3> <span>PARTICIPANTES</span> </h3>
   <table id="myTable" width="90%" align="center">
 	<thead>
 	<tr>
 		<th class="tituloTabla" axis="string">Tipo</th>
 		<th class="tituloTabla" axis="string">Persona</th>
 		<th class="tituloTabla" axis="string">Documento</th>
       </tr>
 	</thead>
 	<tbody>
	 
	<c:if test="${not empty limplicados}">
		<c:forEach items="${limplicados}" var="row">
	 	<tr>                                    
	         <td>&nbsp;<c:out value="${row.tipoRelacion.relDescripcion}"/></td>
	         <td>&nbsp;<c:out value="${row.persona.nombreCompleto}"/></td>
	         <td>&nbsp;<c:out value="${row.persona.numDocumento}"/></td>
	     </tr>
	   </c:forEach>
	</c:if>
	
	</tbody>
</table>

  <c:if test="${ not empty escritura.archivos }">
    <h3> <span>ARCHIVOS</span> </h3>
    
    <table class="bloqueTablaLineal" >
	<c:forEach items="${escritura.archivos}" var="doc">
		<tr>
	         <th>Descargar Archivo:</th>
             <td><a href="descargar.htm?id=${doc.idArchivo}">${doc.nombre}</a>&nbsp;</td>
        </tr>
        <tr>
             <th>Firmar Archivo:</th>
             <td><a href="javascript:firmar(${doc.idArchivo});">${doc.nombre}</a>&nbsp;</td>
         </tr>
    </c:forEach>
    </table>
   </c:if>
  
</form:form>
    
</div>

</div>
<div class="modal-footer">
  <button type="button" class="btn btn-primary" id="modal_detalleEscritura_guardar">Guardar</button>
</div>	

<!-- END detalleEscritura.jsp -->