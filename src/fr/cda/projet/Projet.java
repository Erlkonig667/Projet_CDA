// Projet 1 CDA
// 
// NOM,Prenom
// NOM,Prenom
//
package fr.cda.projet;

import java.io.*;
import java.text.ParseException;
import java.util.*;

import fr.cda.util.*;

// Classe principale d'exécution du projet

public class Projet
{
    public static void main(String a_args[]) throws ParseException {
        Terminal.ecrireStringln("Execution du projet ");

        Site site = new Site();
        GUISite ihm = new GUISite(site);
    }
}
