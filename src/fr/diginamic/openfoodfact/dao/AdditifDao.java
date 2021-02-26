/**
 * 
 */
package fr.diginamic.openfoodfact.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.openfoodfact.entities.Additif;
import fr.diginamic.openfoodfact.entities.Allergene;

/**
 * @author EtienneUrbano
 *
 */
public class AdditifDao extends AbstractDao {
	
	private EntityManager em = AbstractDao.emf.createEntityManager();
	private EntityTransaction transac = em.getTransaction();

	public AdditifDao() {
	}
	
	public List<Additif> findAll() {
		TypedQuery<Additif> query = em.createQuery(
				"SELECT add FROM Additif add", Additif.class);
		return query.getResultList();
	}
	
	public void insert(Additif additif) {
		transac.begin();
		
		em.persist(additif);
		
		transac.commit();
	}

}
