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

import pe.org.cnl.gestiondoc.model.Solicitud;
import pe.org.cnl.gestiondoc.model.SolicitudTramite;
import pe.org.cnl.gestiondoc.model.SolicitudTramitePK;
import pe.org.cnl.gestiondoc.service.ArchivoService;
import pe.org.cnl.gestiondoc.service.EscrituraService;
import pe.org.cnl.gestiondoc.service.SolicitudService;
import pe.org.cnl.gestiondoc.service.SolicitudTramiteService;
import pe.org.cnl.gestiondoc.service.TipoActoService;
import pe.org.cnl.gestiondoc.util.Utiles;

@Controller
public class PendientesController {

	private static final Logger logger = Logger.getLogger(PendientesController.class );
	
	@Autowired
	private SolicitudTramiteService solicitudTramiteService;
	@Autowired
	private TipoActoService tipoActoService;
	@Autowired
	private SolicitudService solicitudService;
	@Autowired
	private EscrituraService escrituraService;
	@Autowired
	private ArchivoService archivoService;
	
	@RequestMapping("/pendientes/lista.htm")
	public String lista(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" lista ");

			model.put("lPendientes", solicitudTramiteService.buscarSolicitudes(1) );
			model.put("pendiente", new SolicitudTramite());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "pendientes/lista";
	}
	
	//
	
	@RequestMapping("/pendientes/lFirmados.htm")
	public String lFirmados(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" lista ");
			model.put("lPendientes", solicitudTramiteService.buscarSolicitudes(2) );
			model.put("pendiente", new SolicitudTramite());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "pendientes/firmados";
	}
	
	
	
	@RequestMapping("firmar.htm")
	public String firmar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" lista ");
			String id1 = request.getParameter("id1");
			logger.debug( id1 );
			String id2 = request.getParameter("id2");
			logger.debug( id2 );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "pendientes/firmar";
	}
	
	//
	
	@RequestMapping("/pendientes/preCargaEscritura.htm")
	public String preAdjuntaEscritura(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			//TODO: aqui debo traer los datos de la solicitud y hacer dos cosas:
			 //pre cargar las cajas de texto y hacer la busqueda con los datos que ya tengo en la solicitud.
			//mostraria los resultados y lascajas llenas
			
			Integer idSol =  Integer.parseInt(request.getParameter( "cod" ));
			SolicitudTramite sol = new SolicitudTramite();
			sol.setSolicitud( solicitudService.obtenerSolicitud( idSol ) );
			sol.setEscritura( solicitudService.completaEsctritura( sol.getSolicitud() ) );
			sol.getEscritura().setIdPersona( -1 );
			sol.getSolicitud().setIdsolicitud( idSol);
			
			logger.debug(" lista ");
			model.put("ltipoacto", tipoActoService.listarTiposActos() );
			model.put("solicitud", sol );
			model.put("lEscrituras", escrituraService.buscarEscitura(sol.getEscritura()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "pendientes/busqueda";
	}
	
	@RequestMapping("/pendientes/buscar.htm")
	public String buscar(@Valid SolicitudTramite solicitud, BindingResult result,HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" buscar ");
			solicitud.getEscritura().setTramFechaRegistro( Utiles.stringToDate( request.getParameter("tramFechaRegistro"), "dd/MM/yyyy"));
			solicitud.getEscritura().setTramFechaRegistro2( Utiles.stringToDate( request.getParameter("tramFechaRegistro2"), "dd/MM/yyyy"));
			model.put("solicitud", solicitud);
			
			model.put("lEscrituras", escrituraService.buscarEscitura(solicitud.getEscritura()));
			model.put("ltipoacto", tipoActoService.listarTiposActos() );
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "pendientes/busqueda";
	}
	
	@RequestMapping("/pendientes/seleccionar.htm")
	public String seleciconar( HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" buscar ");
			
			String idEscritura = request.getParameter("id1");
			String idSolicitud = request.getParameter("id2");
			logger.debug(" idEscritura " + idEscritura);
			logger.debug(" idSolicitud " + idSolicitud);
			
			SolicitudTramite sol = new SolicitudTramite();
			SolicitudTramitePK pk = new SolicitudTramitePK();
			pk.setIdEscritura(Integer.parseInt(idEscritura));
			pk.setIdsolicitud(Integer.parseInt(idSolicitud));
			sol.setId(pk);
			sol.setEstado(1);
			sol.setFecSolicitud( solicitudService.obtenerSolicitud(Integer.parseInt(idSolicitud)).getFechaIngreso() );
			sol.setArchivo( archivoService.obtenerArchivoEscritura( idEscritura) );
			
			solicitudService.registrarSolicitudTramite(sol);
			model.put("ltipoacto", tipoActoService.listarTiposActos() );
			model.put("solicitud", new SolicitudTramite());
			model.put("mensaje", " Se ha asociado la escritura seleccionada con la solicitud.");
			
			model.put("ltipoacto", tipoActoService.listarTiposActos() );
			model.put("solicitud", new Solicitud());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "solicitud/lista";
	}
}
