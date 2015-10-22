package pe.org.cnl.gestiondoc.dao;

import java.util.List;

import pe.org.cnl.gestiondoc.model.Solicitud;
import pe.org.cnl.gestiondoc.model.SolicitudTramite;

public interface SolicitudDAO {

	public List<Solicitud> buscarSolicitudes(Solicitud solicitud);
	public void registrarSolicitud(Solicitud solicitud);
	public void eliminarSolicitud(Integer idSolicitud);
	public Solicitud obtenerSolicitud(Integer idSolicitud);
	public void registrarSolicitudTramite(SolicitudTramite sol);
	public void actualizarEstado(int idsolicitud, int i);	
	
}
