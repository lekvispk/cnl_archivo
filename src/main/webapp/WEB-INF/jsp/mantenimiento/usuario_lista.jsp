<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>

	<jsp:include page="../includes/head.jsp"/>
    <script type="text/javascript">
    	function buscar(){
			document.forms[0].action="buscarUsr.htm";
			document.forms[0].submit();
		}
    	function nuevo(){
    			document.forms[0].action="prenuevoUsr.htm";
    			document.forms[0].submit();
    	}
    	function eliminar(id){
    		if(confirm('seguro que desea eliminar al usuario?')){
    			document.forms[0].action="eliminarUsr.htm?cod="+id;
    			document.forms[0].submit();	
    		}
		}	
   	 $( function(){
    	   $("#displayTagDiv").displayTagAjax();
    	})
    	
    </script>
    
    <form:form name="frmlista" action="." method="post" modelAttribute="usuario">
	
	<div id="contenedor">
	<jsp:include page="../includes/cabecera.jsp" flush="true"/>
    <div id="cuerpo">
        <table class="anchoTotal">
        <tr>
        <td id="anchoMenu">
			<jsp:include page="../includes/menu2.jsp" flush="true"/>
		</td>
        <td id="anchoCuerpo">
            <div id="contenido">
            	<h2>Lista de Usuarios</h2>
                <h4>Busqueda</h4>
                
               	<div align="center" style="color: red"> <c:out value="${msgError}"/> </div>
				<div align="center" style="color: blue"> <c:out value="${mensaje}"/> </div>
                      
            
                <div class="capaEnlace">
                    <center>
                        <table summary="Contenedor Botones" class="tablaAnchoAjustable"><tr><td>
                            <ul class="enlacesCentroAjustables">
                                <li>
                                   <table class="bloqueTablaLineal" >
                                     	<tr>
                                            <th>Entidad:</th>
                                            <td>
                                            	  <form:select path="entidad.idEntidad" cssStyle="height: 20px; width: 180px; font-family: Arial; font-size: 9pt">
	                                            	<form:options items="${lentidad}" itemLabel="nomEntidad" itemValue="idEntidad"/>
                                            	</form:select>
                                            </td>
                                        </tr>
                                    </table>
                                </li>
                            </ul>
                        </td></tr></table>
                    </center>
                </div>
                <div class="capaEnlace">
                    <center>
                        <table summary="Contenedor Botones" class="tablaAnchoAjustable"><tr><td>
                            <ul class="enlacesCentroAjustables">
                            	<li><input type="button" onclick="javascript:buscar();" class="boton" value="Buscar"></li>
                              	<li><input type="button" onclick="javascript:nuevo();" class="boton" value="Nuevo"></li>
                            </ul>
                        </td></tr>
                        </table>
                    </center>
                </div>
                <h4>LISTADO DE USUARIOS</h4>
                <div class="bloqueListadoDatos"><!--Capa que contiene tabla de resultados-->
                     
                    <div id="displayTagDiv">
                        <display:table name="requestScope.lusuarios" requestURI="buscarUsr.htm" class="displaytag" pagesize="10" 
                        defaultsort="1" defaultorder="ascending" export="false" id="row" excludedParams="ajax">
							<display:column title="Entidad" property="entidad.nomEntidad" sortable="true" headerClass="sortable"/>
							<display:column title="Nombre" property="nombreCompleto" sortable="true" headerClass="sortable" />
							<display:column title="Acciones" sortable="true" headerClass="sortable" style=" width: 80px;">
				            	<a href="verUsr.htm?cod=${row.idusuario}" style="border: 0px;" title="Ver"><img src="${pageContext.request.contextPath}/images/view.jpg" width="18" height="18" border="0"></a>
                                   	<a href="editarUsr.htm?cod=${row.idusuario}" style="border: 0px;" title="Modificar"><img src="${pageContext.request.contextPath}/images/edit.png" width="18" height="18" border="0"></a>                                    	
                                   	<a href="javascript:eliminar(${row.idusuario});" style="border: 0px;" title="Eliminar"><img src="${pageContext.request.contextPath}/images/error.png" width="18" height="18" border="0"></a>
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