package fr.TPII_INF4063;


import javax.swing.*;
import java.awt.*;
public class Echiquier {
	

	private static  int taille;

		
	boolean[][] tableau;
		
		
	Echiquier(int t )
	{
		taille=t;
		tableau= new boolean[taille][taille];
		for (int i=0;i<taille;i++)
			for (int j=0;j<taille;j++)
				tableau[i][j]=false;
	}
		
	//En principe, comme les reines sont ajout�es une a une, il n'est pas n�c�ssaire de v�rifier tous le  tableau, mais il n'est pas faux de le faire. 
	boolean EstPositionValide(int ligne, int colonne)
	{
		// vérifier la ligne
		for(int i = 0; i < taille; i++)
		{
			if(tableau[ligne][i] == true)
				return false;
		}
		
		// vérifier la colonne
		for(int i = 0; i < taille; i++)
		{
			if(tableau[i][colonne] == true)
				return false;
		}
		
		// vérifier la diagonale gauche croissante
		for(int i = ligne, j = colonne; i >= 0 && j >= 0; i--, j--)
		{
			if(tableau[i][j] == true)
				return false;
		}
		
		// vérifier la diagonale droite croissante
		for(int i = ligne, j = colonne; i >= 0 && j < taille; i--, j++)
		{
			if(tableau[i][j] == true)
				return false;
		}
		
		// vérifier la diagonale gauche décroissante
		for(int i = ligne, j = colonne; i < taille && j >= 0; i++, j--)
		{
			if(tableau[i][j] == true)
				return false;
		}
		
		// vérifier la diagonale droite décroissante
		for(int i = ligne, j = colonne; i < taille && j < taille; i++, j++)
		{
			if(tableau[i][j] == true)
				return false;
		}
		
		
		return true;
	}

	//Nouvelle methode
	public boolean estPositionValideCylindrique(int ligne, int colonne) {
	    // Vérifier la ligne
	    for (int i = 0; i < taille; i++) {
	        if (i != colonne && tableau[ligne][i]) {
	            return false;
	        }
	    }

	    // Vérifier la colonne
	    for (int i = 0; i < taille; i++) {
	        if (i != ligne && tableau[i][colonne]) {
	            return false;
	        }
	    }

	    // Vérifier les diagonales normales
	    for (int i = 1; i < taille; i++) {
	        int[] dx = {-i, i, -i, i};
	        int[] dy = {-i, -i, i, i};

	        for (int j = 0; j < dx.length; j++) {
	            int x = ligne + dx[j];
	            int y = colonne + dy[j];

	            if (x >= 0 && x < taille && y >= 0 && y < taille) {
	                if (tableau[x][y]) {
	                    return false;
	                }
	            }
	        }
	    }

	    // Vérifier les diagonales cylindriques
	    for (int i = 1; i < taille; i++) {
	        int[] dx = {-i, i, -i, i};
	        int[] dy = {-i, -i, i, i};

	        for (int j = 0; j < dx.length; j++) {
	            int x = ligne + dx[j];
	            int y = (colonne + dy[j] + taille) % taille; // ajustement cylindrique pour les y

	            if (x >= 0 && x < taille) {
	                if (tableau[x][y]) {
	                    return false;
	                }
	            }
	        }
	    }

	    return true;
	}

	
	   //Nouvelle methode
       boolean estPositionValidePourReineACheval(int ligne, int colonne) {
        // Vérifier comme pour une reine
        if (!EstPositionValide(ligne, colonne)) {
            return false;
        }

        // Vérifier les déplacements de cavalier
        int[][] cavalierMouvements = {{2, 1}, {1, 2}, {-1, -2}, {-2, -1}, {-2, 1}, {1, -2}, {-1, 2}, {2, -1}};
        for (int[] mouvement : cavalierMouvements) {
            int x = ligne + mouvement[0];
            int y = colonne + mouvement[1];
            if (x >= 0 && x < taille && y >= 0 && y < taille && tableau[x][y]) {
                return false;
            }
        }
        return true;
    }

       //Nouvelle methode
       boolean estPositionValidePourPrincesse(int ligne, int colonne) {
    	    int limite = taille / 2; // Limite de déplacement

    	    // Vérifications similaires à une reine mais avec une portée limitée
    	    // Lignes et colonnes
    	    for (int i = 1; i <= limite; i++) {
    	        // Haut, Bas, Gauche, Droite
    	        int[] dx = {-i, i, 0, 0};
    	        int[] dy = {0, 0, -i, i};

    	        for (int j = 0; j < 4; j++) {
    	            int x = ligne + dx[j];
    	            int y = colonne + dy[j];

    	            if (x >= 0 && x < taille && y >= 0 && y < taille && tableau[x][y]) {
    	                return false;
    	            }
    	        }
    	    }

    	    // Diagonales
    	    for (int i = 1; i <= limite; i++) {
    	        // Haut-Gauche, Haut-Droite, Bas-Gauche, Bas-Droite
    	        int[] dx = {-i, -i, i, i};
    	        int[] dy = {-i, i, -i, i};

    	        for (int j = 0; j < 4; j++) {
    	            int x = ligne + dx[j];
    	            int y = colonne + dy[j];

    	            if (x >= 0 && x < taille && y >= 0 && y < taille && tableau[x][y]) {
    	                return false;
    	            }
    	        }
    	    }

    	    return true;
    	}

		
		int getTaille()
		{
			return taille; 
		}
		
		
		void placerReine(int ligne, int colone)
		{
			tableau[ligne][colone]=true; 
		}
		
		void enleverReine(int ligne, int colone)
		{
			tableau[ligne][colone]=false; 
		}
		
	   
	void printEchiquierTexte()// Pour afficher l'�chiquier en version texte
		{
			for (int i=0;i<taille;i++)
			{
				for (int j=0;j<taille;j++)
				{	
					if (tableau[i][j]==false)
						System.out.print("0");
					else
						System.out.print("X");
				}
				System.out.println();
			}
		}


        void printEchiquier()//Pour afficher l'�chiquier en version GUI.
        {
            new ChessGUI(tableau);
        }
        private static class ChessGUI extends JFrame {
       
            private boolean[][] tableau;
            private int taille;
       
            public ChessGUI(boolean[][] tableau) {
                this.tableau = tableau;
                this.taille = tableau.length;
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setTitle("�chiquier");
                setSize(800, 800);
                setLocationRelativeTo(null);
                setLayout(new GridLayout(taille, taille));
                afficherEchiquier();
            }
            
         
       
            private void afficherEchiquier() {
                Color beige = new Color(245, 245, 220);
                Color brun = new Color(139, 69, 19);
       
                for (int i = 0; i < taille; i++) {
                    for (int j = 0; j < taille; j++) {
                        JPanel casePanel = new JPanel();
                        casePanel.setLayout(new BorderLayout());
       
                        if ((i + j) % 2 == 0) {
                            casePanel.setBackground(beige);
                        } else {
                            casePanel.setBackground(brun);
                        }
       
                        if (tableau[i][j]) {
                            JLabel reineLabel = new JLabel();
                            ImageIcon reineIcon = new ImageIcon(getClass().getResource("queen.png"));
                            reineLabel.setIcon(new ImageIcon(reineIcon.getImage().getScaledInstance(800/taille, 800/taille, Image.SCALE_SMOOTH)));
                            reineLabel.setHorizontalAlignment(SwingConstants.CENTER);
                            reineLabel.setVerticalAlignment(SwingConstants.CENTER);
                            casePanel.add(reineLabel, BorderLayout.CENTER);
                        }
       
                        add(casePanel);
                    }
                }
                setVisible(true);
            }
        }
       

	
}