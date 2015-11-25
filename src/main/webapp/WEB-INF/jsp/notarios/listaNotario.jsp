<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security'%>
<!-- BEGIN listaNotario.jsp -->
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
	          Lista de Notarios
	     </div>
      
		<div class="panel-body">
       
     		<form:form name="frmlista" action="buscar.htm" method="post" modelAttribute="escritura">
  	 		<input type="hidden" name="tipoTram1.idTipotram" value="1" />
          
          	<div class="row">
	    	<div class="col-lg-12">
	    		<div id="tablaDinamica">
		 		<div id="resultado">
	   			<div id="displayTagDiv">
	   			
                             <display:table name="requestScope.lNotarios" requestURI="lista.htm" class="displaytag" pagesize="25" defaultsort="1"
								defaultorder="ascending" export="false" id="row" excludedParams="ajax">
									<display:column title="Notario" property="nombre" sortable="true" headerClass="sortable"/>
									<display:column title="email" property="email" sortable="true" headerClass="sortable" />
									<display:column title="Encargado de Archivo" property="encargadoArchivo" sortable="true" headerClass="sortable" />
									<display:column title="Acciones" sortable="true" headerClass="sortable" style=" width: 80px;">
						            	<a id="ver_${row.idNotaria}" href="#" data-link="ver.htm?cod=${row.idNotaria}" style="border: 0px;" title="Ver"><img src="${pageContext.request.contextPath}/images/view.jpg" width="18" height="18" border="0"></a>
						            	<a href="editar.htm?cod=${row.idNotaria}" style="border: 0px;" title="Modificar"><img src="${pageContext.request.contextPath}/images/edit.png" width="18" height="18" border="0"></a>                                    	
                                    	<a href="javascript:eliminar(${row.idNotaria});" style="border: 0px;" title="Eliminar"><img src="${pageContext.request.contextPath}/images/error.png" width="18" height="18" border="0"></a>
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
    	
    	
    </script>
  
  
    </body>
</html>

 <!-- END listaNotario.jsp -->