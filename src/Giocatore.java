import java.util.ArrayList;
import java.util.List;

public class Giocatore {
    private String nome;
    private int puntiFerita;
    private List<Carta> mano;
    private List<Carta> equipaggiamento;

    public Giocatore(String nome, int puntiFerita) {
        this.nome = nome;
        this.puntiFerita = puntiFerita;
        this.mano = new ArrayList<>();
        this.equipaggiamento = new ArrayList<>();
    }


    public Carta rimuoviCarta(int scelta) {
        Carta scarto = mano.get(scelta);
        mano.remove(scelta);
        return scarto;
    }

    public String getNome() {
        return nome;
    }

    public int getPuntiFerita() {
        return puntiFerita;
    }

    public void setPuntiFerita(int puntiFerita) {
        this.puntiFerita = puntiFerita;
    }

    public List<Carta> getMano() {
        return mano;
    }

    public List<Carta> getEquipaggiamento() {
        return equipaggiamento;
    }

    public void visualizza_mano(){
        for (Carta c : mano) {
            System.out.println(c.toString());
        }
    }

    public void visualizza_equipaggiamento(){
        for (Carta c : equipaggiamento) {
            System.out.println(c.toString());
        }
    }


    public void aggiungiCarta(Carta c){
        if (c.getTipo() == 1) mano.add(c);
        else equipaggiamento.add(c);
    }

    public void giocaCarta(Carta carta, Giocatore giocatore) {
        if (mano.contains(carta)) {
            mano.remove(carta);
            applicaEffetto(carta, giocatore);
        }
        else if (equipaggiamento.contains(carta)){
            applicaEffetto(carta, giocatore);
        }else {
            System.out.println("Carta non disponibile nella mano del giocatore.");
        }
    }

    private void applicaEffetto(Carta carta, Giocatore giocatore) {
        switch (carta) {
            case BANG:
                System.out.println(nome + " ha giocato " + carta);
                giocatore.setPuntiFerita(giocatore.getPuntiFerita() - 1);
                break;
            case MANCATO:
                System.out.println(nome + " ha giocato " + carta + " e annulla un attacco.");
                // Logica per annullare un attacco
                break;
            case SCHOFIELD:
                System.out.println(nome + " ha giocato " + carta + " e equipaggia un'arma.");
                giocatore.setPuntiFerita(giocatore.getPuntiFerita() - 1);
                break;
            case REMINGTHON:
                System.out.println(nome + " ha giocato " + carta + " e equipaggia un'arma.");
                giocatore.setPuntiFerita(giocatore.getPuntiFerita() - 1);
                break;
            case REV_CARABINE:
                System.out.println(nome + " ha giocato " + carta + " e equipaggia un'arma.");
                giocatore.setPuntiFerita(giocatore.getPuntiFerita() - 1);
                break;
            case WINCHESTER:
                System.out.println(nome + " ha giocato " + carta + " e equipaggia un'arma.");
                giocatore.setPuntiFerita(giocatore.getPuntiFerita() - 1);
                break;
            default:
                System.out.println("Effetto della carta non gestito.");
        }
    }

    public void scartaCarta(Carta carta) {
        if (mano.contains(carta)) {
            mano.remove(carta);
            System.out.println(nome + " ha scartato " + carta);
        } else {
            System.out.println("Carta non disponibile nella mano del giocatore.");
        }
    }
}

