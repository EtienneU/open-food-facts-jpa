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
import fr.diginamic.openfoodfact.entities.Marque;

/**
 * @author EtienneUrbano
 *
 */
public class MarqueDao extends AbstractDao {
	
	private EntityManager em;

	public MarqueDao(EntityManager em) {
		this.em = em;
	}
	
	public void insert(Marque marque) {
		// Vérification en DB si la marque n'existe pas déjà 
		Query query = em.createQuery("SELECT m FROM Marque m WHERE m.nom = ?1");
		query.setParameter(1, marque.getNom());
	    query.setMaxResults(1);
		List<Marque> marqueDB = query.getResultList();
		if (marqueDB == null || marqueDB.isEmpty()) {
			em.persist(marque);
		} else { // Si ma categorie existe déjà en DB
			// Il faut absolument que ma categorie ait un id, sinon erreur "transient instance"
			marque.setId(marqueDB.get(0).getId());
		}
		
	}

}
