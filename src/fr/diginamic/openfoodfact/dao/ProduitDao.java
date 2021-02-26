/**
 * 
 */
package fr.diginamic.openfoodfact.dao;

import javax.persistence.EntityManager;

import fr.diginamic.openfoodfact.entities.Produit;

/**
 * @author EtienneUrbano
 *
 */
public class ProduitDao {

	private EntityManager em;

	public ProduitDao(EntityManager em) {
		this.em = em;
	}

	public Produit findById(int id) {
		return em.find(Produit.class, id);
	}

	public void insert(Produit produit) {
		em.persist(produit);
	}

	public void updateNom(Produit produit) {
		Produit produitDB = findById(produit.getId());
		produitDB.setNom(produit.getNom());
	}

	public void delete(Produit produit) {
		Produit produitDB = findById(produit.getId());
		em.refresh(produitDB);
	}

}
