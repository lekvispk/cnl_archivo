package pe.org.cnl.gestiondoc.dao;

import pe.org.cnl.gestiondoc.model.Persona;

import java.util.List;

public interface PersonaDAO {

	public List<Persona> buscarPersonas(Persona persona);
	public void registrarPersona(Persona persona);
	public void eliminarPersona(Integer idPersona);
	public Persona obtenerPersona(Integer idPersona);
	public List<Persona> listarPersonas(Persona persona);
}
