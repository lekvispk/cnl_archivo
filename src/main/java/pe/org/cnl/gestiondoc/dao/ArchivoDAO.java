package pe.org.cnl.gestiondoc.dao;

import pe.org.cnl.gestiondoc.model.Archivo;
import pe.org.cnl.gestiondoc.model.SolicitudTramite;

public interface ArchivoDAO {

	Archivo obtenerArchivo(Integer idArchivo);
	Archivo obtenerArchivoDoc(Integer idDocumento);
	void registrarArchivo(Archivo archivo);
	void eliminarArchivo(Integer idArchivo);
	@Deprecated
	SolicitudTramite obtenerArchivoPendiente(Integer id1, Integer id2);
	void registrarArchivoPendiente(SolicitudTramite archivo);
	byte[] obtenerArchivoEscritura(String idEscritura);
}
