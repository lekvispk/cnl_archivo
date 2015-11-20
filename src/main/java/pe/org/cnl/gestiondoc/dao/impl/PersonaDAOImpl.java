package pe.org.cnl.gestiondoc.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.org.cnl.gestiondoc.dao.PersonaDAO;
import pe.org.cnl.gestiondoc.model.Persona;
import pe.org.cnl.gestiondoc.util.Utiles;

@Repository
@Transactional
public class PersonaDAOImpl implements PersonaDAO {

	private static final Logger logger = Logger.getLogger(PersonaDAOImpl.class );
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Persona> buscarPersonas(Persona persona) {
		logger.trace("buscarPersonas");
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Persona.class);
		if(persona !=null){
			if(  !Utiles.nullToBlank( persona.getNombre() ).equals("")){
				criteria.add( Restrictions.eq("nombre", persona.getNombre() ) );
			}
			criteria.addOrder( Order.desc("nombre") );
		}				
		return criteria.list();
	}

	@Override
	public void registrarPersona(Persona persona) {
		 this.sessionFactory.getCurrentSession().merge(persona);
	}

	@Override
	public void eliminarPersona(Integer idPersona) {
		Query query =  this.sessionFactory.getCurrentSession()
					.createQuery(" delete from Persona where idPersona =:id ")
					.setInteger("id", idPersona);
        query.executeUpdate();
	}

	@Override
	public Persona obtenerPersona(Integer idPersona) {
		Query query =  this.sessionFactory.getCurrentSession()
					.createQuery(" from Persona where idPersona = :id ")
					.setInteger("id", idPersona);
        return (Persona) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Persona> listarPersonas(Persona persona) {
		logger.debug(" listarPersonas(persona) ");
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Persona.class);
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
		return criteria.list();
	}
	
}
