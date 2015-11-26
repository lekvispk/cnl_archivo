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

import pe.org.cnl.gestiondoc.model.Notaria;
import pe.org.cnl.gestiondoc.service.NotariaService;

@Controller
@Scope("session")
@RequestMapping(value="/notario")
public class NotarioController {
	
	private static final Logger logger = Logger.getLogger( NotarioController.class );
	
	@Autowired
	private NotariaService notariaService;
	
	@RequestMapping("/lista.htm")
	public String lista(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" lista ");
			if( "1".equals(request.getParameter("m")) ){
				model.put("mensaje", "La informacion se ha grabado exitosamente");
			}
			if( "2".equals(request.getParameter("m")) ){
				model.put("mensaje", "Se ha establecido al nuevo notario encargado de archivo");
			}
			model.put("lNotarios", notariaService.buscar( new Notaria() ) );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "notarios/listaNotario";
	}
	
	@RequestMapping("/ver.htm")
	public String ver(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" ver ");
			model.put("notario", notariaService.obtener( 1 ) );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "notarios/verNotario";
	}
	
	@RequestMapping(value="/nuevo.htm", method=RequestMethod.GET)
	public String preNuevo(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" prenuevo ");
			Notaria n = new Notaria();
			n.setEstado(1);
			n.setEncargadoArchivo(0);
			model.put("notario", n );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "notarios/registroNotario";
	}
	
	@RequestMapping(value="/nuevo.htm", method=RequestMethod.POST)
	public String nuevo(@Valid Notaria notario, BindingResult result,  ModelMap model, HttpServletRequest request, HttpServletResponse response){
		try {
			logger.debug(" nuevo ");
			
			notariaService.registrar(notario);
		} catch (Exception e) {
			e.printStackTrace();
			model.put("msgError", e.getMessage());
			return "notarios/registroNotario";
		}
		return "redirect:/notario/lista.htm?m=1";
	}
	
	@RequestMapping("/editar.htm")
	public String editar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" editar ");
			model.put("notario", notariaService.obtener( new Integer( request.getParameter("cod") ) ) );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "notarios/registroNotario";
	}
	
	@RequestMapping("/encargar.htm")
	public String encargar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" encargar como nuevo encargado de archivo ");
			notariaService.encargar( new Integer( request.getParameter("cod") ) );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/notario/lista.htm?m=2";
	}
	
	@RequestMapping("/eliminar.htm")
	public String eliminar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" eliminar ");
			
			model.put("ltipoacto", notariaService.buscar( new Notaria() ) );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "notarios/listaNotario";
	}
}
