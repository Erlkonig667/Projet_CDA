package fr.cda.projet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import fr.cda.util.*;

/**
 * The type Site.
 */
// Classe de definition du site de vente
public class Site
{
    private ArrayList<Produit> stock;       // Les produits du stock
    private ArrayList<Commande> commandes;  // Les bons de commande

    /**
     * Instantiates a new Site.
     *
     * @throws ParseException the parse exception
     */
    public Site() throws ParseException {                                                                                  //Constructeur
        stock = new ArrayList<Produit>();
        initialiserStock("C:\\Users\\greta2022\\Desktop\\Programmes Java\\ProjetCDA1\\data/Produits.txt");       //Lecture du fichier data/Produits.txt et création du stock
        commandes = new ArrayList<Commande>();
        initialiserCommandes("C:\\Users\\greta2022\\Desktop\\Programmes Java\\ProjetCDA1\\data/Commandes.txt");  //Lecture du fichier data/Commandes.txt et création de la liste de commandes

    }

    /**
     * Gets stock.
     *
     * @return the stock
     */
    public ArrayList<Produit> getStock() {
        return stock;
    }

    /**
     * Gets commandes.
     *
     * @return the commandes
     */
    public ArrayList<Commande> getCommandes() {
        return commandes;
    }

    /**
     * Is commande presente boolean.
     *
     * @param id the id we want to test
     * @return the boolean
     */
    public boolean isCommandePresente(int id){                                                                          //Méthode qui vérifie si une commande est présente dans la liste des commandes, grâce à l'id
        boolean present = false;
        for (int i=0;i<commandes.size();i++){
            Commande c = commandes.get(i);
            if (c.getNumero()==id)
                present = true;
        }
        return present;
    }

    /**
     * Chercher commande commande.
     *
     * @param id the id of the order we want to get
     * @return the order matching the id
     */
    public Commande chercherCommande(int id){                                                                           //Méthode qui renvoie la commande ayant pour id celui entré en paramètre
        Commande res = new Commande();
        for (int i=0;i<commandes.size();i++){
            Commande c = commandes.get(i);
            if (c.getNumero()==id)
                res = c;
        }
        return res;
    }

    /**
     * Initialize Stock.
     *
     * @param nomFichier the name of the file
     * @return an array containing the products of our stock
     */
    private void initialiserStock(String nomFichier)
    {
        String[] lignes = Terminal.lireFichierTexte(nomFichier);                                                        //Chargement du fichier de Produit
        for(String ligne :lignes)                                                                                       //Pour chaque ligne, on crée un Produit que l'on ajoute au stock
            {
                System.out.println(ligne);
                String[] champs = ligne.split("[;]",4);
                String reference = champs[0];
                String nom = champs[1];
                double prix = Double.parseDouble(champs[2]);
                int quantite =  Integer.parseInt(champs[3]);
                Produit p = new Produit(reference,nom,prix,quantite);
                stock.add(p);
            }
    }
    /**
     * Initialize Orders
     *
     * @param nomFichier the name of the file
     * @return an array containing the orders
     */
    private void initialiserCommandes(String nomFichier) throws ParseException {
        String[] lignes = Terminal.lireFichierTexte(nomFichier);                                                        //Chargement du fichier de commandes
        for(String ligne :lignes)                                                                                       //Pour chaque ligne, crée une commande ou ajoute une reference à une commande existante
        {
            System.out.println(ligne);
            String[] champs = ligne.split("[;=]",5);
            int idCommande = Integer.parseInt(champs[0]);
            String date = champs[1];
            String client = champs[2];
            String reference = champs[3];
            int quantite = Integer.parseInt(champs[4]);
            if (this.isCommandePresente(idCommande)){
                this.chercherCommande(idCommande).getReferences().add(reference);
                this.chercherCommande(idCommande).getQuantites().add(quantite);
            }
            else {
                Commande newCommande = new Commande(idCommande,date,client);
                newCommande.getReferences().add(reference);
                newCommande.getQuantites().add(quantite);
                commandes.add(newCommande);
            }
        }
    }

    /**
     * Lister tous produits string.
     *
     * @return the string containing every product's description in our stock
     */
    public String listerTousProduits(){                                                                                 //Méthode qui retourne tous les produits du stock sous la forme d'une chaîne de caractères
        String res="";
        for(Produit prod : stock)
            if (prod.getQuantite()>0)
                res+=prod.toString()+"\n";
        return res;
    }

    /**
     * Lister commande string.
     *
     * @param id the id of the order we are looking for
     * @return the string containing the order's description
     */
    public String listerCommande(int id){                                                                               //Méthode qui retourne une commande sous la forme d'une chaîne de caractères à partir d'un id donné
        Commande a = chercherCommande(id);
        return a.toString();
    }

    /**
     * Lister toutes commandes string.
     *
     * @return the string containing every order's description
     */
    public String listerToutesCommandes(){                                                                              //Méthode qui retourne toutes les commandes sous forme d'une chaîne de caractères
        String res="";
        for(Commande com :commandes){
            res+=com.toString();
        }
        return res;
    }

    /**
     * Commandes non livrees string.
     *
     * @return the string containing every order that couldn't be delivered
     */
    public String commandesNonLivrees(){                                                                                  // Méthode qui renvoie les commandes non-livrées
        String res="";
        for (Commande commande : commandes){
            if (!commande.isLivre())
                res+=commande.toString();
        }
        return res;
  }

    /**
     * Chercher produit produit.
     *
     * @param ref the reference of the product we are looking for
     * @return the product we are looking for
     */
    public Produit chercherProduit(String ref){                                                                           // Méthode qui renvoie le produit ayant une référence donnée
        Produit res=new Produit();
        for (int i=0;i<stock.size();i++){
            Produit a = stock.get(i);
            if (a.getReference().equals(ref)){
                res = a;
            }
        }
        return res;
  }

    /**
     * Livraison.
     *
     * Delivers every order that can be delivered
     */
    public void livraison() {                                                                                              // Méthode qui effectue les livraisons
      for (int i = 0; i < commandes.size(); i++) {
          Commande a = commandes.get(i);
          if (!a.isLivre()) {
              String manque = "";
              boolean livrable = true;
              for (int j = 0; j < a.getReferences().size(); j++) {
                  String ref = a.getReferences().get(j);
                  Produit p = chercherProduit(ref);
                  if (a.getQuantites().get(j) > p.getQuantite()) {
                      livrable = false;                                                                                 // Si la quantité demandée est supérieure à la quantité du produit en stock, on considère que la commande n'est pas livrable
                      int nbManque = a.getQuantites().get(j) - p.getQuantite();
                      manque += "Il manque " + nbManque + " " + ref + "\n";                                             //On ajoute ainsi un motif de non-livraison qui comprend le nombre de produits manquants
                      a.setMotifNonLivraison(manque);
                  }
              }
              if (livrable) {
                  for (int k = 0; k < a.getReferences().size(); k++) {
                      String ref = a.getReferences().get(k);
                      Produit p = chercherProduit(ref);
                      int newQuantite = p.getQuantite() - a.getQuantites().get(k);
                      p.setQuantite(newQuantite);                                                                       // Si la commandes est livrable, on décrémente la quantité de produits dans le stock et on change le statut de la commande en "livrée"
                  }
                  a.setLivre(true);
                  a.setMotifNonLivraison("");
              }
          }
      }
  }

    /**
     * Calcul ventes string.
     *
     * @return the string containing the invoice elements for every order that has been delivered
     */
    public String calculVentes(){                                                                                         // Méthode qui calcule le montant total d'une commande qui a été livrée
        String res="";
        for (int i=0;i< commandes.size();i++){
            Commande a = commandes.get(i);
            if (a.isLivre()){
                double total = 0;
                res += "Commande : "+a.getNumero();
                for (int j=0;j<a.getReferences().size();j++){
                    String ref = a.getReferences().get(j);
                    Produit p = chercherProduit(ref);
                    res+="\n\t\t"+p.getNom()+"\t"+a.getQuantites().get(j)+"\t"+p.getPrix()+"\n";
                    total += a.getQuantites().get(j)*p.getPrix();
                }
                res+="==========================================================================\n";
                res+="SOMME : "+total+" euros\n\n\n\n";
            }
        }
        return res;
  }
}