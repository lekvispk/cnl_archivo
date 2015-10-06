<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security'%>

<script language="JavaScript" type="text/JavaScript">
	function salir(){
	   window.parent.location.href = "${pageContext.request.contextPath}/login.htm";
	}
</script>
<div id="menuPrincipal">
	<!--Capa que contiene el menu-->
	<h2>MENU</h2>
	<ul id="men_ContenedorItem">
		<li><a class="notcurrent">OPCIONES DEL SISTEMA</a>
			<div class="oculto">
				<ul class="men_SubContItem">
					<li><a class="otroClase" href="${pageContext.request.contextPath}/solicitud/lista.htm">SOLICITUDES</a></li>
					<li><a class="otroClase" href="${pageContext.request.contextPath}/escritura/lista.htm">ESCRITURAS</a></li>
					<li><a class="otroClase" href="${pageContext.request.contextPath}/pendientes/lista.htm">PENDIENTES</a></li>
					<li><a class="otroClase" href="${pageContext.request.contextPath}/pendientes/lFirmados.htm">FIRMADOS</a></li>
					<!-- 
					<li><a class="otroClase" href="${pageContext.request.contextPath}/admin/listaUsr.htm">USUARIOS</a></li>
					<li><a class="otroClase" href="${pageContext.request.contextPath}/admin/lpersonas.htm">PERSONAS</a></li>
					 -->
					<li><a class="otroClase" href="${pageContext.request.contextPath}/admin/precpass.htm">CAMBIAR CONTRASE&Ntilde;A</a></li>
					
					<li><a class="otroClase" href="javascript:salir();">SALIR DEL SISTEMA</a></li>
				</ul>				
			</div></li>
	</ul>
</div>