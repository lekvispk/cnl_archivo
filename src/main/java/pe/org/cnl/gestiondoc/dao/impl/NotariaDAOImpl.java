package pe.org.cnl.gestiondoc.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.org.cnl.gestiondoc.dao.NotariaDAO;
import pe.org.cnl.gestiondoc.model.EncargadoArchivo;
import pe.org.cnl.gestiondoc.model.Notaria;
import pe.org.cnl.gestiondoc.model.Usuario;
import pe.org.cnl.gestiondoc.util.Utiles;

@Repository
@Transactional
public class NotariaDAOImpl implements NotariaDAO {

	private static final Logger logger = Logger.getLogger(NotariaDAOImpl.class );
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Notaria> buscar(Notaria notaria) {
		logger.trace("buscar");
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Notaria.class);
		
		if(notaria !=null){
			if( !Utiles.nullToBlank(notaria.getIdNotaria()).equals("")){
				criteria.add( Restrictions.ge("idNotaria", notaria.getIdNotaria()) );
				logger.debug( "idNotaria"+ notaria.getIdNotaria() );
			}
			if( !Utiles.nullToBlank(notaria.getNombre()).equals("")){
				criteria.add( Restrictions.ge("nombre", notaria.getNombre() ) );
				logger.debug( "nombre"+ notaria.getNombre() );
			}
		}
		criteria.add( Restrictions.eq("estado", 1 ) );
		criteria.addOrder( Order.asc("nombre") );
		return criteria.list();
	}

	@Override
	public void registrar(Notaria notaria) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(notaria);
	}

	@Override
	public void eliminar(Integer idNotaria) {
		Query query = this.sessionFactory.getCurrentSession()
					.createQuery(" update Notaria set estado=0 where idNotaria =:id ")
					.setInteger("id", idNotaria);
        query.executeUpdate();
	}

	@Override
	public Notaria obtener(Integer idNotaria) {
		logger.trace("obtener");
		Query query = this.sessionFactory.getCurrentSession()
					.createQuery(" from Notaria where idNotaria = :id ")
					.setInteger("id", idNotaria);
        return (Notaria) query.uniqueResult();
	}

	@Override
	public void encargar(Integer idNotaria) {
		
		Query query = this.sessionFactory.getCurrentSession()
				.createQuery(" update Notaria set encargadoArchivo=0  ");
		query.executeUpdate();
		
		query = this.sessionFactory.getCurrentSession()
				.createQuery(" update Notaria set encargadoArchivo=1 where idNotaria =:id ")
				.setInteger("id", idNotaria);
		query.executeUpdate();
		
		EncargadoArchivo ea = new EncargadoArchivo();
		ea.setEstado(1);
		ea.setFechaCreacion( new Date() );
		Notaria n = new Notaria();
		n.setIdNotaria( idNotaria );
		ea.setNotaria( n );
		ea.setUsuarioCreacion( Usuario.getUsuarioBean().getUsername() );
		this.sessionFactory.getCurrentSession().saveOrUpdate(ea);
    
	}

	@Override
	public EncargadoArchivo obtenerEncargado() {
		Query query = this.sessionFactory.getCurrentSession()
				.createQuery(" from EncargadoArchivo where estado = 1 ");
		return (EncargadoArchivo) query.uniqueResult();
	}

}
