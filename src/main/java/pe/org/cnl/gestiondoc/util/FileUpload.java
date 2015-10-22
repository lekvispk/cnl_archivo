package pe.org.cnl.gestiondoc.util;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload{
 
	MultipartFile file;
	Integer idDocumento;
	Integer idAdjunto;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public Integer getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	public Integer getIdAdjunto() {
		return idAdjunto;
	}

	public void setIdAdjunto(Integer idAdjunto) {
		this.idAdjunto = idAdjunto;
	}

 
}