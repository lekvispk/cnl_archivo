package pe.org.cnl.gestiondoc.service;

import java.util.List;

import pe.org.cnl.gestiondoc.model.Escritura;
import pe.org.cnl.gestiondoc.model.PersonaEscritura;

public interface EscrituraService {

	public List<Escritura> buscarEscitura(Escritura escritura);
	public void registrarEscritura(Escritura escritura);
	public void eliminarEscritura(Integer idescritura);
	public Escritura obtenerEscritura(Integer idescritura);
	public void registraRelacionado(PersonaEscritura persona);
	public List<PersonaEscritura> obtenerPersonasEscritura(int idEscritura);
	
}
