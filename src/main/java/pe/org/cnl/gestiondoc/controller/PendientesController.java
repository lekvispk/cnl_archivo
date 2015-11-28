package pe.org.cnl.gestiondoc.controller;

import java.io.File;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.org.cnl.gestiondoc.dao.UsuarioDao;
import pe.org.cnl.gestiondoc.firmador.EscrituraXML;
import pe.org.cnl.gestiondoc.firmador.Firmador;
import pe.org.cnl.gestiondoc.firmador.KeyStoreGenerator;
import pe.org.cnl.gestiondoc.model.Escritura;
import pe.org.cnl.gestiondoc.model.Solicitud;
import pe.org.cnl.gestiondoc.model.SolicitudTramite;
import pe.org.cnl.gestiondoc.model.Tramite;
import pe.org.cnl.gestiondoc.model.TramiteAdjunto;
import pe.org.cnl.gestiondoc.model.TramiteUsuario;
import pe.org.cnl.gestiondoc.service.ArchivoService;
import pe.org.cnl.gestiondoc.service.EscrituraService;
import pe.org.cnl.gestiondoc.service.SolicitudService;
import pe.org.cnl.gestiondoc.service.SolicitudTramiteService;
import pe.org.cnl.gestiondoc.service.TipoActoService;
import pe.org.cnl.gestiondoc.service.TramiteService;
import pe.org.cnl.gestiondoc.util.Mail;
import pe.org.cnl.gestiondoc.util.Utiles;

@Controller
@Scope("session")
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
	@Autowired
	private UsuarioDao usuarioDao;
	@Autowired
	private TramiteService tramiteService;
	@Autowired
	private Mail mailService;
	
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
	
	@RequestMapping(value="firmar.htm",method=RequestMethod.GET)
	public String preFirmar(HttpServletRequest request, ModelMap model){
		try {
			logger.debug(" firmar ");
			
			String id1 = request.getParameter("id1");
			String id2 = request.getParameter("id2");
			String id3 = request.getParameter("id3");
			List<String> firmas = new ArrayList<String>();
			logger.debug( id1 );
			logger.debug( id2 );
			logger.debug( id3 );
			KeyStore ks;
			 
			System.gc();
			logger.debug("Cargando certificados ...");
			KeyStoreGenerator ksg = new KeyStoreGenerator();
			ks = ksg.crearKeyStore(0);
			logger.debug("Almacen de llaves cargado ...");
			Enumeration enumAlias = ks.aliases();
			logger.debug("Almacen de llaves Elementos ...");
			while (enumAlias.hasMoreElements()) {
				String ali = (String)enumAlias.nextElement();
				if (ks.isKeyEntry(ali)) {
			    	logger.debug(ali);
			    	firmas.add( ali );
			    }
			}
			model.put("listaNotarios", firmas);
			model.put("id1", id1);
			model.put("id2", id2);
			model.put("id3", id3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "pendientes/firmar";
	}
	
	@RequestMapping(value="firmar.htm",method=RequestMethod.POST)
	public String firmar( HttpServletRequest request , HttpServletResponse response ){
		
		Integer id1 = Integer.valueOf(request.getParameter("id1"));//escritura
		Integer id2 = Integer.valueOf(request.getParameter("id2"));//solicitud
		Integer idTramite = Integer.valueOf(request.getParameter("id3"));//tramite
	
		try {
			
			Solicitud sol = solicitudService.obtenerSolicitud( id2 );
			
			logger.debug("Inicia Proceso de Firmado");
	    	String path = "";
	    	
	    	Escritura escritura = escrituraService.obtenerEscritura( id1 );
	    	TramiteAdjunto archivo = (TramiteAdjunto)request.getSession().getAttribute("archivoPendiente");
	    	//SolicitudTramite archivo = archivoService.obtenerArchivoPendiente(id1,id2);
            EscrituraXML xmlEsccritura = new EscrituraXML();
            
            logger.debug( escritura.getNumeroDoc() );
            
            xmlEsccritura.setAnioTramite( escritura.getNumeroDoc() );
            xmlEsccritura.setCodigoActo( escritura.getNumeroDoc() ) ;
            xmlEsccritura.setFecha( Utiles.DateToString( escritura.getFechaCreacion() , "dd/MM/yyyy") ) ;
            xmlEsccritura.setArchivo( archivo.getArchivo() );
            xmlEsccritura.setNombreArchivo(archivo.getNombre());
            xmlEsccritura.setKardex( escritura.getKardex() ) ;
            xmlEsccritura.setNumeroTramite( escritura.getNumeroInstrumento() );
            
            logger.debug("archivo en el modal " + xmlEsccritura);

            Firmador f = new Firmador();
            f.cargaCertificados();
            f.setNotarioCert( request.getParameter("cmbNotario"));
            f.setObjSunarpXML(xmlEsccritura);
            xmlEsccritura.setArchivoFirmado(f.firmarConCertificado());
            xmlEsccritura.setIdEscritura(id1);
            xmlEsccritura.setIdSolicitud(id2);

            if(xmlEsccritura.getArchivoFirmado()==null){
            	throw new Exception("No se pudo realizar la firma del nuevo archivo");
            }
            logger.debug(" * *  ARCHIVO FIRMADO DENTRO DE ESCRITURAXML");	

            // actualizar el archivo nuevo por el archivo antiguo 
            archivoService.actualizarArchivoEnDisco(xmlEsccritura, escritura.getIdEscritura());
            

			Tramite tr = tramiteService.obtener( idTramite ) ;
			
			TramiteUsuario tu = new TramiteUsuario();
			tu.setTramite( tr );
			
            tramiteService.registrarConclusion( tu);
            
            //enviar email al usuario con el nuevo archivo
            StringBuilder mensaje = new StringBuilder();
			mensaje.append("<html>");
			mensaje.append("<p>");
			mensaje.append( "Se ha completado el proceso de conclusion de firma. Se le remite el archivo firmado." );
			mensaje.append("</p>");
			mensaje.append("</html>");
			
			File fff = new File("tmp_"+archivo.getNombre());
			FileUtils.writeByteArrayToFile(fff, xmlEsccritura.getArchivoFirmado());
			FileSystemResource file = new FileSystemResource( fff );
			
            mailService.sendMailWithAttachment(
						            		"archivocnl@acsserviciosgenerales.com", 
						            		sol.getPersona().getEmail() ,
						            		null,
						            		"Fin de Conclusion de Firma", 
						            		mensaje.toString() , 
						            		archivo.getNombre() , 
						            		file );
            
            
	    }catch (Exception ex) {
	      ex.printStackTrace();
	      return "redirect:/tramites/porConcluir.htm?a=2";
	    }
		return "redirect:/tramites/concluidos.htm?a=1";
	}
	
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
			
			Escritura busqueda = new  Escritura();
			busqueda.setFechaEscritura( Utiles.stringToDate( request.getParameter("tramFechaRegistro"), "dd/MM/yyyy"));
			busqueda.setTramFechaRegistro2( Utiles.stringToDate( request.getParameter("tramFechaRegistro2"), "dd/MM/yyyy"));
			
			model.put("solicitud", solicitud);
			
			model.put("lEscrituras", escrituraService.buscarEscitura( busqueda ));
			model.put("ltipoacto", tipoActoService.listarTiposActos() );
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "pendientes/busqueda";
	}
	
	/**
	 * muestra la modal con la lista de empleados de la oficina de archivo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/pendientes/preSeleccionar.htm")
	public String preSeleciconar( HttpServletRequest request, HttpServletResponse response, ModelMap model){
		logger.debug(" preSeleciconar ");
		Integer idEscritura = Integer.parseInt( request.getParameter("idEscritura"));
		Integer idSolicitud = Integer.parseInt( request.getParameter("idSolicitud"));
		
		model.put("idEscritura", idEscritura);
		model.put("idSolicitud", idSolicitud);
		model.put("lsUsuarios", usuarioDao.listaUsuariosPorRole("ROLE_ARCHIVO"));
		
		return "solicitud/listaUsuariosArchivo";
	}
	
	@RequestMapping("/pendientes/seleccionar.htm")
	public String seleciconar( HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" seleciconar ");
			
			Integer idEscritura = Integer.parseInt(request.getParameter("idEscritura"));
			Integer idSolicitud = Integer.parseInt(request.getParameter("idSolicitud"));
			String username = request.getParameter("username");

			solicitudService.registrarSolicitudTramite(idEscritura, idSolicitud , username );
			
			model.put("ltipoacto", tipoActoService.listarTiposActos() );
			model.put("solicitud", new SolicitudTramite());
			model.put("mensaje", " Se ha derivado la solicitud.");
			
			model.put("ltipoacto", tipoActoService.listarTiposActos() );
			model.put("solicitud", new Solicitud());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "solicitud/lista";
	}
	
	/**
	 * no se para que se usaria este metodo ni su link
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/pendientes/atender.htm")
	public String atender( HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug("atender ");
			model.put("solicitud", solicitudService.obtenerSolicitud( Integer.parseInt( request.getParameter("id1"))));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "solicitud/lista";
	}
}
