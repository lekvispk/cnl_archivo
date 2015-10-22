package pe.org.cnl.gestiondoc.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.org.cnl.gestiondoc.dao.SolicitudDAO;
import pe.org.cnl.gestiondoc.model.Escritura;
import pe.org.cnl.gestiondoc.model.Solicitud;
import pe.org.cnl.gestiondoc.model.SolicitudTramite;
import pe.org.cnl.gestiondoc.model.Tramite;
import pe.org.cnl.gestiondoc.service.SolicitudService;
import pe.org.cnl.gestiondoc.service.TramiteService;

@Service
public class SolicitudServiceImpl implements SolicitudService {

	@Autowired
	private SolicitudDAO solicitudDAO;
	@Autowired
	private TramiteService tramiteService;
	
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
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void registrarSolicitudTramite(SolicitudTramite sol, String username ) {
		
		//crear detalle
		 solicitudDAO.registrarSolicitudTramite(sol);
		 //cambiar estado solicitud, DERIVADO
		// solicitudDAO.actualizarEstado( sol.getId().getIdsolicitud(), 3 );
		 
		Solicitud solic = solicitudDAO.obtenerSolicitud( sol.getId().getIdsolicitud() );
		solic.setEstado(3);
		solic.setFechaModificacion( new Date() );
		solicitudDAO.registrarSolicitud( solic );
		
		 //crea el tramite 
		 Tramite tram = new Tramite();
			tram.setSolicitud( new Solicitud( sol.getId().getIdsolicitud() ) );
			tram.setEscritura( new Escritura( sol.getId().getIdEscritura() ) );
			
			tramiteService.registrar( tram , username);
			
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
