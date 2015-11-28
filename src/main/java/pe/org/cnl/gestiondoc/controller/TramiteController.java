package pe.org.cnl.gestiondoc.controller;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import pe.org.cnl.gestiondoc.model.Tramite;
import pe.org.cnl.gestiondoc.model.TramiteAdjunto;
import pe.org.cnl.gestiondoc.model.TramiteUsuario;
import pe.org.cnl.gestiondoc.model.Usuario;
import pe.org.cnl.gestiondoc.service.TramiteService;
import pe.org.cnl.gestiondoc.util.FileUpload;
import pe.org.cnl.gestiondoc.util.Mail;
import pe.org.cnl.gestiondoc.util.Utiles;

@Controller
@Scope("session")
@SessionAttributes("archivoPendiente")
@RequestMapping(value="/tramites")
public class TramiteController {
	
	private static final Logger logger = Logger.getLogger(TramiteController.class );
	
	@Autowired
	private TramiteService tramiteService;
	
	@Autowired
	private Mail mailService;
	
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
	/**
	 * Bandeja de respondidos, el notario debe ver solo los tramites que el ha respondido. no importa el estado en que se encuentre el tramite 
	 * ya que si se ha concluido igual creo que debe de verlos por su historico 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/respondidos.htm")
	public String respondidos(HttpServletRequest request,ModelMap model){
		logger.debug(" respondidos.html");
		
		Tramite tr = new Tramite();
		TramiteUsuario te = new TramiteUsuario();
		
		if( Usuario.tienePermiso("ROLE_NOTARIO")){
			//el notario ve todos los que el ha respondido sin importar si esten concluidos o no, por eso no seteo estado
			te.setEstadoTramiteFinal(4);
			te.setSecUsuario1( Usuario.getUsuarioBean() );	
		}else{
			//personal de archivo ve aqui solo los responndidos recientes, si el los deriva ya deja de verlos aqui 
			tr.setEstado( 4 );	
		}
		
		tr.addTramiteUsuario( te );
		
		//por si lo he invocado luego de un insert o update
		if( "1".equals(request.getParameter("ex")) ){
			model.put("mensaje", "El tramite ha sido respondido exitosamente");
		}
		
		model.put("tramite", new Tramite());
		model.put("lTramites", tramiteService.buscar(tr));
		
		return "tramite/listaTramite";
	}
	
	@RequestMapping("/porConcluir.htm")
	public String porConcluir(HttpServletRequest request,ModelMap model){
		logger.debug("porConcluir.htm");
		
		Tramite tr = new Tramite();
		TramiteUsuario te = new TramiteUsuario();
 
		tr.setEstado( 5 );
		tr.addTramiteUsuario( te );
		
		//falla al concluir
		if( "2".equals(request.getParameter("a")) ){
			model.put("msgError", "El tramite no se ha podido concluir");
		}
		
		model.put("tramite", new Tramite());
		model.put("lTramites", tramiteService.buscar(tr));
		
		return "tramite/listaTramite";
	}
	
	@RequestMapping("/concluidos.htm")
	public String concluidos(HttpServletRequest request,ModelMap model){
		logger.debug(" concluidos.html");
		
		Tramite tr = new Tramite();
		TramiteUsuario te = new TramiteUsuario();
 
		tr.setEstado( 6 );
		
		tr.addTramiteUsuario( te );
		
		//por si lo he invocado luego de un insert o update
		if( "1".equals(request.getParameter("a")) ){
			model.put("mensaje", "El tramite ha sido concluido exitosamente");
		}
		
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
			model.put("mensaje", "Se ha derivado correctamente");
			return lista(request, model);
			
		} catch (Exception e) {
			e.printStackTrace();
			model.put("msgError", "" + e.getMessage());
		}
		return "tramite/listaTramite";
	}
	
	@RequestMapping("/preatender.htm")
	public String preAtender(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" preAtender "  + request.getParameter("cod") );
			Tramite tr =  tramiteService.obtener( Integer.parseInt( request.getParameter("cod") ));
			//logger.debug(  "tramite" + tr );
			
			//por si lo he invocado luego de cargar un adjunto
			if( "a".equals(request.getParameter("ex")) ){
				model.put("mensaje", "Se ha cargado el archivo correctamente");
			}
			if( "a2".equals(request.getParameter("ex")) ){
				model.put("msgError", "Error al cargar el archivo.");
			}
			
			model.put("tramite", tr );
			model.put("uploadForm", new FileUpload() );
			
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
			model.put("mensaje", "Los cambios han sido guardados exitosamente");
			
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
		logger.debug("cargarAdjunto -> grabo adjunto en BD");
		 try{
			 logger.debug(" id documento " + formArchivo.getIdDocumento());
			 logger.debug(" file " + formArchivo.getFile());
			 tramiteService.registrarArchivoEnDisco( formArchivo.getFile() , formArchivo.getIdDocumento() );
            
       	}catch(Exception ex) {
           logger.debug(ex.getMessage());
           return "redirect:/tramites/preatender.htm?cod="+formArchivo.getIdDocumento()+"&ex=a2";
        }
		return "redirect:/tramites/preatender.htm?cod="+formArchivo.getIdDocumento()+"&ex=a";
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
			//model.addAttribute("mensaje", "El tramite ha sido respondido exitosamente");
			
			return "redirect:/tramites/respondidos.htm?ex=1";
			
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
			//tu.setEstadoTramiteFinal(5);
			
			try {
				tramiteService.registrarNotificacion( tu );
				 
				StringBuilder mensaje = new StringBuilder();
				mensaje.append("<html>");
				mensaje.append("<p>");
				mensaje.append(tr.getDetalleNotificacion());
				mensaje.append("</p>");
				mensaje.append("<p>");
				mensaje.append( Utiles.DateToString(tr.getFechaConclusion(), "dd/MM/yyyy") );
				mensaje.append("</p>");
				mensaje.append("</html>");
				
				logger.debug( tr.getSolicitud().getPersona().getEmail() );
				logger.debug( mensaje.toString() );
				  
				mailService.sendMail("archivocnl@acsserviciosgenerales.com",  tr.getSolicitud().getPersona().getEmail() , null, "Notificaciones", mensaje.toString());
				  
				model.put("mensaje", "El cliente ha sido notificado exitosamente");	
				//TODO cambiar por redirectView
				tr = new Tramite();
				tr.setEstado( 4 );
				TramiteUsuario te = new TramiteUsuario();
				te.setEstado(1);
				tr.addTramiteUsuario( te );
				model.put("tramite", new Tramite());
				model.put("lTramites", tramiteService.buscar(tr));
					
			} catch (Exception e) {
				e.printStackTrace();
				model.put("msgError", "" + e.getMessage() );
			}
			
			
			return "tramite/listaTramite";
			
		} catch (Exception e) {
			e.printStackTrace();
			model.put("tramite", tramite );
			model.put("msgError", e.getMessage());
			return "tramite/tramiteForm";
		}
	}
	
	@RequestMapping(value="/concluir.htm", method=RequestMethod.GET)
	public String preConcluir( HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" preConcluir ");
			Tramite tr = tramiteService.obtener( Integer.parseInt( request.getParameter("idTramite")) ) ;
			model.put("tramite", tr );
			request.getSession().removeAttribute("archivoPendiente");
			model.remove("archivoPendiente");
			model.put("uploadForm", new FileUpload() );
		} catch (Exception e) {
			e.printStackTrace();
			model.put("msgError", e.getMessage());
		}
		return "tramite/concluir";
	}
	
	@RequestMapping(value="/cargarNuevaEscritura.htm", method=RequestMethod.POST)
	public String cargarNuevaEscritura(@ModelAttribute("uploadForm") FileUpload formArchivo, HttpServletRequest request, ModelMap model){
		logger.debug("cargarNuevaEscritura");
		TramiteAdjunto tr = null;
		 try{
			 logger.debug(" id documento " + formArchivo.getIdDocumento());
			 logger.debug(" file " + formArchivo.getFile());
			 
			 tr = new TramiteAdjunto();
			 tr.setNombre( formArchivo.getFile().getOriginalFilename() );
			 tr.setArchivo( formArchivo.getFile().getBytes() );
			 
			 model.addAttribute("archivoPendiente", tr );
			
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
	
}
