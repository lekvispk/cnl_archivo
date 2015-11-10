package pe.org.cnl.gestiondoc.dao;

import java.util.List;

import pe.org.cnl.gestiondoc.model.Tramite;
import pe.org.cnl.gestiondoc.model.TramiteAdjunto;
import pe.org.cnl.gestiondoc.model.TramiteUsuario;

public interface TramiteDAO {
	
	public List<Tramite> buscar(Tramite tramite);
	public void registrar(Tramite tramite);
	public void registrarMovimiento( TramiteUsuario tramiteUsuario);
	public void eliminar(Integer idTramite);
	public void eliminarMovimientosPrevios(Integer idTramite);
	public Tramite obtener(Integer idTramite);
	public TramiteAdjunto obtenerAdjunto(Integer idAdjunto);
	public void registrarAdjunto(TramiteAdjunto archivo);
	public void derivar(Tramite tr);
	
}
