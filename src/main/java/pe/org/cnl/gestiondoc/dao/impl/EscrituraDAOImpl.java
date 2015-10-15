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

import pe.org.cnl.gestiondoc.dao.EscrituraDAO;
import pe.org.cnl.gestiondoc.model.Escritura;
import pe.org.cnl.gestiondoc.model.PersonaEscritura;
import pe.org.cnl.gestiondoc.util.Utiles;

@Repository
public class EscrituraDAOImpl extends HibernateDaoSupport implements EscrituraDAO {

	@Autowired
	public EscrituraDAOImpl(SessionFactory sessionFactory){
		setHibernateTemplate( new HibernateTemplate(sessionFactory));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Escritura> buscarEscitura(Escritura escritura) {
		logger.debug( "buscarEscitura INI " );
		DetachedCriteria criteria = DetachedCriteria.forClass(Escritura.class);
		if(escritura !=null){
			
			if( !Utiles.nullToBlank(escritura.getTramFechaRegistro()).equals("")){
				criteria.add( Restrictions.ge("tramFechaRegistro", escritura.getTramFechaRegistro() ) );
				logger.debug( "getTramFechaRegistro"+ escritura.getTramFechaRegistro() );
			}
			
			if( !Utiles.nullToBlank(escritura.getTramFechaRegistro2()).equals("")){
				criteria.add( Restrictions.le("tramFechaRegistro", escritura.getTramFechaRegistro2() ) );
				logger.debug( "getTramFechaRegistro2"+ escritura.getTramFechaRegistro2() );
			}
			
			if( escritura.getTipoActo() != null && !Utiles.nullToBlank(escritura.getTipoActo().getIdActo()).equals("-1")){
				criteria.add( Restrictions.eq("tipoActo.idActo", escritura.getTipoActo().getIdActo() ) );
				logger.debug( "tipoActo.idActo"+ escritura.getTipoActo().getIdActo() );
			}
			
			if(  !Utiles.nullToBlank(escritura.getKardex() ).equals("")){
				criteria.add( Restrictions.eq("kardex", escritura.getKardex() ) );
				logger.debug( "kardex"+escritura.getKardex()  );
			}
			
			if(  !Utiles.nullToBlank(escritura.getNumeroDoc() ).equals("")){
				criteria.add( Restrictions.eq("numeroDoc", escritura.getNumeroDoc() ) );
				logger.debug( "numeroDoc"+escritura.getNumeroDoc()  );
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
			///criteria.add(Restrictions.or( ));
			 
			criteria.add( Restrictions.eq("estado", 1 ) );
			criteria.addOrder( Order.desc("tramFechaRegistro") );
		}
		logger.debug( "buscarEscitura END " );
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public void registrarEscritura(Escritura escritura) {
		this.getHibernateTemplate().saveOrUpdate(escritura);
	}

	@Override
	public void eliminarEscritura(Integer idescritura) {
		Query query = getSession().createQuery(" update Escritura e set tramEstado=0 where e.idEscritura =:id ")
        .setInteger("id", idescritura);
        query.executeUpdate();
	}

	@Override
	public Escritura obtenerEscritura(Integer idescritura) {
		Query query = getSession().createQuery(" from Escritura d where d.idEscritura = :id ")
        .setInteger("id", idescritura);
        return (Escritura) query.uniqueResult();
	}

	@Override
	public void registraRelacionado(PersonaEscritura persona) {
		this.getHibernateTemplate().persist(persona);	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PersonaEscritura> obtenerPersonasEscritura(int idEscritura) {
		logger.debug( idEscritura );
		Query query = getSession().createQuery(" from PersonaEscritura d where d.escritura.idEscritura = :id ")
        .setInteger("id", idEscritura);
        return query.list();
	}

}
