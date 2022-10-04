package fr.cda.projet;

import java.util.*;

// Classe de definition d'un produit du stock
public class Produit{
    private String  reference;      // reference du produit
    private String  nom;            // nom du produit
    private double  prix;           // prix du produit
    private int     quantite;       // quantit� du produit

    public Produit(String reference,String nom,double prix, int quantite){                                              //Constructeur
        this.reference = reference;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
    }
    public Produit(){}

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String toString(){                                                                                           //Conversion en cha�ne de caract�res
        String res ="";
        if (nom.length()>50)
            res = String.format("%-10s\t%-55s\t%3.2f\t%3d",reference,nom,prix,quantite);
        else res = String.format("%-10s\t%-55s\t\t%3.2f\t%3d",reference,nom,prix,quantite);
        return res ;
    }
}