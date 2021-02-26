/**
 * 
 */
package fr.diginamic.openfoodfact.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.diginamic.openfoodfact.entities.Marque;

/**
 * @author EtienneUrbano
 *
 */
public class MarqueDao extends AbstractDao {
	
	private EntityManager em = AbstractDao.emf.createEntityManager();
	private EntityTransaction transac = em.getTransaction();

	public MarqueDao() {
		
	}
	
	public void insert(Marque marque) {
		transac.begin();
		
		em.persist(marque);
		
		transac.commit();
	}

}
