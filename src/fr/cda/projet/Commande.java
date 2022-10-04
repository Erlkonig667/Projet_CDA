package fr.cda.projet;

import java.util.*;

// Classe de definition d'une commande
public class Commande{
    private int     numero;         // Numero de la commande
    private String  date;           // Date de la commande. Au format JJ/MM/AAAA
    private String  client;         // Nom du client
    private ArrayList<String> references;   // Les references des produits de la commande
    private ArrayList<Integer> quantites;   // Le nombre de chaque produit demandé dans la commande
    private boolean isLivre;                // Le statut "livré" ou "non livré" de la commande
    private String motifNonLivraison;       // Motif de non-livraison de la commande

    public Commande(int numero,String date,String client){                                                              // Constructeur
        this.numero = numero;
        this.date = date;
        this.client = client;
        this.references = new ArrayList<String>();
        this.quantites = new ArrayList<Integer>();
        this.isLivre = false;
        this.motifNonLivraison="";
    }
    public Commande(){}                                                                                                 //Constructeur vide

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public ArrayList<String> getReferences() {
        return references;
    }

    public void setReferences(ArrayList<String> references) {
        this.references = references;
    }

    public ArrayList<Integer> getQuantites() {
        return quantites;
    }

    public void setQuantites(ArrayList<Integer> quantites) {
        this.quantites = quantites;
    }

    public boolean isLivre() {
        return isLivre;
    }

    public void setLivre(boolean livre) {
        isLivre = livre;
    }

    public String getMotifNonLivraison() {
        return motifNonLivraison;
    }

    public void setMotifNonLivraison(String motifNonLivraison) {
        this.motifNonLivraison = motifNonLivraison;
    }

    public String afficherReferences(){
        String res="";
        for (int i=0;i<references.size();i++){
            res+="\n\t\t"+quantites.get(i)+" x "+references.get(i);
        }
        return res;
    }

    public String toString() {
        String res = "Commande : "+this.getNumero()+"\n\t Date : "+this.getDate()+"\n\t Client : "
                +this.getClient()+"\n\t References des produits : "+this.afficherReferences()+"\n"
                +this.getMotifNonLivraison()+"\n";
        res+="------------------------------------------------------------------------------------------------------------------------------\n";
        return res;
    }



}