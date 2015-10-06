package pe.org.cnl.gestiondoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.org.cnl.gestiondoc.dao.TramiteDAO;
import pe.org.cnl.gestiondoc.model.Tramite;
import pe.org.cnl.gestiondoc.service.TramiteService;
@Service
public class TramiteServiceImpl implements TramiteService {

	@Autowired
	private TramiteDAO tramiteDAO;
	
	@Override
	public List<Tramite> buscar(Tramite tramite) {
		return tramiteDAO.buscar(tramite);
	}

	@Override
	public void registrar(Tramite tramite) {
		tramiteDAO.registrar(tramite);
	}

	@Override
	public void eliminar(Integer idTramite) {
		tramiteDAO.eliminar(idTramite);
	}

	@Override
	public Tramite obtener(Integer idTramite) {
		return tramiteDAO.obtener(idTramite);
	}

}
