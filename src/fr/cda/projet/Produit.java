package fr.cda.projet;

import java.util.*;

/**
 * The type Produit.
 */
// Classe de definition d'un produit du stock
public class Produit{
    private String  reference;      // reference du produit
    private String  nom;            // nom du produit
    private double  prix;           // prix du produit
    private int     quantite;       // quantité du produit

    /**
     * Instantiates a new Produit.
     *
     * @param reference the product's reference
     * @param nom       the product's name
     * @param prix      the product's price
     * @param quantite  the product's quantity
     */
    public Produit(String reference,String nom,double prix, int quantite){                                              //Constructeur
        this.reference = reference;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
    }

    /**
     * Instantiates a new Produit.
     */
    public Produit(){}

    /**
     * Gets reference.
     *
     * @return the reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * Sets reference.
     *
     * @param reference the product's reference
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * Gets nom.
     *
     * @return the product's name
     */
    public String getNom() {
        return nom;
    }

    /**
     * Sets nom.
     *
     * @param nom the product's name
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Gets prix.
     *
     * @return the product's price
     */
    public double getPrix() {
        return prix;
    }

    /**
     * Sets prix.
     *
     * @param prix the product's price
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }

    /**
     * Gets quantite.
     *
     * @return the product's quantity
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * Sets quantite.
     *
     * @param quantite the product's quantity
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String toString(){                                                                                           //Conversion en chaîne de caractères
        String res ="";
        if (nom.length()>50)
            res = String.format("%-10s\t%-55s\t%3.2f\t%3d",reference,nom,prix,quantite);
        else res = String.format("%-10s\t%-55s\t\t%3.2f\t%3d",reference,nom,prix,quantite);
        return res ;
    }
}