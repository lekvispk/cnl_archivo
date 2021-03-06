<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security'%>

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
                        <security:authorize ifAnyGranted="ROLE_MP,ROLE_ADMIN">
                        <li>
                            <a href="#"><i class="fa fa-edit fa-fw"></i> Mesa de Partes<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li><a href="${pageContext.request.contextPath}/solicitud/inicial.htm">Solicitud</a></li>
                                <li><a href="${pageContext.request.contextPath}/solicitud/prenuevo.htm">Nuevo</a></li>
                                <li><a href="${pageContext.request.contextPath}/solicitud/listaPorCotizar.htm">Cotizar</a></li>
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
                            <a href="#"><i class="fa fa-edit fa-fw"></i> Archivo Ex-Notarios<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                            	<security:authorize ifAnyGranted="ROLE_ARCHIVO,ROLE_ADMIN">
                                <li><a href="${pageContext.request.contextPath}/tramites/lista.htm?estado=1">Tramite</a></li>
                                <li><a href="${pageContext.request.contextPath}/tramites/lista.htm?estado=2">Por Derivar</a></li>
                                </security:authorize>
                                <li><a href="${pageContext.request.contextPath}/tramites/lista.htm?estado=3">Derivados</a></li>
                                <li><a href="${pageContext.request.contextPath}/tramites/respondidos.htm">Respondidos</a></li>
                                <security:authorize ifAnyGranted="ROLE_ARCHIVO,ROLE_ADMIN">
                                <li><a href="${pageContext.request.contextPath}/tramites/porConcluir.htm">Por Concluir</a></li>
                                <li><a href="${pageContext.request.contextPath}/tramites/concluidos.htm">Concluidos</a></li>
                                </security:authorize>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        </security:authorize>
                        <security:authorize ifAnyGranted="ROLE_ADMIN">
                        <li>
                            <a href="#"><i class="fa fa-users fa-fw"></i> Administraci&oacute;n<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <%--
                                <li><a href="${pageContext.request.contextPath}/admin/lpersonas.htm">Personas</a></li>
                                <li><a href="${pageContext.request.contextPath}/admin/lusuarios.htm">Usuarios</a></li>
                                <li><a href="${pageContext.request.contextPath}/admin/ltipodocumentos.htm">Tipo de Documentos</a></li>
                                <li><a href="${pageContext.request.contextPath}/admin/ltipotramite.htm">Tipo de Tramite</a></li>
                               --%>
                                 <li><a href="${pageContext.request.contextPath}/notario/lista.htm">Notar&iacute;as</a></li>
                                <li><a href="${pageContext.request.contextPath}/escritura/lista.htm">Lista de Escrituras</a></li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        </security:authorize>
                        <security:authorize ifAnyGranted="ROLE_SEC">
                         <li>
                            <a href="#"><i class="fa fa-edit fa-fw"></i> Consultas<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li><a href="${pageContext.request.contextPath}/secretaria/solicitudes.htm">Solicitudes</a></li>
                                <li><a href="${pageContext.request.contextPath}/secretaria/enArchivo.htm">Tramites en archivo</a></li>
                                <li><a href="${pageContext.request.contextPath}/secretaria/enNotaria.htm">Tramites en Notaria</a></li>
                                <li><a href="${pageContext.request.contextPath}/reportes/reporteAnual.htm">Reporte por a&ntilde;os</a></li>
                                <li><a href="${pageContext.request.contextPath}/reportes/reporteMensual.htm">Reportes por meses</a></li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        </security:authorize>
                        <li><a href="${pageContext.request.contextPath}/cambioDeClave.htm"><i class="fa fa-users fa-fw"></i> Cambiar Clave<span class="fa arrow"></span></a></li>
                        <%-- <li><a href="${pageContext.request.contextPath}/login.htm"><i class="fa fa-users fa-fw"></i> Salir<span class="fa arrow"></span></a></li>--%>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>