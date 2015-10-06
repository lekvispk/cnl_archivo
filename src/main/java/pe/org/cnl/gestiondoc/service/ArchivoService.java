package pe.org.cnl.gestiondoc.service;

import pe.org.cnl.gestiondoc.model.Archivo;
import pe.org.cnl.gestiondoc.model.SolicitudTramite;

public interface ArchivoService {

	Archivo obtenerArchivo(Integer idArchivo);
	Archivo obtenerArchivoDoc(Integer idDocumento);
	void registrarArchivo(Archivo archivo);
	void eliminarArchivo(Integer idArchivo);
	SolicitudTramite obtenerArchivoPendiente(Integer idEscritura, Integer idSolicitud);
	void registrarArchivoPendiente(SolicitudTramite archivo);
	byte[] obtenerArchivoEscritura(String idEscritura);
}
