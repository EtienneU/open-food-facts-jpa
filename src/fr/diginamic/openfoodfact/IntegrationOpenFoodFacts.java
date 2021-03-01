/**
 * 
 */
package fr.diginamic.openfoodfact;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.diginamic.openfoodfact.dao.AbstractDao;
import fr.diginamic.openfoodfact.dao.AdditifDao;
import fr.diginamic.openfoodfact.dao.AllergeneDao;
import fr.diginamic.openfoodfact.dao.CategorieDao;
import fr.diginamic.openfoodfact.dao.IngredientDao;
import fr.diginamic.openfoodfact.dao.MarqueDao;
import fr.diginamic.openfoodfact.dao.ProduitDao;
import fr.diginamic.openfoodfact.entities.Additif;
import fr.diginamic.openfoodfact.entities.Allergene;
import fr.diginamic.openfoodfact.entities.Categorie;
import fr.diginamic.openfoodfact.entities.Ingredient;
import fr.diginamic.openfoodfact.entities.Marque;
import fr.diginamic.openfoodfact.entities.Produit;
import fr.diginamic.openfoodfact.utils.DoubleUtils;

/**
 * @author EtienneUrbano
 *
 */
public class IntegrationOpenFoodFacts {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		// lecture du fichier open-food-facts.csv
		String filePath = "C:/Users/urban/OneDrive/OD-Documents/DEV/Diginamic/Java/workspaceSTS/traitement-fichier-jpa-off/resources/open-food-facts.csv";
		Path pathFile = Paths.get(filePath);
		// Récupération des lignes du fichier CSV dans une liste de String
		List<String> infosBrutesProduits = Files.readAllLines(pathFile);
		infosBrutesProduits.remove(0); // Je retire la ligne d'entête

		// Insertion des lignes du fichier csv dans les tables de la DB
		EntityManager em = AbstractDao.emf.createEntityManager();
		EntityTransaction transac = em.getTransaction();
		ProduitDao 		produitDao    = new ProduitDao(em);
		CategorieDao 	categorieDao  = new CategorieDao(em);
		MarqueDao 		marqueDao 	  = new MarqueDao(em);
		IngredientDao   ingredientDao = new IngredientDao(em);
		AllergeneDao 	allergeneDao  = new AllergeneDao(em);
		AdditifDao 		additifDao 	  = new AdditifDao(em);
		int compteur = 0;
		for (String line : infosBrutesProduits) {
			// début de transaction - 1 transaction par ligne du fichier csv
			transac.begin();

			// Conversion de chaque ligne CSV en tableau de String
			// '-1' spécifie qu'une succession de pipe n'équivaut pas à 1 seul pipe mais bien à plusieurs colonnes
			String[] tabInfosProduits = line.split("\\|", -1);

			// Récupération des colonnes du CSV qui nous intéressent
			String nomCategorie = tabInfosProduits[0];
			String nomMarque 	= tabInfosProduits[1];
			String nomProduit 	= tabInfosProduits[2];
			char nutriGrade 	= tabInfosProduits[3].charAt(0);
			// Extraction des ingrédients
			List<Ingredient> listeIngredients = new ArrayList<>();
			List<String> ingredients= Arrays.asList(tabInfosProduits[4].replaceAll("[_*]"," ").split("[,;-]", -1));
			for (String nomIngredient : ingredients) {
				Ingredient ingredient = new Ingredient(nomIngredient.trim());
				int longueur = ingredient.getNom().length();
				// si l'ingredient est trop court ou trop long, je n'en prend pas compte
				if (longueur > 2 && longueur <= 255) {
					ingredientDao.insert(ingredient);
					listeIngredients.add(ingredient);
				}
			}
			double energie100g	= DoubleUtils.parse(tabInfosProduits[5]);
			double graisse100g	= DoubleUtils.parse(tabInfosProduits[6]);
			double sucres100g	= DoubleUtils.parse(tabInfosProduits[7]);
			double fibres100g	= DoubleUtils.parse(tabInfosProduits[8]);
			double proteines100g= DoubleUtils.parse(tabInfosProduits[9]);
			double sel100g		= DoubleUtils.parse(tabInfosProduits[10]);
			// Extraction des allergenes
			List<Allergene> listeAllergenes = new ArrayList<>();
			List<String> allergenes= Arrays.asList(tabInfosProduits[28].replaceAll("en:","").split("[,;-]", -1));
			for (String nomAllergene : allergenes) {
				Allergene allergene = new Allergene(nomAllergene.trim());
				allergeneDao.insert(allergene);
				listeAllergenes.add(allergene);
			}
			// Extraction des additifs
			List<Additif> listeAdditifs = new ArrayList<>();
			List<String> additifs = Arrays.asList(tabInfosProduits[29].split("[,;]", -1));
			for (String nomAdditif : additifs) {
				Additif additif = new Additif(nomAdditif.trim());
				additifDao.insert(additif);
				listeAdditifs.add(additif);
			}
			
			Marque marque = new Marque(nomMarque);
			marqueDao.insert(marque);
			
			Categorie categorie = new Categorie(nomCategorie);
			categorieDao.insert(categorie);
			
			Produit produit = new Produit(nomProduit, nutriGrade, categorie, marque,
					energie100g, graisse100g, sucres100g, fibres100g,
					proteines100g, sel100g);
			produit.setIngredients(listeIngredients);
			produit.setAllergenes(listeAllergenes);
			produit.setAdditifs(listeAdditifs);
			produitDao.insert(produit);
			compteur++;
			
			if(compteur % 100 == 0) {
				System.out.println(compteur + " produits testées avant insertion en DB");
			}
			
			// fin de transaction
			transac.commit();
		}

//		List<Produit> resultRech = new ArrayList<>();
//		ProduitDao produitDao = new ProduitDao();
//		resultRech = produitDao.findByNomContains("olive");
//		System.out.println(resultRech);

	}

}
