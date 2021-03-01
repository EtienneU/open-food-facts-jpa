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

/**
 * @author EtienneUrbano
 *
 */
public class CategorieDao extends AbstractDao {
	
	private EntityManager em;

	public CategorieDao(EntityManager em) {
		this.em = em;
	}
	
	public List<Categorie> findAll() {
		TypedQuery<Categorie> query = em.createQuery(
				"SELECT c FROM Categorie c", Categorie.class);
		return query.getResultList();
	}
	
	public void insert(Categorie categorie) {
		
		Query query = em.createQuery("SELECT c FROM Categorie c WHERE c.nom = ?1");
		query.setParameter(1, categorie.getNom());
	    query.setMaxResults(1);
		List<Categorie> categorieDB = query.getResultList();
		if (categorieDB == null || categorieDB.isEmpty()) {
			em.persist(categorie);
		} else { // Si ma categorie existe déjà en DB
			// Il faut absolument que ma categorie ait un id, sinon erreur "transient instance"
			categorie.setId(categorieDB.get(0).getId());
		}
	
	}

}
