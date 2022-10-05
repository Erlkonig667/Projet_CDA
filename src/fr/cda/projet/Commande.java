package fr.cda.projet;

import java.util.*;

/**
 * The type Commande.
 */
// Classe de definition d'une commande
public class Commande{
    private int     numero;         // Numero de la commande
    private String  date;           // Date de la commande. Au format JJ/MM/AAAA
    private String  client;         // Nom du client
    private ArrayList<String> references;   // Les references des produits de la commande
    private ArrayList<Integer> quantites;   // Le nombre de chaque produit demandé dans la commande
    private boolean isLivre;                // Le statut "livré" ou "non livré" de la commande
    private String motifNonLivraison;       // Motif de non-livraison de la commande

    /**
     * Instantiates a new Commande.
     *
     * @param numero the id number of the order
     * @param date   the date when the order was made
     * @param client the client who made the order
     */
    public Commande(int numero,String date,String client){                                                              // Constructeur
        this.numero = numero;
        this.date = date;
        this.client = client;
        this.references = new ArrayList<String>();
        this.quantites = new ArrayList<Integer>();
        this.isLivre = false;
        this.motifNonLivraison="";
    }

    /**
     * Instantiates a new Commande.
     */
    public Commande(){}                                                                                                 //Constructeur vide

    /**
     * Gets numero.
     *
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Sets numero.
     *
     * @param numero the numero
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets client.
     *
     * @return the client
     */
    public String getClient() {
        return client;
    }

    /**
     * Sets client.
     *
     * @param client the client
     */
    public void setClient(String client) {
        this.client = client;
    }

    /**
     * Gets references.
     *
     * @return the references
     */
    public ArrayList<String> getReferences() {
        return references;
    }

    /**
     * Gets quantites.
     *
     * @return the quantites
     */
    public ArrayList<Integer> getQuantites() {
        return quantites;
    }

    /**
     * Is livre boolean.
     *
     * We want to check whether the order is delivered or not
     *
     * @return the boolean
     */
    public boolean isLivre() {
        return isLivre;
    }

    /**
     * Sets livre.
     *
     * @param livre
     */
    public void setLivre(boolean livre) {
        isLivre = livre;
    }

    /**
     * Gets motif non-livraison.
     *
     * @return the string explaining why the order couldn't be delivered
     */
    public String getMotifNonLivraison() {
        return motifNonLivraison;
    }

    /**
     * Sets motif non-livraison.
     *
     * @param motifNonLivraison
     */
    public void setMotifNonLivraison(String motifNonLivraison) {
        this.motifNonLivraison = motifNonLivraison;
    }

    /**
     * Afficher references string.
     *
     * @return the string containing every item included in the order
     */
    public String afficherReferences(){
        String res="";
        for (int i=0;i<references.size();i++){
            res+="\n\t\t"+quantites.get(i)+" x "+references.get(i);
        }
        return res;
    }

    /**
     * To String string.
     *
     * @return the string containing every information about the order
     */

    public String toString() {
        String res = "Commande : "+this.getNumero()+"\n\t Date : "+this.getDate()+"\n\t Client : "
                +this.getClient()+"\n\t References des produits : "+this.afficherReferences()+"\n"
                +this.getMotifNonLivraison()+"\n";
        res+="------------------------------------------------------------------------------------------------------------------------------\n";
        return res;
    }



}