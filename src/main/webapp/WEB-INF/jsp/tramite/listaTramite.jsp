<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security'%>
<!-- BEGIN listaTramite.jsp-->
<jsp:include page="../includes/cabecera.jsp"/>

    
    <!-- Page Content -->
    <div id="page-wrapper">
    <div class="container-fluid">
    <div class="row">
    <div class="col-lg-12">
                   
	    <form:form name="frmlista" action="buscar.htm" method="post" modelAttribute="tramite">
	       
	    <input type="hidden" name="tipoTram1.idTipotram" value="1" />
	    
		<div align="center" style="color: red"> <c:out value="${msgError}"/> </div>
		<div align="center" style="color: blue"> <c:out value="${mensaje}"/> </div>
	       
	       	<h4>Lista Solicitudes</h4>  
	           
	           <div class="capaEnlace">
	               
	                   <table summary="Contenedor Botones" class="tablaAnchoAjustable"><tr><td>
	                       <ul class="enlacesCentroAjustables">
	                           <li>
	                               <table class="bloqueTablaLineal" summary="Datos del Calendario">
	                                    <tr>
	                                       <th>Acto:</th>
	                                       <td colspan="3">
	                                       
	                                      
	                                       	
	                                       </td>
	                                   </tr>
	                                 <tr>
	                                   <th>Persona:</th>
	                                   <td colspan="3">
	                                   		
	                                   </td>        
	                                 </tr>
	                                    <tr>
	                                   <th>Solicitudes Registradas Desde:</th>
	                                   <td></td>  
	                                   <th>Hasta:</th>
	                                   <td></td>          
	                                 </tr>
	                               </table>
	                           </li>
	                       </ul>
	                   </td></tr></table>
	               
	           </div>
	           
	           <div class="capaEnlace">
	               
	                   <table summary="Contenedor Botones" class="tablaAnchoAjustable"><tr><td>
	                       <ul class="enlacesCentroAjustables">
	                         <li>
	                          <input type="button" onclick="javascript:buscar();" class="boton" value="Buscar">
	                          <input type="button" onclick="javascript:nuevo();" class="boton" value="Nuevo">
	                       </ul>
	                   </td></tr>
	                   </table>
	             
	           </div>
	           
	          <div class="bloqueListadoDatos">
	          
	          <div id="displayTagDiv">
	          <display:table name="requestScope.lTramites" requestURI="buscar.htm" class="displaytag" pagesize="25" defaultsort="1"
				defaultorder="ascending" export="false" id="row" excludedParams="ajax">
						<display:column title="Acciones" sortable="true" headerClass="sortable" style=" width: 80px;">
		            	<a href="ver.htm?cod=${row.idTramite}" style="border: 0px;" title="Ver"><img src="${pageContext.request.contextPath}/images/view.jpg" width="18" height="18" border="0"></a>
	                   	<a href="editar.htm?cod=${row.idTramite}" style="border: 0px;" title="Modificar"><img src="${pageContext.request.contextPath}/images/edit.png" width="18" height="18" border="0"></a>
	                   	<a href="javascript:eliminar(${row.idTramite});" style="border: 0px;" title="Eliminar"><img src="${pageContext.request.contextPath}/images/error.png" width="18" height="18" border="0"></a>
                   	</display:column>
			 </display:table>
			</div>
		</div>
         
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

    <script type="text/javascript">
    	function nuevo(){
    			document.forms[0].action="prenuevo.htm";
    			document.forms[0].submit();
    	}
    	function buscar(){
			document.forms[0].action="buscar.htm";
			document.forms[0].submit();
		}
    	function eliminar(id){
    		if(confirm('seguro que desea eliminar la Solicitud?')){
    			document.forms[0].action="eliminar.htm?cod="+id;
    			document.forms[0].submit();	
    		}
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
    	
    	 $( function(){
    	   $("#displayTagDiv").displayTagAjax();
    	})
	    	
	    	
    </script>
    
    </body>
</html>
<!-- END listaTramite.jsp-->