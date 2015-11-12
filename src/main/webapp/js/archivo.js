$(function() {
	//para darle funcion ajax al displaytag
	$("#displayTagDiv").displayTagAjax();
	 
});

//clic en ver detalle de cualquier lista
$(document).undelegate('[id^=ver_], [id^=derivar_]', 'click').delegate('[id^=ver_], [id^=derivar_]', 'click', function(){ 
	 console.info('click en ver detalle de escritura');
	 $("#modalView .modal-content").load( $(this).attr('data-link') , function() { 
   		  $( "#modalView" ).modal("show"); 
	  });
}); 

//boton cancelar
$(document).undelegate('#modal_cerrar', 'click').delegate('#modal_cerrar', 'click', function(){
	$("#modalView").modal('hide');
});

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

/** 
 * Boton que abre segunda modal para firmar documento
 * */
$(document).undelegate('#modal_concluir_actualizar', 'click').delegate('#modal_concluir_actualizar', 'click', function(){
	
	console.debug('levanta segunda modal');
	var idEs = $(this).attr('data-idescritura');
	var idSol = $(this).attr('data-idsolicitud');
	var url = $(this).attr('data-url');
	url = url+'?id1='+idEs+'&id2='+idSol ; 
	console.debug( url );
	
	$('#modalView').modal('hide');
	var modal = $('#firmaModal').modal({remote: url });
	modal.modal('show');
	
//	$('#firmaModal .modal-content').html( url );
	//$('#firmaModal').modal('show');
	
});

//Firmar nuevo documento y grabarlo en la BD y cerrar la modal 
$(document).undelegate('#btnFirmarNuevaEscritura', 'click').delegate('#btnFirmarNuevaEscritura', 'click', function(){
	console.debug("listo para invocar a la firma");
	$('#firmaModal').modal('hide');
});
	