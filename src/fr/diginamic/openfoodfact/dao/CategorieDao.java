/**
 * 
 */
package fr.diginamic.openfoodfact.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.openfoodfact.entities.Categorie;

/**
 * @author EtienneUrbano
 *
 */
public class CategorieDao extends AbstractDao {
	
	private EntityManager em = AbstractDao.emf.createEntityManager();
	private EntityTransaction transac = em.getTransaction();

	public CategorieDao() {
	}
	
	public List<Categorie> findAll() {
		TypedQuery<Categorie> query = em.createQuery(
				"SELECT c FROM Categorie c", Categorie.class);
		return query.getResultList();
	}
	
	public void insert(Categorie categorie) {
		
		TypedQuery<Categorie> query = em.createQuery("SELECT c FROM Categorie c WHERE c.nom = ?1", Categorie.class);
		query.setParameter(1, categorie.getNom());
		List<Categorie> categorieDB = query.getResultList();
		if (categorieDB.isEmpty()) {
			transac.begin();
			em.persist(categorie);
			transac.commit();
		}
	
	}

}
