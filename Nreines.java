package fr.TPII_INF4063;

import java.util.Scanner;

public class Nreines {

    private static int solutionCount = 0; // Compteur de solutions

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir le nombre de ligne que vous souhaitez avoir svp ");
        int n = sc.nextInt();
        Echiquier echiquier = new Echiquier(n);
        
        int mode;
        do {
        	
        	System.out.println("Choisissez le mode: 1 pour N Reines, 2 pour Reines-à-Cheval, 3 pour Princesses, 4 pour Reines sur échiquier cylindrique");
            mode = sc.nextInt();
            
            
            ResoudreNReine(echiquier, 0);
            
            switch (mode) {
            case 1:
                ResoudreNReine(echiquier, 0);
                break;
            case 2:
                resoudreReineACheval(echiquier, 0);
                break;
            case 3:
                resoudrePrincesse(echiquier, 0);
                break;
            case 4:
                resoudreReineCylindrique(echiquier, 0);
                break;
            default:
                System.out.println("Mode invalide.");
                break;
        }
        	
        }while(mode < 1 || mode > 4);
        

        // Affichage du nombre de solutions
        System.out.println("Nombre total de solutions : " + solutionCount);
        
     // Calculer et afficher le nombre maximal de tours, cavaliers et fous
        calculerMaxPieces(n);
        
        sc.close(); // Fermer le scanner
    }
    
    public static void resoudreReineCylindrique(Echiquier e, int col) {
        if (col >= e.getTaille()) {
            e.printEchiquier();
            solutionCount++;
            return;
        }

        for (int i = 0; i < e.getTaille(); i++) {
            if (e.estPositionValideCylindrique(i, col)) {
                e.placerReine(i, col);
                resoudreReineCylindrique(e, col + 1);
                e.enleverReine(i, col);
            }
        }
    }

    //Nouvelle methode
    public static void calculerMaxPieces(int n) {
        // Pour les tours
        int tours = n;

        // Pour les cavaliers
        int cavaliers = (n % 2 == 0) ? (n * n / 2) : ((n * n / 2) + 1);

        // Pour les fous
        int fous = 2 * (n - 1);

        System.out.println("Nombre maximal de tours que l'on peut placer : " + tours);
        System.out.println("Nombre maximal de cavaliers que l'on peut placer : " + cavaliers);
        System.out.println("Nombre maximal de fous que l'on peut placer : " + fous);
    }
    
    //nouvelle methode
    public static void resoudrePrincesse(Echiquier e, int col) {
        if (col >= e.getTaille()) {
            e.printEchiquier();
            solutionCount++;
            return;
        }

        for (int i = 0; i < e.getTaille(); i++) {
            if (e.estPositionValidePourPrincesse(i, col)) {
                e.placerReine(i, col); // Utiliser la même méthode pour placer une pièce
                resoudrePrincesse(e, col + 1);
                e.enleverReine(i, col); // Enlever la pièce
            }
        }
    }


    //nouvelle methode
    public static void resoudreReineACheval(Echiquier e, int col) {
        if (col >= e.getTaille()) {
            e.printEchiquier();
            solutionCount++;
            return;
        }

        for (int i = 0; i < e.getTaille(); i++) {
            if (e.estPositionValidePourReineACheval(i, col)) {
                e.placerReine(i, col);
                resoudreReineACheval(e, col + 1);
                e.enleverReine(i, col);
            }
        }
    }


    public static void ResoudreNReine(Echiquier e, int col) {
        // Condition d'arrêt: Si toutes les reines sont placées
        if (col >= e.getTaille()) {
            solutionCount++; // Comptabiliser la solution
            e.printEchiquier(); // Afficher la solution
            return;
        }

        for (int i = 0; i < e.getTaille(); i++) {
            if (e.EstPositionValide(i, col)) {
                e.placerReine(i, col); // Place la reine
                ResoudreNReine(e, col + 1);
                e.enleverReine(i, col); // Enlève la reine pour chercher d'autres solutions
            }
        }
    }
}
