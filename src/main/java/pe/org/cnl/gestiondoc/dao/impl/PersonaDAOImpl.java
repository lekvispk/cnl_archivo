package pe.org.cnl.gestiondoc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pe.org.cnl.gestiondoc.dao.PersonaDAO;
import pe.org.cnl.gestiondoc.model.Persona;
import pe.org.cnl.gestiondoc.util.Utiles;

@Repository
public class PersonaDAOImpl extends HibernateDaoSupport implements PersonaDAO {

	@Autowired
	public PersonaDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Persona> buscarPersonas(Persona persona) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Persona.class);
		if(persona !=null){
			if(  !Utiles.nullToBlank( persona.getNombre() ).equals("")){
				criteria.add( Restrictions.eq("nombre", persona.getNombre() ) );
			}
			criteria.addOrder( Order.desc("nombre") );
		}				
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public void registrarPersona(Persona persona) {
		this.getHibernateTemplate().merge(persona);
	}

	@Override
	public void eliminarPersona(Integer idPersona) {
		Query query = getSession().createQuery(" delete from Persona where idPersona =:id ")
        .setInteger("id", idPersona);
        query.executeUpdate();
	}

	@Override
	public Persona obtenerPersona(Integer idPersona) {
		Query query = getSession().createQuery(" from Persona where idPersona = :id ")
        .setInteger("id", idPersona);
        return (Persona) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Persona> listarPersonas(Persona persona) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Persona.class);
		logger.debug(" listarPersonas(persona) ");
		if(persona !=null){
			if( !Utiles.nullToBlank(persona.getNombre() ).equals("")){
				criteria.add ( Restrictions.ilike("nombreCompleto", persona.getNombre() , MatchMode.ANYWHERE) );
				//Criterion ape = Restrictions.ilike("apPaterno", persona.getNombre() , MatchMode.ANYWHERE) ;
				//criteria.add( Restrictions.ilike("apMaterno", persona.getApMaterno() , MatchMode.ANYWHERE) ;
				logger.debug(" nombre = "+ persona.getNombre() );
				//LogicalExpression expression = Restrictions.or(name, ape);
			    //criteria.add(expression);
			}
		}
		return getHibernateTemplate().findByCriteria(criteria);
	}
	
}
