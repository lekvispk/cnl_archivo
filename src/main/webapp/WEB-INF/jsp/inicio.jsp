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
					     	El Colegio de Notarios de Lima (CNL) pone a disposici&oacute;n de los Miembros de la Orden este m&oacute;dulo de "Servico Ex Notarios". 
					     	En este espacio virtual podr&aacute;n tener acceso a toda la informaci&oacute;n importante que el CNL proporciona a los Clientes, como: 
					     </div>
					     
					     <h3> <span>CONCLUSIÓN DE ESCRITURAS P&Uacute;BLICAS</span> </h3>
					     <div class="parrafo">
					     	Para la registrar la solicitud de b&uacute;squeda y atenci&oacute;n del servicio de Conclusi&oacute;n de Escrituras, culminando con la 
					     	entrega del Parte Notarial en formato digital, garantizando su legalidad empleando Firma Digital.
					     </div>
					     
					      <h3> <span>COPIAS SIMPLES DE ESCRITURAS P&Uacute;BLICAS</span> </h3>
					     <div class="parrafo">
					     	Para el registro de b&uacute;squeda y atenci&oacute;n de las copias simples de Escrituras P&uacute;blicas de una manera r&aacute;pida y segura.
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