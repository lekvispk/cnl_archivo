package pe.org.cnl.gestiondoc.service.impl;

import java.util.Date;
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
		if(notaria.getIdNotaria()>1){
			notaria.setFecCreacion( new Date() );
		}
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

	@Override
	public void encargar(Integer idNotaria) {
		notariaDAO.encargar(idNotaria);
	}

}
