package pe.org.cnl.gestiondoc.controller;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.org.cnl.gestiondoc.model.Tramite;
import pe.org.cnl.gestiondoc.model.TramiteAdjunto;
import pe.org.cnl.gestiondoc.model.TramiteUsuario;
import pe.org.cnl.gestiondoc.service.TramiteService;
import pe.org.cnl.gestiondoc.util.FileUpload;
import pe.org.cnl.gestiondoc.util.Utiles;

@Controller
@RequestMapping(value="/tramites")
public class TramiteController {
	
	private static final Logger logger = Logger.getLogger(TramiteController.class );
	
	@Autowired
	private TramiteService tramiteService;
	
	@RequestMapping("/lista.htm")
	public String lista(HttpServletRequest request,ModelMap model){
		logger.debug(" lista.html");
		Tramite tr = new Tramite();
		
		Integer estado = null;
		if( request.getParameter("estado") != null )
			estado = Integer.parseInt( request.getParameter("estado") );
		else
			estado = (Integer)request.getAttribute("estado");
		
		tr.setEstado( estado );
		TramiteUsuario te = new TramiteUsuario();
		te.setEstado(1);
		tr.addTramiteUsuario( te );
		model.put("tramite", new Tramite());
		model.put("lTramites", tramiteService.buscar(tr));
		return "tramite/listaTramite";
	}
	
	@RequestMapping("/buscar.htm")
	public String buscar(@Valid Tramite tramite, BindingResult result,HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" buscar ");
			tramite.setFechaCreacion( Utiles.stringToDate( request.getParameter("fechaCreacion"), "dd/MM/yyyy"));
			model.put("lTramites", tramiteService.buscar(tramite) );
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			model.put("tramite", tramite);
		}
		return  "tramite/listaTramite";
	}
	
	@RequestMapping("/derivar.htm")
	public String derivar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			
			logger.debug(" derivar ");
			Tramite tr =  tramiteService.obtener( Integer.parseInt(request.getParameter("cod")) ) ;
			Integer ultimoEstado = tr.getEstado();
			tramiteService.derivar( tr );
			request.setAttribute("estado", ultimoEstado );
			return lista(request, model);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "tramite/listaTramite";
	}
	
	@RequestMapping("/preatender.htm")
	public String preAtender(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" preAtender "  + request.getParameter("cod") );
			Tramite tr =  tramiteService.obtener( Integer.parseInt( request.getParameter("cod") ));
			logger.debug(  "tramite" + tr );
			model.put("tramite", tr );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "tramite/tramiteForm";
	}
	
	@RequestMapping("/atender.htm")
	public String atender(@Valid Tramite tramite, BindingResult result,HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" atender ");
			
			Tramite tr = tramiteService.obtener( tramite.getIdTramite() ) ;
			tr.setInformeSolicitud( tramite.getInformeSolicitud() );
			
			TramiteUsuario tu = new TramiteUsuario();
			tu.setTramite( tr );
			
			tramiteService.registrarAtencion( tu );
			model.put("mensaje", "El tramite ha sido creado exitosamente");
			
			tr = new Tramite();
			tr.setEstado( 2 );
			TramiteUsuario te = new TramiteUsuario();
			te.setEstado(1);
			tr.addTramiteUsuario( te );
			model.put("tramite", new Tramite());
			model.put("lTramites", tramiteService.buscar(tr));
			return "tramite/listaTramite";
			
		} catch (Exception e) {
			e.printStackTrace();
			model.put("tramite", tramite );
			model.put("msgError", e.getMessage());
			return "tramite/tramiteForm";
		}
	}
	
	@RequestMapping("/ver.htm")
	public String ver(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" ver ");
			model.put("tramite", tramiteService.obtener( Integer.parseInt(request.getParameter("cod")) ) );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "tramite/tramiteView";
	}
	
	@RequestMapping(value="/cargarAdjunto.htm", method=RequestMethod.POST)
	public String cargarAdjunto(@ModelAttribute("uploadForm") FileUpload formArchivo, HttpServletRequest request, ModelMap model){
		logger.debug("grabo adjunto en BD");
		 try{
			 logger.debug(" id documento " + formArchivo.getIdDocumento());
			 logger.debug(" file " + formArchivo.getFile());
			 tramiteService.registrarArchivoEnDisco( formArchivo.getFile() , formArchivo.getIdDocumento() );
            model.put("mensaje", "archivo cargado");
            
       	}catch(Exception ex) {
           logger.debug(ex.getMessage());
       	   model.put("msgError","Error " + ex.getMessage());
        }finally{
        	model.put("uploadForm", new FileUpload() );
            model.put("tramite",  tramiteService.obtener( formArchivo.getIdDocumento() ) );
        }
        return "tramite/tramiteForm";
	}
	
	/* DESCARGA DE DOC*/
	@RequestMapping(value="/descargar.htm",method=RequestMethod.GET)
    public String descargar(HttpServletRequest request, HttpServletResponse response, ModelMap model){		
		logger.debug("obtengo el archivo que estaba grabado");
         try {                   
             response.reset(); 
             Integer id = Integer.parseInt( request.getParameter("id") );
             response.setContentType("application/pdf");             
             ServletOutputStream out = response.getOutputStream();
             TramiteAdjunto archivo = tramiteService.obtenerArchivoEnDisco(id);
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
	
	@RequestMapping(value="/responder.htm", method=RequestMethod.POST)
	public String responder(@Valid Tramite tramite, BindingResult result,HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" responder ");
			
			Tramite tr = tramiteService.obtener( tramite.getIdTramite() ) ;
			tr.setObservacionesNotario( tramite.getObservacionesNotario() );
			tr.setFechaConclusion( Utiles.stringToDate( request.getParameter("fechaConclusion"), "dd/MM/yyyy"));
			
			TramiteUsuario tu = new TramiteUsuario();
			tu.setTramite( tr );
			
			tramiteService.registrarRespuesta( tu );
			model.put("mensaje", "El tramite ha sido creado exitosamente");
			
			tr = new Tramite();
			tr.setEstado( 4 );
			TramiteUsuario te = new TramiteUsuario();
			te.setEstado(1);
			tr.addTramiteUsuario( te );
			model.put("tramite", new Tramite());
			model.put("lTramites", tramiteService.buscar(tr));
			return "tramite/listaTramite";
			
		} catch (Exception e) {
			e.printStackTrace();
			model.put("tramite", tramite );
			model.put("msgError", e.getMessage());
			return "tramite/tramiteForm";
		}
	}
	
	@RequestMapping(value="/notificar.htm", method=RequestMethod.POST)
	public String notificar(@Valid Tramite tramite, BindingResult result,HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" notificar ");
			
			Tramite tr = tramiteService.obtener( tramite.getIdTramite() ) ;
			tr.setDetalleNotificacion( tramite.getDetalleNotificacion() );
			
			TramiteUsuario tu = new TramiteUsuario();
			tu.setTramite( tr );
			
			tramiteService.registrarRespuesta( tu );
			model.put("mensaje", "El tramite ha sido creado exitosamente");
			
			tr = new Tramite();
			tr.setEstado( 4 );
			TramiteUsuario te = new TramiteUsuario();
			te.setEstado(1);
			tr.addTramiteUsuario( te );
			model.put("tramite", new Tramite());
			model.put("lTramites", tramiteService.buscar(tr));
			return "tramite/listaTramite";
			
		} catch (Exception e) {
			e.printStackTrace();
			model.put("tramite", tramite );
			model.put("msgError", e.getMessage());
			return "tramite/tramiteForm";
		}
	}
}
