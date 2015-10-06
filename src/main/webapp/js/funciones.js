$(document).ready(function () {
    var emailreg = /^[a-zA-Z0-9_\.\-]+@[a-zA-Z0-9\-]+\.[a-zA-Z0-9\-\.]+$/;
      
    $(".boton").click(function (){
    	var resultado = true;
        $(".error").remove();
        if( $(".notario").val() == "-1" ){
            $(".notario").focus().after("<span class='error'>Seleccione al notario</span>");
            resultado = false ;//return false;
        }
        if( $(".tipotram").val() == "-1" ){
            $(".tipotram").focus().after("<span class='error'>Seleccione el Tipo de Tramite</span>");
            resultado = false ;//return false;
        }
        if( $(".observaciones").val() == "" ){
            $(".observaciones").focus().after("<span class='error'>Ingrese el detalle</span>");
            resultado = false ;//return false;
        }
        if( $(".asunto").val() == "" ){
            $(".asunto").focus().after("<span class='error'>Ingrese el asunto</span>");
            resultado = false ;//return false;
        } 
        if( $(".numero").val() == "" ){
            $(".numero").focus().after("<span class='error'>Ingrese el numero de circular</span>");
            resultado = false ;//return false;
        } 
        if( $(".fecha").val() == "" ){
            $(".fecha").focus().after("<span class='error'>Ingrese la fecha</span>");
            resultado = false ;//return false;
        } 
        if( $(".fecharespuesta").val() == "" ){
            $(".fecharespuesta").focus().after("<span class='error'>Ingrese la fecha de respuesta</span>");
            resultado = false ;//return false;
        } 
        if( $(".docrespuesta").val() == "" ){
            $(".docrespuesta").focus().after("<span class='error'>Ingrese el documento de respuesta</span>");
            resultado = false ;//return false;
        } 
        return resultado;
        /*if( $(".email").val() == "" || !emailreg.test($(".email").val()) ){
            $(".email").focus().after("<span class='error'>Ingrese un email correcto</span>");
            return false;
        }else if( $(".mensaje").val() == "" ){
            $(".mensaje").focus().after("<span class='error'>Ingrese un mensaje</span>");
            return false;
        }*/
    });
    
    $(".observaciones, .asunto, .tipotram, .numero").keyup(function(){
        if( $(this).val() != "" ){
            $(".error").fadeOut();
            return false;
        }
    });
    
    $(".email").keyup(function(){
        if( $(this).val() != "" && emailreg.test($(this).val())){
            $(".error").fadeOut();
            return false;
        }
    });
    

});


/* Fijarse en los estilos que lo acompañan, estilos.css al final de la hoja*/