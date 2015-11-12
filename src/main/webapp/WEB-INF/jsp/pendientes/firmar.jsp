<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- BEGIN firmar.jsp -->
<script src="${pageContext.request.contextPath}/js/archivo.js"></script>

<form action="${pageContext.request.contextPath}/firmar.htm" name="frmFirmar" method="post">
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <h4 class="modal-title" id="modalViewLabel">Concluir Escritura</h4>
</div>

<div class="modal-body">

	<div id="contenido">

	<input type="hidden" name="id1" value="${id1}">
	<input type="hidden" name="id2" value="${id2}">	
	<select name="cmbNotario">
		<c:forEach items="${listaNotarios}" var="firma">
		<option>${firma }</option>
		</c:forEach>
	</select>
	
	<br>
	Documento a firmar:- ${archivoPendiente.nombre }
	
	 
<%--
		<form class="jqtransform" >
	
		<object type="application/x-java-applet" height="300" width="400">
		  <param name="code" value="pe.org.cnl.gestiondoc.firmador.FirmadorApp" />
		  <param name="archive" value="app/firmaApplet.jar" />
		  <param name="id1" value="<%=request.getParameter("id1")%>">
	      <param name="id2" value="<%=request.getParameter("id2")%>">
		  Applet failed to run.  No Java plug-in was found.
		</object>
     	
		</form>
	 --%>	
	</div>

</div>
<div class="modal-footer">
	<button type="button" class="btn btn-primary" data-dismiss="modal">Cancelar</button>
	<button type="submit" class="btn btn-primary" id="btnFirmarNuevaEscritura">Firmar</button>
</div>
</form>
<!-- END firmar.jsp-->