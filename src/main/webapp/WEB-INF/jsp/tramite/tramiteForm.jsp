<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!-- BEGIN tramiteForm.jsp-->
	<jsp:include page="../includes/cabecera.jsp"/>

    <!-- Page Content -->
    <div id="page-wrapper">
    
    <div class="row">
	    <div class="col-lg-12">
	    	<h1 class="page-header">Registro de Tramite</h1>
	    </div>
    </div>
    
	<jsp:include page="../includes/error.jsp"/>
    
    <div class="row">
    <div class="col-lg-12">
    
    <div class="panel panel-default">
      <div class="panel-heading">
          Basic Form Elements
      </div>
      
      <div class="panel-body">
      <div class="row">
      
      <div class="col-lg-6">
      
      <form:form name="frm2" action="nuevo.htm" method="post" modelAttribute="tramite">
      <input type="hidden" name="idTramite">
      
		<!-- Text input-->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="cantHojas">Cantidad de Hojas</label>  
		  <input id="cantHojas" name="cantHojas" type="text" placeholder="cant. hojas" class="form-control input-md">
		</div>
		
		<!-- Textarea -->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="detalleNotificacion">Detalle de Notificacion</label>
		  <textarea class="form-control" id="detalleNotificacion" name="detalleNotificacion">Ingrese el detalle</textarea>
		</div>
		
		<!-- File Button --> 
		<div class="form-group">
		  <label class="col-md-4 control-label" for="filebutton">Adjuntar</label>
		  <input id="filebutton" name="filebutton" class="input-file" type="file">
		</div>
		
	    <button id="button1id" name="button1id" class="btn btn-success">Grabar</button>
	    <button id="button2id" name="button2id" class="btn btn-danger">Cancelar</button>
		  
     </form:form>
     </div>
     
     <div class="col-lg-6">
     </div>
     
     </div>
     </div>
     </div>
          
     </div>
     </div>
     
    </div>
                            
	<jsp:include page="../includes/pie.jsp" flush="true"/>
	
	<script>
	
		function nuevo(){
	      /*
	        if (document.forms[0].obs9.value.length > 500 )  {
	          alert("La longitud de la observacion del remitente no debe ser mayor a 500");
	          return false;
	        }  */
	        document.forms[0].action = "nuevo.htm";
	    	document.forms[0].submit();
		}
		
		function cancelar(){
		    document.forms[0].action = "lista.htm";
		    document.forms[0].submit();
		}
		
    $(function() {
    	
		$( "#tagsPersona" ).autocomplete({
			width: 300,
	        max: 10,
	        delay: 100,
	        autoFocus: true,
	        cacheLength: 1,
	        scroll: true,
	        highlight: false,
			source: function(request, response) {
	            $.ajax({
	            	url: "${pageContext.request.contextPath}/admin/lpersonasAuto.htm",
	                dataType: "json",
	                data: request,
	                success: function( data, textStatus, jqXHR) {
	                    /*console.log( data);*/
	                    var items = data;
	                    response(items);
	                },
	                error: function(jqXHR, textStatus, errorThrown){
	                    /* console.log(textStatus);*/
	                }
	            });
	        },
			minLength: 2,
			select: function( event, ui ) {
				  $( "#idPersona" ).val( ui.item.id );
			}
		});
		
    });	
    
    </script>
    
   </body>
<!-- END tramiteForm.jsp-->
</html>