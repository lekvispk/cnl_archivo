package pe.org.cnl.gestiondoc.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.org.cnl.gestiondoc.model.Persona;
import pe.org.cnl.gestiondoc.model.Usuario;
import pe.org.cnl.gestiondoc.service.PersonaService;
import pe.org.cnl.gestiondoc.util.Utiles;

@Controller
public class AdminController {

	private static final Logger logger = Logger.getLogger(AdminController.class );
	
	@Autowired
	private PersonaService personaService;
	
	@RequestMapping("/login.htm")
	public String login(HttpServletRequest request){
		logger.debug(" login.html");
		return "login";
	}
	
	@RequestMapping("/inicio.htm")
	public String inicio(HttpServletRequest request, ModelMap model){
		//try {
		//	String usuario = request.getParameter("j_username");
		//	String password= request.getParameter("j_password");
			logger.debug(" inicio.htm, usuario ya autenticado");
			
	//	} catch (Exception e) {
		//	e.printStackTrace();
	//	}
		return "inicio";
	}
	
	@RequestMapping(value="*/prenpersona.htm")
	public String prenpersona(HttpServletRequest request, ModelMap model){
		try {
			model.put("r", "0");
			model.put("persona", new Persona() );
			logger.debug("includes/prenpersona");
		} catch (Exception e) {
			model.put("msgError", e.getMessage());
		}
		return "includes/npersona";
	}
	
	@RequestMapping(value="*/npersona.htm")
	public String npersona(@Valid Persona persona, BindingResult result,HttpServletRequest request, ModelMap model){
		try {
			model.put("r", "1");
			
			persona.setNombreCompleto( persona.getApePaterno() + " " + persona.getApeMaterno() + " " + persona.getNombre() );
			personaService.registrarPersona(persona);
			logger.debug("includes/npersona r=1");
			
		} catch (Exception e) {
			model.put("msgError", e.getMessage());
			model.put("r", "0");
		}
		model.put("persona", persona );
		return "includes/npersona";
	}
	

	@RequestMapping(value="*/lpersonasAuto.htm")
	public String lpersonaAuto(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		PrintWriter out = null;
		try {
			//Para mostrar caracteres raros
			response.setContentType("text/html;charset=ISO-8859-1");
	        request.setCharacterEncoding("UTF8");	        
			out = response.getWriter();
			String para = request.getParameter("queryString");
			if(para==null)
				para = request.getParameter("term");
			
			Persona p = new Persona();
			p.setNombre( para );
			if( Utiles.nullToBlank( para ).length() > 0){
				List<Persona> lista = personaService.listarPersonas( p );
				logger.debug("Encontrados : " + lista.size() );
				out.println("[");
				int i = 0 ; 
				for(Persona per : lista ){
					if( i != 0 ){out.print(","); } i++;
					out.println("{ \"id\": \""+per.getIdPersona()+"\", \"label\": \""+per.getNombreCompleto()+"\", \"value\": \""+per.getNombreCompleto()+"\" }");
				}
				out.println("]");
			}
			logger.debug("includes/autocomplete");
		} catch (Exception e) {
			model.put("msgError", e.getMessage());
		} finally {
            out.close();
        }
		return null;
	}

	@RequestMapping(value="/cambioDeClave.htm",method=RequestMethod.GET)
	public String preCambioDeClave( ModelMap model ){
		logger.debug("preCambioDeClave.htm");
		model.put("usuario", Usuario.getUsuarioBean() );
		return "mantenimiento/usuario_clave";
	}
	
	@RequestMapping(value="/cambioDeClave.htm",method=RequestMethod.POST)
	public String cambioDeClave( HttpServletRequest request,ModelMap model ){
		logger.debug("cambioDeClave.htm");
		try {
			String clave = request.getParameter("clave");
			String nueva = request.getParameter("nueva");
			String confirmar = request.getParameter("confirmar");
			
			if ( !Usuario.getUsuarioBean().getClave().equals( Utiles.hashMd5( clave ) ) ){
				throw new Exception("Su clave no es correcta");
			}
			if ( !nueva.equals( confirmar ) ){
				throw new Exception("Su nueva calve es incorrecta");
			}
			
			model.put("mensaje", "La clave ha sido actualizada");
		} catch (Exception e) {
			e.printStackTrace();
			model.put("msgError","Error " + e.getMessage());
			return "mantenimiento/usuario_clave";
		}
		return "inicio";
	}
	
}
