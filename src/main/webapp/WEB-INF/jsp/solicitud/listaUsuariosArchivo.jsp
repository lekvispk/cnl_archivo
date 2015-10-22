<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!-- BEGIN listaUsuariosArchivo.jsp-->

<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <h4 class="modal-title" id="modalViewLabel">Seleccionar usuario de Archivo</h4>
</div>

<form action="seleccionar.htm" method="post">
<div class="modal-body">
      <div id="contenido"  align="center">
          <jsp:include page="../includes/error.jsp"/>
            
          <input type="hidden" name="idSolicitud" value="${idSolicitud}"/>
          <input type="hidden" name="idEscritura" value="${idEscritura}"/>
          
          <c:forEach items="${ lsUsuarios }" var="usuario">
          	<label>
          	<input type="radio" name="username" value="${usuario.username}" checked="checked"> ${usuario.username } 
          	</label>
          	<br>
          </c:forEach>
        
      </div>            
</div>
<div class="modal-footer">
  <button type="submit" class="btn btn-primary"  >Derivar</button>
  <button type="button" class="btn btn-primary" data-dismiss="modal" >Cerrar</button>
</div>	
 </form>
<!-- END listaUsuariosArchivo.jsp-->