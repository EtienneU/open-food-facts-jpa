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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author EtienneUrbano
 *
 */
@Entity
@Table(name = "PRODUIT")
public class Produit {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NOM")
	private String nom;

	@Column(name = "NUTRI_GRADE")
	private String nutriGrade;

	@ManyToOne
	@JoinColumn(name = "CATEGORIE_ID")
	private Categorie categorie;

	@ManyToOne
	@JoinColumn(name = "MARQUE_ID")
	private Marque marque;

	@ManyToMany
	@JoinTable(name = "PRODUITS_INGREDIENTS", 
		joinColumns = @JoinColumn(name = "ID_PRODUIT", referencedColumnName = "ID"), 
		inverseJoinColumns = @JoinColumn(name = "ID_INGREDIENT", referencedColumnName = "ID"))
	private List<Ingredient> ingredients = new ArrayList<>();

	@Column(name = "ENERGIE100G")
	private Double energie100g;

	@Column(name = "GRAISSE100G")
	private Double graisse100g;

	@Column(name = "SUCRE100G")
	private Double sucre100g;

	@Column(name = "FIBRES100G")
	private Double fibres100g;

	@Column(name = "PROTEINES100G")
	private Double proteines100g;

	@Column(name = "SEL100G")
	private Double sel100g;

	public Produit() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNutriGrade() {
		return nutriGrade;
	}

	public void setNutriGrade(String nutriGrade) {
		this.nutriGrade = nutriGrade;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Marque getMarque() {
		return marque;
	}

	public void setMarque(Marque marque) {
		this.marque = marque;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Double getEnergie100g() {
		return energie100g;
	}

	public void setEnergie100g(Double energie100g) {
		this.energie100g = energie100g;
	}

	public Double getGraisse100g() {
		return graisse100g;
	}

	public void setGraisse100g(Double graisse100g) {
		this.graisse100g = graisse100g;
	}

	public Double getSucre100g() {
		return sucre100g;
	}

	public void setSucre100g(Double sucre100g) {
		this.sucre100g = sucre100g;
	}

	public Double getFibres100g() {
		return fibres100g;
	}

	public void setFibres100g(Double fibres100g) {
		this.fibres100g = fibres100g;
	}

	public Double getProteines100g() {
		return proteines100g;
	}

	public void setProteines100g(Double proteines100g) {
		this.proteines100g = proteines100g;
	}

	public Double getSel100g() {
		return sel100g;
	}

	public void setSel100g(Double sel100g) {
		this.sel100g = sel100g;
	}

}
