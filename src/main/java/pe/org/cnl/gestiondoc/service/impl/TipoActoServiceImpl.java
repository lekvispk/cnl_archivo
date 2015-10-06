package pe.org.cnl.gestiondoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.org.cnl.gestiondoc.dao.TipoActoDAO;
import pe.org.cnl.gestiondoc.model.TipoActo;
import pe.org.cnl.gestiondoc.service.TipoActoService;

@Service
public class TipoActoServiceImpl implements TipoActoService {

	@Autowired
	private TipoActoDAO tipoActoDAO;
	
	@Override
	public List<TipoActo> listarTiposActos() {
		return tipoActoDAO.listarTiposActos();
	}

}
