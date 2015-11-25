package pe.org.cnl.gestiondoc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.org.cnl.gestiondoc.model.Solicitud;
import pe.org.cnl.gestiondoc.service.NotariaService;
import pe.org.cnl.gestiondoc.service.SolicitudService;
import pe.org.cnl.gestiondoc.service.TipoActoService;
import pe.org.cnl.gestiondoc.service.TipoSolicitudService;
import pe.org.cnl.gestiondoc.util.Utiles;

@Controller
@Scope("session")
@RequestMapping(value="/solicitud")
public class SolicitudController {

	private static final Logger logger = Logger.getLogger( SolicitudController.class );
	@Autowired
	private TipoActoService tipoActoService;

	@Autowired
	private TipoSolicitudService tipoSolicitudService;
	
	@Autowired
	private SolicitudService solicitudService;
	
	@Autowired
	private NotariaService notariaService;
	
	@RequestMapping("/inicial.htm")
	public String listaInicial(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" lista ");
			model.put("lSolicitudes", solicitudService.buscarSolicitudes( new Solicitud() ) );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "solicitud/listaInicialSolicitud";
	}
	
	/**
	 * pantalla inicial en donde se realizaran las busquedas de las solicitudes
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/lista.htm")
	public String lista(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" lista ");
			model.put("ltipoacto", tipoActoService.listarTiposActos() );
			model.put("solicitud", new Solicitud());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "solicitud/lista";
	}
	
	@RequestMapping("/buscar.htm")
	public String buscar(@Valid Solicitud solicitud, BindingResult result,HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" buscar ");
			solicitud.setFechaIngreso( Utiles.stringToDate( request.getParameter("fechaIngreso"), "dd/MM/yyyy"));
			solicitud.setFechaIngreso2( Utiles.stringToDate( request.getParameter("fechaIngreso2"), "dd/MM/yyyy"));
			model.put("solicitud", solicitud);
			model.put("ltipoacto", tipoActoService.listarTiposActos() );
			model.put("lSolicitudes", solicitudService.buscarSolicitudes(solicitud) );
			model.put("ltiposolicitid", tipoSolicitudService.listarTipoSolicitud() );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "solicitud/lista";
	}
	
	@RequestMapping("/prenuevo.htm")
	public String prenuevo(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" prenuevo ");
			model.put("ltipoacto", tipoActoService.listarTiposActos() );
			model.put("lTipoSol",  tipoSolicitudService.listarTipoSolicitud() );
			model.put("lnotarias",  notariaService.buscar( null ) );
			model.put("solicitud", new Solicitud());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "solicitud/registro";
	}
	
	@RequestMapping(value="/nuevo.htm",method=RequestMethod.POST)
	public String nuevo(@Valid Solicitud solicitud, BindingResult result,HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" nuevo ");
			
			solicitud.setFechaIngreso( Utiles.stringToDate( request.getParameter("fechaIngreso"), "dd/MM/yyyy"));
			solicitudService.registrarSolicitud(solicitud);
			model.put("mensaje", "La solicitud ha sido creada exitosamente");
			
			return lista(request, response, model);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			model.put("solicitud", solicitud );
			model.put("ltipoacto", tipoActoService.listarTiposActos() );
			model.put("lTipoSol",  tipoSolicitudService.listarTipoSolicitud() );
			model.put("lnotarias",  notariaService.buscar( null ) );
			model.put("msgError", e.getMessage());
			
			return "solicitud/registro";
		}
		
	}
	
	@RequestMapping("/preparticipantes.htm")
	public String preparticipantes(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" preparticipantes ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "solicitud/participantes";
	}
	
	@RequestMapping("/participantes.htm")
	public String participantes(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" participantes ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "solicitud/participantes";
	}
	
	@RequestMapping("/ver.htm")
	public String ver(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" ver ");
			model.put("solicitud", solicitudService.obtenerSolicitud( Integer.parseInt(request.getParameter("cod")) ) );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "solicitud/vista";
	}
	
	@RequestMapping("/editar.htm")
	public String editar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" editar ");
			model.put("solicitud", solicitudService.obtenerSolicitud( Integer.parseInt(request.getParameter("cod")) ) );
			model.put("ltipoacto", tipoActoService.listarTiposActos() );
			model.put("lTipoSol",  tipoSolicitudService.listarTipoSolicitud() );
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "solicitud/registro";
	}
	
	
	@RequestMapping(value="/eliminar.htm")
	public String eliminarALerta(HttpServletRequest request, ModelMap model){
		try {
			logger.debug("alerta/eliminar");
			
			model.put("ltipoacto", tipoActoService.listarTiposActos() );
			model.put("solicitud", new Solicitud());			
			Integer id = Integer.parseInt( request.getParameter("cod"));
			solicitudService.eliminarSolicitud( id );
			
			model.put("mensaje", "La solicitud ha sido eliminado");
		} catch (Exception e) {
			e.printStackTrace();
			model.put("msgError", e.getMessage());
		}
		return "solicitud/lista";
	}
	

	@RequestMapping("/listaPorCotizar.htm")
	public String listaPorCotizar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" listaPorCotizar ");
			Solicitud solicitud = new Solicitud();
			solicitud.setEstado(1);
			model.put("lSolicitudes", solicitudService.buscarSolicitudes( solicitud ) );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "solicitud/listaSolicitudPorCotizar";
	}
	
	
}
