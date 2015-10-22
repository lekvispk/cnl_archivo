package pe.org.cnl.gestiondoc.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.org.cnl.gestiondoc.dao.TramiteDAO;
import pe.org.cnl.gestiondoc.dao.UsuarioDao;
import pe.org.cnl.gestiondoc.model.Tramite;
import pe.org.cnl.gestiondoc.model.TramiteUsuario;
import pe.org.cnl.gestiondoc.model.Usuario;
import pe.org.cnl.gestiondoc.service.TramiteService;
@Service
public class TramiteServiceImpl implements TramiteService {

	@Autowired
	private TramiteDAO tramiteDAO;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	public List<Tramite> buscar(Tramite tramite) {
		return tramiteDAO.buscar(tramite);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void registrar(Tramite tramite , String username) {
		
		tramite.setEstado(1);
		tramiteDAO.registrar(tramite);
		
		TramiteUsuario mov = new TramiteUsuario();
		mov.setEstado(1);
		mov.setFechaRegistro( new Date() );
		mov.setTramite( tramite );
		mov.setSecUsuario1( Usuario.getUsuarioBean() );
		mov.setSecUsuario2( usuarioDao.obtenerUsuario(username) );
		
		tramiteDAO.registrarMovimiento( mov );
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
