package pe.org.cnl.gestiondoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.org.cnl.gestiondoc.dao.TipoRelacionDAO;
import pe.org.cnl.gestiondoc.model.TipoRelacion;
import pe.org.cnl.gestiondoc.service.TipoRelacionService;

@Service
public class TipoRelacionServiceImpl implements TipoRelacionService {

	@Autowired
	private TipoRelacionDAO tipoRelacionDAO;
	
	@Override
	public List<TipoRelacion> listarTipos() {
		return tipoRelacionDAO.listarTipos();
	}

}
