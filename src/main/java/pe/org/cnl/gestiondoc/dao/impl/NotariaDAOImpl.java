package pe.org.cnl.gestiondoc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.org.cnl.gestiondoc.dao.NotariaDAO;
import pe.org.cnl.gestiondoc.model.Notaria;
import pe.org.cnl.gestiondoc.util.Utiles;

@Repository
public class NotariaDAOImpl extends HibernateDaoSupport implements NotariaDAO {

	@Autowired
	public NotariaDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Notaria> buscar(Notaria notaria) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Notaria.class);
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
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public void registrar(Notaria notaria) {
		this.getHibernateTemplate().saveOrUpdate(notaria);
	}

	@Override
	public void eliminar(Integer idNotaria) {
		Query query = getSession().createQuery(" update Notaria set estado=0 where idNotaria =:id ")
        .setInteger("id", idNotaria);
        query.executeUpdate();
	}

	@Override
	public Notaria obtener(Integer idNotaria) {
		Query query = getSession().createQuery(" from Notaria where idNotaria = :id ")
        .setInteger("id", idNotaria);
        return (Notaria) query.uniqueResult();
	}

}
