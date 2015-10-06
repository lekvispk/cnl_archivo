package pe.org.cnl.gestiondoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.org.cnl.gestiondoc.dao.SolicitudTramiteDAO;
import pe.org.cnl.gestiondoc.model.SolicitudTramite;
import pe.org.cnl.gestiondoc.service.SolicitudTramiteService;

@Service
public class SolicitudTramiteServiceImpl implements SolicitudTramiteService {

	@Autowired
	private SolicitudTramiteDAO solicitudTramiteDAO;
	
	@Override
	public List<SolicitudTramite> buscarSolicitudes(Integer estado) {
		return solicitudTramiteDAO.buscarSolicitudes(estado);
	}

	@Override
	public void registrarSolicitudTramite(SolicitudTramite solicitudTramite) {
		solicitudTramiteDAO.registrarSolicitudTramite(solicitudTramite);
	}

	@Override
	public SolicitudTramite obtenerSolicitudTramite(Integer idSol,Integer idEscri) {
		return solicitudTramiteDAO.obtenerSolicitudTramite(idSol, idEscri);
	}

	@Override
	public void eliminarSolicitudTramite(Integer idSol, Integer idEscri) {
		solicitudTramiteDAO.eliminarSolicitudTramite(idSol, idEscri);
	}

}
