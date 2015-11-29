package pe.org.cnl.gestiondoc.controller;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.org.cnl.gestiondoc.model.Archivo;
import pe.org.cnl.gestiondoc.service.ArchivoService;
import pe.org.cnl.gestiondoc.service.EscrituraService;
import pe.org.cnl.gestiondoc.service.TipoActoService;
import pe.org.cnl.gestiondoc.util.FileUpload;

@Controller
@Scope("session")
@RequestMapping(value="/archivo")
public class EscrituraArchivo {

	private static final Logger logger = Logger.getLogger(EscrituraArchivo.class );
	
	@Autowired
	private ArchivoService archivoService; 
	@Autowired
	private TipoActoService tipoActoService;
	@Autowired
	private EscrituraService escrituraService;
	
	@RequestMapping(value="cargarEscritura.htm", method=RequestMethod.POST)
	public String cargar(@ModelAttribute("uploadForm") FileUpload formArchivo, HttpServletRequest request, ModelMap model){
		logger.debug("grabo adjunto en BD");
		 try{
			 
			 logger.debug(" file " + formArchivo.getFile() +  " extension = " +formArchivo.getFile().getContentType()); 
			 String extension = formArchivo.getFile().getOriginalFilename();
			 if( !extension.contains( ".pdf" ) ){
				 throw new Exception("Formato no permitido. Cargar archivos .PDF");
			 }
			 
            archivoService.registrarArchivoEnDisco( formArchivo.getFile() , formArchivo.getIdDocumento() );
            //request.setAttribute("idDocumento", formArchivo.getIdDocumento() );
            model.put("mensaje", "archivo cargado");
            
       	}catch(Exception ex) {
           logger.debug(ex.getMessage());
       	   model.put("msgError","Error " + ex.getMessage());
        }finally{
        	model.put("uploadForm", new FileUpload() );
        	model.put("ltipoacto", tipoActoService.listarTiposActos() );
            model.put("escritura",  escrituraService.obtenerEscritura( formArchivo.getIdDocumento() ));
        }
        return "escritura/registroEscritura";
	}
	
	@RequestMapping("/eliminarArchivo.htm")
	public String eliminarArchivoEscritura(HttpServletRequest request,ModelMap model){
		logger.debug("eliminarArchivoEscritura");
		
		Integer cod = Integer.parseInt( request.getParameter("id"));
		Integer isEscritura = Integer.parseInt( request.getParameter("isEscritura"));
		archivoService.eliminarArchivo( cod );
		
		model.put("uploadForm", new FileUpload() );
    	model.put("ltipoacto", tipoActoService.listarTiposActos() );
        model.put("escritura",  escrituraService.obtenerEscritura( isEscritura ));
        
		return "escritura/registroEscritura";
	}

	/* DESCARGA DE DOC*/
	@RequestMapping("/descargar.htm")
    public String descargar(HttpServletRequest request, HttpServletResponse response, ModelMap model){		
		logger.debug("obtengo el archivo que estaba grabado");
         try {                   
             response.reset(); 
             Integer id = Integer.parseInt( request.getParameter("id") );
             Archivo archivo = archivoService.obtenerArchivoEnDisco(id);
             response.setContentType( archivo.getMimetype() );
             ServletOutputStream out = response.getOutputStream();
             response.addHeader("Content-Disposition", "attachment;filename=\""+ archivo.getNombre() +"\"");
             out.write( archivo.getArchivo() , 0,  archivo.getArchivo().length );
             out.flush();
             out.close();                  
         } catch (Exception e) {
             e.printStackTrace();
             model.put("msgError","Error " + e.getMessage());
         }
         return null;
    }
	
}
