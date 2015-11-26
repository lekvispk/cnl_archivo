<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- BEGIN registroNotario.jsp -->

<jsp:include page="../includes/cabecera.jsp"/>
<spring:url value="/" var="root_url" />
 <script>

   	function cargar(){
   	    document.forms[1].action = "${root_url}archivo/cargarEscritura.htm";
   	    document.forms[1].submit();
   	}
   	
   	function eliminarArchivo( idArchivo ,isEscritura){
   	    if(confirm('Está segur de eliminar el archivo')){
   	    	var url = '${root_url}archivo/eliminarArchivo.htm?id='+idArchivo+'&isEscritura='+isEscritura ;
   	    	console.info( 'ir a ' + url) ;
   	    	window.location= url ;
   	    }
   	}
   	
	function nuevo(){
        document.forms[0].action = "nuevo.htm";
    	document.forms[0].submit();
	}
	
	function cancelar(){
	    document.forms[0].action = "lista.htm";
	    document.forms[0].submit();
	}
	
	function implicados(){
	    document.forms[0].action = "registrarNotario.htm";
	    document.forms[0].submit();
	}
</script>


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
         REGISTRO DE ESCRITURA
      </div>
      
    <div class="panel-body">

          <form:form name="frm2" action="nuevo.htm" method="post" modelAttribute="notario">
          <form:hidden path="idNotaria"/>
          
          <div class="row">
		    	<div class="col-lg-3">
					<label class="control-label">Notaria:</label>
		    	</div>
		    	<div class="col-lg-3"><form:input path="nombre" size="50" cssClass="form-control"/></div>
		   </div>
		   <div class="row">
		    	<div class="col-lg-3">
					<label class="control-label">Email:</label>
		    	</div>
		    	<div class="col-lg-3"><form:input path="email" size="15" cssClass="form-control"/></div>
		   </div>
		   <div class="row">
		    	<div class="col-lg-3">
					<label class="control-label">Encargado de archivo:</label>
		    	</div>
		    	<div class="col-lg-3">
		    		<c:if test="${notario.encargadoArchivo eq '1'}">
		    			Si
		    		</c:if>
		    		<c:if test="${notario.encargadoArchivo ne '1'}">
		    			No
		    		</c:if>
		    		<form:hidden path="encargadoArchivo"/>
		    	</div>
		   </div>
		   <div class="row">
		    	<div class="col-lg-3">
					<label class="control-label">Estado:</label>
		    	</div>
		    	<div class="col-lg-3">
		    		<c:if test="${notario.estado eq '1'}">
		    			Activo
		    		</c:if>
		    		<c:if test="${notario.estado ne '1'}">
		    			Inactivo
		    		</c:if>
		    		<form:hidden path="estado"/>
		    	</div>
		   </div>
       	   <div class="row">
	   			<div class="col-lg-12">
            
              	<input type="submit" class="btn btn-success" value="Guardar">
               	<input type="button" onclick="javascript:cancelar();" class="btn btn-success" value="Cancelar">
                              
           		</div>
       		</div>
       
       </form:form>
       
        
      
 	</div>
   
   </div>
   </div>
   </div>
   
    </div>
    <!-- /#page-wrapper -->

	<div id='somediv' style="display: none">
		<div id="cuerpoDiv"></div>
	</div>
	
	<jsp:include page="../includes/pie.jsp" flush="true"/>

    </body>
</html>         
<!-- END registroNotario.jsp -->