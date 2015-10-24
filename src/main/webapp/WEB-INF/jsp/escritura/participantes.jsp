<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!-- BEGIN participantes.jsp -->
<jsp:include page="../includes/cabecera.jsp"/>

<script>

	function cancelar(){
	    document.forms[0].action = "lista.htm";
	    document.forms[0].submit();
	}
		
	function agregar(){
	    document.forms[0].action = "participantes.htm";
	    document.forms[0].submit();
	}
	
	function nuevo(){
		window.open("prenpersona.htm", "Registro de Persona", "toolbar=0,location=0,directories=0,status=no,menubar=0,scrollbars=yes,resizable=yes,width=600,height=580,titlebar=no");
	}
	
	$(function() {
		$( "#tags" ).autocomplete({
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
	                     console.log(textStatus);
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
         REGISTRO DE PARTICIPANTES
      </div>
      
    <div class="panel-body">
    
    <form:form name="frm3" action="#" method="post" modelAttribute="perescritura">
    
    <div class="row">
		<div class="col-lg-12">                        
	
		<!-- <h2>Implicados</h2> -->
  		<h3> <span>MENCIONADOS EN OFICIO</span> </h3>
  
	  	<table class="bloqueTablaLineal" >
	       <tr>
	           <th>Persona:</th>
	           <td>
	           	
	           	<form:hidden path="escritura.idEscritura" id="idEscritura"/>
	           	<form:hidden path="persona.idPersona" id="idPersona"/>
	
			    <div class="demo">
					<div class="ui-widget">
						<input id="tags" type="text" size="40"/>
					</div>
				</div>
	         </td>
	         <td>	
	         	 <ul class="enlacesCentroAjustables">
	                   <li>
	                       <input type="button" onclick="javascript:nuevo();" class="boton" value="Nueva Persona">
	                      </li>
	                 </ul>
	           </td>
	       </tr>
	       <tr>
	           <th valign="middle">Tipo:</th>
	           <td valign="middle">
	           	
	           	<form:select path="tipoRelacion.idTipoRelacion" style="height: 20px; width: 180px; font-family: Arial; font-size: 9pt">
	           		<form:options items="${lTipoRelacion}" itemLabel="relDescripcion" itemValue="idTipoRelacion"/>
	              	</form:select>                                               	
	
	           </td>
	            <td valign="middle">
					<input type="button" onclick="javascript:agregar();" class="boton" value="Agregar">
	           </td>
	       </tr>
	    </table>
    
    	</div>
    </div>
   
    </form:form>

    <div class="row">
	   	<div class="col-lg-12">
	    		<div id="tablaDinamica">
		 		<div id="resultado">
	   			<div id="displayTagDiv">
	   			
	                 <display:table name="requestScope.limplicados" requestURI="buscar.htm" class="displaytag" pagesize="25" defaultsort="1"
						defaultorder="ascending" export="false" id="row" excludedParams="ajax">
							<display:column title="Tipo" property="tipoRelacion.relDescripcion" sortable="true" headerClass="sortable"/>
							<display:column title="Persona" property="persona.nombreCompleto" sortable="true" headerClass="sortable" />
							<display:column title="Documento" property="persona.numDocumento" sortable="true" headerClass="sortable" />
							<display:column title="Acciones" sortable="true" headerClass="sortable" style=" width: 80px;">
				            	<!--    	<a href="ver.htm?codd=${row.escritura.idEscritura}&codp=${row.persona.idPersona}" style="border: 0px;" title="Ver"><img src="${pageContext.request.contextPath}/images/view.jpg" width="18" height="18" border="0"></a>
							         	<a href="javascript:eliminar(${row.escritura.idEscritura},${row.persona.idPersona});" style="border: 0px;" title="Eliminar"><img src="${pageContext.request.contextPath}/images/error.png" width="18" height="18" border="0"></a>
							     --> 
	                        </display:column>
					 </display:table>
					 
		 		</div>
			  	</div>
				</div>	
	   	</div>
    </div>
    		
 	<div class="row">
	    <div class="col-lg-12">
			<input type="button" onclick="javascript:cancelar();" class="boton" value="Finalizar">	    
	    </div>
    </div>
    
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
<!-- END participantes.jsp -->