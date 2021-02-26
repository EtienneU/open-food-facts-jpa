/**
 * 
 */
package fr.diginamic.openfoodfact.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.diginamic.openfoodfact.IntegrationOpenFoodFacts;
import fr.diginamic.openfoodfact.entities.Produit;

/**
 * @author EtienneUrbano
 *
 */
public class ProduitDao extends AbstractDao {
	
	private EntityManager em = AbstractDao.emf.createEntityManager();
	private EntityTransaction transac = em.getTransaction();

	public ProduitDao() {
	}
	
	public List<Produit> findAll() {
		TypedQuery<Produit> query = em.createQuery(
				"SELECT prod FROM Produit prod", Produit.class);
		return query.getResultList();
	}

	public Produit findById(int id) {
		return em.find(Produit.class, id);
	}
	
	public List<Produit> findByNomContains(String nom) {
		TypedQuery<Produit> query = em.createQuery("SELECT prod FROM Produit prod WHERE prod.nom LIKE ?1", Produit.class);
		query.setParameter(1, "%" + nom + "%");
		return query.getResultList();
	}
	

	public void insert(Produit produit) {
		transac.begin();
		
		em.persist(produit);
		
		transac.commit();
	}

	public void updateNom(Produit produit) {
		transac.begin();
		
		Produit produitDB = findById(produit.getId());
		produitDB.setNom(produit.getNom());
		
		transac.commit();
	}

	public void delete(Produit produit) {
		transac.begin();

		Produit produitDB = findById(produit.getId());
		em.refresh(produitDB);
		
		transac.commit();
	}

}
