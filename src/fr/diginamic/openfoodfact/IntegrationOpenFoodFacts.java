/**
 * 
 */
package fr.diginamic.openfoodfact;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import fr.diginamic.openfoodfact.dao.CategorieDao;
import fr.diginamic.openfoodfact.dao.IngredientDao;
import fr.diginamic.openfoodfact.dao.MarqueDao;
import fr.diginamic.openfoodfact.dao.ProduitDao;
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
		ProduitDao 		produitDao    = new ProduitDao();
		CategorieDao 	categorieDao  = new CategorieDao();
		MarqueDao 		marqueDao 	  = new MarqueDao();
		IngredientDao   ingredientDao = new IngredientDao();
		for (String line : infosBrutesProduits) {

			// Conversion de chaque ligne CSV en tableau de String
			// '-1' sert à spécifier qu'une succession se pipe n'équivaut pas à 1 seul pipe
			String[] tabInfosProduits = line.split("\\|", -1);

			// Récupération des colonnes du CSV qui nous intéressent
			String nomCategorie = tabInfosProduits[0];
			String nomMarque 	= tabInfosProduits[1];
			String nomProduit 	= tabInfosProduits[2];
			char nutriGrade 	= tabInfosProduits[3].charAt(0);
			String listeIngredi = tabInfosProduits[4];
			String[] ingredients= listeIngredi.split(",", -1);
			double energie100g	= DoubleUtils.parse(tabInfosProduits[5]);
			double graisse100g	= DoubleUtils.parse(tabInfosProduits[6]);
			double sucres100g	= DoubleUtils.parse(tabInfosProduits[7]);
			double fibres100g	= DoubleUtils.parse(tabInfosProduits[8]);
			double proteines100g= DoubleUtils.parse(tabInfosProduits[9]);
			double sel100g		= DoubleUtils.parse(tabInfosProduits[10]);
			
			Marque marque = new Marque(nomMarque);
			marqueDao.insert(marque);
			
			Categorie categorie = new Categorie(nomCategorie);
			categorieDao.insert(categorie);
			
			for (int i = 0; i < listeIngredi.length(); i++) {
				Ingredient ingredient = new Ingredient();
			}

//			PreparedStatement prepStatement = connection
//					.prepareStatement("INSERT INTO " + "ville(nom, population) " + "VALUES (?, ?)");
		}

//		List<Produit> resultRech = new ArrayList<>();
//		ProduitDao produitDao = new ProduitDao();
//		resultRech = produitDao.findByNomContains("olive");
//		System.out.println(resultRech);

	}

}
