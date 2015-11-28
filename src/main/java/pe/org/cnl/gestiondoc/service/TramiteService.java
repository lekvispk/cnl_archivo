package pe.org.cnl.gestiondoc.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import pe.org.cnl.gestiondoc.model.Tramite;
import pe.org.cnl.gestiondoc.model.TramiteAdjunto;
import pe.org.cnl.gestiondoc.model.TramiteUsuario;

public interface TramiteService {
	
	public List<Tramite> buscar(Tramite tramite);
	public List<Tramite> buscarParaSecretaria(Tramite tramite);
	public void registrar(Tramite tramite, String username);
	public void registrarAtencion( TramiteUsuario tramite );
	public void registrarRespuesta( TramiteUsuario tramite );
	public void registrarNotificacion( TramiteUsuario tramite );
	public void eliminar(Integer idTramite);
	public Tramite obtener(Integer idTramite);
	public void registrarArchivoEnDisco(MultipartFile file, Integer idTramite) throws Exception;
	public TramiteAdjunto obtenerArchivoEnDisco(Integer idArchivo);
	public void derivar(Tramite tr);
	public void registrarConclusion(TramiteUsuario tu);
}
