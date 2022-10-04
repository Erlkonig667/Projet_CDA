package fr.cda.projet;

import fr.cda.ihm.Formulaire;
import fr.cda.ihm.FormulaireInt;

public class GUIModifCommande implements FormulaireInt {
    private GUISite formPP;         //Le formulaire principal du programme
    private Site site;
    private int idCommande;
    private Commande commande;

    public GUIModifCommande(GUISite formPP,Site site,int idCommande){
        this.formPP = formPP;
        this.site = site;
        this.idCommande = idCommande;
        this.commande = site.chercherCommande(idCommande);
        Formulaire form = new Formulaire("Modification d'une commande",this,400,150);
        form.setPosition(10,10);
        for (int i=0;i<commande.getReferences().size();i++){
            String ref = commande.getReferences().get(i);
            int quantite = commande.getQuantites().get(i);
            String nomChamp="PRODUIT"+i;
            if (!commande.isLivre())
                form.addText(nomChamp,ref,true,String.valueOf(quantite));
            else
                form.addText(nomChamp,ref,false,String.valueOf(quantite));
        }
        form.addButton("VALIDER","Valider les modifications");
        form.afficher();


    }

    public void submit(Formulaire form, String nom) {

        if (nom.equals("VALIDER")){
            for (int i=0;i<commande.getReferences().size();i++){
                String nomChamp = "PRODUIT"+i;
                int newQuantite = Integer.parseInt(form.getValeurChamp(nomChamp));
                commande.getQuantites().set(i,newQuantite);
            }
            formPP.afficherResultats(commande.toString());
            form.fermer();
        }
    }
}
