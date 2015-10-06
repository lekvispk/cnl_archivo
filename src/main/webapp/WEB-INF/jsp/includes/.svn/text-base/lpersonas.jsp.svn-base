<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>

<jsp:include page="../includes/head.jsp"/> 

<script>

	$( function(){
	   $("#displayTagDiv").displayTagAjax();
	})
	
</script>

            <div id="contenedor">
             <jsp:include page="../includes/cabecera.jsp" flush="true"/>
                <!--Capa que contiene toda la plantilla-->
                 
                <div id="cuerpo" align="center">
                    <table class="anchoTotal" align="center" width="90%" border="1">
                        <tr>
                            <td id="anchoMenu" >
                                <jsp:include page="../includes/menu2.jsp" flush="true"/>
                            </td>
                            <td id="anchoCuerpo">
                            <form name="per" action="#">
                                <div id="contenido">
                                    <!--Capa que contiene el cuerpo de la plantilla-->                                     
                                    <h2>REGISTRO DE PERSONA</h2>
                                    
                                  	<div align="center" style="color: red"> <c:out value="${msgError}"/> </div>
									<div align="center" style="color: blue"> <c:out value="${mensaje}"/> </div>
         							
         							<h3> <span>Registrados</span> </h3>
         							
			                        <div id="displayTagDiv">
			                             <display:table name="requestScope.lpersonas" requestURI="lpersonas.htm" class="displaytag" pagesize="25" defaultsort="1"
											defaultorder="ascending" export="false" id="row" excludedParams="ajax">
												<display:column title="Nombres" property="nombreCompletoCargo" sortable="true" headerClass="sortable" />
										 </display:table>
					 				</div>
		 				                      
                                </div>
                            </form>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <jsp:include page="../includes/pie.jsp" flush="true"/>