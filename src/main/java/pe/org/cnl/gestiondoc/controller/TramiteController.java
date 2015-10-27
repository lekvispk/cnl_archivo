package pe.org.cnl.gestiondoc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.org.cnl.gestiondoc.model.Tramite;
import pe.org.cnl.gestiondoc.model.TramiteUsuario;
import pe.org.cnl.gestiondoc.service.TramiteService;
import pe.org.cnl.gestiondoc.util.Utiles;

@Controller
@RequestMapping(value="/tramites")
public class TramiteController {
	
	private static final Logger logger = Logger.getLogger(TramiteController.class );
	
	@Autowired
	private TramiteService tramiteService;
	
	@RequestMapping("/lista.htm")
	public String lista(HttpServletRequest request,ModelMap model){
		logger.debug(" login.html");
		Tramite tr = new Tramite();
		tr.setEstado( Integer.parseInt( request.getParameter("estado")) );
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
	
	@RequestMapping("/prenuevo.htm")
	public String prenuevo(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" prenuevo ");
			//model.put("ltipoacto", tipoActoService.listarTiposActos() );
			//model.put("lTipoSol",  tipoSolicitudService.listarTipoSolicitud() );
			//model.put("lnotarias",  notariaService.buscar( null ) );
			model.put("tramite", new Tramite());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "tramite/tramiteForm";
	}
	
	@RequestMapping("/nuevo.htm")
	public String nuevo(@Valid Tramite tramite, BindingResult result,HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" nuevo ");
			
			//solicitud.setEstado( 1 );
			//solicitud.setFechaIngreso( Utiles.stringToDate( request.getParameter("fechaIngreso"), "dd/MM/yyyy"));
			//solicitudService.registrarSolicitud(solicitud);
			model.put("mensaje", "El tramite ha sido creado exitosamente");
			
			return lista(request, model);
			
		} catch (Exception e) {
			e.printStackTrace();
			model.put("tramite", tramite );
			model.put("msgError", e.getMessage());
			return "tramite/tramiteForm";
		}
	}
	
	@RequestMapping("/atender.htm")
	public String atender(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" atender ");
			//model.put("ltipoacto", tipoActoService.listarTiposActos() );
			//model.put("lTipoSol",  tipoSolicitudService.listarTipoSolicitud() );
			//model.put("lnotarias",  notariaService.buscar( null ) );
			model.put("tramite", tramiteService.obtener( Integer.parseInt( request.getParameter("cod"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "tramite/tramiteForm";
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
}
