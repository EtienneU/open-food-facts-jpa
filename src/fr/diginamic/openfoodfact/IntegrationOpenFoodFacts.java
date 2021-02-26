/**
 * 
 */
package fr.diginamic.openfoodfact;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author EtienneUrbano
 *
 */
public class IntegrationOpenFoodFacts {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Creation de l'entity Manager à partir du peristence unit "pu_open_food_facts" de mon
		// fichier persistence.xml
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_open_food_facts");
		EntityManager em = emf.createEntityManager();

		EntityTransaction et = em.getTransaction();
		et.begin();
		
		
		et.commit();

	}

}
