package pe.org.cnl.gestiondoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.org.cnl.gestiondoc.dao.TipoSolicitudDAO;
import pe.org.cnl.gestiondoc.model.TipoSolicitud;
import pe.org.cnl.gestiondoc.service.TipoSolicitudService;

@Service
public class TipoSolicitudServiceImpl implements TipoSolicitudService {

	@Autowired
	private TipoSolicitudDAO tipoSolicitudDAO;
	
	@Override
	public List<TipoSolicitud> listarTipoSolicitud() {
		return tipoSolicitudDAO.listarTipoSolicitud();
	}

}
