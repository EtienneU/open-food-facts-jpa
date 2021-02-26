/**
 * 
 */
package fr.diginamic.openfoodfact.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.openfoodfact.entities.Allergene;

/**
 * @author EtienneUrbano
 *
 */
public class AllergeneDao extends AbstractDao {
	
	private EntityManager em = AbstractDao.emf.createEntityManager();
	private EntityTransaction transac = em.getTransaction();

	public AllergeneDao() {
	}
	
	public List<Allergene> findAll() {
		TypedQuery<Allergene> query = em.createQuery(
				"SELECT a FROM Allergene a", Allergene.class);
		return query.getResultList();
	}
	
	public void insert(Allergene allergene) {
		transac.begin();
		
		em.persist(allergene);
		
		transac.commit();
	}


}
