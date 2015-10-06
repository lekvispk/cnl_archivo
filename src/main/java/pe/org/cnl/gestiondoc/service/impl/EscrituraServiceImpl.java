package pe.org.cnl.gestiondoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.org.cnl.gestiondoc.dao.EscrituraDAO;
import pe.org.cnl.gestiondoc.model.Escritura;
import pe.org.cnl.gestiondoc.model.PersonaEscritura;
import pe.org.cnl.gestiondoc.service.EscrituraService;

@Service
public class EscrituraServiceImpl implements EscrituraService {

	@Autowired
	private EscrituraDAO escrituraDAO;
	
	@Override
	public List<Escritura> buscarEscitura(Escritura escritura) {
		return escrituraDAO.buscarEscitura(escritura);
	}

	@Override
	public void registrarEscritura(Escritura escritura) {
		escrituraDAO.registrarEscritura(escritura);
	}

	@Override
	public void eliminarEscritura(Integer idescritura) {
		escrituraDAO.eliminarEscritura(idescritura);
	}

	@Override
	public Escritura obtenerEscritura(Integer idescritura) {
		return escrituraDAO.obtenerEscritura(idescritura);
	}

	@Override
	public void registraRelacionado(PersonaEscritura persona) {
		 escrituraDAO.registraRelacionado(persona);
	}

	@Override
	public List<PersonaEscritura> obtenerPersonasEscritura(int idEscritura) {
		return escrituraDAO.obtenerPersonasEscritura(idEscritura);
	}

}
