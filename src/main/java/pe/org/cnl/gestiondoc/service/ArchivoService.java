package pe.org.cnl.gestiondoc.service;

import org.springframework.web.multipart.MultipartFile;

import pe.org.cnl.gestiondoc.firmador.EscrituraXML;
import pe.org.cnl.gestiondoc.model.Archivo;
import pe.org.cnl.gestiondoc.model.SolicitudTramite;

public interface ArchivoService {

	public static final String FOLDER_FILE = "\\temp\\";
	/**
	 * Obtiene el archivo desde la Base de Datos
	 * @param idArchivo
	 * @return
	 */
	Archivo obtenerArchivo(Integer idArchivo);
	/**
	 * Obtiene el archivo grabado en el disco duro 
	 * @param idArchivo
	 * @return
	 */
	Archivo obtenerArchivoEnDisco(Integer idArchivo);
	Archivo obtenerArchivoDoc(Integer idDocumento);
	void registrarArchivo(Archivo archivo);
	/**
	 * Registra el archivo en la base de datos
	 * @param file
	 * @param idEscritura
	 * @throws Exception
	 */
	void registrarArchivo(MultipartFile file, Integer idEscritura) throws Exception;
	/**
	 * Registra el archivo en disco duro 
	 * @param file
	 * @param idEscritura
	 * @throws Exception
	 */
	void registrarArchivoEnDisco(MultipartFile file, Integer idEscritura) throws Exception;
	void actualizarArchivoEnDisco( EscrituraXML xmlEsccritura, Integer idEscritura) throws Exception;
	void eliminarArchivo(Integer idArchivo);
	SolicitudTramite obtenerArchivoPendiente(Integer idEscritura, Integer idSolicitud);
	void registrarArchivoPendiente(SolicitudTramite archivo);
	byte[] obtenerArchivoEscritura(String idEscritura);
	
}
