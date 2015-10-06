<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../includes/head.jsp"/> 
<script>

	function cancelar(){
	    document.forms[0].action = "lista.htm";
	    document.forms[0].submit();
	}
		
	function agregar(){
	    document.forms[0].action = "aimplicados.htm";
	    document.forms[0].submit();
	}
	
	function nuevo(){
		window.open("prenpersona.htm", "Registro de Persona", "toolbar=0,location=0,directories=0,status=no,menubar=0,scrollbars=yes,resizable=yes,width=600,height=580,titlebar=no");
	}
	
	$(function() {
		$( "#tags" ).autocomplete({
			width: 300,
	        max: 10,
	        delay: 100,
	        autoFocus: true,
	        cacheLength: 1,
	        scroll: true,
	        highlight: false,
			source: function(request, response) {
	            $.ajax({
	                url: "${pageContext.request.contextPath}/admin/lpersonasAuto.htm",
	                dataType: "json",
	                data: request,
	                success: function( data, textStatus, jqXHR) {
	                    /*console.log( data);*/
	                    var items = data;
	                    response(items);
	                },
	                error: function(jqXHR, textStatus, errorThrown){
	                     console.log(textStatus);
	                }
	            });
	        },
			minLength: 2,
			select: function( event, ui ) {
				  $( "#idPersona" ).val( ui.item.id );
			}
		});
	});
	</script>
	

            <div id="contenedor">
                <!--Capa que contiene toda la plantilla-->
                <jsp:include page="../includes/cabecera.jsp" flush="true"/>
                 
                <div id="cuerpo" align="center">
                    <table class="anchoTotal" align="center" width="90%" border="1">
                        <tr>
                            <td id="anchoMenu" >
                                <jsp:include page="../includes/menu2.jsp" flush="true"/>
                            </td>
                            <td id="anchoCuerpo">
                            
                                <div id="contenido">
                                    <!--Capa que contiene el cuerpo de la plantilla-->                                     
                                    <h2>REGISTRO DE OFICIO</h2>                                     
                                    
                                   <!-- <h2>Implicados</h2> -->
                                   <h3> <span>MENCIONADOS EN OFICIO</span> </h3>
                                   <form name="frm3" action="#" method="post" >
                                   
                                   <table class="bloqueTablaLineal" cellspacing="6">
                                        <tr>
                                            <th>Persona:</th>
                                            <td>
                                            	<input type="hidden" name="documentoCnl.idDocumento" id="idDocumento"/>
                                            	<input type="hidden" name="persona.idPersona" id="idPersona"/>
                                            	<div class="demo">
													<div class="ui-widget">
														<input id="tags" type="text" size="40"/>
													</div>
												</div>
                                            </td>
                                            <td>	
                                            	 <ul class="enlacesCentroAjustables">
                                                      <li>
                                                          <input type="button" onclick="javascript:nuevo();" class="boton" value="Nueva Persona">
                                                       </li>
                                                  </ul>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th valign="middle">Tipo:</th>
                                            <td valign="middle" colspan="2">
                                            	<select name="tipoRelacion.idTipoRelacion" style="height: 20px; width: 180px; font-family: Arial; font-size: 9pt">
                                            		<option value="3" label="Informante"/>
                                                    <option value="1" label="Implicado"/>
                                               	</select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th valign="middle">Cargo:</th>
                                            <td valign="middle"><input type="text" name="cargo" size="25"/></td>
                                            <td valign="middle">
                                            <ul class="enlacesCentroAjustables">
                                            	<li><input type="button" onclick="javascript:agregar();" class="boton" value="Agregar"></li>
                                            </ul>
                                            </td>
                                        </tr>
                                     </table>
                                     
                                     
                                      <table id="myTable" width="90%" align="center">
						                            <thead>
						                            	<tr>
						                            		<th class="tituloTabla" axis="string">Tipo</th>
						                            		<th class="tituloTabla" axis="string">Persona</th>
						                            		<th class="tituloTabla" axis="string">Documento</th>
						                                    <th class="tituloTabla" axis="string">Cargo</th>
						                                    <th class="tituloTabla" axis="string" width="60px">Acciones</th>
						                                 </tr>
						                            </thead>
						                            <tbody>
						                            
						                             <c:if test="${not empty limplicados}">
						                            	<c:forEach items="${limplicados}" var="row">
						                            	<tr>                                    
						                                    <td>&nbsp;<c:out value="${row.tipoRelacion.relDescripcion}"/></td>
						                                    <td>&nbsp;<c:out value="${row.persona.nombreCompleto}"/></td>
						                                    <td>&nbsp;<c:out value="${row.persona.numDoc}"/></td>
						                                    <td>&nbsp;<c:out value="${row.cargo}"/></td>
						                                    <td>
						                                    	<a href="ver.htm?codd=${row.documentoCnl.idDocumento}&codp=${row.persona.idPersona}" style="border: 0px;" title="Ver"><img src="${pageContext.request.contextPath}/images/view.jpg" width="18" height="18" border="0"></a>
						                                    	<a href="javascript:eliminar(${row.documentoCnl.idDocumento},${row.persona.idPersona});" style="border: 0px;" title="Eliminar"><img src="${pageContext.request.contextPath}/images/error.png" width="18" height="18" border="0"></a>
						                                    </td>
						                                </tr>
						                               </c:forEach>
						                           </c:if>
						                           </tbody>
						                       </table>
                                   </form>


                                <center>
                                        <table summary="Contenedor Botones"
                                               class="tablaAnchoAjustable">
                                            <tr>
                                                <td>
                                                    <ul class="enlacesCentroAjustables">
                                                        <li><input type="button" onclick="javascript:cancelar();" class="boton" value="Finalizar"></li>
                                                    </ul>
                                                </td>
                                            </tr>
                                        </table>
                                    </center>                 
                                                  
                                </div>
                               
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <jsp:include page="../includes/pie.jsp" flush="true"/>