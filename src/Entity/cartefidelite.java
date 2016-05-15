/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author lenovo
 */
public class cartefidelite {
     private int idCarteFidelite;
     private String nombrePointFidele;
     private String dateCreationCarteFidelite;

    public String toString() {
        return "cartefidelite{" + "idCarteFidelite=" + idCarteFidelite + ", nombrePointFidele=" + nombrePointFidele + ", dateCreationCarteFidelite=" + dateCreationCarteFidelite + '}';
    }

    public cartefidelite(int idCarteFidelite) {
        this.idCarteFidelite = idCarteFidelite;
    }

    
    
    
    public cartefidelite(String nombrePointFidele) {
        this.nombrePointFidele = nombrePointFidele;
    }

    public cartefidelite(String nombrePointFidele, String dateCreationCarteFidelite) {
        this.nombrePointFidele = nombrePointFidele;
        this.dateCreationCarteFidelite = dateCreationCarteFidelite;
    }
    
    

    public cartefidelite(int idCarteFidelite, String nombrePointFidele, String dateCreationCarteFidelite) {
        this.idCarteFidelite = idCarteFidelite;
        this.nombrePointFidele = nombrePointFidele;
        this.dateCreationCarteFidelite = dateCreationCarteFidelite;
    }

    
    
    public cartefidelite() {
    }

    public int getIdCarteFidelite() {
        return idCarteFidelite;
    }

    public void setIdCarteFidelite(int idCarteFidelite) {
        this.idCarteFidelite = idCarteFidelite;
    }

    public void setNombrePointFidele(String nombrePointFidele) {
        this.nombrePointFidele = nombrePointFidele;
    }

    public void setDateCreationCarteFidelite(String dateCreationCarteFidelite) {
        this.dateCreationCarteFidelite = dateCreationCarteFidelite;
    }

    public String getNombrePointFidele() {
        return nombrePointFidele;
    }

    public String getDateCreationCarteFidelite() {
        return dateCreationCarteFidelite;
    }


  
     
}
