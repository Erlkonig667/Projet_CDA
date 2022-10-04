package fr.cda.projet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import fr.cda.util.*;

// Classe de definition du site de vente
public class Site
{
    private ArrayList<Produit> stock;       // Les produits du stock
    private ArrayList<Commande> commandes;  // Les bons de commande

    public Site() throws ParseException {                                                                                  //Constructeur
        stock = new ArrayList<Produit>();
        initialiserStock("C:\\Users\\greta2022\\Desktop\\Programmes Java\\ProjetCDA1\\data/Produits.txt");       //Lecture du fichier data/Produits.txt et création du stock
        commandes = new ArrayList<Commande>();
        initialiserCommandes("C:\\Users\\greta2022\\Desktop\\Programmes Java\\ProjetCDA1\\data/Commandes.txt");  //Lecture du fichier data/Commandes.txt et création de la liste de commandes

    }

    public ArrayList<Produit> getStock() {
        return stock;
    }

    public ArrayList<Commande> getCommandes() {
        return commandes;
    }

    public String listerTousProduits(){                                                                                 //Méthode qui retourne tous les produits du stock sous la forme d'une chaîne de caractères
        String res="";
        for(Produit prod : stock)
            res+=prod.toString()+"\n";
        return res;
    }

    public String listerToutesCommandes(){                                                                              //Méthode qui retourne toutes les commandes sous forme d'une chaîne de caractères
        String res="";
        for(Commande com :commandes){
            res+=com.toString();
        }
        return res;
    }
    public String listerCommande(int id){                                                                               //Méthode qui retourne une commande sous la forme d'une chaîne de caractères à partir d'un id donné
        Commande a = chercherCommande(id);
        return a.toString();
    }

    public boolean isCommandePresente(int id){                                                                          //Méthode qui vérifie si une commande est présente dans la liste des commandes, grâce à l'id
        boolean present = false;
        for (int i=0;i<commandes.size();i++){
            Commande c = commandes.get(i);
            if (c.getNumero()==id)
                present = true;
        }
        return present;
    }
    public Commande chercherCommande(int id){                                                                           //Méthode qui renvoie la commande ayant pour id celui entré en paramètre
        Commande res = new Commande();
        for (int i=0;i<commandes.size();i++){
            Commande c = commandes.get(i);
            if (c.getNumero()==id)
                res = c;
        }
        return res;
    }

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

  public String commandesNonLivrees(){                                                                                  // Méthode qui renvoie les commandes non-livrées
        String res="";
        for (Commande commande : commandes){
            if (!commande.isLivre())
                res+=commande.toString();
        }
        return res;
  }
}