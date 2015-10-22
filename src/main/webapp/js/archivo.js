//clic en ver detalle de cualquier lista
$(document).undelegate('[id^=ver_], [id^=derivar_]', 'click').delegate('[id^=ver_], [id^=derivar_]', 'click', function(){ 
	 console.info('click en ver detalle de escritura');
	 $("#modalView .modal-content").load( $(this).attr('data-link') , function() { 
   		  $( "#modalView" ).modal("show"); 
	  });
}); 


//para darle funcion ajax al displaytag
$("#displayTagDiv").displayTagAjax();

//boton guardar en la ventana de busqueda realizada, boton grabar
$(document).undelegate('#modal_detalleEscritura_guardar', 'click').delegate('#modal_detalleEscritura_guardar', 'click', function(){
	console.debug('llamada a controller para que grabe la info');
	// aqui no se que guardar
	$("#modalView").modal('hide');
});

/**
 * Boton Calcular en modal de Detalle de Escritura
 */
$(document).undelegate('#detalleEscrituraCalcularBtn', 'click').delegate('#detalleEscrituraCalcularBtn', 'click', function(){
	console.debug('calcular');
	var nroFirmas = $("#txtFirmas").val(); 
	var costo = $("#hiddenCosto").val();
	if( nroFirmas == ''){ 
		alert('no ha ingresado el numero de firmas.');
		$("#txtFirmas").focus();
		return;
	}
	
	$("#txtCosto").val( costo * nroFirmas);
});
