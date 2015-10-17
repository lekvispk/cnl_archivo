//boton guardar en la ventana de busqueda realizada, boton grabar
$(document).undelegate('#modal_detalleEscritura_guardar', 'click').delegate('#modal_detalleEscritura_guardar', 'click', function(){
	console.debug('llamada a controller para que grabe la info');
	$("#modalView").modal('hide');
});
