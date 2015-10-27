package pe.org.cnl.gestiondoc.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pe.org.cnl.gestiondoc.dao.ArchivoDAO;
import pe.org.cnl.gestiondoc.model.Archivo;
import pe.org.cnl.gestiondoc.model.Escritura;
import pe.org.cnl.gestiondoc.model.SolicitudTramite;
import pe.org.cnl.gestiondoc.service.ArchivoService;
import pe.org.cnl.gestiondoc.util.ParametroUtil;

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

	@Override
	public void registrarArchivo(MultipartFile file, Integer idEscritura) throws Exception {
		
		if(file.getSize() > ParametroUtil.FILE_MAX_SIZE ){
        	throw new Exception("El archivo es muy grande y no se ha podido grabar en el sistema");
        }
		
		Archivo archivo = new Archivo();
		archivo.setNombre( file.getOriginalFilename() );
        archivo.setArchivo( file.getBytes() );
		Escritura doc = new Escritura();
		doc.setIdEscritura( idEscritura );
		archivo.setEscritura( doc );
        archivoDAO.registrarArchivo(archivo);
        
	}

	@Override
	public void registrarArchivoEnDisco(MultipartFile file, Integer idEscritura) throws Exception {
		
		if(file.getSize() > ParametroUtil.FILE_MAX_SIZE ){
        	throw new Exception("El archivo es muy grande y no se ha podido grabar en el sistema");
        }
		
		Archivo archivo = new Archivo();
		archivo.setNombre( file.getOriginalFilename() );
        //archivo.setArchivo( file.getBytes() );
		Escritura doc = new Escritura();
		doc.setIdEscritura( idEscritura );
		archivo.setEscritura( doc );
        archivoDAO.registrarArchivo(archivo);
        
        try {

        	String diskFolder = ArchivoService.FOLDER_FILE ; 
			
        	OutputStream out = new FileOutputStream(new File( diskFolder +  archivo.getIdArchivo()  ));
			out.write( file.getBytes() );
			out.close();
			
			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}

	@Override
	public Archivo obtenerArchivoEnDisco(Integer idArchivo) {
		Archivo file = archivoDAO.obtenerArchivo( idArchivo );
		
		File tmp = new File( ArchivoService.FOLDER_FILE + idArchivo );
		try {
			file.setArchivo( FileUtils.readFileToByteArray( tmp) );
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return file;
	}

}
