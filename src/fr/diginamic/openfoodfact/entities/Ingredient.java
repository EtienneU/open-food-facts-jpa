/**
 * 
 */
package fr.diginamic.openfoodfact.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author EtienneUrbano
 *
 */
@Entity
@Table(name = "INGREDIENT")
public class Ingredient {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="NOM")
	private String nom;

	@ManyToMany
	@JoinTable(name = "PRODUITS_INGREDIENTS", 
		joinColumns = @JoinColumn(name = "ID_INGREDIENT", referencedColumnName = "ID"), 
		inverseJoinColumns = @JoinColumn(name = "ID_PRODUIT", referencedColumnName = "ID"))
	private List<Produit> produits = new ArrayList<>();

	public Ingredient() {

	}
	
	public Ingredient(String nom) {
		this.nom = nom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
