package dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.User;

public abstract class GenericDAO<T> {

    private Class<T>                          type;
  
   @PersistenceContext(unitName="testhib")
    protected EntityManager                   em;

    public GenericDAO() {
    }

    public void beginTransaction() {
    
    }

    public void commit() {
    }

    public void rollback() {
    }

    public void closeTransaction() {
    }

    public void commitAndCloseTransaction() {
        commit();
        closeTransaction();
    }

    public void flush() {
        em.flush();
    }

    public GenericDAO( Class<T> type ) {
        this.type = type;
    }

    public T create( T t ) {

        this.em.merge( t );
        return t;
    }

    @SuppressWarnings( "unchecked" )
    protected T findOneResult( String namedQuery, Map<String, Object> parameters ) {
        T result = null;

        try {
            Query query = em.createNamedQuery( namedQuery );

            // Method that will populate parameters if they are passed not null
            // and empty
            if ( parameters != null && !parameters.isEmpty() ) {
                populateQueryParameters( query, parameters );
            }

            result = (T) query.getSingleResult();

        } catch ( NoResultException e ) {
            System.out
                    .println( "No result found for named query: " + namedQuery );
            return null;
        } catch ( Exception e ) {
            System.out.println( "Error while running query: " + e.getMessage() );
            e.printStackTrace();
            return null;
        }

        return result;
    }

    private void populateQueryParameters( Query query,
            Map<String, Object> parameters ) {
        for ( Entry<String, Object> entry : parameters.entrySet() ) {
            query.setParameter( entry.getKey(), entry.getValue() );
        }
    }
    
    public T update(T entity  ){
    	
    return em.merge(entity);
    }
    public T find( Object id ) {
        beginTransaction();
        T t = this.em.find( this.type, id );
        closeTransaction();
        return t;
    }

    public void delete( Object id ) {
        Object ref = this.em.getReference( this.type, id );
        this.em.remove( ref );
    }

    @SuppressWarnings( "rawtypes" )
    public List findWithNamedQuery( String namedQueryName ) {
        return this.em.createNamedQuery( namedQueryName ).getResultList();
    }

    @SuppressWarnings( "rawtypes" )
    public List findWithNamedQuery( String namedQueryName, Map parameters ) {
        return findWithNamedQuery( namedQueryName, parameters, 0 );
    }

    @SuppressWarnings( "unchecked" )
    public List<T> findWithNamedQuery( String queryName, int resultLimit ) {
        return this.em.createNamedQuery( queryName ).setMaxResults( resultLimit )
                .getResultList();
    }

    @SuppressWarnings( "unchecked" )
    public List<T> findByNativeQuery( String sql ) {
        return this.em.createNativeQuery( sql, type ).getResultList();
    }

    public int countTotalRecord( String namedQueryName ) {
        Query query = em.createNamedQuery( namedQueryName );
        Number result = (Number) query.getSingleResult();
        return result.intValue();
    }

    @SuppressWarnings( "unchecked" )
    public List<T> findWithNamedQuery( String namedQueryName, @SuppressWarnings( "rawtypes" ) Map parameters,
            int resultLimit ) {
        Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
        Query query = this.em.createNamedQuery( namedQueryName );
        if ( resultLimit > 0 ) {
            query.setMaxResults( resultLimit );
        }
        for ( Map.Entry<String, Object> entry : rawParameters ) {
            query.setParameter( entry.getKey(), entry.getValue() );
        }
        return query.getResultList();
    }

    @SuppressWarnings( "rawtypes" )
    public List findWithNamedQuery( String namedQueryName, int start, int end ) {
        Query query = null;
        try {
            query = this.em.createNamedQuery( namedQueryName );
            query.setMaxResults( end - start );
            query.setFirstResult( start );

        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return query.getResultList();
    }
    
    
    
    //-------------------------------------------------------------------------

	 public boolean deleteItems( List<T> items ) {
       for ( T item : items ) {
           if ( item instanceof User ) {
               User user = (User) item;
              this.delete(user.getId());
           }

          
       }
       return true;
   }//-----------------------------------
}