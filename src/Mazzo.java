import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazzo {
    private List<Carta> carte;

    public Mazzo() {
        carte = new ArrayList<>();
        aggiungiCarte(Carta.SCHOFIELD, 3);
        aggiungiCarte(Carta.REMINGTHON, 1);
        aggiungiCarte(Carta.REV_CARABINE, 1);
        aggiungiCarte(Carta.WINCHESTER, 1);
        aggiungiCarte(Carta.BANG, 50);
        aggiungiCarte(Carta.MANCATO, 24);
        Collections.shuffle(carte); // Mescola il mazzo
    }

    private void aggiungiCarte(Carta carta, int quantità) {
        for (int i = 0; i < quantità; i++) {
            carte.add(carta);
        }
    }

    public Carta pesca() {
        if (carte.isEmpty()) {
            throw new IllegalStateException("Il mazzo è vuoto!");
        }
        return carte.remove(carte.size() - 1); // Pesca una carta dalla fine del mazzo
    }

    public int carteRimaste() {
        return carte.size();
    }

    public void RifaiMazzo(ArrayList<Carta> carteS) {
        carte = new ArrayList<Carta>(carteS);
    }
}

