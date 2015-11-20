package pe.org.cnl.gestiondoc.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.org.cnl.gestiondoc.dao.EscrituraDAO;
import pe.org.cnl.gestiondoc.model.Escritura;
import pe.org.cnl.gestiondoc.model.PersonaEscritura;
import pe.org.cnl.gestiondoc.util.Utiles;

@Repository
@Transactional
public class EscrituraDAOImpl implements EscrituraDAO {

	private static final Logger logger = Logger.getLogger( EscrituraDAOImpl.class );
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Escritura> buscarEscitura(Escritura escritura) {
		logger.debug( "buscarEscitura INI " );
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Escritura.class);
		if(escritura !=null){
			
			Disjunction dis = Restrictions.disjunction();
			
			if( !Utiles.nullToBlank(escritura.getFechaEscritura()).equals("")){
				dis.add( Restrictions.ge("fechaEscritura", escritura.getFechaEscritura() ));
				//criteria.add( Restrictions.ge("fechaEscritura", escritura.getFechaEscritura() ) );
				logger.debug( "fechaEscritura="+ escritura.getFechaEscritura() );
			}
			
			if( !Utiles.nullToBlank(escritura.getTramFechaRegistro2()).equals("")){
				dis.add( Restrictions.le("fechaEscritura", escritura.getTramFechaRegistro2() ));
				//criteria.add( Restrictions.le("fechaEscritura", escritura.getTramFechaRegistro2() ) );
				logger.debug( "fechaEscritura2="+ escritura.getTramFechaRegistro2() );
			}
			
		/*	if( escritura.getTipoActo() != null && !Utiles.nullToBlank(escritura.getTipoActo().getIdActo()).equals("-1")){
				criteria.add( Restrictions.eq("tipoActo.idActo", escritura.getTipoActo().getIdActo() ) );
				logger.debug( "tipoActo.idActo"+ escritura.getTipoActo().getIdActo() );
			}*/
			
			if(  !Utiles.nullToBlank(escritura.getKardex() ).equals("")){
				dis.add( Restrictions.eq("kardex", escritura.getKardex() ) );
				//criteria.add( Restrictions.eq("kardex", escritura.getKardex() ) );
				logger.debug( "kardex="+escritura.getKardex()  );
			}
			
			if(  !Utiles.nullToBlank(escritura.getNumeroDoc() ).equals("")){
				dis.add( Restrictions.eq("numeroDoc", escritura.getNumeroDoc() ) );
				//criteria.add( Restrictions.eq("numeroDoc", escritura.getNumeroDoc() ) );
				logger.debug( "numeroDoc="+escritura.getNumeroDoc()  );
			}
			/*
			if(  !Utiles.nullToBlank(escritura.getNotaria() ).equals("")){
				criteria.add( Restrictions.ilike("tramNotario", escritura.getNotaria() , MatchMode.ANYWHERE ) );
				logger.debug( "tramNotario"+ escritura.getNotaria() );
			}
			
			logger.debug(  escritura.getIdPersona()  );
			if( escritura.getIdPersona() > 0 ){
				logger.debug("id persona = "  + escritura.getIdPersona() );
				//if( !Utiles.nullToBlank( escritura.getIdPersona() ).equals("0") ){
					logger.debug("Buscando por personas");
					DetachedCriteria crPer = criteria.createCriteria("personas");
					crPer.add(Restrictions.eq("idPersona", escritura.getIdPersona()  ));
			//	}	
			}
			*/ 
			
			criteria.add( dis );
			criteria.add( Restrictions.eq("estado", 1 ) );
			criteria.addOrder( Order.desc("fechaEscritura") );
		}
		logger.debug( "buscarEscitura END " );
		
		return criteria.list();
	}

	@Override
	public void registrarEscritura(Escritura escritura) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(escritura);
	}

	@Override
	public void eliminarEscritura(Integer idescritura) {
		
		Query query = this.sessionFactory.getCurrentSession().createQuery(" update Escritura e set tramEstado=0 where e.idEscritura =:id ")
        .setInteger("id", idescritura);
        query.executeUpdate();
	}

	@Override
	public Escritura obtenerEscritura(Integer idescritura) {
		Query query = this.sessionFactory.getCurrentSession()
				.createQuery(" from Escritura d where d.idEscritura = :id ")
				.setInteger("id", idescritura);
        return (Escritura) query.uniqueResult();
	}

	@Override
	public void registraRelacionado(PersonaEscritura persona) {
		this.sessionFactory.getCurrentSession().persist(persona);	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PersonaEscritura> obtenerPersonasEscritura(int idEscritura) {
		logger.debug( idEscritura );
		Query query = this.sessionFactory.getCurrentSession().createQuery(" from PersonaEscritura d where d.escritura.idEscritura = :id ")
        .setInteger("id", idEscritura);
        return query.list();
	}

}
