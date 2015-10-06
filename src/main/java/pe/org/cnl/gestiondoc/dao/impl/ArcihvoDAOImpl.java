package pe.org.cnl.gestiondoc.dao.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.org.cnl.gestiondoc.dao.ArchivoDAO;
import pe.org.cnl.gestiondoc.model.Archivo;
import pe.org.cnl.gestiondoc.model.SolicitudTramite;

@Repository
public class ArcihvoDAOImpl extends HibernateDaoSupport  implements ArchivoDAO {

	@Autowired
	public ArcihvoDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@Override
	public Archivo obtenerArchivo(Integer idArchivo) {
		Query query = getSession().createQuery(" from Archivo d where idArchivo = :id ")
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
		this.getHibernateTemplate().save(archivo);
	}

	@Override
	public void eliminarArchivo(Integer idArchivo) {
		Query query = getSession().createQuery(" delete from Archivo where idArchivo = :id ")
        .setInteger("id", idArchivo);
	    query.executeUpdate();
	}

	@Override
	public SolicitudTramite obtenerArchivoPendiente(Integer idEsc, Integer idSol) {
		logger.debug("obtengo archivo pendiente de firma");
		Query query = getSession().createQuery(" from SolicitudTramite s where s.id.idEscritura = :id1 and s.id.idsolicitud= :id2 ")
        .setInteger("id1", idEsc)
        .setInteger("id2", idSol);
        return (SolicitudTramite) query.uniqueResult();
	}

	@Override
	public void registrarArchivoPendiente(SolicitudTramite archivo) {
		this.getHibernateTemplate().merge(archivo);
	}

	@Override
	public byte[] obtenerArchivoEscritura(String idEscritura) {
		Archivo arch = new Archivo();
		Query query = getSession().createQuery(" from Archivo d where d.escritura.idEscritura = :id ")
        .setInteger("id", Integer.parseInt(idEscritura));
		arch =  (Archivo )query.list().get(0);
		return arch.getArchivo();
	}

}
