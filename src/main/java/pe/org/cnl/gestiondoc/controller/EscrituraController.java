package pe.org.cnl.gestiondoc.controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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

import pe.org.cnl.gestiondoc.firmador.EscrituraXML;
import pe.org.cnl.gestiondoc.model.Archivo;
import pe.org.cnl.gestiondoc.model.Escritura;
import pe.org.cnl.gestiondoc.model.PersonaEscritura;
import pe.org.cnl.gestiondoc.model.PersonaEscrituraPK;
import pe.org.cnl.gestiondoc.model.SolicitudTramite;
import pe.org.cnl.gestiondoc.service.ArchivoService;
import pe.org.cnl.gestiondoc.service.EscrituraService;
import pe.org.cnl.gestiondoc.service.SolicitudService;
import pe.org.cnl.gestiondoc.service.TipoActoService;
import pe.org.cnl.gestiondoc.service.TipoRelacionService;
import pe.org.cnl.gestiondoc.util.FileUpload;
import pe.org.cnl.gestiondoc.util.Utiles;

@Controller
public class EscrituraController {
	
	private static final Logger logger = Logger.getLogger( EscrituraController.class );
	
	@Autowired
	private TipoActoService tipoActoService;
	
	@Autowired
	private EscrituraService escrituraService;
	
	@Autowired
	private ArchivoService archivoService;
	
	@Autowired
	private TipoRelacionService tipoRelacionService;
	
	@Autowired
	private SolicitudService solicitudService;
	
	@RequestMapping("/escritura/lista.htm")
	public String login(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" lista ");
			model.put("ltipoacto", tipoActoService.listarTiposActos() );
			model.put("escritura", new Escritura());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "escritura/listaEscritura";
	}
	
	@RequestMapping("/escritura/buscar.htm")
	public String buscar(@Valid Escritura escritura, BindingResult result,HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" buscar ");
			
			escritura.setFechaEscritura( Utiles.stringToDate( request.getParameter("tramFechaRegistro"), "dd/MM/yyyy"));
			escritura.setTramFechaRegistro2( Utiles.stringToDate( request.getParameter("tramFechaRegistro2"), "dd/MM/yyyy"));
			
			model.put("lEscrituras", escrituraService.buscarEscitura(escritura));
			model.put("ltipoacto", tipoActoService.listarTiposActos() );
			model.put("escritura", escritura);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "escritura/listaEscritura";
	}
	
	@RequestMapping("/escritura/prenuevo.htm")
	public String prenuevo(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" lista ");
			
			model.put("ltipoacto", tipoActoService.listarTiposActos() );
			model.put("escritura", new Escritura());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "escritura/registroEscritura";
	}
	
	@RequestMapping("/escritura/nuevo.htm")
	public String nuevo(@Valid Escritura escritura, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" lista ");
			escritura.setFechaModificacion( Utiles.stringToDate( request.getParameter("tramFechaRegistro"), "dd/MM/yyyy"));
			escritura.setTramFechaRegistro2( Utiles.stringToDate( request.getParameter("tramFechaRegistro2"), "dd/MM/yyyy"));
			escritura.setEstado( 1 );
			escrituraService.registrarEscritura(escritura);
			model.put("mensaje", "se ha registrado la registrura correctamente");
			logger.debug( "id escritura = " + escritura.getIdEscritura());
		} catch (Exception e) {
			e.printStackTrace();
			model.put("msgError", e.getMessage());
		}finally{
			model.put("ltipoacto", tipoActoService.listarTiposActos() );
			model.put("escritura", escritura );
		}
		return "escritura/registroEscritura";
	}
	
	@RequestMapping("/escritura/preparticipantes.htm")
	public String preparticipantes(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
	
			if( request.getParameter("idEscritura") == null ){
				throw new Exception("no se ha indicado la escritura");
			}
			
			Integer idEscritura = Integer.parseInt(request.getParameter("idEscritura"));
			PersonaEscritura pe = new PersonaEscritura();
			pe.setEscritura( new Escritura());
			pe.getEscritura().setIdEscritura( idEscritura );
			
			model.put("lTipoRelacion", tipoRelacionService.listarTipos() );
			model.put("perescritura", pe );
			model.put("limplicados", escrituraService.obtenerPersonasEscritura( idEscritura )) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "escritura/participantes";
	}
	
	@RequestMapping("/escritura/participantes.htm")
	public String participantes(@Valid PersonaEscritura persona , BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug("Agrego el participante, implicado  en la alerta");
			
			PersonaEscrituraPK pepk = new PersonaEscrituraPK();
			pepk.setIdEscritura( persona.getEscritura().getIdEscritura() );
			pepk.setIdPersona( persona.getPersona().getIdPersona() );			
			persona.setId(pepk);
			escrituraService.registraRelacionado(persona);
			
			model.put("perescritura", new PersonaEscritura());
			model.put("mensaje", "Registrado");
			
		} catch (Exception e) {
			e.printStackTrace();
			model.put("perescritura", persona );
		}finally{
			model.put("lTipoRelacion", tipoRelacionService.listarTipos() );
			model.put("limplicados", escrituraService.obtenerPersonasEscritura(  persona.getEscritura().getIdEscritura() )) ;	
		}
		return "escritura/participantes";
	}
	
	@RequestMapping("/escritura/ver.htm")
	public String ver(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			Integer idEscritura = Integer.parseInt(request.getParameter("cod"));
			model.put("escritura", escrituraService.obtenerEscritura( idEscritura ));
			model.put("limplicados", escrituraService.obtenerPersonasEscritura( idEscritura )) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "escritura/vista";
	}
	
	
	@RequestMapping(value="*/detalleEscritura.htm")
	public String verDetalle(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			Integer idEscritura = Integer.parseInt(request.getParameter("idEscritura"));
			Integer idSolicitud = Integer.parseInt(request.getParameter("idSolicitud"));
			
			model.put("escritura", escrituraService.obtenerEscritura( idEscritura ));
			model.put("solicitud", solicitudService.obtenerSolicitud( idSolicitud ));
			
			model.put("limplicados", escrituraService.obtenerPersonasEscritura( idEscritura )) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "escritura/detalleEscritura";
	}
	

	@RequestMapping("/escritura/editar.htm")
	public String editar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			model.put("ltipoacto", tipoActoService.listarTiposActos() );
			model.put("escritura", escrituraService.obtenerEscritura( Integer.parseInt( request.getParameter("cod"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "escritura/registroEscritura";
	}
	
	@RequestMapping(value="escritura/cargarEscritura.htm", method=RequestMethod.POST)
	public String cargar(@ModelAttribute("uploadForm") FileUpload formArchivo, HttpServletRequest request, ModelMap model){
		logger.debug("grabo adjunto en BD");
		 try{
            archivoService.registrarArchivo( formArchivo.getFile() , formArchivo.getIdDocumento() );
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
	

	@RequestMapping(value="escritura/eliminar.htm")
	public String eliminarEscritura(HttpServletRequest request, ModelMap model){
		try {
			logger.debug("escritura/eliminar");
			
			model.put("ltipoacto", tipoActoService.listarTiposActos() );
			model.put("escritura", new Escritura());
						 
			Integer id = Integer.parseInt( request.getParameter("cod"));
			escrituraService.eliminarEscritura( id );
			model.put("mensaje", "El Oficio ha sido eliminado");
			
		} catch (Exception e) {
			e.printStackTrace();
			model.put("msgError", e.getMessage());
		}
		return "escritura/listaEscritura";
	}

	
	/* DESCARGA DE DOC*/
	@RequestMapping("*/descargar.htm")
    public String descargar(HttpServletRequest request, HttpServletResponse response, ModelMap model){		
		logger.debug("obtengo el archivo que estaba grabado");
         try {                   
             response.reset(); 
             Integer id = Integer.parseInt( request.getParameter("id") );
             response.setContentType("application/pdf");             
             ServletOutputStream out = response.getOutputStream();
             Archivo archivo = archivoService.obtenerArchivo(id);
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
	
	/* DESCARGA DE DOC*/
	@RequestMapping("*/descargarP.htm")
    public String descargarP(HttpServletRequest request, HttpServletResponse response, ModelMap model){		
		logger.debug("obtengo el archivo que estaba grabado");
         try {                   
             response.reset(); 
             Integer id1 = Integer.parseInt( request.getParameter("id1") );
             Integer id2 = Integer.parseInt( request.getParameter("id2") );
             response.setContentType("application/pdf");             
             ServletOutputStream out = response.getOutputStream();
             SolicitudTramite sol = archivoService.obtenerArchivoPendiente(id1, id2);
             response.addHeader("Content-Disposition", "attachment;filename=\""+ sol.getEscritura().getKardex() +".pdf\"");
             out.write( sol.getArchivo() , 0,  sol.getArchivo().length );
             out.flush();
             out.close();                  
         } catch (Exception e) {
             e.printStackTrace();
             model.put("msgError","Error " + e.getMessage());
         }
         return null;
    }
	
	/**
	 * Recupera el pdfpendiente de firma.
	 * hecho para ser invocado desde el applet.
	 * */
	@RequestMapping("descargarPendiente.htm")
    public String descargarPdf(HttpServletRequest request, HttpServletResponse response, ModelMap model){		
		logger.debug("obtengo el archivo que estaba grabado");
         try {
        	 
             response.reset(); 
             Integer idEsc = Integer.parseInt( request.getParameter("id1") );
             Integer idSol = Integer.parseInt( request.getParameter("id2") );
             logger.debug("obtengo el archivo de la bd ");
             SolicitudTramite archivo = archivoService.obtenerArchivoPendiente(idEsc,idSol);
             EscrituraXML escritura = new EscrituraXML();
             
             logger.debug( archivo.getEscritura() );
             logger.debug( archivo.getEscritura().getNumeroDoc() );
             
             escritura.setAnioTramite( archivo.getEscritura().getNumeroDoc() );
             escritura.setCodigoActo( archivo.getEscritura().getNumeroDoc() ) ;
           //  escritura.setCodigoNotaria( archivo.getEscritura().getNotaria() ) ;             
          
             escritura.setFecha( Utiles.DateToString( archivo.getEscritura().getFechaCreacion() , "dd/MM/yyyy") ) ;
             escritura.setArchivo( archivo.getArchivo() );
             escritura.setKardex( archivo.getEscritura().getKardex() ) ;
            // escritura.setNotaria( archivo.getEscritura().getNotaria()  );
             escritura.setNumeroTramite( archivo.getEscritura().getNumeroInstrumento() );
             
             ObjectOutputStream objObjectOutputStream = new ObjectOutputStream(response.getOutputStream());
             logger.debug("envio el  binario pro el OutputStream ");
             objObjectOutputStream.writeObject(escritura);
             objObjectOutputStream.flush();
             objObjectOutputStream.close();
             
         } catch (Exception e) {
             e.printStackTrace();
             model.put("msgError","Error " + e.getMessage());
             
             try {
            	 ObjectOutputStream objObjectOutputStream = new ObjectOutputStream(response.getOutputStream());
                 logger.debug("envio el  binario pro el OutputStream ");
                 objObjectOutputStream.writeObject(  e.getMessage() );
                 objObjectOutputStream.flush();
                 objObjectOutputStream.close();	
			} catch (Exception e2) {
				  e2.printStackTrace();
				  model.put("msgError","Error " + e2.getMessage());
			}
             
             
         }
         return null;
    }
	
	/**
	 * Recupera el pdfpendiente de firma.
	 * hecho para ser invocado desde el applet.
	 * */
	@RequestMapping("recibeFirmado.htm")
    public String recibeFirmado(HttpServletRequest request, HttpServletResponse response, ModelMap model){		
		logger.debug("obtengo el archivo pdf que ha sido firmado");
         try {
        	 
        	 System.out.println("Iniciando");
             ObjectInputStream inputFromApplet = new ObjectInputStream(request.getInputStream());
             EscrituraXML escritura  = (EscrituraXML)inputFromApplet.readObject();
             inputFromApplet.close();

             logger.debug("Enviando archivo firmado");
             logger.debug("idEscritura " + escritura.getIdEscritura()  + " idsol " +  escritura.getIdSolicitud() );
             SolicitudTramite archivo = archivoService.obtenerArchivoPendiente( escritura.getIdEscritura() ,escritura.getIdSolicitud() );
             archivo.setArchivo(escritura.getArchivoFirmado() );
             archivo.setEstado( 2 );
             archivoService.registrarArchivoPendiente(archivo);
             String objMensaje = "Se ha registrado con exito";

             ObjectOutputStream objObjectOutputStream = new ObjectOutputStream(response.getOutputStream());
             objObjectOutputStream.writeObject(objMensaje);
             objObjectOutputStream.flush();
             objObjectOutputStream.close();
             
         } catch (Exception e) {
             e.printStackTrace();
             model.put("msgError","Error " + e.getMessage());
         }
         return null;
    }
}
