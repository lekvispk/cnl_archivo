<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security'%>

<jsp:include page="../includes/head.jsp"/>
    <script type="text/javascript">
    
		function firmar( id1 , id2 ){		
			window.open("${pageContext.request.contextPath}/firmar.htm?id1="+id1+"&id2="+id2, "firmaPT", "toolbar=0,location=0,directories=0,status=no,menubar=0,scrollbars=yes,resizable=yes,width=780,height=580,titlebar=no");
		}
	
    	 $( function(){
    	   $("#displayTagDiv").displayTagAjax();
    	})
	    	
	    	
    </script>
       <form:form name="frmlista" action="buscar.htm" method="post" modelAttribute="escritura">
       
	<div id="contenedor">
		<jsp:include page="../includes/cabecera.jsp" flush="true"/>
    <div id="cuerpo">
        <table class="anchoTotal">
        <tr>
        <td id="anchoMenu">
			<jsp:include page="../includes/menu2.jsp" flush="true"/>
		</td>
        <td id="anchoCuerpo">
          
          
          <input type="hidden" name="tipoTram1.idTipotram" value="1" />
          
            <div id="contenido">
            
            	<div align="center" style="color: red"> <c:out value="${msgError}"/> </div>
				<div align="center" style="color: blue"> <c:out value="${mensaje}"/> </div>
            
            	<h2>Lista de Documentos Pendientes de Firma</h2>
                 
               <div class="bloqueListadoDatos">
               
                      <div id="displayTagDiv">
                             <display:table name="requestScope.lPendientes" requestURI="lista.htm" class="displaytag" pagesize="25" defaultsort="1"
								defaultorder="ascending" export="false" id="row" excludedParams="ajax">
									<display:column title="F. Solicitud" sortable="true" headerClass="sortable">
										<fmt:formatDate value="${row.fecSolicitud}" pattern="dd/MM/yyyy"/>
									</display:column>
									<display:column title="Escritura" property="escritura.numeroDoc" sortable="true" headerClass="sortable" />
									<display:column title="Kardex" property="escritura.tramKardex" sortable="true" headerClass="sortable" />
									<display:column title="Estado" sortable="true" headerClass="sortable">
										<c:if test="${row.estado==1}">Pendiente</c:if>
										<c:if test="${row.estado==2}">Firmado</c:if>
									</display:column>
									<display:column title="Archivo" sortable="false" headerClass="sortable">
										<a href="descargarP.htm?id1=${row.escritura.idEscritura}&id2=${row.solicitud.idsolicitud}">ver archivo</a>
									</display:column>
							 </display:table>
		 				</div>
		 			</div>
		 			
         	   </div>
         
         
        </td></tr></table>
        
        
		 				
    </div>
   
</div>

  
                     
</form:form>
 <jsp:include page="../includes/pie.jsp" flush="true"/>
