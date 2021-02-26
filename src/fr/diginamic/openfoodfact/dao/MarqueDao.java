/**
 * 
 */
package fr.diginamic.openfoodfact.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

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
		
		TypedQuery<Marque> query = em.createQuery("SELECT m FROM Marque m WHERE m.nom = ?1", Marque.class);
		query.setParameter(1, marque.getNom());
		List<Marque> marqueDB = query.getResultList();
		if (marqueDB.isEmpty()) {
			em.persist(marque);
		}
		
		transac.commit();
	}

}
