<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security'%>

<!-- BEGIN listaEscritura.jsp-->
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
	          Lista de Escrituras Publicas
	     </div>
      
		<div class="panel-body">
       
     		<form:form name="frmlista" action="buscar.htm" method="post" modelAttribute="escritura">
  	 		<input type="hidden" name="tipoTram1.idTipotram" value="1" />
          
          	<div class="row">
			    	<div class="col-lg-3">
						<label class="control-label">Persona:</label>
			    	</div>
			    	<div class="col-lg-3">
			    		<input type="hidden" name="idPersona" id="idPersona"/>
                        <div class="demo">
							<div class="ui-widget">
								<input id="tags" class="form-control" type="text" size="40"/>
							</div>
						</div>
			    	</div>
		    	
		    </div>
            <div class="row">
		    		<div class="col-lg-3">
						<label class="control-label">Escrituras Registradas:</label>
			    	</div>
			    	<div class="col-lg-3">
			    		<div class="form-group input-group">
			    		<fmt:formatDate value="${escritura.fechaEscritura}" pattern="dd/MM/yyyy" var="f_fechaEscritura"/>
                        <input type="text" name="fechaEscritura" id="fechaEscritura" size="15" value="${f_fechaEscritura}" class="form-control" placeholder="Desde"/>
					    <span class="input-group-addon"><img src="${pageContext.request.contextPath}/images/cal.gif" alt="D&iacute;a/Mes/A&ntilde;o" width="16" height="16" border="0" id="triggerCald" /></span>
					    <script type="text/javascript">
									Calendar.setup({
										inputField     :    "fechaEscritura",  // id del campo de texto
										ifFormat       :    "%d/%m/%Y",  // Formato de la Fecha
										showsTime      :    false,       // Flag para mostrar la Fecha
										button         :    "triggerCald",// ID del elemento que llamara al calendario
										singleClick    :    true         // Flag Modo doble-click 
									});
						</script>
						</div>
					</div>
					<div class="col-lg-3">
						
						<div class="form-group input-group">
						<fmt:formatDate value="${escritura.tramFechaRegistro2}" pattern="dd/MM/yyyy" var="f_tramFechaRegistro2"/>
                        <input type="text" name="tramFechaRegistro2" id="tramFechaRegistro2" size="15" value="${f_tramFechaRegistro2}" class="form-control" placeholder="Hasta"/>
                        <span class="input-group-addon"><img src="${pageContext.request.contextPath}/images/cal.gif" alt="D&iacute;a/Mes/A&ntilde;o" width="16" height="16" border="0" id="triggerCalh" /></span>
                        <script type="text/javascript">
										Calendar.setup({
											inputField     :    "tramFechaRegistro2",  // id del campo de texto
											ifFormat       :    "%d/%m/%Y",  // Formato de la Fecha
											showsTime      :    false,       // Flag para mostrar la Fecha
											button         :    "triggerCalh",// ID del elemento que llamara al calendario
											singleClick    :    true         // Flag Modo doble-click 
										});
						</script>
						</div>		
			    	</div>
		    	
		    </div>
		        
			<div class="row">
		    	<div class="col-lg-12">          
	  
	                  <input type="button" onclick="javascript:buscar();" class="btn btn-success" value="Buscar">
	                  <input type="button" onclick="javascript:nuevo();" class="btn btn-success" value="Nuevo">
	          
	           </div> 
	        </div>
                
        	<div class="row">
	    	<div class="col-lg-12">
	    		<div id="tablaDinamica">
		 		<div id="resultado">
	   			<div id="displayTagDiv">
	   			
                             <display:table name="requestScope.lEscrituras" requestURI="buscar.htm" class="displaytag" pagesize="25" defaultsort="1"
								defaultorder="ascending" export="false" id="row" excludedParams="ajax">
									<display:column title="F. Escritura" property="fechaEscritura"  format="{0,date,dd/MM/yyyy}" sortable="true" headerClass="sortable"/>
									<display:column title="Numero" property="numeroDoc" sortable="true" headerClass="sortable" />
									<display:column title="Kardex" property="kardex" sortable="true" headerClass="sortable" />
									<display:column title="Notario" property="notaria.nombre" sortable="true" headerClass="sortable" />
						            <display:column title="Acciones" sortable="true" headerClass="sortable" style=" width: 80px;">
						            	<a id="ver_${row.idEscritura}" href="#" data-link="ver.htm?cod=${row.idEscritura}" style="border: 0px;" title="Ver"><img src="${pageContext.request.contextPath}/images/view.jpg" width="18" height="18" border="0"></a>
						            	<a href="editar.htm?cod=${row.idEscritura}" style="border: 0px;" title="Modificar"><img src="${pageContext.request.contextPath}/images/edit.png" width="18" height="18" border="0"></a>                                    	
                                    	<a href="javascript:eliminar(${row.idEscritura});" style="border: 0px;" title="Eliminar"><img src="${pageContext.request.contextPath}/images/error.png" width="18" height="18" border="0"></a>
                                    </display:column>
							 </display:table>
		 		</div>
			  	</div>
				</div>	
	    	</div>
    		</div>
		 		
			</form:form>

		</div>
	
	</div>
  	</div>
   </div>
   
    </div>
    <!-- /#page-wrapper -->
    
	<%@include file="../includes/modal.jspf" %>

	<div id='somediv' style="display: none">
		<div id="cuerpoDiv"></div>
	</div>
	
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
    		if(confirm('seguro que desea eliminar la escritura?')){
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
    		
    </script>
  
  
    </body>
</html>

 <!-- END listaEscritura.jsp-->