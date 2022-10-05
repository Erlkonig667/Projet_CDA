package fr.cda.projet;

import fr.cda.ihm.*;

/**
 * The type GuiSite.
 */
// Classe de definition de l'IHM principale du compte
public class GUISite implements FormulaireInt
{
    private Site site;  // Le site
    private Formulaire form; // Le formulaire principal du programme

    /**
     * Instantiates a new GuiSite.
     *
     * @param site the site
     */
    public GUISite(Site site){                                                                                          //Constructeur
        this.site = site;
        form = new Formulaire("Site de vente",this,1100,730);                                     //Creation du formulaire
        form.setPosition(10,10);
        form.addLabel("Afficher tous les produits du stock");                                                           //Creation des elements de l'IHM
        form.addButton("AFF_STOCK","Tout le stock");
        form.addLabel("");
        form.addLabel("Afficher tous les bons de commande");
        form.addButton("AFF_COMMANDES","Toutes les commandes");
        form.addLabel("");
        form.addText("NUM_COMMANDE","Numéro de commande",true,"1");
        form.addButton("AFF_COMMANDE","Afficher");
        form.addButton("MODIF_COMMANDE","Modifier");
        form.addLabel("");
        form.addLabel("Effectuer les livraisons");
        form.addButton("LIVRAISON","Livraison des commandes");
        form.addLabel("Afficher les commandes non-livrées");
        form.addButton("AFF_EN_ATTENTE","Commandes en attente");
        form.addLabel("");
        form.addLabel("Calculer les ventes des commandes livrées");
        form.addButton("CALCUL_VENTES","Calcul des ventes");
        form.setPosition(300,0);
        form.addZoneText("RESULTATS","Résultats",
                            true,
                            "",
                            800,700);
        form.afficher();                                                                                                //Affichage du formulaire
    }

    public void submit(Formulaire form,String nomSubmit){                                                               //Methode appelée quand on clique dans un bouton

        if (nomSubmit.equals("AFF_STOCK"))                                                                              //Affichage de tous les produits du stock
            {
                String res = site.listerTousProduits();
                form.setValeurChamp("RESULTATS",res);
            }

        if (nomSubmit.equals("AFF_COMMANDES"))                                                                          //Affichage de toutes les commandes
            {
                String res = site.listerToutesCommandes();
                form.setValeurChamp("RESULTATS",res);
            }

        if (nomSubmit.equals("AFF_COMMANDE"))                                                                           //Affichage d'une commande
            {
                String numStr = form.getValeurChamp("NUM_COMMANDE");
                if (!isNombre(numStr)){
                    afficherResultats("Veuillez entrer un nombre afin de trouver la commande associée.");
                } else {
                    int num = Integer.parseInt(numStr);
                    if (!site.isCommandePresente(num)) {
                        afficherResultats("La commande que vous cherchez n'existe pas.\n" +
                                "Veuillez entrer un numéro de commande valide.");
                    } else {
                        String res = site.listerCommande(num);
                        form.setValeurChamp("RESULTATS", res);
                    }
                }
            }

        if (nomSubmit.equals("MODIF_COMMANDE"))                                                                         //Modification d'une commande en passant par l'ouverture d'une nouvelle fenêtre
        {
            String numStr = form.getValeurChamp("NUM_COMMANDE");
            if (!isNombre(numStr)){
                afficherResultats("Veuillez entrer un nombre afin de trouver la commande associée.");
            } else {
                int num = Integer.parseInt(numStr);
                if (!site.isCommandePresente(num)) {
                    afficherResultats("La commande que vous cherchez n'existe pas.\n" +
                            "Veuillez entrer un numéro de commande valide.");
                } else {
                    GUIModifCommande modif = new GUIModifCommande(this,site,num);
                }
            }
        }

        if (nomSubmit.equals("LIVRAISON"))                                                                              //Livraison de toutes les commandes (si elles peuvent être livrées
        {
            site.livraison();
            String res = "Les commandes ont bien été livrées.\nPour afficher les commandes pour lesquelles il manque" +
                    " des produits, veuillez cliquer sur \"Commandes en attente\"";
            form.setValeurChamp("RESULTATS",res);
        }

        if (nomSubmit.equals("AFF_EN_ATTENTE"))                                                                         //Affichage de toutes les commandes non-livrées
            {
                String res = "COMMANDES NON LIVRÉES :\n\n----------------------------------------------" +
                        "--------------------------------------------------------------------------------\n";
                res+=site.commandesNonLivrees();
                form.setValeurChamp("RESULTATS",res);
             }

        if (nomSubmit.equals("CALCUL_VENTES"))                                                                          //Calcul des ventes des commandes livrées
        {
            form.setValeurChamp("RESULTATS",site.calculVentes());
        }


    }

    /**
     * Afficher resultats.
     *
     * @param res the string we want to display
     */
    public void afficherResultats(String res){
        form.setValeurChamp("RESULTATS",res);
    }

    /**
     * Is nombre boolean.
     *
     * @param texte the String we want to test
     * @return the boolean
     */
    public boolean isNombre(String texte){
        boolean numerique = true;
        for (int i=0;i<texte.length();i++){
            if (!Character.isDigit(texte.charAt(i)))
                numerique = false;
        }
        return numerique;
    }
}