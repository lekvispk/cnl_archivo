package pe.org.cnl.gestiondoc.service;

import java.util.List;

import pe.org.cnl.gestiondoc.model.Tramite;
import pe.org.cnl.gestiondoc.model.TramiteUsuario;

public interface TramiteService {
	
	public List<Tramite> buscar(Tramite tramite);
	public void registrar(Tramite tramite, String username);
	public void registrarAtencion( TramiteUsuario tramite );
	public void eliminar(Integer idTramite);
	public Tramite obtener(Integer idTramite);
	
}
