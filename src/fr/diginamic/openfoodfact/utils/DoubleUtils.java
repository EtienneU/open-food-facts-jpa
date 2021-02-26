/**
 * 
 */
package fr.diginamic.openfoodfact.utils;

/**
 * @author EtienneUrbano
 * Classe stateless ( = sans attributs)
 */
public class DoubleUtils {
	
	// Retourne la valeur de type Double correspondant à la valeur String passée en paramètre
	public static double parse(String valeur) {
		if (valeur.isEmpty()) {
			return 0;
		} else {
			return Double.parseDouble(valeur);
		}
	}
	

}
