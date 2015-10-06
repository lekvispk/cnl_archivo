package pe.org.cnl.gestiondoc.dao;

import java.util.List;

import pe.org.cnl.gestiondoc.model.SolicitudTramite;

public interface SolicitudTramiteDAO {

	public List<SolicitudTramite> buscarSolicitudes(Integer estado);
	public void registrarSolicitudTramite(SolicitudTramite solicitudTramite);
	public SolicitudTramite  obtenerSolicitudTramite(Integer idSol, Integer idEscri);
	public void eliminarSolicitudTramite(Integer idSol, Integer idEscri);
	
}
