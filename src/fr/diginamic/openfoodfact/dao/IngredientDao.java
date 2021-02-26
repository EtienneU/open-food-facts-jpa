/**
 * 
 */
package fr.diginamic.openfoodfact.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

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
	
	public List<Ingredient> findAll() {
		TypedQuery<Ingredient> query = em.createQuery(
				"SELECT i FROM Ingredient i", Ingredient.class);
		return query.getResultList();
	}
	
	public void insert(Ingredient ingredient) {
		
		if(ingredient.getNom().length() <= 255) {
			TypedQuery<Ingredient> query = em.createQuery("SELECT i FROM Ingredient i WHERE i.nom = ?1",
					Ingredient.class);
			query.setParameter(1, ingredient.getNom());
			List<Ingredient> ingredientDB = query.getResultList();
			if (ingredientDB.isEmpty()) {
				transac.begin();
				em.persist(ingredient);
				transac.commit();
			}
		} else {
			System.out.println("TROP LONG : " + ingredient.getNom());
		}
		
		
	
	}
			
		
}
