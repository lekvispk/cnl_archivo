<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- BEGIN usuario_clave.jsp -->
	
<script>
function nuevo(){
	document.forms[0].action = "${pageContext.request.contextPath}/cambioDeClave.htm";
   	document.forms[0].submit();
}

function cancelar(){
    document.forms[0].action = "${pageContext.request.contextPath}/inicio.htm";
    document.forms[0].submit();
}
</script>
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
          CAMBIO DE CONTRASE&Ntilde;A
      </div>
      
      <div class="panel-body">
               
   		<form action="${pageContext.request.contextPath}/cambioDeClave.htm" name="frm1" method="post">
	
	<div class="row">
      	<div class="col-lg-6">
			<div class="col-lg-4"><label class="control-label">Clave actual:</label></div>
			<div class="col-lg-8"><input type="password" name="clave" size="30" class="form-control"/></div>
		</div>
	</div>
	<div class="row">
      	<div class="col-lg-6">
      		<div class="col-lg-4"><label class="control-label">Nueva clave:</label></div>
			<div class="col-lg-8"><input type="password" name="nueva" size="30" class="form-control"/></div>
		</div>
	</div>
	<div class="row">
      	<div class="col-lg-6">
      		<div class="col-lg-4"><label class="control-label">Confirmar clave:</label></div>
			<div class="col-lg-8"><input type="password" name="confirmar" size="30" class="form-control"/></div>
		</div>
	</div>
	<div class="row">
      	<div class="col-lg-12">	
			<div class="form-group">
			 	<input type="button" onclick="javascript:nuevo();" class="btn btn-success" value="Aceptar">
				<input type="button" onclick="javascript:cancelar();" class="btn btn-success" value="Cancelar">
			</div>
		</div>
	</div>
	</form>

	</div>
     </div>
          
     </div>
     </div>
     
    </div>
                            
	<jsp:include page="../includes/pie.jsp" flush="true"/>
   </body>
</html>
<!-- END usuario_clave.jsp -->	