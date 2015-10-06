package pe.org.cnl.gestiondoc.service;

import java.util.List;

import pe.org.cnl.gestiondoc.model.Escritura;
import pe.org.cnl.gestiondoc.model.Solicitud;
import pe.org.cnl.gestiondoc.model.SolicitudTramite;

public interface SolicitudService {

	public List<Solicitud> buscarSolicitudes(Solicitud solicitud);
	public void registrarSolicitud(Solicitud solicitud);
	public void eliminarSolicitud(Integer idSolicitud);
	public Solicitud obtenerSolicitud(Integer idSolicitud);
	public void registrarSolicitudTramite(SolicitudTramite sol);
	public Escritura completaEsctritura(Solicitud solicitud);	
	
}
