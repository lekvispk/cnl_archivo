<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>

	<jsp:include page="../includes/cabecera.jsp"/>

    <!-- Page Content -->
    <div id="page-wrapper">
    <div class="container-fluid">
    <div class="row">
    <div class="col-lg-12">
                               
    <h2>REGISTRO DE TRAMITE</h2> 

    <div align="center" style="color: red"> <c:out value="${msgError}"/> </div>
	<div align="center" style="color: blue"> <c:out value="${mensaje}"/> </div>
            
  	<!--  <h2>Datos de Alerta</h2> -->
    <h3> <span>Datos de Tramite</span> </h3>
    
    <form:form name="frm2" action="nuevo.htm" method="post" modelAttribute="tramite">
     
     <input type="hidden" name="idTramite">
     
     
      <table class="bloqueTablaLineal" cellspacing="6">
         <tr>
             <th>Fecha de Escritura:</th>
             <td>
             	
				</td>
               </tr>
               <tr>
                   <th>Tipo Solicitud:</th>
                   <td></td>
         </tr>
          <tr>
             <th>Acto:</th>
             <td></td>
         </tr>
         <tr>
             <th>Notario:</th>
             <td></td>
         </tr>
         <tr>
             <th>Comprador:</th>
             <td></td>
         </tr>
           <tr>
             <th>Vendedor:</th>
             <td></td>
         </tr>
         <tr>
             <th>Solicitante:</th>
             <td> </td>
         </tr>
         <tr>
             <th>Nro. Kardex:</th>
             <td></td>
         </tr>
          <tr>
             <th>Nro. Folio:</th>
             <td></td>
         </tr>
         <tr>
             <th>Nro. Escritura:</th>
             <td></td>
         </tr>
          <tr>
             <th></th>
             <td> </td>
         </tr>
         <tr>
             <th>Observaciones:</th>
             <td></td>
         </tr>
     </table>
      
     <center>
     
         <table summary="Contenedor Botones" class="tablaAnchoAjustable">
             <tr>
                 <td>
                     <ul class="enlacesCentroAjustables">
                         <li>
                        	<input type="submit" class="boton" value="Aceptar">
                         	<input type="button" onclick="javascript:cancelar();" class="boton" value="Cancelar">
                        </li>
                     </ul>
                 </td>
             </tr>
         </table>
     </center>
     </form:form>
     	
	</div>
    <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    </div>
    <!-- /.container-fluid -->
    </div>
    <!-- /#page-wrapper -->
                            
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
</html>