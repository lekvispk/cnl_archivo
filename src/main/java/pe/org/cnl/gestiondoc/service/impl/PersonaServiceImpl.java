package pe.org.cnl.gestiondoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.org.cnl.gestiondoc.dao.PersonaDAO;
import pe.org.cnl.gestiondoc.model.Persona;
import pe.org.cnl.gestiondoc.service.PersonaService;

@Service
public class PersonaServiceImpl implements PersonaService {

	@Autowired
	private PersonaDAO personaDAO;
	
	@Override
	public List<Persona> buscarPersonas(Persona persona) {
		return personaDAO.buscarPersonas(persona);
	}

	@Override
	public void registrarPersona(Persona persona) {
		personaDAO.registrarPersona(persona);
	}

	@Override
	public void eliminarPersona(Integer idPersona) {
		personaDAO.eliminarPersona(idPersona);
	}

	@Override
	public Persona obtenerPersona(Integer idPersona) {
		return personaDAO.obtenerPersona(idPersona);
	}

	@Override
	public List<Persona> listarPersonas(Persona persona) {
		return personaDAO.listarPersonas(persona);
	}

}
