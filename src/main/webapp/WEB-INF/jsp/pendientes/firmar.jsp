<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- BEGIN firmar.jsp -->
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <h4 class="modal-title" id="modalViewLabel">Concluir Escritura</h4>
</div>

<div class="modal-body">

	<div id="contenido">

		<form class="jqtransform" >
	
		<object type="application/x-java-applet" height="300" width="400">
		  <param name="code" value="pe.org.cnl.gestiondoc.firmador.FirmadorApp" />
		  <param name="archive" value="app/firmaApplet.jar" />
		  <param name="id1" value="<%=request.getParameter("id1")%>">
	      <param name="id2" value="<%=request.getParameter("id2")%>">
		  Applet failed to run.  No Java plug-in was found.
		</object>
     	
		</form>
		
	</div>

</div>
<div class="modal-footer">
	<button type="button" class="btn btn-primary" data-dismiss="modal">Cancelar</button>
</div>
<% System.out.println("sfdsfdsfdsfddddddddddddddd"); %>
<!-- END firmar.jsp-->