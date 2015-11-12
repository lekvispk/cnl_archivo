package pe.org.cnl.gestiondoc.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.org.cnl.gestiondoc.dao.ArchivoDAO;
import pe.org.cnl.gestiondoc.model.Archivo;
import pe.org.cnl.gestiondoc.model.SolicitudTramite;

@Repository
@Transactional
public class ArchivoDAOImpl implements ArchivoDAO {
	
	private static final Logger logger = Logger.getLogger( ArchivoDAOImpl.class );
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Archivo obtenerArchivo(Integer idArchivo) {
		Query query = this.sessionFactory.getCurrentSession().createQuery(" from Archivo d where idArchivo = :id ")
        .setInteger("id", idArchivo);
        return (Archivo) query.uniqueResult();
	}

	@Override
	public Archivo obtenerArchivoDoc(Integer idDocumento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registrarArchivo(Archivo archivo) {
		this.sessionFactory.getCurrentSession().save(archivo);
	}

	@Override
	public void eliminarArchivo(Integer idArchivo) {
		Query query = this.sessionFactory.getCurrentSession().createQuery(" delete from Archivo where idArchivo = :id ")
        .setInteger("id", idArchivo);
	    query.executeUpdate();
	}

	@Deprecated
	@Override
	public SolicitudTramite obtenerArchivoPendiente(Integer idEsc, Integer idSol) {
		logger.debug("obtengo archivo pendiente de firma");
		Query query = this.sessionFactory.getCurrentSession()
						.createQuery(" from SolicitudTramite s where s.id.idEscritura = :id1 and s.id.idsolicitud= :id2 ")
        .setInteger("id1", idEsc)
        .setInteger("id2", idSol);
        return (SolicitudTramite) query.uniqueResult();
	}

	@Override
	public void registrarArchivoPendiente(SolicitudTramite archivo) {
		this.sessionFactory.getCurrentSession().merge(archivo);
	}

	@Override
	public byte[] obtenerArchivoEscritura(String idEscritura) {
		Archivo arch = new Archivo();
		Query query = this.sessionFactory.getCurrentSession()
						.createQuery(" from Archivo d where d.escritura.idEscritura = :id ")
        .setInteger("id", Integer.parseInt(idEscritura));
		arch =  (Archivo )query.list().get(0);
		return arch.getArchivo();
	}

}
