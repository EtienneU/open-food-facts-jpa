/**
 * 
 */
package fr.diginamic.openfoodfact.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.diginamic.openfoodfact.entities.Categorie;
import fr.diginamic.openfoodfact.entities.Ingredient;
import fr.diginamic.openfoodfact.entities.Produit;

/**
 * @author EtienneUrbano
 *
 */
public class IngredientDao extends AbstractDao {
	
	private EntityManager em;

	public IngredientDao(EntityManager em) {
		this.em = em;
	}
	
	public List<Ingredient> findAll() {
		TypedQuery<Ingredient> query = em.createQuery(
				"SELECT i FROM Ingredient i", Ingredient.class);
		return query.getResultList();
	}
	
	public void insert(Ingredient ingredient) {
				
		Query query = em.createQuery("SELECT i FROM Ingredient i WHERE i.nom = ?1");
		query.setParameter(1, ingredient.getNom());
		query.setMaxResults(0);
		List<Ingredient> ingredientDB = query.getResultList();
		if (ingredientDB == null || ingredientDB.isEmpty()) {
			em.persist(ingredient);
		} else {
			ingredient.setId(ingredientDB.get(0).getId());
		} 
	}		
}
