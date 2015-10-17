package pe.org.cnl.gestiondoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.org.cnl.gestiondoc.dao.SolicitudDAO;
import pe.org.cnl.gestiondoc.model.Escritura;
import pe.org.cnl.gestiondoc.model.Solicitud;
import pe.org.cnl.gestiondoc.model.SolicitudTramite;
import pe.org.cnl.gestiondoc.service.SolicitudService;

@Service
public class SolicitudServiceImpl implements SolicitudService {

	@Autowired
	private SolicitudDAO solicitudDAO;
	
	@Override
	public List<Solicitud> buscarSolicitudes(Solicitud solicitud) {
		return solicitudDAO.buscarSolicitudes(solicitud);
	}

	@Override
	public void registrarSolicitud(Solicitud solicitud) {
		solicitudDAO.registrarSolicitud(solicitud);
	}

	@Override
	public void eliminarSolicitud(Integer idSolicitud) {
		solicitudDAO.eliminarSolicitud(idSolicitud);
	}

	@Override
	public Solicitud obtenerSolicitud(Integer idSolicitud) {
		return solicitudDAO.obtenerSolicitud(idSolicitud);
	}

	@Override
	public void registrarSolicitudTramite(SolicitudTramite sol) {
		 solicitudDAO.registrarSolicitudTramite(sol);
	}

	@Override
	public Escritura completaEsctritura(Solicitud solicitud) {
		if(solicitud!= null){
			Escritura  escritura = new Escritura();
			
			//escritura.setTipoActo( solicitud.getTipoActo() );
			escritura.setNotaria( solicitud.getNotaria() );
			escritura.setKardex( solicitud.getTramKardex() );
			escritura.setNumeroDoc( solicitud.getTramEscritura() );
			escritura.setNumeroFolios( solicitud.getTramFolios() );
			escritura.setFechaEscritura( solicitud.getFechaIngreso() );
			escritura.setComprador( solicitud.getTramComprador() );
			escritura.setVendedor( solicitud.getTramVendedor() );
			
			return escritura;
		}
		return null;
	}

}
