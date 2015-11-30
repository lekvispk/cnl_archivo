package pe.org.cnl.gestiondoc.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("session")
public class ReportesController {

	private static final Logger logger = Logger.getLogger(ReportesController.class );
	
	@RequestMapping("/reportes/reporteAnual.htm")
	public String reporteAnual(HttpServletRequest request, ModelMap model){
		logger.debug("reporteAnual");
		return "reportes/reporteAnual";
	}
	
	@RequestMapping("/reportes/reporteMensual.htm")
	public String inicio(HttpServletRequest request, ModelMap model){
		logger.debug("reporteMensual");
		return "reportes/reporteMensual";
	}
}
