<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!-- BEGIN registro.jsp -->
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
          REGISTRO DE SOLICITUDES
    </div>            
    
    <div class="panel-body">
    
	    <div class="row">
		</div>
		
    <form:form name="frm2" action="nuevo.htm" method="post" modelAttribute="solicitud">
	<div class="row">
		<div class="col-lg-12">
		     
     <form:hidden path="idsolicitud"/>
     
      <table class="bloqueTablaLineal">
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
						<form:input path="persona.nombreCompleto" id="tagsPersona" size="20" cssClass="form-control input-md" />
					</div>
				</div>
				
             </td>
             <th>Fecha de Solicitud:</th>
             <td>
              <div class="form-group input-group">
             	<fmt:formatDate value="${solicitud.fechaIngreso}" pattern="dd/MM/yyyy" var="f_fechaIngreso"/>
	          	<input type="text" name="fechaIngreso" id="fechaIngreso" size="15" value="${f_fechaIngreso}" class="form-control"/>
	          	<span class="input-group-addon"><img src="${pageContext.request.contextPath}/images/cal.gif" alt="D&iacute;a/Mes/A&ntilde;o" width="16" height="16" border="0" id="triggerCald" /></span>
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
				</div>
				</td>
         </tr>
         
               <tr>
                   <th>Tipo Solicitud:</th>
                   <td colspan="3">
                   	<form:select path="tipoSolicitud.idTipoSolicitud" class="form-control">
	             		<form:option value="-1">-TODOS-</form:option>
	              		<form:options items="${lTipoSol}" itemLabel="nombreTipoSolicitud" itemValue="idTipoSolicitud"/>
	             	</form:select>
             </td>
         </tr>
        
        <tr>
             <th>Comprador:</th>
             <td colspan="3">
             	<div class="row">
  				<div class="col-lg-4"><input name="tramComprador" placeholder="Nombres"  size="10" class="form-control input-md"/></div>
             	<div class="col-lg-4"><input name="tramCompradorPaterno" placeholder="Ap. Paterno"  size="10" class="form-control input-md"/></div>
             	<div class="col-lg-4"><input name="tramCompradorMaterno" placeholder="Ap. Materno" size="10" class="form-control input-md"/></div>
             	</div>
             </td>
         </tr>
           <tr>
             <th>Vendedor:</th>
             <td colspan="3">
             	<div class="row">
  				<div class="col-lg-4"><input name="tramVendedor" placeholder="Nombres"  size="10" class="form-control input-md"/></div>
             	<div class="col-lg-4"><input name="tramVendedorPaterno" placeholder="Ap. Paterno"  size="10" class="form-control input-md"/></div>
             	<div class="col-lg-4"><input name="tramVendedorMaterno" placeholder="Ap. Materno" size="10" class="form-control input-md"/></div>
             	</div>
             </td>
         </tr>
         
         <tr>
             <th>Tramitado en el Oficio Notarial:</th>
             <td colspan="3">
             	<form:select path="notaria.idNotaria" class="form-control" >
             		<form:option value="-1">-TODOS-</form:option>
              		<form:options items="${lnotarias}" itemLabel="nombre" itemValue="idNotaria"/>
             	</form:select>
             </td>
         </tr>
         
         <tr>
             <th>Nro. Kardex:</th>
             <td><form:input path="tramKardex" class="form-control" size="10"/></td>
             <th>Fecha de Escritura:</th>
             <td>
	             <div class="form-group input-group">
	             	<fmt:formatDate value="${solicitud.tramFechaInicial}" pattern="dd/MM/yyyy" var="f_tramFechaInicial"/>
		          	<input type="text" name="tramFechaInicial" id="tramFechaInicial" size="15" value="${f_tramFechaInicial}" class="form-control"/>
		          	<span class="input-group-addon"><img src="${pageContext.request.contextPath}/images/cal.gif" alt="D&iacute;a/Mes/A&ntilde;o" width="16" height="16" border="0" id="triggerCald2" /></span>
					<script type="text/javascript">
						var fechita = new Date();
						fechota =  fechita.getDate()  + "/" + (fechita.getMonth()+1) + "/" +  fechita.getFullYear();
						document.forms[0].fechaIngreso.value = fechota;
						Calendar.setup({
							inputField     :    "tramFechaInicial",  // id del campo de texto
							ifFormat       :    "%d/%m/%Y",  // Formato de la Fecha
							showsTime      :    false,       // Flag para mostrar la Fecha
							button         :    "triggerCald2",// ID del elemento que llamara al calendario
							singleClick    :    true         // Flag Modo doble-click 
						});
					</script>
				</div>
			</td>
         </tr>
          <tr>
             <th>Nro. Folio o Foja:</th>
             <td><form:input path="tramFolios" class="form-control  input-md" size="10"/></td>
             <th>Nro. Instrumento:</th>
             <td><form:input path="tramInstrumentoNum" class="form-control" size="15"/></td>
         </tr>
         <tr>
             <th>Nro. Minuta:</th>
             <td><form:input path="tramFojas" class="form-control  input-md" size="10"/></td>
             <th>Nro. Escritura:</th>
             <td><form:input path="tramEscritura" class="form-control" size="15"/></td>
         </tr>
         <tr>
             <th>Comprobante de Pago:</th>
             <td colspan="3">
             	<div class="form-group input-group">
	             	<label><input type="radio" name="tipoComprobante" id="tipoComprobante1" value="1"/>Boleta</label>
	             	<label><input type="radio" name="tipoComprobante" id="tipoComprobante2" value="2"/>Factura</label>
             	</div>
             </td>
         </tr>
         <tr>
             <th>RUC:</th>
             <td>
                	<input type="text" name="numeroRuc" id="numeroRuc" class="form-control"/>
             </td>
             <th>Razon Social:</th>
             <td>
                 	<input type="text" name="razonSocial" id="razonSocial" class="form-control"/>
             </td>
         </tr>
     </table>
      
    	</div>
    </div>
    	
    <div class="row">
		<div class="col-lg-12">
			
       		<input type="button" onclick="javascript:cancelar();" class="btn btn-success" value="Cancelar">
       		<input type="submit" class="btn btn-success" value="Guardar">
       		
        </div>
    </div>   
    </form:form>
    
    </div>
    
    </div>
     	
	</div>
    <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
   
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
<!-- END registro.jsp -->