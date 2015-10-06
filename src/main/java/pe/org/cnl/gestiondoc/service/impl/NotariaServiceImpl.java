package pe.org.cnl.gestiondoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.org.cnl.gestiondoc.dao.NotariaDAO;
import pe.org.cnl.gestiondoc.model.Notaria;
import pe.org.cnl.gestiondoc.service.NotariaService;

@Service
public class NotariaServiceImpl  implements NotariaService {

	@Autowired
	private NotariaDAO notariaDAO;

	@Override
	public List<Notaria> buscar(Notaria notaria) {
		return notariaDAO.buscar(notaria);
	}

	@Override
	public void registrar(Notaria notaria) {
		notariaDAO.registrar(notaria);
	}

	@Override
	public void eliminar(Integer idNotaria) {
		notariaDAO.eliminar(idNotaria);
	}

	@Override
	public Notaria obtener(Integer idNotaria) {
		return notariaDAO.obtener(idNotaria);
	}

}
