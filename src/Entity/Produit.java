/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author pc
 */
public class Produit {

    private int id ;
    private  String designation ;
    private String marque ;
    private String imageName ;
    private String Date;
    
      
    private String description;//
    private String reference;//

    private String size; 
    private float prixdachat;
  
    private float prixvente;
    private int note;
    private int NbreV=0;
    private String disponible;
    private String couleur;//
    //private String 	;
    private int quantite;
    private int boutique_id;
     private Date updatedAt ;
     private String categories;
     private float prixancien;
     private String 	solde;
     private String 	year;

     
     
     
     
     
    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public float getPrixdachat() {
        return prixdachat;
    }
    public String getPrixdachatS() {
        return String.valueOf(prixdachat);
    }

    public void setPrixdachat(float prixdachat) {
        this.prixdachat = prixdachat;
    }
    public String setPrixdachatS(String prixdachat) {
        return prixdachat ;
    }

    public float getPrixvente() {
        return prixvente;
    }

    public String getPrixventeS() {
        return String.valueOf(prixvente);
    }
    
    public void setPrixvente(float prixvente) {
        this.prixvente = prixvente;
    }
    public String setPrixventeS(String prixvente) {
        return prixvente ;
    }

    public int getNote() {
        return note;
    }
 public String getNoteS() {
        return String.valueOf(note);
    }
    public void setNote(int note) {
        this.note = note;
    }
    public String setNoteS(String note) {
        return note;
    }

    public int getNbreV() {
        return NbreV;
    }
    public String getNbreVS() {
        return String.valueOf(NbreV);
    }

    public void setNbreV(int NbreV) {
        this.NbreV = NbreV;
    }
    public String setNbreVS(String NbreV) {
        return NbreV;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public int getQuantite() {
        return quantite;
    }

    public String getQuantiteS() {
        return String.valueOf(quantite);
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public String setQuantiteS(String quantite) {
        return quantite ;
    }

    public int getBoutique_id() {
        return boutique_id;
    }
public String getBoutique_idS() {
        return String.valueOf(boutique_id);
    }

    public void setBoutique_id(int boutique_id) {
        this.boutique_id = boutique_id;
    }
    public String setBoutique_idS(String boutique_id) {
        return boutique_id;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getUpdatedAtS() {
        return String.valueOf(updatedAt);
    }
    
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    public void setUpdatedAtS(String updatedAt) {
        
        this.Date = updatedAt;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public float getPrixancien() {
        return prixancien;
    }
    
    public String getPrixancienS() {
        return String.valueOf(prixancien);
    }

    public void setPrixancien(float prixancien) {
        this.prixancien = prixancien;
    }
public String setPrixancienS(String prixancien) {
        return prixancien;
    }

    public String getSolde() {
        return solde;
    }

    public void setSolde(String solde) {
        this.solde = solde;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
     
     
  
public Produit(){}
    

    
    public void setId(int cin) {
        this.id = cin;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

  

    public void setPhoto(String photo) {
        this.imageName = photo;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public int getID() {
        return id;
    }
   public String setIdS(String id){
   return id ;
   }
    public String getDesignation() {
        return designation;
    }

    public String getMarque() {
        return marque;
    }

    
    public String getPhoto() {
        return imageName;
    }

    public String getDate() {
        return Date;
    }


}
