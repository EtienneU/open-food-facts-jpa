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
 * @author urban
 *
 */
@Entity
@Table(name="ADDITIF")
public class Additif {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="NOM")
	private String nom;

	@ManyToMany
	@JoinTable(name = "PRODUITS_ADDITIFS", 
		joinColumns = @JoinColumn(name = "ID_ADDITIF", referencedColumnName = "ID"), 
		inverseJoinColumns = @JoinColumn(name = "ID_PRODUIT", referencedColumnName = "ID"))
	private List<Produit> produits = new ArrayList<>();
	
	public Additif() {
	}
	
	public Additif(String nom) {
		this.nom = nom;
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

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

}
