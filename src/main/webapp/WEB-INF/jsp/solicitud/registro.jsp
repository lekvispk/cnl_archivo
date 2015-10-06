<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>

	<jsp:include page="../includes/cabecera.jsp"/>

	
    <!-- Page Content -->
    <div id="page-wrapper">
    <div class="container-fluid">
    <div class="row">
    <div class="col-lg-12">
                               
    <h2>REGISTRO DE SOLICITUDES</h2> 

    <div align="center" style="color: red"> <c:out value="${msgError}"/> </div>
	<div align="center" style="color: blue"> <c:out value="${mensaje}"/> </div>
            
  	<!--  <h2>Datos de Alerta</h2> -->
    <h3> <span>Datos de Solicitud</span> </h3>
    
    <form:form name="frm2" action="nuevo.htm" method="post" modelAttribute="solicitud">
     
     <form:hidden path="idsolicitud"/>
     
      <table class="bloqueTablaLineal" cellspacing="6">
         <tr>
             <th>Fecha de Escritura:</th>
             <td>
             	<fmt:formatDate value="${solicitud.fechaIngreso}" pattern="dd/MM/yyyy" var="f_fechaIngreso"/>
	          	<input type="text" name="fechaIngreso" id="fechaIngreso" size="15" value="${f_fechaIngreso}"/>
		 		<img src="${pageContext.request.contextPath}/images/cal.gif" alt="D&iacute;a/Mes/A&ntilde;o" width="16" height="16" border="0" id="triggerCald" />
				<script type="text/javascript">
				var fechita = new Date();
				fechota =  fechita.getDate()  + "/" + (fechita.getMonth()+1) + "/" +  fechita.getFullYear();
				document.forms[0].fechaIngreso.value = fechota;
				Calendar.setup({
					inputField     :    "fechaIngreso",  // id del campo de texto
					ifFormat       :    "%d/%m/%Y",  // Formato de la Fecha
					showsTime      :    false,       // Flag para mostrar la Fecha
					button         :    "triggerCald",// ID del elemento que llamara al calendario
					singleClick    :    true         // Flag Modo doble-click 
				});
				</script>
				</td>
               </tr>
               <tr>
                   <th>Tipo Solicitud:</th>
                   <td>
                   	<form:select path="tipoSolicitud.idTipoSolicitud" cssStyle="height: 20px; width: 180px; font-family: Arial; font-size: 9pt">
             		<form:option value="-1">-TODOS-</form:option>
              	<form:options items="${lTipoSol}" itemLabel="nombreTipoSolicitud" itemValue="idTipoSolicitud"/>
             	</form:select>
             </td>
         </tr>
          <tr>
             <th>Acto:</th>
             <td>
             	<form:select path="tipoActo.idActo" cssStyle="height: 20px; width: 180px; font-family: Arial; font-size: 9pt">
             		<form:option value="-1">-TODOS-</form:option>
              		<form:options items="${ltipoacto}" itemLabel="nombreActo" itemValue="idActo"/>
             	</form:select>
             </td>
         </tr>
         <tr>
             <th>Notario:</th>
             <td>
             	<form:select path="notaria.idNotaria" cssStyle="height: 20px; width: 180px; font-family: Arial; font-size: 9pt">
             		<form:option value="-1">-TODOS-</form:option>
              		<form:options items="${lnotarias}" itemLabel="nombre" itemValue="idNotaria"/>
             	</form:select>
             </td>
         </tr>
         <tr>
             <th>Comprador:</th>
             <td><form:input path="tramComprador" class="asunto" size="50"/></td>
         </tr>
           <tr>
             <th>Vendedor:</th>
             <td><form:input path="tramVendedor" class="asunto" size="50"/></td>
         </tr>
         <tr>
             <th>Solicitante:</th>
             <td>
             
             	<c:if test="${solicitud.persona.idPersona > 0}">
                 	<form:hidden path="persona.idPersona" id="idPersona"/>
                 </c:if>
                 <c:if test="${empty solicitud.persona.idPersona || solicitud.persona.idPersona <= 0}">
                 	<input type="hidden" name="persona.idPersona" id="idPersona" value="-1"/>
                 </c:if>
                 	
                 <div class="demo" style="float: left;">
					<div class="ui-widget">
						<form:input path="persona.nombreCompleto" id="tagsPersona" size="40" cssClass="form-control input-md" />
					</div>
				</div>
             </td>
         </tr>
         <tr>
             <th>Nro. Kardex:</th>
             <td><form:input path="tramKardex" class="asunto" size="15"/></td>
         </tr>
          <tr>
             <th>Nro. Folio:</th>
             <td><form:input path="tramFolios" class="folio" size="15"/></td>
         </tr>
         <tr>
             <th>Nro. Escritura:</th>
             <td><form:input path="tramEscritura" class="asunto" size="15"/></td>
         </tr>
          <tr>
             <th></th>
             <td> <a href="javascript:muestraApoderado();">Entregar Poder</a> </td>
         </tr>
         <tr id="trpoder1" style="display: none;">
             <th>Nombre Apoderado:</th>
             <td><form:input path="tramApoderado" class="asunto" size="35"/></td>
         </tr>
         <tr id="trpoder2" style="display: none;">
             <th>Doc. Apoderado:</th>
             <td><form:input path="tramApodDocumento" class="asunto" size="15"/></td>
         </tr>
         <tr id="trpoder3" style="display: none;">
             <th>Telefono:</th>
             <td><form:input path="tramApodTelefono" class="asunto" size="20"/></td>
         </tr>
          <tr id="trpoder4" style="display: none;">
             <th>Direccion:</th>
             <td><form:input path="tramApodDireccion" class="asunto" size="40"/></td>
         </tr>
         <tr>
             <th>Observaciones:</th>
             <td><form:textarea path="tramSolicitado" class="asunto" cols="40" rows="5"/></td>
         </tr>
     </table>
      
     <center>
     
         <table summary="Contenedor Botones"
                class="tablaAnchoAjustable">
             <tr>
                 <td>
                     <ul class="enlacesCentroAjustables">
                         <li>
                        	<input type="submit" class="boton" value="Aceptar">
                         	<input type="button" onclick="javascript:cancelar();" class="boton" value="Cancelar">
                         	<!--    <c:if test="${ documento.idDocumento != 0}">
                             	<input type="button" onclick="javascript:implicados();" class="boton" value="Agregar Personas">
                             </c:if>
                             --> 
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
	
	  function muestraApoderado(){
	    	 var estado = document.getElementById("trpoder1").style.display;
	    	 document.getElementById("trpoder1").style.display= (estado=='none') ? 'block' : 'none';
	    	 document.getElementById("trpoder2").style.display= (estado=='none') ? 'block' : 'none';
	    	 document.getElementById("trpoder3").style.display= (estado=='none') ? 'block' : 'none';
	    	 document.getElementById("trpoder4").style.display= (estado=='none') ? 'block' : 'none';
	     }
	        
	   	
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
		
		function implicados(){
		    document.forms[0].action = "preparticipantes.htm";
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