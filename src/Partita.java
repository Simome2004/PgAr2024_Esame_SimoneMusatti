import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class Partita {
    Giocatore sceriffo = new Giocatore("Sceriffo", 5);
    Giocatore rinnegato = new Giocatore("Rinnegato", 4);
    Giocatore bandito1 = new Giocatore("Bandito1", 4);
    Giocatore bandito2 = new Giocatore("Bandito2", 4);
    Giocatore bandito3 = new Giocatore("Bandito3", 4);
    Giocatore vice1 = new Giocatore("Vice1", 4);
    Giocatore vice2 = new Giocatore("Vice2", 4);
    private String titolo = "Scegli un'azione";
    private String [] voci = {"Visualizza sceriffo", "Visualizza carte", "Visualizza distanza", "Conosci i tuoi PF", "Gioca una carta", "Taunt"};
    private Mazzo mazzo = new Mazzo();
    private ArrayList<Giocatore> giocatori = new ArrayList<>();
    private ArrayList<Carta> carteScartate = new ArrayList<>();

    public void rimescolaMazzoSeNecessario(ArrayList<Carta> scartini){
        if (mazzo.carteRimaste() == 0) mazzo.RifaiMazzo(scartini);
    }
    private int giocatoriNum;
    public Partita() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Scegli quanti giocatori utilizzare (4 : 7):");
        giocatoriNum = sc.nextInt();
        switch (giocatoriNum) {
            case 4:
                giocatori.add(rinnegato);
                giocatori.add(bandito1);
                giocatori.add(bandito2);
                Collections.shuffle(giocatori);
                giocatori.add(0, sceriffo);
                break;
            case 5:
                giocatori.add(rinnegato);
                giocatori.add(bandito1);
                giocatori.add(bandito2);
                giocatori.add(vice1);
                Collections.shuffle(giocatori);
                giocatori.add(0, sceriffo);
                break;
            case 6:
                giocatori.add(rinnegato);
                giocatori.add(bandito1);
                giocatori.add(bandito2);
                giocatori.add(bandito3);
                giocatori.add(vice1);
                Collections.shuffle(giocatori);
                giocatori.add(0, sceriffo);
                break;
            case 7:
                giocatori.add(rinnegato);
                giocatori.add(bandito1);
                giocatori.add(bandito2);
                giocatori.add(bandito3);
                giocatori.add(vice1);
                giocatori.add(vice2);
                Collections.shuffle(giocatori);
                giocatori.add(0, sceriffo);
                break;
        }
    }

    public int calcolaDistanza (ArrayList<Giocatore> giocatori, Giocatore giocatoreAtt, int IndiceGiocatoreRif) {
        int i = 1;
        int IndiceGiocatoreAtt = 0;
        int distanzaOraria = 100000;
        int distanzaAntioraria = 100000;

        for (Giocatore g : giocatori) {
            if (g == giocatoreAtt) {
                IndiceGiocatoreAtt = i;
            }
            i++;
        }
        //System.out.println("Indicegiocatorerif"+IndiceGiocatoreRif);
        //System.out.println("IndicegiocatoreAtt"+IndiceGiocatoreAtt);
        if (IndiceGiocatoreAtt == IndiceGiocatoreRif) return 0;
        else if (IndiceGiocatoreAtt < IndiceGiocatoreRif){
            distanzaOraria = IndiceGiocatoreRif - IndiceGiocatoreAtt;
            //System.out.println("Distoraria:" + distanzaOraria);
            distanzaAntioraria = IndiceGiocatoreAtt + (giocatori.toArray().length - IndiceGiocatoreRif);
            //System.out.println("Antitoria:" + distanzaAntioraria);
            if (distanzaOraria <= distanzaAntioraria) {
                return distanzaOraria;
            }
            else return distanzaAntioraria;
        }
        else {
            distanzaOraria = giocatori.toArray().length - (IndiceGiocatoreAtt) + IndiceGiocatoreRif;
            distanzaAntioraria = IndiceGiocatoreAtt - IndiceGiocatoreRif;
            if (distanzaOraria <= distanzaAntioraria) {
                return distanzaOraria;
            }
            else return distanzaAntioraria;
        }

    }

    public void iniziaPartita() {
        System.out.println("La partita è iniziata!");
        int i = 1;
        for (Giocatore giocatore : giocatori) {
            System.out.println("Giocatore" + i);
            i++;
        }

        // Avvia il primo turno
        giocaTurni();
    }

    private void giocaTurni() {
        boolean partitaInCorso = true;
        while (partitaInCorso) {
            for (Giocatore giocatore : giocatori) {
                if (giocatore.getPuntiFerita() > 0) {
                    giocaTurno(giocatore);
                }
                partitaInCorso = controllaVittoria();
                if (!partitaInCorso) {
                    break;
                }
            }
        }
    }

    private void giocaTurno(Giocatore giocatore) {
        System.out.println("Turno di " + giocatore.getNome());
        int i = 1;
        for (Giocatore g : giocatori) {
            if (giocatore == g){
                System.out.println("Giocatore" + i);
            }
            i++;
        }
        rimescolaMazzoSeNecessario(carteScartate);
        giocatore.aggiungiCarta(mazzo.pesca());
        giocatore.aggiungiCarta(mazzo.pesca());
        //System.out.println(giocatore.getMano().toArray().length);
        if (giocatore.getPuntiFerita() < giocatore.getMano().toArray().length){
            System.out.println("Scegli " + (giocatore.getMano().toArray().length - giocatore.getPuntiFerita()) + " carte da scartare");
            giocatore.visualizza_mano();
            Scanner scanner = new Scanner(System.in);

            while (giocatore.getPuntiFerita() < (giocatore.getMano().toArray().length)){
                System.out.println("Scegli la i-esima carta da scartare dalla tua mano:");
                giocatore.visualizza_mano();
                int scelta_ = scanner.nextInt() - 1;
                carteScartate.add(giocatore.rimuoviCarta(scelta_));
            }

            giocatore.visualizza_mano();
            System.out.println("Elenco Carte Scartate:");
            for (Carta c : carteScartate) {
                System.out.println(c);
            }
        }

        if (!giocatore.getMano().isEmpty()) {
            boolean bangGiocato = false;
            Scanner scanner = new Scanner(System.in);
            Menu menu = new Menu(titolo, voci);
            int scelta = menu.scegli();
            while (scelta != 7) {
                switch (scelta) {
                    case 1:
                        System.out.println("Visualizzazione delle informazioni dello sceriffo...");
                        int il = 1;
                        for (Giocatore g : giocatori){
                            if (g.getNome().equalsIgnoreCase("sceriffo")) {
                                System.out.println("Lo sceriffo è il giocatore" + il);
                            }
                            il++;
                        }
                        break;
                    case 2:
                        System.out.println("Visualizzazione delle carte...");
                        giocatore.visualizza_mano();
                        giocatore.visualizza_equipaggiamento();
                        break;
                    case 3:
                        System.out.println("Inserisci il numero del giocatore dal quale vuoi calcolare la distanza:");
                        Giocatore giocatoreRif = giocatore;
                        int indice = scanner.nextInt();
                        while (indice > giocatori.toArray().length || indice < 1){
                            System.out.println("Inserisci nuovamente l'indice in quanto è troppo grande:");
                            indice = scanner.nextInt();
                        }
                        int in = 0;
                        for (Giocatore g : giocatori){
                            if (indice == in) giocatoreRif = g;
                            in++;
                        }
                        System.out.println("La distanza con il giocatore" + indice + "è:" + calcolaDistanza(giocatori, giocatore, indice));
                        break;
                    case 4:
                        System.out.println("Visualizzazione dei propri punti ferita (PF)...");
                        System.out.println(giocatore.getPuntiFerita());
                        break;
                    case 5:
                        int scelta__;
                        Giocatore attaccato;
                        System.out.println("Scegli la carta da utilizzare (numero ordine nell'elenco):");
                        giocatore.visualizza_mano();
                        List<Carta> mano = new ArrayList<>(giocatore.getMano());
                        if (!mano.contains(Carta.BANG)){
                            System.out.println("NON HAI CARTE GIOCABILI");
                            break;
                        }
                        if (bangGiocato == true){
                            System.out.println("HAI GIA ATTACCATO");
                            break;
                        }
                        int _scelta = scanner.nextInt() - 1;
                        while (mano.get(_scelta) == Carta.MANCATO){
                            if (mano.get(_scelta) == Carta.MANCATO) System.out.println("Reinserire la carta, non puoi giocare la carta mancato:");
                            _scelta = scanner.nextInt() - 1;
                        }
                        System.out.println("Scegli contro chi vuoi giocare la tua carta (numero del giocatore)");
                        scelta__ = scanner.nextInt() - 1;
                        int dist = 1;
                        if (giocatore.getEquipaggiamento().contains(Carta.WINCHESTER)) dist = 5;
                        if (giocatore.getEquipaggiamento().contains(Carta.REV_CARABINE)) dist = 4;
                        if (giocatore.getEquipaggiamento().contains(Carta.REMINGTHON)) dist = 3;
                        if (giocatore.getEquipaggiamento().contains(Carta.SCHOFIELD)) dist = 2;

                        while (calcolaDistanza(giocatori, giocatore, scelta__ + 1) > dist) {
                            System.out.println("La persona scelta è troppo distante:");
                            scelta__ = scanner.nextInt() - 1;
                        }
                        attaccato = giocatori.get(scelta__);
                        if (attaccato.getMano().contains(Carta.MANCATO)) {
                            Scanner sc = new Scanner(System.in);
                            System.out.println(attaccato.getNome() + "Scegli se usare la tua catra MANCATO!! (Si/no):");
                            String risposta = sc.next();
                            int index = 0;
                            int poscarta = 0;
                            if (risposta.equalsIgnoreCase("Si")) {
                                for (Carta c : attaccato.getMano()){
                                    if (c.getNome().equalsIgnoreCase("Mancato")) {
                                        poscarta = index;
                                    }
                                    index++;
                                }
                                attaccato.rimuoviCarta(poscarta);
                            }
                            else {
                                giocatore.giocaCarta(mano.get(_scelta), attaccato);
                                bangGiocato = true;
                            }
                        }
                        else {
                            giocatore.giocaCarta(mano.get(_scelta), attaccato);
                            bangGiocato = true;
                        }
                        break;
                    case 6:
                        Taunt.TauntFunc();
                        break;
                    default:
                        System.out.println("Scelta non valida. Riprova.");
                        break;
                }
                scelta = menu.scegli();
            }
            System.out.println("Turno terminato.");
        }

    }


    private boolean controllaVittoria() {
        boolean sceriffoVivo = sceriffo.getPuntiFerita() > 0;
        boolean tuttiBanditiMorti = false;
        if (giocatoriNum == 4 || giocatoriNum == 5) tuttiBanditiMorti = bandito1.getPuntiFerita() <= 0 && bandito2.getPuntiFerita() <= 0;
        else if (giocatoriNum == 6 || giocatoriNum == 7) {
            tuttiBanditiMorti = bandito1.getPuntiFerita() <= 0 &&
                    bandito2.getPuntiFerita() <= 0 &&
                    bandito3.getPuntiFerita() <= 0;
        }
        boolean protagmorto = rinnegato.getPuntiFerita() <= 0;

        if (!sceriffoVivo && protagmorto) {
            System.out.println("I banditi vincono!");
            return false;
        } else if (!sceriffoVivo && (tuttiBanditiMorti)) {
            System.out.println("Il rinnegato vince!!");
            return false;
        } else if (sceriffoVivo && tuttiBanditiMorti) {
            System.out.println("Sceriffo e vice vincono!");
            return false;
        }
        return true;
    }

}
