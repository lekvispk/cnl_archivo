package pe.org.cnl.gestiondoc.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import pe.org.cnl.gestiondoc.dao.TramiteDAO;
import pe.org.cnl.gestiondoc.dao.UsuarioDao;
import pe.org.cnl.gestiondoc.model.Tramite;
import pe.org.cnl.gestiondoc.model.TramiteAdjunto;
import pe.org.cnl.gestiondoc.model.TramiteUsuario;
import pe.org.cnl.gestiondoc.model.Usuario;
import pe.org.cnl.gestiondoc.service.ArchivoService;
import pe.org.cnl.gestiondoc.service.TramiteService;
import pe.org.cnl.gestiondoc.util.ParametroUtil;
@Service
public class TramiteServiceImpl implements TramiteService {

	@Autowired
	private TramiteDAO tramiteDAO;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	public List<Tramite> buscar(Tramite tramite) {
		return tramiteDAO.buscar(tramite);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void registrar(Tramite tramite , String username) {
		
		tramite.setEstado(1);
		tramite.setFechaCreacion( new Date() );
		tramiteDAO.registrar(tramite);
		
		TramiteUsuario mov = new TramiteUsuario();
		mov.setEstado(1);
		mov.setFechaRegistro( new Date() );
		mov.setTramite( tramite );
		mov.setSecUsuario1( Usuario.getUsuarioBean() );
		mov.setSecUsuario2( usuarioDao.obtenerUsuario(username) );
		
		tramiteDAO.registrarMovimiento( mov );
	}

	@Override
	public void eliminar(Integer idTramite) {
		tramiteDAO.eliminar(idTramite);
	}

	@Override
	public Tramite obtener(Integer idTramite) {
		return tramiteDAO.obtener(idTramite);
	}

	@Override
	public void registrarAtencion(TramiteUsuario tramite) {
		
		//movimiento de juan hacia juan
		tramite.setSecUsuario1( Usuario.getUsuarioBean() );
		tramite.setSecUsuario2( Usuario.getUsuarioBean() );
		tramite.setEstado(1);
		tramite.setEstadoTramiteFinal( ParametroUtil.EstadoTramite.ATENDIDO.value );
		tramite.setFechaRegistro( new Date() );
		tramiteDAO.registrarMovimiento( tramite );
		
		tramite.getTramite().setEstado( ParametroUtil.EstadoTramite.ATENDIDO.value );
		tramiteDAO.registrar( tramite.getTramite() );
		
	}
	
	@Override
	public void derivar(Tramite tr) {
		
		TramiteUsuario tramite = new TramiteUsuario();
		tramite.setTramite( tr );
		tramite.setSecUsuario1( Usuario.getUsuarioBean() );
		tramite.setSecUsuario2( usuarioDao.obtenerUsuario( "gjara" ) );
		tramite.setEstado(1);
		tramite.setEstadoTramiteFinal( ParametroUtil.EstadoTramite.DERIVADO.value );
		tramite.setFechaRegistro( new Date() );
		tramiteDAO.registrarMovimiento( tramite );
		
		//movimiento de Juan hacia Jara 
		tr.setEstado( ParametroUtil.EstadoTramite.DERIVADO.value );
		tramiteDAO.derivar( tr );
	}

	@Override
	public void registrarRespuesta(TramiteUsuario tramite) {

		tramite.setSecUsuario1( Usuario.getUsuarioBean() );
		tramite.setSecUsuario2( usuarioDao.obtenerUsuario( "jculqui" ) );
		tramite.setEstado(1);
		tramite.setEstadoTramiteFinal( ParametroUtil.EstadoTramite.RESPONDIDO.value );
		tramite.setFechaRegistro( new Date() );
		tramiteDAO.registrarMovimiento( tramite );
		
		tramite.getTramite().setEstado( ParametroUtil.EstadoTramite.RESPONDIDO.value );		
		tramiteDAO.registrar( tramite.getTramite() );
	}

	@Override
	public void registrarNotificacion(TramiteUsuario tramite) {

		tramite.setSecUsuario1( Usuario.getUsuarioBean() );
		tramite.setSecUsuario2( Usuario.getUsuarioBean() );
		tramite.setEstado(1);
		tramite.setEstadoTramiteFinal( ParametroUtil.EstadoTramite.NOTIFICADO.value );
		tramite.setFechaRegistro( new Date() );
		tramiteDAO.registrarMovimiento( tramite );
		
		tramite.getTramite().setEstado( ParametroUtil.EstadoTramite.NOTIFICADO.value );		
		tramiteDAO.registrar( tramite.getTramite() );
		
	}

	@Override
	public void registrarArchivoEnDisco(MultipartFile file, Integer idTramite) throws Exception {

		if(file.getSize() > ParametroUtil.FILE_MAX_SIZE ){
        	throw new Exception("El archivo es muy grande y no se ha podido grabar en el sistema");
        }
		
		TramiteAdjunto archivo = new TramiteAdjunto();
		archivo.setNombre( file.getOriginalFilename() );
        //archivo.setArchivo( file.getBytes() );
		Tramite doc = new Tramite();
		doc.setIdTramite( idTramite );
		archivo.setTramite( doc );
        tramiteDAO.registrarAdjunto(archivo);
        
        try {

        	String diskFolder = ArchivoService.FOLDER_FILE ; 
			
        	OutputStream out = new FileOutputStream(new File( diskFolder +  archivo.getIdAdjunto()  ));
			out.write( file.getBytes() );
			out.close();
			
			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}

	@Override
	public TramiteAdjunto obtenerArchivoEnDisco(Integer idArchivo) {
		TramiteAdjunto file = tramiteDAO.obtenerAdjunto( idArchivo );
		
		File tmp = new File( ArchivoService.FOLDER_FILE + idArchivo );
		try {
			file.setArchivo( FileUtils.readFileToByteArray( tmp) );
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return file;
	}

	@Override
	public List<Tramite> buscarParaSecretaria(Tramite tramite) {
		return tramiteDAO.buscarParaSecretaria(tramite);
	}
}
