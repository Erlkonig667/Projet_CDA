package fr.cda.projet;

import fr.cda.ihm.*;

// Classe de definition de l'IHM principale du compte
public class GUISite implements FormulaireInt
{
    private Site site;  // Le site
    private Formulaire form;

    public GUISite(Site site){                                                                                          // Constructeur
        this.site = site;
        form = new Formulaire("Site de vente",this,1100,730);                          // Creation du formulaire
        form.setPosition(10,10);
        form.addLabel("Afficher tous les produits du stock");                                                           // Creation des elements de l'IHM
        form.addButton("AFF_STOCK","Tout le stock");
        form.addLabel("");
        form.addLabel("Afficher tous les bons de commande");
        form.addButton("AFF_COMMANDES","Toutes les commandes");
        form.addLabel("");
        form.addText("NUM_COMMANDE","Num�ro de commande",true,"1");
        form.addButton("AFF_COMMANDE","Afficher");
        form.addButton("MODIF_COMMANDE","Modifier");
        form.addLabel("");
        form.addLabel("Effectuer les livraisons");
        form.addButton("LIVRAISON","Livraison des commandes");
        form.addLabel("Afficher les commandes non-livr�es");
        form.addButton("AFF_EN_ATTENTE","Commandes en attente");
        form.addLabel("");
        form.addLabel("Calculer les ventes des commandes livr�es");
        form.addButton("CALCUL_VENTES","Calcul des ventes");
        form.setPosition(300,0);
        form.addZoneText("RESULTATS","R�sultats",
                            true,
                            "",
                            800,700);
        form.afficher();                                                                                                //Affichage du formulaire
    }

    public void submit(Formulaire form,String nomSubmit){                                                               //Methode appel�e quand on clique dans un bouton

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
                int num = Integer.parseInt(numStr);
                String res = site.listerCommande(num);
                form.setValeurChamp("RESULTATS",res);
            }

        if (nomSubmit.equals("MODIF_COMMANDE"))
        {
            int id = Integer.parseInt(form.getValeurChamp("NUM_COMMANDE"));
            GUIModifCommande modif = new GUIModifCommande(this,site,id);
        }

        if (nomSubmit.equals("LIVRAISON"))
        {
            site.livraison();
            String res = "Les commandes ont bien �t� livr�es.\nPour afficher les commandes pour lesquelles il manque" +
                    " des produits, veuillez cliquer sur \"Commandes en attente\"";
            form.setValeurChamp("RESULTATS",res);
        }

        if (nomSubmit.equals("AFF_EN_ATTENTE"))                                                                       // Affichage de toutes les commandes non-livr�es
            {
                String res = "COMMANDES NON LIVR�ES :\n\n----------------------------------------------" +
                        "--------------------------------------------------------------------------------\n";
                res+=site.commandesNonLivrees();
                form.setValeurChamp("RESULTATS",res);
             }

        if (nomSubmit.equals("CALCUL_VENTES"))
        {
            form.setValeurChamp("RESULTATS",site.calculVentes());
        }


    }
    public void afficherResultats(String res){
        form.setValeurChamp("RESULTATS",res);
    }

}