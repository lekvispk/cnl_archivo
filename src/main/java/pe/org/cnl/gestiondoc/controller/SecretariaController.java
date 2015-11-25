package pe.org.cnl.gestiondoc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.org.cnl.gestiondoc.model.Solicitud;
import pe.org.cnl.gestiondoc.model.Tramite;
import pe.org.cnl.gestiondoc.model.TramiteUsuario;
import pe.org.cnl.gestiondoc.service.SolicitudService;
import pe.org.cnl.gestiondoc.service.TramiteService;

@Controller
@RequestMapping(value="/secretaria")
public class SecretariaController {

	private static final Logger logger = Logger.getLogger( SecretariaController.class );
	
	@Autowired
	private SolicitudService solicitudService;
	
	@Autowired
	private TramiteService tramiteService;
	
	@RequestMapping("/solicitudes.htm")
	public String solicitudes(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" lista ");
			model.put("lSolicitudes", solicitudService.buscarSolicitudes( new Solicitud() ) );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "solicitud/listaInicialSolicitud";
	}
	
	@RequestMapping("/enArchivo.htm")
	public String enArchivo(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		logger.debug("enArchivo.html");
		
		Tramite tr = new Tramite();
		tr.setEstado( 2 );
		TramiteUsuario te = new TramiteUsuario();
		te.setEstado(1);		
		tr.addTramiteUsuario( te );
		
		model.put("tramite", new Tramite());
		model.put("lTramites", tramiteService.buscarParaSecretaria(tr));
		
		return "tramite/listaTramite";
	}
	
	@RequestMapping("/enNotaria.htm")
	public String enNotaria(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		logger.debug("enNotaria.html");
		
		Tramite tr = new Tramite();
		tr.setEstado( 3 );
		TramiteUsuario te = new TramiteUsuario();
		te.setEstado(1);
		
		tr.addTramiteUsuario( te );
		
		model.put("tramite", new Tramite());
		model.put("lTramites", tramiteService.buscar(tr));
		
		return "tramite/listaTramite";
	}
	
	@RequestMapping("/reportes.htm")
	public String reportes(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" lista ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "solicitud/listaInicialSolicitud";
	}
}
