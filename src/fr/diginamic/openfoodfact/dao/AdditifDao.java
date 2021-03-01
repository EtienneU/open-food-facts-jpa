/**
 * 
 */
package fr.diginamic.openfoodfact.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.diginamic.openfoodfact.entities.Additif;
import fr.diginamic.openfoodfact.entities.Allergene;

/**
 * @author EtienneUrbano
 *
 */
public class AdditifDao extends AbstractDao {
	
	private EntityManager em;

	public AdditifDao(EntityManager em) {
		this.em = em;
	}
	
	public List<Additif> findAll() {
		TypedQuery<Additif> query = em.createQuery(
				"SELECT add FROM Additif add", Additif.class);
		return query.getResultList();
	}
	
	public void insert(Additif additif) {
		
		Query query = em.createQuery("SELECT a FROM Additif a WHERE a.nom = ?1");
		query.setParameter(1, additif.getNom());
	    query.setMaxResults(1);
		List<Additif> additifDB = query.getResultList();
		if (additifDB == null || additifDB.isEmpty()) {
			em.persist(additif);
		} else { // Si ma categorie existe déjà en DB
			// Il faut absolument que ma categorie ait un id, sinon erreur "transient instance"
			additif.setId(additifDB.get(0).getId());
		}
	}

}
