package pe.org.cnl.gestiondoc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.org.cnl.gestiondoc.dao.ArchivoDAO;
import pe.org.cnl.gestiondoc.model.Archivo;
import pe.org.cnl.gestiondoc.model.SolicitudTramite;
import pe.org.cnl.gestiondoc.service.ArchivoService;

@Service
public class ArchivoServiceImpl implements ArchivoService {

	@Autowired
	private ArchivoDAO archivoDAO;
	
	@Override
	public Archivo obtenerArchivo(Integer idArchivo) {
		return archivoDAO.obtenerArchivo(idArchivo);
	}

	@Override
	public Archivo obtenerArchivoDoc(Integer idDocumento) {
		return archivoDAO.obtenerArchivoDoc(idDocumento);
	}

	@Override
	public void registrarArchivo(Archivo archivo) {
		archivoDAO.registrarArchivo(archivo);
	}

	@Override
	public void eliminarArchivo(Integer idArchivo) {
		archivoDAO.eliminarArchivo(idArchivo);
	}

	@Override
	public SolicitudTramite obtenerArchivoPendiente(Integer idEscritura, Integer idSol) {
		return archivoDAO.obtenerArchivoPendiente(idEscritura,idSol);
	}

	@Override
	public void registrarArchivoPendiente(SolicitudTramite archivo) {
		archivoDAO.registrarArchivoPendiente( archivo );
	}

	@Override
	public byte[] obtenerArchivoEscritura(String idEscritura) {
		return archivoDAO.obtenerArchivoEscritura( idEscritura );
	}

}
