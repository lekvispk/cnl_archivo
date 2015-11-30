<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security'%>
<!-- BEGIN listaInicialSolicitud.jsp-->
<jsp:include page="../includes/cabecera.jsp"/>

    
    <!-- Page Content -->
    <div id="page-wrapper">
	    <div class="row">
		    <div class="col-lg-12">
		    	&nbsp;
		    </div>
	    </div>
	    <jsp:include page="../includes/error.jsp"/>
	    <div class="panel panel-default">
           <div class="panel-heading">
               <i class="fa fa-bar-chart-o fa-fw"></i> Reporte de tr&aacute;mites
               <div class="pull-right">
                   <div class="btn-group">
                      
                   </div>
               </div>
           </div>
           <!-- /.panel-heading -->
           <div class="panel-body">
               <div class="row">
                   <!-- /.col-lg-4 (nested) -->
                   <div class="col-lg-8">
                       <div id="bar-example"></div>
                   </div>
                   <!-- /.col-lg-8 (nested) -->
               </div>
               <!-- /.row -->
           </div>
           <!-- /.panel-body -->
       </div>
   </div>
	
    <!-- /#page-wrapper -->
    
  	<%@include file="../includes/modal.jspf" %>
    	
	<jsp:include page="../includes/pie.jsp" flush="true"/>

    <script type="text/javascript">
    	  	
    	$(function() {
    		
    		Morris.Bar({
    			  element: 'bar-example',
    			  data: [
    			    { y: 'Enero', a: 96, b: 90 },
    			    { y: 'Febrero', a: 75,  b: 65 },
    			    { y: 'Marzo', a: 50,  b: 40 },
    			    { y: 'Abril', a: 75,  b: 65 },
    			    { y: 'Mayo', a: 50,  b: 40 },
    			    { y: 'Junio', a: 75,  b: 65 },
    			    { y: 'Julio', a: 96, b: 75 },
    			    { y: 'Agosto', a: 77, b: 80 },
    			    { y: 'Setiembre', a: 95, b: 66 },
    			    { y: 'Octubre', a: 60, b: 90 },
    			    { y: 'Noviembre', a: 66, b: 95 },
    			    <!--  { y: 'Diciembre', a: 68, b: 88 }-->
    			  ],
    			  xkey: 'y',
    			  ykeys: ['a', 'b'],
    			  labels: ['Conclusion de firmas', 'Copias simples']
    			});
    		
    	});
	    	
    </script>
    
    </body>
</html>
<!-- END listaInicialSolicitud.jsp-->