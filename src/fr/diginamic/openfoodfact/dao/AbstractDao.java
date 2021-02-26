/**
 * 
 */
package fr.diginamic.openfoodfact.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author EtienneUrbano
 *
 */
public class AbstractDao {

	// Creation de l'entity Manager via le peristence unit "pu_open_food_facts" de
	// mon fichier persistence.xml
	public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_open_food_facts");

}
