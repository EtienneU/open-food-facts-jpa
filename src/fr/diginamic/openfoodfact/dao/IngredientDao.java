/**
 * 
 */
package fr.diginamic.openfoodfact.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.diginamic.openfoodfact.entities.Ingredient;

/**
 * @author EtienneUrbano
 *
 */
public class IngredientDao extends AbstractDao {
	
	private EntityManager em = AbstractDao.emf.createEntityManager();
	private EntityTransaction transac = em.getTransaction();

	public IngredientDao() {
		
	}
	
	public void insert(Ingredient ingredient) {
		transac.begin();
		
		em.persist(ingredient);
		
		transac.commit();
	}
}
