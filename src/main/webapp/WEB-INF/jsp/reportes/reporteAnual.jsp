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
    			    { y: '2006', a: 100, b: 90 },
    			    { y: '2007', a: 75,  b: 65 },
    			    { y: '2008', a: 50,  b: 40 },
    			    { y: '2009', a: 75,  b: 65 },
    			    { y: '2010', a: 50,  b: 40 },
    			    { y: '2011', a: 75,  b: 65 },
    			    { y: '2012', a: 100, b: 90 }
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