/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author sawssen
 */
public class ProduitPanier {
  private int id;
  private String nom;
  private String reference;
  private int quantite;
  private float prix;

    public ProduitPanier() {
    }

    public ProduitPanier(int id, String nom, String reference, int quantite, float prix) {
        this.id = id;
        this.nom = nom;
        this.reference = reference;
        this.quantite = quantite;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
  

   
 
   
 
    
}
