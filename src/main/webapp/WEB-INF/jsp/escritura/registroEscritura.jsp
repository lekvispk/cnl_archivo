<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!-- BEGIN registroEscritura.jsp-->
<jsp:include page="../includes/cabecera.jsp"/>

 <script>

   	function cargar(){
   	    document.forms[1].action = "cargarEscritura.htm";
   	    document.forms[1].submit();
   	}
   	
   	function eliminar(cod,docum){
   	    if(confirm('Está segur de eliminar la escritura')){
   	    	window.location='eliminarDoc.htm?id='+cod+"&cod="+docum;
   	    }
   	}
   	
	function nuevo(){
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
         REGISTRO DE ESCRITURA
      </div>
      
    <div class="panel-body">

          <form:form name="frm2" action="nuevo.htm" method="post" modelAttribute="escritura">
           <div class="row">
	   		<div class="col-lg-12">
	   		
            <form:hidden path="idEscritura"/>
          	<table class="bloqueTablaLineal" >
               <tr>
                   <th>Notaria:</th>
                   <td><form:input path="notaria.nombre" size="50"/></td>
               </tr>
              
               <tr>
                   <th>Nro. Kardex:</th>
                   <td><form:input path="kardex" size="15"/></td>
               </tr>
               <tr>
                   <th>Nro. Escritura:</th>
                   <td><form:input path="numeroDoc" size="15"/></td>
               </tr>
                <tr>
                   <th>Nro. Instrumento:</th>
                   <td><form:input path="numeroInstrumento" size="15"/></td>
               </tr>
                <tr>
                   <th>Nro. Folios:</th>
                   <td><form:input path="numeroFolios" size="15"/></td>
               </tr>
               <tr>
                   <th>Fecha de Documento:</th>
                   <td>
                   	<fmt:formatDate value="${escritura.fechaEscritura}" pattern="dd/MM/yyyy" var="f_tramFechaRegistro"/>
               		<input type="text" name="tramFechaRegistro" id="tramFechaRegistro" size="15" value="${f_tramFechaRegistro}"/>
                 		<img src="${pageContext.request.contextPath}/images/cal.gif" alt="D&iacute;a/Mes/A&ntilde;o" width="16" height="16" border="0" id="triggerCalr" />
						<script type="text/javascript">
						Calendar.setup({
							inputField     :    "tramFechaRegistro",  // id del campo de texto
							ifFormat       :    "%d/%m/%Y",  // Formato de la Fecha
							showsTime      :    false,       // Flag para mostrar la Fecha
							button         :    "triggerCalr",// ID del elemento que llamara al calendario
							singleClick    :    true         // Flag Modo doble-click 
						});
						</script>
					</td>
                  </tr>                                    
              </table>
        </div>
       </div>    
                         
       <div class="row">
	   		<div class="col-lg-12">
            
              	<input type="submit" class="boton" value="Guardar">
               	<input type="button" onclick="javascript:cancelar();" class="boton" value="Cancelar">
                <c:if test="${ escritura.idEscritura != 0}">
                   	<input type="button" onclick="javascript:implicados();" class="boton" value="Agregar Participantes">
                </c:if>
                              
           </div>
       </div>
       </form:form>
       
        <div class="row">
	    	<div class="col-lg-12">
            <c:if test="${ escritura.idEscritura != 0}">
           
            <c:if test="${ empty escritura.archivos }">
            
           <h3><span>Cargar Archivo</span> </h3>
			
			<form:form id="frmAdjuntar" name="frmAdjuntar" action="#" method="POST" modelAttribute="uploadForm" enctype="multipart/form-data">
			<input type="hidden" name="idDocumento" value="${escritura.idEscritura}"/>
			                         
			<table class="bloqueTablaLineal" cellspacing="6">
				<tr>
                     <th>Seleccionar Archivo:</th>
                     <td><input type="file" name="file"></td>
                  </tr>
             </table>
             <table summary="Contenedor Botones" class="tablaAnchoAjustable">
                 <tr>
                     <td>
                         <input type="button" onclick="javascript:cargar();" class="boton" value="Cargar">
                   </td>
               </tr>
       	    </table>
          </form:form>
          </c:if>
           
            <c:if test="${ not empty  escritura.archivos }">
             <table class="bloqueTablaLineal" cellspacing="6">
				<c:forEach items="${escritura.archivos}" var="doc">
				<tr>
                      <th>Descargar Archivo:</th>
                      <td>
                      <a href="descargar.htm?id=${doc.idArchivo}">${doc.nombre}</a>&nbsp;&nbsp;&nbsp;&nbsp;			                                          
                      <a href="javascript:eliminar('${doc.idArchivo}','${documento.idDocumento}');" class="btnEnlaceCentro"> 
                     		<img title="Eliminar archivo" src="${pageContext.request.contextPath}/images/error.png" width="18" height="18" border="0">
                     	</a>
                      </td>
                  </tr>
                  </c:forEach>
             </table>
            </c:if>
     	</c:if>
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
<!-- END registroEscritura.jsp-->