/**
 * 
 */
package fr.diginamic.openfoodfact.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.diginamic.openfoodfact.entities.Allergene;
import fr.diginamic.openfoodfact.entities.Categorie;

/**
 * @author EtienneUrbano
 *
 */
public class AllergeneDao extends AbstractDao {
	
	private EntityManager em;

	public AllergeneDao(EntityManager em) {
		this.em = em;
	}
	
	public List<Allergene> findAll() {
		TypedQuery<Allergene> query = em.createQuery(
				"SELECT a FROM Allergene a", Allergene.class);
		return query.getResultList();
	}
	
	public void insert(Allergene allergene) {
		
		Query query = em.createQuery("SELECT a FROM Allergene a WHERE a.nom = ?1");
		query.setParameter(1, allergene.getNom());
	    query.setMaxResults(1);
		List<Allergene> allergeneDB = query.getResultList();
		if (allergeneDB == null || allergeneDB.isEmpty()) {
			em.persist(allergene);
		} else { // Si ma categorie existe déjà en DB
			// Il faut absolument que ma categorie ait un id, sinon erreur "transient instance"
			allergene.setId(allergeneDB.get(0).getId());
		}
	}


}
