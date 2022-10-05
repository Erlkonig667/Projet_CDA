package fr.cda.projet;

import fr.cda.ihm.Formulaire;
import fr.cda.ihm.FormulaireInt;

/**
 * The type GuiModifCommande.
 */
// Classe de definition de l'IHM de modification de commande
public class GUIModifCommande implements FormulaireInt {
    private GUISite formPP;         //Le formulaire principal du programme
    private Site site;              //Le site
    private int idCommande;         //L'id de la commande qu'on veut modifier
    private Commande commande;      //La commande qu'on souhaite modifier

    /**
     * Instantiates a new GuiModifCommande.
     *
     * @param formPP     the main form
     * @param site       the site
     * @param idCommande the id of the order we want to edit
     */
    public GUIModifCommande(GUISite formPP,Site site,int idCommande){                                                   //Constructeur
        this.formPP = formPP;
        this.site = site;
        this.idCommande = idCommande;
        this.commande = site.chercherCommande(idCommande);
        Formulaire form = new Formulaire("Modification d'une commande",this,400,150);              //Création du formulaire
        form.setPosition(10,10);
        for (int i=0;i<commande.getReferences().size();i++){                                                            //Pour chaque produit présent dans la commande, on crée un nouveau champ
            String ref = commande.getReferences().get(i)+"   ";
            int quantite = commande.getQuantites().get(i);
            String nomChamp="PRODUIT"+i;
            if (!commande.isLivre())
                form.addText(nomChamp,ref,true,String.valueOf(quantite));
            else
                form.addText(nomChamp,ref,false,String.valueOf(quantite));                                       //Si la commande est déjà livrée on empêche la modification du champ
        }
        form.addLabel("");
        form.addButton("VALIDER","Valider les modifications");
        form.addButton("ANNULER","Annuler");
        form.addLabel("");
        form.afficher();                                                                                                //Affichage du formulaire
    }

    public void submit(Formulaire form, String nom) {                                                                   //Méthode appelée quand on clique sur un bouton

        if (nom.equals("VALIDER")){                                                                                     //Validation des modifications
            for (int i=0;i<commande.getReferences().size();i++){
                String nomChamp = "PRODUIT"+i;
                int newQuantite = Integer.parseInt(form.getValeurChamp(nomChamp));
                commande.getQuantites().set(i,newQuantite);                                                             //On change les quantités dans la commande avec les nouvelles quantités renseignées
            }
            formPP.afficherResultats(commande.toString());
            form.fermer();
        }

        if (nom.equals("ANNULER")){
            form.fermer();
        }
    }
}
