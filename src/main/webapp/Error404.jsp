<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>

	<%-- <div id="cabecera"><!--Capa que contiene la cabecera-->
    <table class="anchoMaximo">
    	<tr>
    		<td id="anchoLogo"></td>
    		<td id="anchoTextos">
        	<div id="bloqueTextos">
            	<span id="nombreUsuario">Bienvenido: <b>${pageContext.request.userPrincipal.name} </b></span>
            </div>
        	<a href="${pageContext.request.contextPath}/inicio.htm"><h1> SISTEMA DE B&Uacute;SQUEDA DE ESCRITURAS P&Uacute;BLICAS DE EX NOTARIOS</h1></a>
    		</td>
    		<td id="anchoExtremo">
        		<div id="extremoDerecho"></div>
    		</td>
    	</tr>
    </table>
</div>
<div id="bloqueSombra"><!--Sombra del Encabezado-->
    <a id="btnCerrarSession" href="${pageContext.request.contextPath}/login.htm">Cerrar sesi&oacute;n</a><!-- ${pageContext.request.contextPath}/j_spring_security_logout -->
</div>
-------------------------------------------
--%><!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Reclamos</title>

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${pageContext.request.contextPath}/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${pageContext.request.contextPath}/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

	<link href="${pageContext.request.contextPath}/css/displaytag.css" rel="stylesheet" type="text/css"/>

	<link href="${pageContext.request.contextPath}/css/jquery-ui-1.10.4.custom.min.css" rel="stylesheet">
	
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/calendar-system.css" media="all" title="system" />
		
		
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar-es.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar-setup.js"></script> 
    
</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}/inicio.htm">SISTEMA DE B&Uacute;SQUEDA DE ESCRITURAS P&Uacute;BLICAS DE EX NOTARIOS</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
               <%-- <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-envelope fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-messages">
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>Read All Messages</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-messages -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-tasks fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-tasks">
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 1</strong>
                                        <span class="pull-right text-muted">40% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                            <span class="sr-only">40% Complete (success)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 2</strong>
                                        <span class="pull-right text-muted">20% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
                                            <span class="sr-only">20% Complete</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 3</strong>
                                        <span class="pull-right text-muted">60% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                            <span class="sr-only">60% Complete (warning)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 4</strong>
                                        <span class="pull-right text-muted">80% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                                            <span class="sr-only">80% Complete (danger)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>See All Tasks</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-tasks -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-bell fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-alerts">
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-comment fa-fw"></i> New Comment
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                                    <span class="pull-right text-muted small">12 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-envelope fa-fw"></i> Message Sent
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-tasks fa-fw"></i> New Task
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-upload fa-fw"></i> Server Rebooted
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>See All Alerts</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-alerts -->
                </li>
                --%> 
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile (${pageContext.request.userPrincipal.name})</a>
                        </li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="${pageContext.request.contextPath}/j_spring_security_logout"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

           
			<div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <%--
                        <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="button">
                                        <i class="fa fa-search"></i>
                                    </button>
                                </span>
                            </div>
                            <!-- /input-group -->
                        </li>
                         --%>
                        <security:authorize ifAnyGranted="ROLE_CONSULTA,ROLE_ARCHIVO,ROLE_ADMIN">
                        <li>
                            <a href="#"><i class="fa fa-edit fa-fw"></i> Solicitud<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li><a href="${pageContext.request.contextPath}/solicitud/prenuevo.htm">Nuevo</a></li>
                                <li><a href="${pageContext.request.contextPath}/solicitud/lista.htm">Consultar</a></li>
                                <%-- 
                                <li><a href="${pageContext.request.contextPath}/reclamos/lEvaluar.htm">Evaluar</a></li>
                                <li><a href="${pageContext.request.contextPath}/reclamos/lSolucionar.htm">Solucionar</a></li>
                                <li><a href="${pageContext.request.contextPath}/reclamos/lIndemnizar.htm">Indemnizar</a></li>
                                <li>
                                	<a href="#"><i class="fa fa-edit fa-fw"></i> Fidelizar<span class="fa arrow"></span></a>
                                	<ul class="nav nav-third-level">
                                		<li><a href="${pageContext.request.contextPath}/fidelizar/lFidelizar.htm">Fidelizar</a></li>
                                		<li><a href="#">Compensar</a></li>
                                		<li><a href="${pageContext.request.contextPath}/fidelizar/lPromocion.htm">Promoci&oacute;n</a></li>
                                	</ul>	
                                </li>
                                --%>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        </security:authorize>
                        <security:authorize ifAnyGranted="ROLE_ARCHIVO,ROLE_ADMIN,ROLE_NOTARIO">
                        <li>
                            <a href="#"><i class="fa fa-edit fa-fw"></i> Tr&aacute;mites<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li><a href="${pageContext.request.contextPath}/tramites/lista.htm?estado=1">Por Derivar</a></li>
                                <li><a href="${pageContext.request.contextPath}/tramites/lista.htm?estado=2">Derivados</a></li>
                                <li><a href="${pageContext.request.contextPath}/tramites/lista.htm?estado=3">Respondidos</a></li>
                                <li><a href="${pageContext.request.contextPath}/tramites/lista.htm?estado=4">Concluidos</a></li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        </security:authorize>
                        <security:authorize ifAnyGranted="ROLE_ADMIN">
                        <li>
                            <a href="#"><i class="fa fa-edit fa-fw"></i> Reportes<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li><a href="${pageContext.request.contextPath}/reportes/lista.htm?tipo=1">Reporte 1</a></li>
                                <li><a href="${pageContext.request.contextPath}/reportes/lista.htm?tipo=2">Reporte 2</a></li>
                                <li><a href="${pageContext.request.contextPath}/reportes/lista.htm?tipo=3">Reporte 3</a></li>
                                <li><a href="${pageContext.request.contextPath}/reportes/lista.htm?tipo=4">Reporte 4</a></li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-users fa-fw"></i> Administraci&oacute;n<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li><a href="${pageContext.request.contextPath}/admin/lpersonas.htm">Personas</a></li>
                                <li><a href="${pageContext.request.contextPath}/admin/lusuarios.htm">Usuarios</a></li>
                                <li><a href="${pageContext.request.contextPath}/admin/ltipodocumentos.htm">Tipo de Documentos</a></li>
                                <li><a href="${pageContext.request.contextPath}/admin/lnotarias.htm">Notar&iacute;as</a></li>
                                <li><a href="${pageContext.request.contextPath}/admin/ltipotramite.htm">Tipo de Tramite</a></li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        </security:authorize>
                        <li><a href="${pageContext.request.contextPath}/admin/precpass.htm"><i class="fa fa-users fa-fw"></i> Cambiar Clave<span class="fa arrow"></span></a></li>
                        <%-- <li><a href="${pageContext.request.contextPath}/login.htm"><i class="fa fa-users fa-fw"></i> Salir<span class="fa arrow"></span></a></li>--%>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            
            <!-- /.navbar-static-side -->
        </nav>

	
     <input type="hidden" name='idLastEdited' value='<bean:write name="codigo"/>'/>
     
     <!-- Page Content -->
       <div id="page-wrapper">
           <div class="container-fluid">
               <div class="row">
                   <div class="col-lg-12">
                   
                       <h1 class="page-header">P&aacute;gina no encontrada</h1>
                       
					   <div class="parrafo">
					     	 esta secci&oacute;n esta siendo desarrollada
					   </div>
					                   
                   </div>
                   <!-- /.col-lg-12 -->
               </div>
               <!-- /.row -->
           </div>
           <!-- /.container-fluid -->
       </div>
       <!-- /#page-wrapper -->
       
     
    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="${pageContext.request.contextPath}/bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="${pageContext.request.contextPath}/dist/js/sb-admin-2.js"></script>
   
   <script src="${pageContext.request.contextPath}/js/jquery.displaytag-ajax-1.2.js"></script> 
   
    <script src="${pageContext.request.contextPath}/js/jquery-ui-1.10.4.custom.min.js"></script>

     
    </body>
</html>