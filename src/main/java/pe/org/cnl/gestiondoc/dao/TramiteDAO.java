package pe.org.cnl.gestiondoc.dao;

import java.util.List;

import pe.org.cnl.gestiondoc.model.Tramite;

public interface TramiteDAO {
	
	public List<Tramite> buscar(Tramite tramite);
	public void registrar(Tramite tramite);
	public void eliminar(Integer idTramite);
	public Tramite obtener(Integer idTramite);
}
