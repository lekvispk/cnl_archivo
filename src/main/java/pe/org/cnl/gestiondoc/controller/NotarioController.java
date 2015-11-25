package pe.org.cnl.gestiondoc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@RequestMapping("/editar.htm")
	public String editar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try {
			logger.debug(" editar ");
			model.put("notario", notariaService.obtener( 1 ) );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "notarios/registroNotario";
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
