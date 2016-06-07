package model;

/**
 * Created by Co on 04/06/2016.
 * Ici effectue le calcul de l'Elo des joueurs à la suite de leur Versus
 * On considère que le j1 est le vainqueur du Versus.
 * (((Méthode inspirée du calcul de l'Elo des joueurs d'échecs)))
 */
public class CalculateMasher {
	public void calculElo(Masher j1,Masher j2) {
		int D=j1.getElo()-j2.getElo(); 				//Différence d'Elo entre les 2 joueurs
		float probJ1_J2=(1+10^((-1*D)/400))^-1; 	//Probabilité que J1 avait de gagner contre J2
		float probJ2_J1=(1+10^(1/400))^-1; 			//Vice-versa -> Proba comprise entre 0 et 1
		j1.setElo((int)(j1.getElo()+40*(1-probJ1_J2)));	//MàJ Elo joueurs (ici K=40-> -30 parties)
		j2.setElo((int)(j2.getElo()+40*(0-probJ2_J1)));
	}
}