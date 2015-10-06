<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>

	<jsp:include page="includes/cabecera.jsp"/>
	
     <input type="hidden" name='idLastEdited' value='<bean:write name="codigo"/>'/>
     
     <!-- Page Content -->
       <div id="page-wrapper">
           <div class="container-fluid">
               <div class="row">
                   <div class="col-lg-12">
                       <h1 class="page-header">Bienvenidos</h1>
                       
					     <div class="parrafo">
					     	El Colegio de Notarios de Lima (CNL) pone a disposici&oacute;n de los Miembros de la Orden este m&oacute;dulo de consultas en l&iacute;nea. 
					     	En este espacio virtual podr&aacute;n tener acceso a toda la informaci&oacute;n importante que el CNL proporciona a los Notarios, como: 
					     </div>
					     
					     <h3> <span>COPIAS DE ESCRITURAS PUBLICAS</span> </h3>
					     <div class="parrafo">
					     	Para mpresion mas rapida de Partes, escrituras y muchos otros documentos, garantizando su legalidad empleando Firma Digital.
					     </div>
					                       
                   </div>
                   <!-- /.col-lg-12 -->
               </div>
               <!-- /.row -->
           </div>
           <!-- /.container-fluid -->
       </div>
       <!-- /#page-wrapper -->
       
     <jsp:include page="includes/pie.jsp" flush="true"/>
     
    </body>
</html>