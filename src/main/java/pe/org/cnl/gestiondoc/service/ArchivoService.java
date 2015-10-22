package pe.org.cnl.gestiondoc.service;

import org.springframework.web.multipart.MultipartFile;

import pe.org.cnl.gestiondoc.model.Archivo;
import pe.org.cnl.gestiondoc.model.SolicitudTramite;

public interface ArchivoService {

	Archivo obtenerArchivo(Integer idArchivo);
	Archivo obtenerArchivoDoc(Integer idDocumento);
	void registrarArchivo(Archivo archivo);
	void registrarArchivo(MultipartFile file, Integer idEscritura) throws Exception;
	void eliminarArchivo(Integer idArchivo);
	SolicitudTramite obtenerArchivoPendiente(Integer idEscritura, Integer idSolicitud);
	void registrarArchivoPendiente(SolicitudTramite archivo);
	byte[] obtenerArchivoEscritura(String idEscritura);
	
}
