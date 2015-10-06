package pe.org.cnl.gestiondoc.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.org.cnl.gestiondoc.service.TramiteService;

@Controller
@RequestMapping(value="/tramites")
public class TramiteController {
	
	private static final Logger logger = Logger.getLogger(TramiteController.class );
	
	@Autowired
	private TramiteService tramiteService;
	
	@RequestMapping("/lista.htm")
	public String login(HttpServletRequest request){
		logger.debug(" login.html");
		return "tramite/listaTramite";
	}
	
}
